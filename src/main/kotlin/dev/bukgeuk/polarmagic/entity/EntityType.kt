package dev.bukgeuk.polarmagic.entity

import dev.bukgeuk.polarmagic.entity.magic_circle.*
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup

class EntityType {
    companion object {
        // great magic circle
        val GREAT_FIRE_MAGIC_CIRCLE: EntityType<GreatMagicCircleEntity_Fire> = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ::GreatMagicCircleEntity_Fire)
            .dimensions(EntityDimensions.fixed(82f, 0.01f)).build()
        val GREAT_WATER_MAGIC_CIRCLE: EntityType<GreatMagicCircleEntity_Water> = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ::GreatMagicCircleEntity_Water)
            .dimensions(EntityDimensions.fixed(82f, 0.01f)).build()
        val GREAT_EARTH_MAGIC_CIRCLE: EntityType<GreatMagicCircleEntity_Earth> = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ::GreatMagicCircleEntity_Earth)
            .dimensions(EntityDimensions.fixed(82f, 0.01f)).build()
        val GREAT_DARK_MAGIC_CIRCLE: EntityType<GreatMagicCircleEntity_Dark> = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ::GreatMagicCircleEntity_Dark)
            .dimensions(EntityDimensions.fixed(82f, 0.01f)).build()
        val GREAT_LIGHT_MAGIC_CIRCLE: EntityType<GreatMagicCircleEntity_Light> = FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, ::GreatMagicCircleEntity_Light)
            .dimensions(EntityDimensions.fixed(82f, 0.01f)).build()
    }
}