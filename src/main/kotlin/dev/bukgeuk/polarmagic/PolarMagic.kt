package dev.bukgeuk.polarmagic

import dev.bukgeuk.polarmagic.register.CommandRegister
import dev.bukgeuk.polarmagic.register.ItemRegister
import dev.bukgeuk.polarmagic.register.PotionRegister
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PolarMagic: ModInitializer {
    companion object {
        val logger: Logger = LogManager.getLogger(PolarMagic::class)
    }

    override fun onInitialize() {
        ItemRegister()

        CommandRegister()

        PotionRegister()
    }
}