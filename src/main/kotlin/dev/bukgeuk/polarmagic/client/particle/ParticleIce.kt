package dev.bukgeuk.polarmagic.client.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper

@Environment(EnvType.CLIENT)
class ParticleIce(clientWorld: ClientWorld?, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double) :
    AbstractSlowingParticle(clientWorld, d, e, f, g, h, i) {
    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE
    }

    override fun move(dx: Double, dy: Double, dz: Double) {
        boundingBox = boundingBox.offset(dx, dy, dz)
        repositionFromBoundingBox()
    }

    override fun getSize(tickDelta: Float): Float {
        val f = (age.toFloat() + tickDelta) / maxAge.toFloat()
        return scale * (1.0f - f * f * 0.5f)
    }


    companion object {
        @Environment(EnvType.CLIENT)
        class SmallFactory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType?> {
            override fun createParticle(defaultParticleType: DefaultParticleType?, clientWorld: ClientWorld, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double): Particle {
                val iceParticle = ParticleIce(clientWorld, d, e, f, g, h, i)
                iceParticle.setSprite(spriteProvider)
                iceParticle.scale(0.5f)
                return iceParticle
            }
        }

        @Environment(EnvType.CLIENT)
        class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType?> {
            override fun createParticle(defaultParticleType: DefaultParticleType?, clientWorld: ClientWorld, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double): Particle {
                val iceParticle = ParticleIce(clientWorld, d, e, f, g, h, i)
                iceParticle.setSprite(spriteProvider)
                return iceParticle
            }
        }
    }
}