package net.redcat.plushies;


import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;


public class ModSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Plushies.MODID);
    public static final RegistryObject<SoundEvent> SQEAK = REGISTRY.register("sqeak", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("plushies", "sqeak")));
}
