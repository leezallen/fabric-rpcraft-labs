package rpcraft.lab.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import rpcraft.lab.LabMain;
import rpcraft.lab.blocks.custom.BatteryBlock;
import rpcraft.lab.items.ModItemGroups;

import static rpcraft.lab.LabMain.MODID;


public class ModBlocks {

	// Define base block details
	public static final Block HAZARD = RegisterLabBlock(new Block(FabricBlockSettings.of(Material.METAL).strength(1f, 1f)) ,
			"hazard" ,ModItemGroups.RPCLAB);
			
	public static final BatteryBlock BATTERY = (BatteryBlock) RegisterLabBlock(new BatteryBlock(FabricBlockSettings.of(Material.METAL).strength(3f, 3f)), 
			"battery", ModItemGroups.RPCLAB, 16);
	
	public static final Block DEEPSLATE_URANIUM_ORE = RegisterLabBlock(new OreBlock(FabricBlockSettings.of(Material.METAL).strength(3f, 3f).requiresTool(), UniformIntProvider.create(3, 7)),
			"deepslateuraniumore",  ModItemGroups.RPCLAB);
	
	public static final Block NETHER_LITHIUM_ORE = RegisterLabBlock(new OreBlock(FabricBlockSettings.of(Material.METAL).strength(3f, 3f).requiresTool(), UniformIntProvider.create(3, 7)),
			"netherlithiumore",  ModItemGroups.RPCLAB);
	
	
	private static Block RegisterLabBlock (Block block, String name, ItemGroup tab, int maxCount)
	{
		
		LabMain.LOGGER.info("Registering block: " + name);
		RegisterBlockItem(block, name, tab, maxCount);
		return Registry.register(Registry.BLOCK, new Identifier(MODID, name) ,block);
		
	}
	
	private static Block RegisterLabBlock (Block block, String name, ItemGroup tab)
	{
		return RegisterLabBlock(block,name,tab,64);
	}
	
	
	private static Item RegisterBlockItem(Block block, String name, ItemGroup tab, int maxCount) 
	{
		return Registry.register(Registry.ITEM, new Identifier(MODID, name), new BlockItem(block, new FabricItemSettings().group(tab).maxCount(maxCount)));
	}
	public static void RegisterModBlocks()
	{
		
		LabMain.LOGGER.debug("Registered blocks for " + LabMain.MODID);

	}

}
