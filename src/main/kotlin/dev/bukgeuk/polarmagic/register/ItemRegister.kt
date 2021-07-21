package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.item.*
import net.fabricmc.fabric.api.`object`.builder.v1.client.model.FabricModelPredicateProviderRegistry
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

val ItemPredicateLambda = { itemStack: ItemStack, _: ClientWorld?, livingEntity: LivingEntity?, _: Int ->
    if (livingEntity == null) {
        0.0F
    } else if (livingEntity.isUsingItem && livingEntity.activeItem == itemStack) {
        1.0f
    } else {
        0.0f
    }
}

fun ItemPredicateRegister() {
    FabricModelPredicateProviderRegistry.register(ITEM_WAND, Identifier("throwing"), ItemPredicateLambda)

    FabricModelPredicateProviderRegistry.register(ITEM_WAND2_DARK, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND2_FIRE, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND2_LIGHT, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND2_WATER, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND2_EARTH, Identifier("throwing"), ItemPredicateLambda)

    FabricModelPredicateProviderRegistry.register(ITEM_WAND3_DARK, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND3_FIRE, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND3_LIGHT, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND3_WATER, Identifier("throwing"), ItemPredicateLambda)
    FabricModelPredicateProviderRegistry.register(ITEM_WAND3_EARTH, Identifier("throwing"), ItemPredicateLambda)
}

fun ItemRegister() {
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand"), ITEM_WAND)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand2_fire"), ITEM_WAND2_FIRE)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand2_water"), ITEM_WAND2_WATER)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand2_earth"), ITEM_WAND2_EARTH)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand2_light"), ITEM_WAND2_LIGHT)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand2_dark"), ITEM_WAND2_DARK)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand3_fire"), ITEM_WAND3_FIRE)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand3_water"), ITEM_WAND3_WATER)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand3_earth"), ITEM_WAND3_EARTH)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand3_light"), ITEM_WAND3_LIGHT)
    Registry.register(Registry.ITEM, Identifier("polarmagic", "wand3_dark"), ITEM_WAND3_DARK)
}