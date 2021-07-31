package dev.bukgeuk.polarmagic

import dev.bukgeuk.polarmagic.register.*
import net.fabricmc.api.ModInitializer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.world.ClientWorld
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class PolarMagic: ModInitializer {
    companion object {
        const val ModID = "polarmagic"
        val logger: Logger = LogManager.getLogger(PolarMagic::class)
        val MagicCirclePacketID = Identifier(ModID, "magic_circle_spawn_packet")
        val ProjectilePacketID = Identifier(ModID, "projectile_spawn_packet")
        const val lightValue: Int = 15728880
    }

    override fun onInitialize() {
        ItemRegister()

        CommandRegister()

        PotionRegister()

        EntityRegister()
    }
}