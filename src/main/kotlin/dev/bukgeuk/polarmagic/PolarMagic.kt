package dev.bukgeuk.polarmagic

import dev.bukgeuk.polarmagic.register.CommandRegister
import dev.bukgeuk.polarmagic.register.ItemRegister
import dev.bukgeuk.polarmagic.register.ParticleRegister
import dev.bukgeuk.polarmagic.register.PotionRegister
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.fabricmc.loader.util.mappings.MixinIntermediaryDevRemapper
import net.fabricmc.mapping.tree.TinyTree
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.render.BufferBuilder
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleEffect
import net.minecraft.particle.ParticleTypes
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

        ParticleRegister()
    }
}