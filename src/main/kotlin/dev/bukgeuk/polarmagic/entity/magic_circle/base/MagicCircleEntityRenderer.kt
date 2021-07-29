package dev.bukgeuk.polarmagic.entity.magic_circle.base

import dev.bukgeuk.polarmagic.PolarMagic
import net.minecraft.client.render.*
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier


abstract class MagicCircleEntityRenderer(ctx: EntityRendererFactory.Context) : EntityRenderer<MagicCircleEntity>(ctx) {
    //private val rotationSpeed: Float = rotationSpeed

    override fun render(
        entity: MagicCircleEntity,
        yaw: Float,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int
    ) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
    }

    override fun getTexture(entity: MagicCircleEntity): Identifier {
        return Identifier(PolarMagic.ModID, "textures/entity/magic_circle/${entity.getSizeString()}_${entity.getMagicTypeString()}.png")
    }
}