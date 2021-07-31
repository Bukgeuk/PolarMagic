package dev.bukgeuk.polarmagic.register

import com.mojang.brigadier.arguments.DoubleArgumentType
import com.mojang.brigadier.arguments.IntegerArgumentType
import dev.bukgeuk.polarmagic.command.EditData
import dev.bukgeuk.polarmagic.command.Magic
import dev.bukgeuk.polarmagic.command.PrintData
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal

fun CommandRegister() {
    CommandRegistrationCallback.EVENT.register { dispatcher, _ -> dispatcher.register(literal("editdata").requires { source -> source.hasPermissionLevel(4) }
        .then(literal("maxManaAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.1)).executes { ctx -> EditData().maxManaAmount(ctx) }))
        .then(literal("currentManaAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes { ctx -> EditData().currentManaAmount(ctx) }))
        .then(literal("manaRecoveryAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes{ ctx -> EditData().manaRecoveryAmount(ctx) }))
        .then(literal("magicCurrentExp")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes{ ctx -> EditData().magicCurrentExp(ctx) }))
        .then(literal("magicMaxExp")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes{ ctx -> EditData().magicMaxExp(ctx) }))
    )}

    CommandRegistrationCallback.EVENT.register { dispatcher, _ -> dispatcher.register(literal("printdata").requires { source -> source.hasPermissionLevel(4) }
        .then(literal("maxManaAmount")
            .executes { ctx -> PrintData().maxManaAmount(ctx) })
        .then(literal("currentManaAmount")
            .executes { ctx -> PrintData().currentManaAmount(ctx) })
        .then(literal("manaRecoveryAmount")
            .executes{ ctx -> PrintData().manaRecoveryAmount(ctx) })
        .then(literal("magicCurrentExp")
            .executes{ ctx -> PrintData().magicCurrentExp(ctx) })
        .then(literal("magicMaxExp")
            .executes{ ctx -> PrintData().magicMaxExp(ctx) })
    )}

    CommandRegistrationCallback.EVENT.register { dispatcher, _ -> dispatcher.register(literal("magic").requires { source -> source.hasPermissionLevel(4) }
        .then(literal("fireball")
            .then(argument("level", IntegerArgumentType.integer(1)).executes { ctx -> Magic().fireball(ctx) }))
    )}

    /*ClientCommandManager.DISPATCHER.register(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("editdata").requires { source -> source.hasPermissionLevel(2) }
        .then(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("maxManaAmount")
            .then(argument<FabricClientCommandSource?, Double?>("amount", DoubleArgumentType.DoubleArg(0.1f)).executes { ctx -> EditData().maxManaAmount(ctx) }))
        .then(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("currentManaAmount")
            .then(argument<FabricClientCommandSource?, Double?>("amount", DoubleArgumentType.DoubleArg(0f)).executes { ctx -> EditData().currentManaAmount(ctx) }))
    )*/
}