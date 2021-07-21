package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.item.INSTANT_MANA
import dev.bukgeuk.polarmagic.item.INSTANT_MANA_POTION
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

private fun registerEffects() {
    Registry.register(Registry.STATUS_EFFECT, Identifier("polarmagic", "instant_mana"), INSTANT_MANA)
}

private fun registerPotions() {
    Registry.register(Registry.POTION, Identifier("polarmagic", "instant_mana"), INSTANT_MANA_POTION)
}

fun PotionRegister() {
    registerEffects()

    registerPotions()
}