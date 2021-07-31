package dev.bukgeuk.polarmagic.item

import dev.bukgeuk.polarmagic.spell.SpellBase.MagicType
import dev.bukgeuk.polarmagic.spell.SpellFireball
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.text.Text
import net.minecraft.text.TranslatableText
import net.minecraft.util.*
import net.minecraft.world.World

val ITEM_GROUP = FabricItemGroupBuilder.create(Identifier("polarmagic", "magic"))
    .icon { -> ItemStack(Items.BLAZE_ROD) }
    .build()

val WAND_ITEM_SETTINGS = Item.Settings().group(ITEM_GROUP).maxCount(1).fireproof()
val ORB_ITEM_SETTINGS = Item.Settings().group(ITEM_GROUP).maxCount(16).fireproof().rarity(Rarity.EPIC)

val ITEM_WAND = ItemWand(WAND_ITEM_SETTINGS.rarity(Rarity.UNCOMMON))
val ITEM_WAND2_FIRE = ItemWand2(MagicType.Fire, WAND_ITEM_SETTINGS.rarity(Rarity.RARE))
val ITEM_WAND2_WATER = ItemWand2(MagicType.Water, WAND_ITEM_SETTINGS.rarity(Rarity.RARE))
val ITEM_WAND2_EARTH = ItemWand2(MagicType.Earth, WAND_ITEM_SETTINGS.rarity(Rarity.RARE))
val ITEM_WAND2_LIGHT = ItemWand2(MagicType.Light, WAND_ITEM_SETTINGS.rarity(Rarity.RARE))
val ITEM_WAND2_DARK = ItemWand2(MagicType.Dark, WAND_ITEM_SETTINGS.rarity(Rarity.RARE))
val ITEM_WAND3_FIRE = ItemWand3(MagicType.Fire, WAND_ITEM_SETTINGS.rarity(Rarity.EPIC))
val ITEM_WAND3_WATER = ItemWand3(MagicType.Water, WAND_ITEM_SETTINGS.rarity(Rarity.EPIC))
val ITEM_WAND3_EARTH = ItemWand3(MagicType.Earth, WAND_ITEM_SETTINGS.rarity(Rarity.EPIC))
val ITEM_WAND3_LIGHT = ItemWand3(MagicType.Light, WAND_ITEM_SETTINGS.rarity(Rarity.EPIC))
val ITEM_WAND3_DARK = ItemWand3(MagicType.Dark, WAND_ITEM_SETTINGS.rarity(Rarity.EPIC))

val ITEM_ORB_OF_FIRE = OrbOfFire(ORB_ITEM_SETTINGS)


open class WandBase(private val magicType: MagicType, settings: Settings) : Item(settings) {
    fun getMagicType(): MagicType { return this.magicType }

    override fun getUseAction(stack: ItemStack?): UseAction {
        return UseAction.SPEAR
    }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (world != null && user != null) SpellFireball(1).cast(world, user)

        return ItemUsage.consumeHeldItem(world, user, hand)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 72000
    }
}

class ItemWand(settings: Settings) : WandBase(MagicType.None, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand.tooltip_2"))
    }
}

class ItemWand2(type: MagicType, settings: Settings) : WandBase(type, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand2.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand2.tooltip_2"))
    }
}

class ItemWand3(type: MagicType, settings: Settings) : WandBase(type, settings) {
    override fun appendTooltip(stack: ItemStack?, world: World?, tooltip: MutableList<Text>?, context: TooltipContext?) {
        tooltip?.add(TranslatableText("item.polarmagic.wand3.tooltip_1"))
        tooltip?.add(TranslatableText("item.polarmagic.wand3.tooltip_2"))
    }
}

class OrbOfFire(settings: Settings): Item(settings)

