package dev.bukgeuk.polarmagic.entity.projectile

import dev.bukgeuk.polarmagic.client.particle.ParticleTypes
import dev.bukgeuk.polarmagic.entity.MagicEntityType
import net.minecraft.client.render.entity.ProjectileEntityRenderer
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.Item
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import java.util.*


class MagicFireballEntity: ProjectileBase {
    private var damage = 3f

    constructor(entityType: EntityType<out ProjectileBase>, world: World): super(entityType, world)
    constructor(world: World, owner: LivingEntity) : super(MagicEntityType.MAGIC_FIREBALL, world, owner)
    constructor(world: World, x: Double, y: Double, z: Double): super(MagicEntityType.MAGIC_FIREBALL, world, x, y, z)

    init {
        this.setNoGravity(true)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        super.onEntityHit(entityHitResult)
        val entity: Entity = entityHitResult.entity // sets a new Entity instance as the EntityHitResult (victim)

        entity.damage(DamageSource.thrownProjectile(this, owner), damage) // deals damage
        entity.setOnFireFor(3)

        /*if (entity is LivingEntity) {

        }*/
    }

    override fun levelMultiply() {

    }

    private fun randomParticle(): ParticleTypes.ParticleFlameType? {
        return when (Random().nextInt(4)) {
            0 -> ParticleTypes.PARTICLE_FLAME_0_TYPE
            1 -> ParticleTypes.PARTICLE_FLAME_1_TYPE
            2 -> ParticleTypes.PARTICLE_FLAME_2_TYPE
            3 -> ParticleTypes.PARTICLE_FLAME_3_TYPE
            else -> null
        }
    }

    override fun tick() {
        super.tick()

        for (i in 1..2)
            this.world.addParticle(randomParticle(), this.x - velocity.x, this.y - velocity.y + (this.height / 2), this.z - velocity.z, 0.0, 0.0, 0.0)
    }
}