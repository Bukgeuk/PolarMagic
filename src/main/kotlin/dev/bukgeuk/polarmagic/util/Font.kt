package dev.bukgeuk.polarmagic.util

import net.minecraft.client.MinecraftClient

fun getStringWidth(text: String): Float {
    return MinecraftClient.getInstance().textRenderer.textHandler.getWidth(text)
}