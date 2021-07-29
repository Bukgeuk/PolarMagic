package dev.bukgeuk.polarmagic

import dev.bukgeuk.polarmagic.register.CommandRegister
import dev.bukgeuk.polarmagic.register.EntityRegister
import dev.bukgeuk.polarmagic.register.ItemRegister
import dev.bukgeuk.polarmagic.register.PotionRegister
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PolarMagic: ModInitializer {
    companion object {
        val ModID = "polarmagic"
        val logger: Logger = LogManager.getLogger(PolarMagic::class)
        val PacketID = Identifier(ModID, "magic_circle_spawn_packet")
        val lightValue: Int = 15728880
    }

    override fun onInitialize() {
        ItemRegister()

        CommandRegister()

        PotionRegister()

        EntityRegister()
    }
}