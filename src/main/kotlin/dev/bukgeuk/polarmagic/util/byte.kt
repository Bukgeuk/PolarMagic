package dev.bukgeuk.polarmagic.util

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.network.PacketByteBuf
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

fun getByteSizeOfHashMap(map: HashMap<*, *>): Int {
    val baos = ByteArrayOutputStream()
    val oos = ObjectOutputStream(baos)
    oos.writeObject(map)
    oos.close()

    return baos.size()
}

fun convertHashMapToByteArray(map: HashMap<*, *>): ByteArray {
    val baos = ByteArrayOutputStream()
    val oos = ObjectOutputStream(baos)
    oos.writeObject(map)

    return baos.toByteArray()
}

fun <K, V> convertByteArrayToHashMap(array: ByteArray): HashMap<K, V> {
    val bais = ByteArrayInputStream(array)
    val ois = ObjectInputStream(bais)

    return ois.readObject() as HashMap<K, V>
}

fun convertMagicDataToPacketByteBuf(data: MagicData): PacketByteBuf {
    val buf = PacketByteBufs.create()

    buf.writeDouble(data.maxManaAmount)
    buf.writeDouble(data.currentManaAmount)
    buf.writeVarInt(data.magicLevel)
    buf.writeDouble(data.magicCurrentExp)
    buf.writeDouble(data.magicMaxExp)
    buf.writeDouble(data.manaRecoveryAmount)
    buf.writeDouble(data.aCurrentManaAmount)
    buf.writeDouble(data.aMagicCurrentExp)
    buf.writeDouble(data.aMagicMaxExp)

    return buf
}

fun convertPacketByteBufToMagicData(buf: PacketByteBuf): MagicData {
    val a = buf.readDouble()
    val b = buf.readDouble()
    val c = buf.readVarInt()
    val d = buf.readDouble()
    val e = buf.readDouble()
    val f = buf.readDouble()
    val g = buf.readDouble()
    val h = buf.readDouble()
    val i = buf.readDouble()

    return MagicData(a, b, c, d, e, f, g, h, i)
}