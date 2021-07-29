package dev.bukgeuk.polarmagic.client

import dev.bukgeuk.polarmagic.PolarMagic.Companion.PacketID
import dev.bukgeuk.polarmagic.register.EntityModelRegister
import dev.bukgeuk.polarmagic.register.HudRegister
import dev.bukgeuk.polarmagic.register.ItemPredicateRegister
import dev.bukgeuk.polarmagic.register.ParticleRegister
import dev.bukgeuk.polarmagic.util.MagicCircleEntityData
import dev.bukgeuk.polarmagic.util.MagicDataSync
import dev.bukgeuk.polarmagic.util.convertPacketByteBufToMagicData
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.registry.Registry
import java.util.*


class  PolarMagicClient: ClientModInitializer {
    companion object {
        val tempToSpawnEntity: Queue<MagicCircleEntityData> = LinkedList()

        fun onEntitySpawn(client: MinecraftClient, handler: ClientPlayNetworkHandler, buf: PacketByteBuf, sender: PacketSender) {
            val type = Registry.ENTITY_TYPE.get(buf.readVarInt())
            val entityUUID = buf.readUuid()
            val entityID = buf.readVarInt()
            val x = buf.readDouble()
            val y = buf.readDouble()
            val z = buf.readDouble()
            val pitch = buf.readByte() * 360 / 256.0f
            val yaw = buf.readByte() * 360 / 256.0f
            val world = MinecraftClient.getInstance().world

            if (world == null) {
                tempToSpawnEntity.add(MagicCircleEntityData(type, entityUUID, entityID, x, y, z, pitch, yaw))
                return
            }

            val entity = type.create(world)

            client.execute {
                if (entity != null) {
                    entity.updatePosition(x, y, z)
                    entity.updateTrackedPosition(x, y, z)
                    entity.pitch = pitch
                    entity.yaw = yaw
                    entity.id = entityID
                    entity.uuid = entityUUID

                    world.addEntity(entityID, entity)
                }
            }
        }
    }

    override fun onInitializeClient() {
        ItemPredicateRegister()

        HudRegister()

        EntityModelRegister()

        ParticleRegister()

        ClientPlayNetworking.registerGlobalReceiver(MagicDataSync.packetChannel) { client, _, buf, _ ->
            val data = convertPacketByteBufToMagicData(buf)

            client.execute {
                MagicDataSync.setClientData(data)
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(PacketID, ::onEntitySpawn)
    }
}