package dev.bukgeuk.polarmagic.client.particle

import dev.bukgeuk.polarmagic.PolarMagic
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.math.MathHelper

@Environment(EnvType.CLIENT)
class ParticleFlame(world: ClientWorld, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double, spriteProvider: SpriteProvider) : SpriteBillboardParticle(world, x, y, z) {
    private var spriteProvider: SpriteProvider? = null

    init {
        this.spriteProvider = spriteProvider
        this.maxAge = 7
        setSpriteForAge(spriteProvider)
    }

    public override fun getBrightness(tint: Float): Int {
        var f = (age.toFloat() + tint) / maxAge.toFloat()
        f = MathHelper.clamp(f, 0.0f, 1.0f)
        val i = super.getBrightness(tint)
        var j = i and 255
        val k = i shr 16 and 255
        j += (f * 15.0f * 16.0f).toInt()
        if (j > 240) {
            j = 240
        }
        return j or k shl 16
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
                val flameParticle = ParticleFlame(clientWorld, d, e, f, g, h, i, spriteProvider)

                return flameParticle
            }
        }
    }
}