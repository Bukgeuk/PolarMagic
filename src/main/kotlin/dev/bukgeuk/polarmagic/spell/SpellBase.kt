package dev.bukgeuk.polarmagic.spell

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World

abstract class SpellBase(private val tier: Tier, private val magicType: MagicType, private val manaUsage: Double, private val level: Int) {
    enum class Tier {
        ClassC,
        ClassB,
        ClassA,
        ClassS
    }
    enum class MagicType {
        None,
        Fire,
        Water,
        Earth,
        Light,
        Dark
    }

    fun getTier(): Tier { return this.tier }
    fun getMagicType(): MagicType { return this.magicType }
    fun getManaUsage(): Double { return this.manaUsage }
    fun getLevel(): Int { return this.level }

    abstract fun cast(world: World, caster: PlayerEntity)
}