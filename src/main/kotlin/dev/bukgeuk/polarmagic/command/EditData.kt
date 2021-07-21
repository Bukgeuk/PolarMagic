package dev.bukgeuk.polarmagic.command

import com.mojang.brigadier.context.CommandContext
import dev.bukgeuk.polarmagic.ext.PlayerEntityExt
import net.minecraft.text.LiteralText
import com.mojang.brigadier.arguments.DoubleArgumentType.getDouble
import net.minecraft.server.command.ServerCommandSource


class EditData {
    fun maxManaAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = getDouble(ctx, "amount")

        (ctx.source.player as PlayerEntityExt).maxManaAmount = amount

        ctx.source.sendFeedback(LiteralText("'maxManaAmount' is $amount now"), false)

        return 0
    }

    fun currentManaAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = getDouble(ctx, "amount")

        (ctx.source.player as PlayerEntityExt).currentManaAmount = amount

        ctx.source.sendFeedback(LiteralText("'currentManaAmount' is $amount now"), false)

        return 0
    }

    fun manaRecoveryAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = getDouble(ctx, "amount")

        (ctx.source.player as PlayerEntityExt).manaRecoveryAmount = amount

        ctx.source.sendFeedback(LiteralText("'manaRecoveryAmount' is $amount now"), false)

        return 0
    }
}