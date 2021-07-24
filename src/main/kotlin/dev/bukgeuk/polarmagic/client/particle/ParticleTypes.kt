package dev.bukgeuk.polarmagic.client.particle

import net.minecraft.particle.DefaultParticleType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class ParticleTypes {
    companion object {
        val PARTICLE_FLAME_0_TYPE: ParticleFlameType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "flame_0"), ParticleFlameType(false))
        val PARTICLE_FLAME_1_TYPE: ParticleFlameType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "flame_1"), ParticleFlameType(false))
        val PARTICLE_FLAME_2_TYPE: ParticleFlameType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "flame_2"), ParticleFlameType(false))
        val PARTICLE_FLAME_3_TYPE: ParticleFlameType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "flame_3"), ParticleFlameType(false))

        val PARTICLE_ICE_TYPE: ParticleIceType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "ice"), ParticleIceType(false))

        val PARTICLE_LIGHTNING_0_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_0"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_1_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_1"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_2_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_2"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_3_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_3"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_4_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_4"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_5_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_5"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_6_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_6"), ParticleLightningType(false))
        val PARTICLE_LIGHTNING_7_TYPE: ParticleLightningType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_7"), ParticleLightningType(false))

        val PARTICLE_LIGHTNING_PULSE_TYPE: ParticleLightningPulseType = Registry.register(Registry.PARTICLE_TYPE, Identifier("polarmagic", "lightning_pulse"), ParticleLightningPulseType(false))
    }

    class ParticleFlameType(alwaysShow: Boolean): DefaultParticleType(alwaysShow)
    class ParticleIceType(alwaysShow: Boolean): DefaultParticleType(alwaysShow)
    class ParticleLightningType(alwaysShow: Boolean): DefaultParticleType(alwaysShow)
    class ParticleLightningPulseType(alwaysShow: Boolean): DefaultParticleType(alwaysShow)
}