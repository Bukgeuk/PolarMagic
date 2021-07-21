package dev.bukgeuk.polarmagic
import dev.bukgeuk.polarmagic.register.CommandRegister
import dev.bukgeuk.polarmagic.register.ItemRegister
import dev.bukgeuk.polarmagic.register.PotionRegister
import net.fabricmc.api.ModInitializer

class PolarMagic: ModInitializer {
    override fun onInitialize() {
        ItemRegister()

        CommandRegister()

        PotionRegister()
    }
}