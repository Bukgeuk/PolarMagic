package dev.bukgeuk.polarmagic.mixin;

import dev.bukgeuk.polarmagic.PolarMagic;
import dev.bukgeuk.polarmagic.ext.PlayerEntityExt;
import dev.bukgeuk.polarmagic.util.MagicData;
import dev.bukgeuk.polarmagic.util.MagicDataTable;
import dev.bukgeuk.polarmagic.util.aQueueData;
import dev.bukgeuk.polarmagic.util.GraphKt;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.Queue;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {
    private static final int aTick = 5;
    private static final int rTick = 100;
    private static final double expMultiplier = 1.1;
    private int rIdx = 1;

    private Double maxManaAmount = null;
    private Double currentManaAmount = null;
    private Double aCurrentManaAmount = null;
    private Integer magicLevel = null;
    private Double magicCurrentExp = null;
    private Double aMagicCurrentExp = null;
    private Double magicMaxExp = null;
    private Double aMagicMaxExp = null;
    private Double manaRecoveryAmount = null;
    private final Queue<aQueueData> aManaQueue = new LinkedList<>();
    private final Queue<aQueueData> aExpQueue = new LinkedList<>();

    private boolean init = false;

    protected PlayerEntityMixin(EntityType<LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private boolean error() {
        return (maxManaAmount == null || currentManaAmount == null || magicLevel == null || magicCurrentExp == null || magicMaxExp == null || maxManaAmount == 0.0 || magicLevel == 0.0 || magicMaxExp == 0.0);
    }

    private boolean isClient() {
        return (this.getClass().getName().equals("net.minecraft.client.network.ClientPlayerEntity"));
    }

    private void reset() {
        PolarMagic.Companion.getLogger().warn("Reset " + this.getDisplayName().getString() + "'s MagicData to default");

        this.maxManaAmount = 100.0;
        this.currentManaAmount = 0.0;
        this.magicLevel = 1;
        this.magicCurrentExp = 0.0;
        this.magicMaxExp = 100.0;
        this.manaRecoveryAmount = 10.0;
        this.aCurrentManaAmount = 0.0;
        this.aMagicCurrentExp = 0.0;
        this.aMagicMaxExp = 100.0;
    }

    @Override
    public void setMaxManaAmount(double amount) { this.maxManaAmount = amount; }

    @Override
    public void setCurrentManaAmount(double amount) {
        if (this.maxManaAmount < amount)
            amount = this.maxManaAmount;

        if (this.currentManaAmount < amount) {
            double v = amount - this.currentManaAmount;

            // (at^3)/3 = v
            double a = GraphKt.getGraphA(v, aTick);

            // (a(t_2-t)^3-a(t_1-t)^3)/3 = s
            for (int i = 1; i <= aTick; i++) {
                double s = GraphKt.getGraphS(a, i, aTick);

                aManaQueue.add(new aQueueData(true, s));
            }

        } else if (this.currentManaAmount > amount) {
            double v = this.currentManaAmount - amount;

            // (at^3)/3 = v
            double a = GraphKt.getGraphA(v, aTick);

            // (a(t_2-t)^3-a(t_1-t)^3)/3 = s
            for (int i = 1; i <= aTick; i++) {
                double s = GraphKt.getGraphS(a, i, aTick);

                aManaQueue.add(new aQueueData(false, s));
            }
        }

        this.currentManaAmount = amount;
    }

    @Override
    public Double getMaxManaAmount() { return this.maxManaAmount; }

    @Override
    public Double getCurrentManaAmount() { return this.currentManaAmount; }

    private void magicLevelUp() {
        if (this.magicLevel != null) {
            this.magicLevel++;
            this.magicMaxExp *= expMultiplier;
        }
    }

    private void aMagicLevelUp() {
        if (this.magicLevel != null) {
            this.aMagicMaxExp *= expMultiplier;
        }
    }

    @Override
    public Integer getMagicLevel() { return this.magicLevel; }

    @Override
    public Double getMagicMaxExp() { return this.magicMaxExp; }

    @Override
    public Double getMagicCurrentExp() { return this.magicCurrentExp; }

    @Override
    public void addMagicCurrentExp(double exp) {
        if (exp == 0) return;
        boolean isUp = exp > 0;
        if (!isUp) {
            exp = Math.abs(exp);
            if (exp > this.magicCurrentExp) exp = this.magicCurrentExp;
        }

        // (at^3)/3 = v
        double a = GraphKt.getGraphA(exp, aTick);

        // (a(t_2-t)^3-a(t_1-t)^3)/3 = s
        for (int i = 1; i <= aTick; i++) {
            double s = GraphKt.getGraphS(a, i, aTick);

            aExpQueue.add(new aQueueData(isUp, s));
        }

        if (isUp) {
            double v = this.magicCurrentExp + exp;
            while (v > this.magicMaxExp) {
                v -= this.magicMaxExp;
                this.magicLevelUp();
            }

            this.magicCurrentExp = v;
        } else
            this.magicCurrentExp -= exp;
    }

    @Override
    public void setMagicMaxExp(double exp) {
        this.magicMaxExp = exp;
        this.aMagicMaxExp = exp;
    }

    public Double getACurrentManaAmount() { return this.aCurrentManaAmount; }

    public Double getAMagicCurrentExp() { return this.aMagicCurrentExp; }

    @Override
    public Double getManaRecoveryAmount() { return this.manaRecoveryAmount; }

    @Override
    public void setManaRecoveryAmount(double amount) { this.manaRecoveryAmount = amount; }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        magicLevel = nbt.getInt("magicLevel");

        magicCurrentExp = nbt.getDouble("magicCurrentExp");
        magicMaxExp = nbt.getDouble("magicMaxExp");

        currentManaAmount = nbt.getDouble("currentManaAmount");
        maxManaAmount = nbt.getDouble("maxManaAmount");

        manaRecoveryAmount = nbt.getDouble("manaRecoveryAmount");

        aCurrentManaAmount = currentManaAmount;
        aMagicCurrentExp = magicCurrentExp;
        aMagicMaxExp = magicMaxExp;

        if (error() && !isClient()) reset();
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        if (this.magicLevel != null)
            nbt.putInt("magicLevel", this.magicLevel);

        if (this.magicCurrentExp != null)
            nbt.putDouble("magicCurrentExp", this.magicCurrentExp);
        if (this.magicMaxExp != null)
            nbt.putDouble("magicMaxExp", this.magicMaxExp);

        if (this.currentManaAmount != null)
            nbt.putDouble("currentManaAmount", this.currentManaAmount);
        if (this.maxManaAmount != null)
            nbt.putDouble("maxManaAmount", this.maxManaAmount);

        if (this.manaRecoveryAmount != null)
            nbt.putDouble("manaRecoveryAmount", this.manaRecoveryAmount);
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void tick(CallbackInfo info) {
        if (rIdx == rTick) {
            if (this.currentManaAmount != null && this.maxManaAmount != null && this.maxManaAmount != 0.0 && this.manaRecoveryAmount != null)
                this.setCurrentManaAmount(this.currentManaAmount + this.manaRecoveryAmount);

            rIdx = 1;
        } else {
            rIdx++;
        }

        if (!aManaQueue.isEmpty()) {
            aQueueData data = aManaQueue.poll();

            if (data.isUp)
                this.aCurrentManaAmount += data.amount;
            else
                this.aCurrentManaAmount -= data.amount;
        }

        if (!aExpQueue.isEmpty()) {
            aQueueData data = aExpQueue.poll();

            if (data.isUp) {
                double v = this.aMagicCurrentExp + data.amount;

                while (v > this.aMagicMaxExp) {
                    v -= this.aMagicMaxExp;
                    this.aMagicLevelUp();
                }
                this.aMagicCurrentExp = v;
            } else
                this.aMagicCurrentExp -= data.amount;
        }

        if (maxManaAmount != null && currentManaAmount != null && magicLevel != null && magicCurrentExp != null && magicMaxExp != null)
            MagicDataTable.setData(this.uuid, new MagicData(maxManaAmount, currentManaAmount, magicLevel, magicCurrentExp, magicMaxExp, manaRecoveryAmount, aCurrentManaAmount, aMagicCurrentExp, aMagicMaxExp));

        if (!init) {
            if (error() && !isClient())
                reset();

            init = true;
        }
    }
}