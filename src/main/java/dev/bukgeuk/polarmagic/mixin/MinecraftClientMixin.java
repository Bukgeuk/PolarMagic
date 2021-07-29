package dev.bukgeuk.polarmagic.mixin;

import dev.bukgeuk.polarmagic.client.PolarMagicClient;
import dev.bukgeuk.polarmagic.util.MagicCircleEntityData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Queue;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "setWorld", at = @At("RETURN"))
    private void joinWorld(@Nullable ClientWorld world, CallbackInfo info) {
        Queue<MagicCircleEntityData> q = PolarMagicClient.Companion.getTempToSpawnEntity();
        while (!q.isEmpty()) {
            MagicCircleEntityData v = q.poll();

            Entity entity = v.type.create(world);

            if (entity != null) {
                entity.updatePosition(v.x, v.y, v.z);
                entity.updateTrackedPosition(v.x, v.y, v.z);
                entity.setPitch(v.pitch);
                entity.setYaw(v.yaw);
                entity.setId(v.entityID);
                entity.setUuid(v.entityUUID);

                assert world != null;
                world.addEntity(v.entityID, entity);
            }
        }
    }
}
