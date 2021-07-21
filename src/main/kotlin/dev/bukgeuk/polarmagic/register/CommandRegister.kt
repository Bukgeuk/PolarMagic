package dev.bukgeuk.polarmagic.register

import com.mojang.brigadier.arguments.DoubleArgumentType
import dev.bukgeuk.polarmagic.command.EditData
import dev.bukgeuk.polarmagic.command.PrintData
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.argument
import net.minecraft.server.command.CommandManager.literal

fun CommandRegister() {
    CommandRegistrationCallback.EVENT.register { dispatcher, dedicated -> dispatcher.register(literal("editdata").requires { source -> source.hasPermissionLevel(2) }
        .then(literal("maxManaAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.1)).executes { ctx -> EditData().maxManaAmount(ctx) }))
        .then(literal("currentManaAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes { ctx -> EditData().currentManaAmount(ctx) }))
        .then(literal("manaRecoveryAmount")
            .then(argument("amount", DoubleArgumentType.doubleArg(0.0)).executes{ ctx -> EditData().manaRecoveryAmount(ctx) }))
    )}

    CommandRegistrationCallback.EVENT.register { dispatcher, dedicated -> dispatcher.register(literal("printdata").requires { source -> source.hasPermissionLevel(2) }
        .then(literal("maxManaAmount")
            .executes { ctx -> PrintData().maxManaAmount(ctx) })
        .then(literal("currentManaAmount")
            .executes { ctx -> PrintData().currentManaAmount(ctx) })
        .then(literal("manaRecoveryAmount")
            .executes{ ctx -> PrintData().manaRecoveryAmount(ctx) })
    )}

    /*ClientCommandManager.DISPATCHER.register(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("editdata").requires { source -> source.hasPermissionLevel(2) }
        .then(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("maxManaAmount")
            .then(argument<FabricClientCommandSource?, Double?>("amount", DoubleArgumentType.DoubleArg(0.1f)).executes { ctx -> EditData().maxManaAmount(ctx) }))
        .then(LiteralArgumentBuilder.literal<FabricClientCommandSource?>("currentManaAmount")
            .then(argument<FabricClientCommandSource?, Double?>("amount", DoubleArgumentType.DoubleArg(0f)).executes { ctx -> EditData().currentManaAmount(ctx) }))
    )*/
}