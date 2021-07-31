package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.PolarMagic
import dev.bukgeuk.polarmagic.entity.MagicEntityType
import dev.bukgeuk.polarmagic.entity.magic_circle.*
import dev.bukgeuk.polarmagic.entity.magic_circle.base.GreatMagicCircleModel
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.FlyingItemEntityRenderer
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

fun EntityRegister() {
    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "great_fire_magic_circle"), MagicEntityType.GREAT_FIRE_MAGIC_CIRCLE)
    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "great_water_magic_circle"), MagicEntityType.GREAT_WATER_MAGIC_CIRCLE)
    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "great_earth_magic_circle"), MagicEntityType.GREAT_EARTH_MAGIC_CIRCLE)
    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "great_dark_magic_circle"), MagicEntityType.GREAT_DARK_MAGIC_CIRCLE)
    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "great_light_magic_circle"), MagicEntityType.GREAT_LIGHT_MAGIC_CIRCLE)

    Registry.register(Registry.ENTITY_TYPE, Identifier(PolarMagic.ModID, "fireball"), MagicEntityType.MAGIC_FIREBALL)
}

fun EntityModelRegister() {
    EntityRendererRegistry.INSTANCE.register(MagicEntityType.GREAT_FIRE_MAGIC_CIRCLE) { context ->
        GreatMagicCircleRenderer_Fire(context)
    }
    EntityModelLayerRegistry.registerModelLayer(GreatMagicCircleModel_Fire.modelLayer, GreatMagicCircleModel::getTexturedModelData)

    EntityRendererRegistry.INSTANCE.register(MagicEntityType.GREAT_WATER_MAGIC_CIRCLE) { context ->
        GreatMagicCircleRenderer_Water(context)
    }
    EntityModelLayerRegistry.registerModelLayer(GreatMagicCircleModel_Water.modelLayer, GreatMagicCircleModel::getTexturedModelData)

    EntityRendererRegistry.INSTANCE.register(MagicEntityType.GREAT_EARTH_MAGIC_CIRCLE) { context ->
        GreatMagicCircleRenderer_Earth(context)
    }
    EntityModelLayerRegistry.registerModelLayer(GreatMagicCircleModel_Earth.modelLayer, GreatMagicCircleModel::getTexturedModelData)

    EntityRendererRegistry.INSTANCE.register(MagicEntityType.GREAT_DARK_MAGIC_CIRCLE) { context ->
        GreatMagicCircleRenderer_Dark(context)
    }
    EntityModelLayerRegistry.registerModelLayer(GreatMagicCircleModel_Dark.modelLayer, GreatMagicCircleModel::getTexturedModelData)

    EntityRendererRegistry.INSTANCE.register(MagicEntityType.GREAT_LIGHT_MAGIC_CIRCLE) { context ->
        GreatMagicCircleRenderer_Light(context)
    }
    EntityModelLayerRegistry.registerModelLayer(GreatMagicCircleModel_Light.modelLayer, GreatMagicCircleModel::getTexturedModelData)
}

fun EntityRendererRegister() {
    EntityRendererRegistry.INSTANCE.register(MagicEntityType.MAGIC_FIREBALL) { context ->
        FlyingItemEntityRenderer(context)
    }
}