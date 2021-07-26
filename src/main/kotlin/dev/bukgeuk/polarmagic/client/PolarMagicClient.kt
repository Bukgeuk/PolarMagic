package dev.bukgeuk.polarmagic.client

import dev.bukgeuk.polarmagic.register.EntityModelRegister
import dev.bukgeuk.polarmagic.register.HudRegister
import dev.bukgeuk.polarmagic.register.ItemPredicateRegister
import dev.bukgeuk.polarmagic.register.ParticleRegister
import dev.bukgeuk.polarmagic.util.MagicDataSync
import dev.bukgeuk.polarmagic.util.convertPacketByteBufToMagicData
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking

class  PolarMagicClient: ClientModInitializer {
    override fun onInitializeClient() {
        ItemPredicateRegister()

        HudRegister()

        EntityModelRegister()

        ParticleRegister()

        ClientPlayNetworking.registerGlobalReceiver(MagicDataSync.packetChannel) { client, handler, buf, responseSender ->
            val data = convertPacketByteBufToMagicData(buf)

            client.execute {
                MagicDataSync.setClientData(data)
            }
        }
    }
}