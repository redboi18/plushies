package net.redcat.plushies;


import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.redcat.plushies.blocks.ModBlocks;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Plushies.MODID);

    public static final RegistryObject<CreativeModeTab> PLUSHIE_TAB = CREATIVE_MODE_TABS.register("plushie_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.RED_PLUSHIE.get()))
                    .title(Component.translatable("creativetab.plushie_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModBlocks.RED_PLUSHIE.get());
                        pOutput.accept(ModBlocks.ASTRAL_PLUSHIE.get());
                        pOutput.accept(ModBlocks.CARWYNN_PLUSHIE.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}