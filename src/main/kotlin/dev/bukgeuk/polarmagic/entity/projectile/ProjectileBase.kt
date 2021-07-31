package dev.bukgeuk.polarmagic.entity.projectile

import dev.bukgeuk.polarmagic.PolarMagic
import dev.bukgeuk.polarmagic.item.ITEM_ORB_OF_FIRE
import dev.bukgeuk.polarmagic.networking.EntitySpawnPacket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.thrown.ThrownItemEntity
import net.minecraft.item.Item
import net.minecraft.network.Packet
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

abstract class ProjectileBase: ThrownItemEntity {
    companion object {
        const val playerZOffset = 1.5f
    }

    private var speed = 0.4
    private var level = 1
    private var lifetime = 20 * 20

    constructor(entityType: EntityType<out ThrownItemEntity>, world: World): super(entityType, world)
    constructor(entityType: EntityType<out ThrownItemEntity>, world: World, owner: LivingEntity): super(entityType, owner, world)
    constructor(entityType: EntityType<out ThrownItemEntity>, world: World, x: Double, y: Double, z: Double): super(entityType, x, y, z, world)

    override fun getBrightnessAtEyes(): Float {
        return 15f
    }

    open fun despawn() {
        this.remove(RemovalReason.DISCARDED)
    }

    override fun tick() {
        super.tick()

        val vec3d = velocity
        val j = if (isTouchingWater) 0.8 else 0.99
        velocity = vec3d.multiply(1 / j)

        if (this.lifetime > 0) {
            --this.lifetime
        } else {
            this.despawn()
        }
    }

   /* @Environment(EnvType.CLIENT)
    protected abstract fun getParticleParameters(): ParticleEffect

    @Environment(EnvType.CLIENT)
    override fun handleStatus(status: Byte) {

    }*/

    override fun getDefaultItem(): Item {
        return ITEM_ORB_OF_FIRE
    }

    override fun onCollision(hitResult: HitResult?) {
        super.onCollision(hitResult)
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, 3.toByte())
            this.despawn()
        }
    }

    override fun createSpawnPacket(): Packet<*> {
        return EntitySpawnPacket.create(this, PolarMagic.ProjectilePacketID)
    }

    abstract fun levelMultiply()

    fun setSpeed(value: Double) { this.speed = value }
    fun getSpeed(): Double { return this.speed }

    fun setLevel(value: Int) {
        this.level = value
        levelMultiply()
    }
    fun getLevel(): Int { return this.level }

    //fun setLifetime(value: Int) { this.lifetime = value }
    fun getLifetime(): Int { return this.lifetime }

    override fun setProperties(user: Entity, pitch: Float, yaw: Float, roll: Float, modifierZ: Float, modifierXYZ: Float) {
        super.setProperties(user, pitch, yaw, roll, modifierZ, modifierXYZ)
        velocity = velocity.multiply(speed)
    }
}