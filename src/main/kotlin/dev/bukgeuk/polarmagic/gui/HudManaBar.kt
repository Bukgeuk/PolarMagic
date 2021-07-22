package dev.bukgeuk.polarmagic.gui

import dev.bukgeuk.polarmagic.util.MagicDataTable
import dev.bukgeuk.polarmagic.util.getStringWidth
import io.github.cottonmc.cotton.gui.client.ScreenDrawing
import io.github.cottonmc.cotton.gui.widget.WWidget
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
class HudManaBar : WWidget() {
    companion object {
        var h: Int = 0
        var w: Int = 0

        const val EmptyManaBarColor = 0xFF_4A4A4A80.toInt()
        const val ManaBarBorderColor = 0xFF_000000.toInt()
        const val ManaTextColor = 0xFF_FFFFFF.toInt()
        const val ManaBarColor = 0xFF_7F56AF.toInt()
    }

    init {
        renewScreen()
    }

    override fun paint(matrices: MatrixStack?, x: Int, y: Int, mouseX: Int, mouseY: Int) {
        val p = MinecraftClient.getInstance().player

        if (p?.isCreative == false) {
            val manaWidth = w / 2 - 110 - 2
            val manaLeft = 12
            val manaTop = h - 10

            // Border
            ScreenDrawing.coloredRect(matrices, manaLeft, manaTop, w / 2 - 110, 1, ManaBarBorderColor)
            ScreenDrawing.coloredRect(matrices, manaLeft, manaTop + 5, w / 2 - 110, 1, ManaBarBorderColor)
            ScreenDrawing.coloredRect(matrices, manaLeft, manaTop, 1, 6, ManaBarBorderColor)
            ScreenDrawing.coloredRect(matrices, manaLeft + manaWidth + 1, manaTop, 1, 6, ManaBarBorderColor)

            // Content
            val table = MagicDataTable.getData(p.uuid)
            val rawCurrentMana = table?.aCurrentManaAmount
            val rawMaxMana = table?.maxManaAmount

            val percentage = if (rawMaxMana == 0.0 || rawMaxMana == null || rawCurrentMana == null) 0.0 else rawCurrentMana / rawMaxMana

            if (percentage > 0.0)
                ScreenDrawing.coloredRect(matrices, manaLeft + 1, manaTop + 1, (manaWidth * percentage).toInt(), 4, ManaBarColor)
            ScreenDrawing.coloredRect(matrices, manaLeft + 1, manaTop + 1, manaWidth, 4, EmptyManaBarColor)

            // Text
            val currentMana = String.format("%.1f", rawCurrentMana)
            val maxMana = String.format("%.1f", rawMaxMana)
            val manaText = "$currentMana/$maxMana"
            ScreenDrawing.drawString(matrices, manaText, (manaLeft + 1 + manaWidth / 2 - getStringWidth(manaText) / 2).toInt(), manaTop - 1, ManaTextColor)

            // Icon
            ScreenDrawing.texturedRect(matrices, 1, manaTop - 7, 16, 16, Identifier("polarmagic:textures/gui/mana_icon.png"), 0xFF_FFFFFF.toInt())

        }
    }

    override fun tick() {
        renewScreen()
    }

    private fun renewScreen() {
        val screen = MinecraftClient.getInstance().currentScreen
        if (screen != null) {
            h = screen.height
            w = screen.width
        }
    }


}