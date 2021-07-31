package dev.bukgeuk.polarmagic.spell

import dev.bukgeuk.polarmagic.entity.projectile.MagicFireballEntity
import dev.bukgeuk.polarmagic.entity.projectile.ProjectileBase
import dev.bukgeuk.polarmagic.util.useManaFeature
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class SpellFireball(level: Int) : SpellBase(Tier.ClassC, MagicType.Fire, manaUsage, level) {
    companion object {
        const val manaUsage: Double = 10.0
    }

    override fun cast(world: World, caster: PlayerEntity) {
        val entity = MagicFireballEntity(world, caster)
        entity.setLevel(getLevel())
        entity.setProperties(caster, caster.pitch, caster.yaw, 0f, ProjectileBase.playerZOffset, 0f)

        world.spawnEntity(entity)
        world.playSound(entity.x, entity.y, entity.z, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1f, 1f, true)

        if (useManaFeature(caster)) {
            println(world.isClient)
        }
    }


}