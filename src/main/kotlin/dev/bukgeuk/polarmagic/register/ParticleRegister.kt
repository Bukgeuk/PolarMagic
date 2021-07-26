package dev.bukgeuk.polarmagic.register

import dev.bukgeuk.polarmagic.client.particle.*
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry

@Environment(EnvType.CLIENT)
fun ParticleRegister() {
    val instance = ParticleFactoryRegistry.getInstance()
    
    instance.register(ParticleTypes.PARTICLE_FLAME_0_TYPE, ParticleFlame.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_FLAME_1_TYPE, ParticleFlame.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_FLAME_2_TYPE, ParticleFlame.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_FLAME_3_TYPE, ParticleFlame.Companion::Factory)

    instance.register(ParticleTypes.PARTICLE_ICE_TYPE, ParticleIce.Companion::Factory)

    instance.register(ParticleTypes.PARTICLE_LIGHTNING_0_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_1_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_2_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_3_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_4_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_5_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_6_TYPE, ParticleLightning.Companion::Factory)
    instance.register(ParticleTypes.PARTICLE_LIGHTNING_7_TYPE, ParticleLightning.Companion::Factory)

    instance.register(ParticleTypes.PARTICLE_LIGHTNING_PULSE_TYPE, ParticleLightningPulse.Companion::Factory)
}