package dev.bukgeuk.polarmagic.client.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper

@Environment(EnvType.CLIENT)
class ParticleLightningPulse(world: ClientWorld, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double, spriteProvider: SpriteProvider) : SpriteBillboardParticle(world, x, y, z) {
    private var spriteProvider: SpriteProvider? = null

    init {
        this.spriteProvider = spriteProvider
        this.maxAge = 5
        setSpriteForAge(spriteProvider)
    }

    override fun getType(): ParticleTextureSheet? {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE
    }

    override fun tick() {
        super.tick()
        setSpriteForAge(spriteProvider)
    }

    companion object {
        @Environment(EnvType.CLIENT)
        class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<DefaultParticleType?> {
            override fun createParticle(defaultParticleType: DefaultParticleType?, clientWorld: ClientWorld, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double): Particle {
                val pulseParticle = ParticleLightningPulse(clientWorld, d, e, f, g, h, i, spriteProvider)

                return pulseParticle
            }
        }
    }
}