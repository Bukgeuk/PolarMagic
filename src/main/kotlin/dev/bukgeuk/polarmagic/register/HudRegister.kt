package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.client.gui.HudLevel
import dev.bukgeuk.polarmagic.client.gui.HudManaBar
import io.github.cottonmc.cotton.gui.client.CottonHud

fun HudRegister() {
    CottonHud.add(HudManaBar())
    CottonHud.add(HudLevel())
}