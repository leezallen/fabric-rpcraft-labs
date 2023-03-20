package rpcraft.lab.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import rpcraft.lab.LabMain;
import rpcraft.lab.blocks.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreFeatureConfig.Target> NETHER_LITHIUM_ORES = List.of(
        OreFeatureConfig.createTarget(OreConfiguredFeatures.BASE_STONE_NETHER, ModBlocks.NETHER_LITHIUM_ORE.getDefaultState())
    );

    public static final List<OreFeatureConfig.Target> OVERWORLD_URANIUM_ORES = List.of(
        OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_URANIUM_ORE.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> LITHIUM_ORE =
        ConfiguredFeatures.register("lithium_ore", Feature.ORE, new OreFeatureConfig(NETHER_LITHIUM_ORES, 8));
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> URANIUM_ORE =
        ConfiguredFeatures.register("uranium_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_URANIUM_ORES, 6));
    public static void registerConfiguredFeatures() {
        LabMain.LOGGER.debug("Registering the ModConfiguredFeatures");
    }
}
