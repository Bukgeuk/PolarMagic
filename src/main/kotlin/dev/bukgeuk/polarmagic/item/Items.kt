package dev.bukgeuk.polarmagic.item

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.*
import net.minecraft.recipe.Ingredient
import net.minecraft.screen.slot.Slot
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.*
import net.minecraft.world.World
import org.lwjgl.opengl.GL11

enum class Type {
    None,
    Fire,
    Water,
    Earth,
    Light,
    Dark
}

val ITEM_GROUP = FabricItemGroupBuilder.create(Identifier("polarmagic", "magic"))
    .icon { -> ItemStack(Items.BLAZE_ROD) }
    .build()

val ITEM_SETTINGS = Item.Settings().group(ITEM_GROUP).maxCount(1)

val ITEM_WAND = ItemWand(ITEM_SETTINGS.rarity(Rarity.UNCOMMON).fireproof())
val ITEM_WAND2_FIRE = ItemWand2(Type.Fire, ITEM_SETTINGS.rarity(Rarity.RARE).fireproof())
val ITEM_WAND2_WATER = ItemWand2(Type.Water, ITEM_SETTINGS.rarity(Rarity.RARE).fireproof())
val ITEM_WAND2_EARTH = ItemWand2(Type.Earth, ITEM_SETTINGS.rarity(Rarity.RARE).fireproof())
val ITEM_WAND2_LIGHT = ItemWand2(Type.Light, ITEM_SETTINGS.rarity(Rarity.RARE).fireproof())
val ITEM_WAND2_DARK = ItemWand2(Type.Dark, ITEM_SETTINGS.rarity(Rarity.RARE).fireproof())
val ITEM_WAND3_FIRE = ItemWand3(Type.Fire, ITEM_SETTINGS.rarity(Rarity.EPIC).fireproof())
val ITEM_WAND3_WATER = ItemWand3(Type.Water, ITEM_SETTINGS.rarity(Rarity.EPIC).fireproof())
val ITEM_WAND3_EARTH = ItemWand3(Type.Earth, ITEM_SETTINGS.rarity(Rarity.EPIC).fireproof())
val ITEM_WAND3_LIGHT = ItemWand3(Type.Light, ITEM_SETTINGS.rarity(Rarity.EPIC).fireproof())
val ITEM_WAND3_DARK = ItemWand3(Type.Dark, ITEM_SETTINGS.rarity(Rarity.EPIC).fireproof())


open class WandBase(private val type: Type, settings: Settings) : Item(settings) {
    fun getMagicType(): Type { return this.type }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.SPEAR
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        return ItemUsage.consumeHeldItem(world, user, hand)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 72000
    }
}

class ItemWand(settings: Settings) : WandBase(Type.None, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand.tooltip_2"))
    }


}

class ItemWand2(type: Type, settings: Settings) : WandBase(type, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand2.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand2.tooltip_2"))
    }
}

class ItemWand3(type: Type, settings: Settings) : WandBase(type, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand3.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand3.tooltip_2"))
    }
}


