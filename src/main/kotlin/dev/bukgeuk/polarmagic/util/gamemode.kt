package dev.bukgeuk.polarmagic.util

import net.minecraft.entity.player.PlayerEntity

fun useManaFeature(player: PlayerEntity?): Boolean {
    if (player == null) return false
    return !(player.isCreative || player.isSpectator)
}