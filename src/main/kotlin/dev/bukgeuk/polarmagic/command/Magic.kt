package dev.bukgeuk.polarmagic.command

import com.mojang.brigadier.arguments.DoubleArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import com.mojang.brigadier.context.CommandContext
import dev.bukgeuk.polarmagic.spell.SpellFireball
import net.minecraft.server.command.ServerCommandSource


class Magic {
    fun fireball(ctx: CommandContext<ServerCommandSource>): Int {
        val level = IntegerArgumentType.getInteger(ctx, "level")

        SpellFireball(level).cast(ctx.source.world, ctx.source.player)

        return 0
    }
}