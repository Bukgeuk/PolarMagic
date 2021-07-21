package dev.bukgeuk.polarmagic

import dev.bukgeuk.polarmagic.register.HudRegister
import dev.bukgeuk.polarmagic.register.ItemPredicateRegister
import net.fabricmc.api.ClientModInitializer

class PolarMagicClient: ClientModInitializer {
    override fun onInitializeClient() {
        ItemPredicateRegister()

        HudRegister()
    }
}