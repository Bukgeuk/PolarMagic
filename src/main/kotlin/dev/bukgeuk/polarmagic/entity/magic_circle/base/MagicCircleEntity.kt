package dev.bukgeuk.polarmagic.entity.magic_circle.base

import dev.bukgeuk.polarmagic.PolarMagic.Companion.MagicCirclePacketID
import io.netty.buffer.Unpooled
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.math.Direction
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import net.minecraft.world.World


open class MagicCircleEntity(entityType: EntityType<*>, world: World, private var size: String, private var magicType: String) : Entity(entityType, world) {
    companion object {
        const val effectTick: Int = 40
    }

    private var alpha: Float = 0f
    private var lastTick: Int = 0
    private var despawning: Boolean = false

    init {
        this.ignoreCameraFrustum = true
    }

    fun getAlpha(): Float {
        return this.alpha
    }

    fun getMagicTypeString(): String {
        return this.magicType
    }

    fun getSizeString(): String {
        return this.size
    }

    fun despawn() {
        this.lastTick = 0
        this.despawning = true
    }

    override fun initDataTracker() {

    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {

    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {

    }

    override fun createSpawnPacket(): Packet<*> {
        val buf = PacketByteBuf(Unpooled.buffer())

        buf.writeVarInt(Registry.ENTITY_TYPE.getRawId(type))
            .writeUuid(getUuid())
            .writeVarInt(this.id)
            .writeDouble(this.x)
            .writeDouble(this.y)
            .writeDouble(this.z)
            .writeByte(MathHelper.floor(pitch * 256.0f / 360.0f))
            .writeByte(MathHelper.floor(yaw * 256.0f / 360.0f))

        return ServerPlayNetworking.createS2CPacket(MagicCirclePacketID, buf)
    }

    override fun isCollidable(): Boolean {
        return false
    }

    override fun doesNotCollide(offsetX: Double, offsetY: Double, offsetZ: Double): Boolean {
        return true
    }

    override fun getHorizontalFacing(): Direction {
        return Direction.fromRotation(this.yaw.toDouble())
    }

    override fun collidesWith(other: Entity?): Boolean {
        return false
    }

    @Environment(EnvType.CLIENT)
    override fun shouldRender(distance: Double): Boolean {
        val d = 64.0 * getRenderDistanceMultiplier()
        return distance < d * d
    }

    @Environment(EnvType.CLIENT)
    override fun method_30951(f: Float): Vec3d {
        return this.pos
    }

    override fun isFireImmune(): Boolean {
        return true
    }

    override fun tick() {
        super.tick()

        if (lastTick < effectTick + 1) {
            this.alpha = if (!this.despawning) (1f / effectTick) * lastTick else (1f / effectTick) * (effectTick - lastTick)
            lastTick++
        }

        if (despawning && lastTick >= effectTick + 1) {
            this.kill()
        }
    }
}