package dev.bukgeuk.polarmagic.gui

import dev.bukgeuk.polarmagic.util.MagicDataTable
import dev.bukgeuk.polarmagic.util.getStringHeight
import dev.bukgeuk.polarmagic.util.getStringWidth
import io.github.cottonmc.cotton.gui.client.ScreenDrawing
import io.github.cottonmc.cotton.gui.widget.WWidget
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack

@Environment(EnvType.CLIENT)
class HudLevel : WWidget() {
    companion object {
        const val LevelTextColor = 0xFF_FFD700.toInt()
        const val ExpBarBorderColor = 0xFF_000000.toInt()
        const val ExpBarColor = 0xFF_32CD32.toInt()
        const val EmptyExpBarColor = 0xFF_4A4A4A80.toInt()
        const val ExpTextColor = 0xFF_FFFFFF.toInt()
    }

    override fun paint(matrices: MatrixStack?, x: Int, y: Int, mouseX: Int, mouseY: Int) {
        val p = MinecraftClient.getInstance().player

        if (p?.isCreative == false) {
            val table = MagicDataTable.getData(p.uuid)

            val str = "Lv.${table.magicLevel} ${p.displayName.string}"
            ScreenDrawing.drawString(matrices, str, 5, 5, LevelTextColor)

            // Border
            val expLeft = 5
            val expTop = 5 + getStringHeight() + 2
            val expWidth = getStringWidth(str).toInt() + 20

            ScreenDrawing.coloredRect(matrices, expLeft, expTop, expWidth, 1, ExpBarBorderColor)
            ScreenDrawing.coloredRect(matrices, expLeft, expTop + 5, expWidth, 1, ExpBarBorderColor)
            ScreenDrawing.coloredRect(matrices, expLeft, expTop, 1, 6, ExpBarBorderColor)
            ScreenDrawing.coloredRect(matrices, expLeft + expWidth, expTop, 1, 6, ExpBarBorderColor)

            // Content
            val rawCurrentExp = table?.aMagicCurrentExp
            val rawMaxExp = table?.aMagicMaxExp

            val percentage = if (rawMaxExp == 0.0 || rawCurrentExp == null || rawMaxExp == null) 0.0 else rawCurrentExp / rawMaxExp

            if (percentage > 0.0)
                ScreenDrawing.coloredRect(matrices, expLeft + 1, expTop + 1, (expWidth * percentage).toInt(), 4, ExpBarColor)
            ScreenDrawing.coloredRect(matrices, expLeft + 1, expTop + 1, expWidth, 4, EmptyExpBarColor)

            // Text
            val currentExp = String.format("%.1f", rawCurrentExp)
            val maxExp = String.format("%.1f", rawMaxExp)
            val expText = "$currentExp/$maxExp"
            ScreenDrawing.drawString(matrices, expText, (expLeft + 1 + expWidth / 2 - getStringWidth(expText) / 2).toInt(), expTop - 1, ExpTextColor)
        }
    }
}