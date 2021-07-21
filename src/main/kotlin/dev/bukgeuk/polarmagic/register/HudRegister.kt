package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.gui.HudManaBar
import io.github.cottonmc.cotton.gui.client.CottonHud

fun HudRegister() {
    CottonHud.add(HudManaBar())
}