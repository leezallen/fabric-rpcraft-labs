package rpcraft.lab.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.util.Identifier;
import rpcraft.lab.LabMain;
import rpcraft.lab.blocks.ModBlocks;
import rpcraft.lab.items.ModItems;

import java.util.function.BiConsumer;

public class ModFabricLootTableProvider extends SimpleFabricLootTableProvider {
    public ModFabricLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> identifierBuilderBiConsumer) {

        identifierBuilderBiConsumer.accept(
                 new Identifier(LabMain.MODID, "blocks/deepslate_uranium_ore" ),
                BlockLootTableGenerator.drops(ModBlocks.DEEPSLATE_URANIUM_ORE, ModItems.URANIUM_RAW)
        );

        identifierBuilderBiConsumer.accept(
                new Identifier(LabMain.MODID, "blocks/nether_lithium_ore" ),
                BlockLootTableGenerator.drops(ModBlocks.NETHER_LITHIUM_ORE, ModItems.LITHIUM_RAW)
        );

    }
}
