package dev.bukgeuk.polarmagic.entity.magic_circle

import dev.bukgeuk.polarmagic.util.MagicCircleSpawnS2CPacket
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.Packet
import net.minecraft.world.World

open class MagicCircleEntity(type: EntityType<*>, world: World) : Entity(type, world) {
    protected var radius: Float = 0f

    fun getRadiusValue(): Float {
        return this.radius
    }

    fun setRadiusValue(value: Float) {
        this.radius = value
    }

    override fun initDataTracker() {

    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        this.radius = nbt.getFloat("radius")
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        nbt.putFloat("radius", this.radius)
    }

    override fun createSpawnPacket(): Packet<*> {
        return MagicCircleSpawnS2CPacket(this)
    }
}