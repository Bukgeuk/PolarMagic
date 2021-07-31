package dev.bukgeuk.polarmagic.client

import dev.bukgeuk.polarmagic.PolarMagic
import dev.bukgeuk.polarmagic.PolarMagic.Companion.MagicCirclePacketID
import dev.bukgeuk.polarmagic.networking.EntitySpawnPacket.PacketBufUtil.readAngle
import dev.bukgeuk.polarmagic.networking.EntitySpawnPacket.PacketBufUtil.readVec3d
import dev.bukgeuk.polarmagic.register.*
import dev.bukgeuk.polarmagic.util.MagicCircleEntityData
import dev.bukgeuk.polarmagic.util.MagicDataSync
import dev.bukgeuk.polarmagic.util.convertPacketByteBufToMagicData
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry
import net.fabricmc.fabric.api.network.PacketContext
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
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

        fun receiveEntityPacket() {
            ClientSidePacketRegistry.INSTANCE.register(PolarMagic.ProjectilePacketID) { ctx: PacketContext, byteBuf: PacketByteBuf ->
                val et: EntityType<*> = Registry.ENTITY_TYPE[byteBuf.readVarInt()]
                val uuid = byteBuf.readUuid()
                val entityId = byteBuf.readVarInt()
                val pos = readVec3d(byteBuf)
                val pitch = readAngle(byteBuf)
                val yaw = readAngle(byteBuf)
                ctx.taskQueue.execute {
                    checkNotNull(MinecraftClient.getInstance().world) { "Tried to spawn entity in a null world!" }
                    val e: Entity = et.create(MinecraftClient.getInstance().world)
                        ?: throw IllegalStateException(
                            "Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(
                                et
                            ) + "\"!"
                        )
                    e.updateTrackedPosition(pos)
                    e.setPos(pos.x, pos.y, pos.z)
                    e.pitch = pitch
                    e.yaw = yaw
                    e.id = entityId
                    e.uuid = uuid
                    MinecraftClient.getInstance().world!!.addEntity(entityId, e)
                }
            }
        }

    }

    override fun onInitializeClient() {
        ItemPredicateRegister()

        HudRegister()

        EntityModelRegister()

        EntityRendererRegister()
        receiveEntityPacket()

        ParticleRegister()

        ClientPlayNetworking.registerGlobalReceiver(MagicDataSync.packetChannel) { client, _, buf, _ ->
            val data = convertPacketByteBufToMagicData(buf)

            client.execute {
                MagicDataSync.setClientData(data)
            }
        }
        ClientPlayNetworking.registerGlobalReceiver(MagicCirclePacketID, ::onEntitySpawn)
    }
}