package dev.bukgeuk.polarmagic.item

import dev.bukgeuk.polarmagic.ext.PlayerEntityExt
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.*
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.potion.Potion

const val ManaColor: Int = 0x7F56AF //0x00BFFF

val INSTANT_MANA = InstantManaEffect()
val INSTANT_MANA_POTION = PotionOfInstantMana()

class InstantManaEffect : InstantStatusEffect(StatusEffectType.BENEFICIAL, ManaColor) {
    companion object {
        const val multiplier: Int = 1
    }

    override fun applyInstantEffect(
        source: Entity?,
        attacker: Entity?,
        target: LivingEntity?,
        amplifier: Int,
        proximity: Double
    ) {
        super.applyInstantEffect(source, attacker, target, amplifier, proximity)

        if (target is PlayerEntity) {
            val j = (proximity * (4 shl amplifier).toDouble() + 0.5) * multiplier
            val p = (target as PlayerEntityExt)
            if (p.currentManaAmount != null && p.maxManaAmount != null && p.maxManaAmount != 0.0)
                p.currentManaAmount += j
        }
    }

    override fun applyUpdateEffect(entity: LivingEntity?, amplifier: Int) {
        super.applyUpdateEffect(entity, amplifier)

        if (entity is PlayerEntity) {
            val p = (entity as PlayerEntityExt)

            if (p.currentManaAmount != null && p.maxManaAmount != null && p.maxManaAmount != 0.0)
                p.currentManaAmount += (4 shl amplifier).coerceAtLeast(0) * multiplier
        }
    }
}

class PotionOfInstantMana(amplifier: Int = 0) : Potion(StatusEffectInstance(INSTANT_MANA, 1, amplifier)) {

}


