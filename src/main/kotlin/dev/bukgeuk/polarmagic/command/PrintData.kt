package dev.bukgeuk.polarmagic.command

import com.mojang.brigadier.context.CommandContext
import dev.bukgeuk.polarmagic.ext.PlayerEntityExt
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText

class PrintData {
    fun maxManaAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = (ctx.source.player as PlayerEntityExt).maxManaAmount

        ctx.source.sendFeedback(LiteralText("'maxManaAmount' is $amount"), false)

        return 0
    }

    fun currentManaAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = (ctx.source.player as PlayerEntityExt).currentManaAmount

        ctx.source.sendFeedback(LiteralText("'currentManaAmount' is $amount"), false)

        return 0
    }

    fun manaRecoveryAmount(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = (ctx.source.player as PlayerEntityExt).manaRecoveryAmount

        ctx.source.sendFeedback(LiteralText("'manaRecoveryAmount' is $amount"), false)

        return 0
    }

    fun magicCurrentExp(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = (ctx.source.player as PlayerEntityExt).magicCurrentExp

        ctx.source.sendFeedback(LiteralText("'magicCurrentExp' is $amount"), false)

        return 0
    }

    fun magicMaxExp(ctx: CommandContext<ServerCommandSource>): Int {
        val amount = (ctx.source.player as PlayerEntityExt).magicMaxExp

        ctx.source.sendFeedback(LiteralText("'magicMaxExp' is $amount"), false)

        return 0
    }
}