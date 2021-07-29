package dev.bukgeuk.polarmagic.entity.magic_circle

import dev.bukgeuk.polarmagic.PolarMagic
import dev.bukgeuk.polarmagic.PolarMagic.Companion.lightValue
import dev.bukgeuk.polarmagic.entity.magic_circle.base.GreatMagicCircleModel
import dev.bukgeuk.polarmagic.entity.magic_circle.base.MagicCircleEntity
import dev.bukgeuk.polarmagic.entity.magic_circle.base.MagicCircleEntityRenderer
import dev.bukgeuk.polarmagic.entity.magic_circle.base.MagicCircleModel
import net.minecraft.client.model.*
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityType
import net.minecraft.util.Identifier
import net.minecraft.world.LightType
import net.minecraft.world.World

// Entity Classes
class GreatMagicCircleEntity_Fire(entityType: EntityType<*>, world: World) : MagicCircleEntity(entityType, world, "great", "fire")
class GreatMagicCircleEntity_Water(entityType: EntityType<*>, world: World) : MagicCircleEntity(entityType, world, "great", "water")
class GreatMagicCircleEntity_Earth(entityType: EntityType<*>, world: World) : MagicCircleEntity(entityType, world, "great", "earth")
class GreatMagicCircleEntity_Dark(entityType: EntityType<*>, world: World) : MagicCircleEntity(entityType, world, "great", "dark")
class GreatMagicCircleEntity_Light(entityType: EntityType<*>, world: World) : MagicCircleEntity(entityType, world, "great", "light")


// Model Classes
class GreatMagicCircleModel_Fire(modelPart: ModelPart) : GreatMagicCircleModel(modelPart) {
    companion object {
        val modelLayer = EntityModelLayer(Identifier(PolarMagic.ModID, "great_fire_magic_circle"), "main")
    }
}
class GreatMagicCircleModel_Water(modelPart: ModelPart) : GreatMagicCircleModel(modelPart) {
    companion object {
        val modelLayer = EntityModelLayer(Identifier(PolarMagic.ModID, "great_water_magic_circle"), "main")
    }
}
class GreatMagicCircleModel_Earth(modelPart: ModelPart) : GreatMagicCircleModel(modelPart) {
    companion object {
        val modelLayer = EntityModelLayer(Identifier(PolarMagic.ModID, "great_earth_magic_circle"), "main")
    }
}
class GreatMagicCircleModel_Dark(modelPart: ModelPart) : GreatMagicCircleModel(modelPart) {
    companion object {
        val modelLayer = EntityModelLayer(Identifier(PolarMagic.ModID, "great_dark_magic_circle"), "main")
    }
}
class GreatMagicCircleModel_Light(modelPart: ModelPart) : GreatMagicCircleModel(modelPart) {
    companion object {
        val modelLayer = EntityModelLayer(Identifier(PolarMagic.ModID, "great_light_magic_circle"), "main")
    }
}


// Renderer Classes
class GreatMagicCircleRenderer_Fire(ctx: EntityRendererFactory.Context) : MagicCircleEntityRenderer(ctx) {
    private val model = GreatMagicCircleModel_Fire(ctx.getPart(GreatMagicCircleModel_Fire.modelLayer))

    override fun render(entity: MagicCircleEntity, yaw: Float, tickDelta: Float, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.push()
        val alpha = entity.getAlpha()
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), lightValue, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, alpha)
        matrices.pop()
    }
}
class GreatMagicCircleRenderer_Water(ctx: EntityRendererFactory.Context) : MagicCircleEntityRenderer(ctx) {
    private val model = GreatMagicCircleModel_Water(ctx.getPart(GreatMagicCircleModel_Water.modelLayer))

    override fun render(entity: MagicCircleEntity, yaw: Float, tickDelta: Float, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.push()
        val alpha = entity.getAlpha()
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), lightValue, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, alpha)
        matrices.pop()
    }
}
class GreatMagicCircleRenderer_Earth(ctx: EntityRendererFactory.Context) : MagicCircleEntityRenderer(ctx) {
    private val model = GreatMagicCircleModel_Earth(ctx.getPart(GreatMagicCircleModel_Earth.modelLayer))

    override fun render(entity: MagicCircleEntity, yaw: Float, tickDelta: Float, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.push()
        val alpha = entity.getAlpha()
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), lightValue, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, alpha)
        matrices.pop()
    }
}
class GreatMagicCircleRenderer_Dark(ctx: EntityRendererFactory.Context) : MagicCircleEntityRenderer(ctx) {
    private val model = GreatMagicCircleModel_Dark(ctx.getPart(GreatMagicCircleModel_Dark.modelLayer))

    override fun render(entity: MagicCircleEntity, yaw: Float, tickDelta: Float, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.push()
        val alpha = entity.getAlpha()
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), lightValue, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, alpha)
        matrices.pop()
    }
}
class GreatMagicCircleRenderer_Light(ctx: EntityRendererFactory.Context) : MagicCircleEntityRenderer(ctx) {
    private val model = GreatMagicCircleModel_Light(ctx.getPart(GreatMagicCircleModel_Light.modelLayer))

    override fun render(entity: MagicCircleEntity, yaw: Float, tickDelta: Float, matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
        matrices.push()
        val alpha = entity.getAlpha()
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(this.getTexture(entity))), lightValue, OverlayTexture.DEFAULT_UV, 1f, 1f, 1f, alpha)
        matrices.pop()
    }
}


