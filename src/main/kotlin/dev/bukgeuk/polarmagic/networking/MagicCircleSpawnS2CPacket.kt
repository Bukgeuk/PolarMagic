package dev.bukgeuk.polarmagic.util

import dev.bukgeuk.polarmagic.entity.magic_circle.MagicCircleEntity
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket

class MagicCircleSpawnS2CPacket: EntitySpawnS2CPacket {
    private var radius: Float = 0f

    constructor(entity: MagicCircleEntity): super(entity) {
        this.radius = entity.getRadiusValue()
    }

    constructor(buf: PacketByteBuf): super(buf) {
        this.radius = buf.readFloat()
    }


    override fun write(buf: PacketByteBuf) {
        super.write(buf)

        buf.writeFloat(this.radius)
    }
}