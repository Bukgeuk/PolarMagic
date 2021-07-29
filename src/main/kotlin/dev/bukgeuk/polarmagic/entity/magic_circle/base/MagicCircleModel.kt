package dev.bukgeuk.polarmagic.entity.magic_circle.base

import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.util.math.MatrixStack

abstract class MagicCircleModel(modelPart: ModelPart) : EntityModel<MagicCircleEntity>() {
    private val bone = modelPart.getChild(EntityModelPartNames.CUBE)

    override fun render(
        matrices: MatrixStack?,
        vertices: VertexConsumer?,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        bone.render(matrices, vertices, light, overlay, red, green, blue, alpha)
    }

    override fun setAngles(
        entity: MagicCircleEntity?,
        limbAngle: Float,
        limbDistance: Float,
        animationProgress: Float,
        headYaw: Float,
        headPitch: Float
    ) {

    }
}

abstract class GreatMagicCircleModel(modelPart: ModelPart) : MagicCircleModel(modelPart) {
    companion object {
        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root

            modelPartData.addChild(EntityModelPartNames.CUBE, ModelPartBuilder.create().uv(0, 0).cuboid(-675f, 0f, -675f, 1350f, 0.01f, 1350f),
                ModelTransform.pivot(0f, 0f, 0f))

            return TexturedModelData.of(modelData, 1350, 1350)
        }
    }
}