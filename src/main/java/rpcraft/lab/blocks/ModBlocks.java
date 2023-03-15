package rpcraft.lab.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import rpcraft.lab.LabMain;
import rpcraft.lab.blocks.custom.BatteryBlock;
import rpcraft.lab.items.ModItemGroups;

import static rpcraft.lab.LabMain.MODID;


public class ModBlocks {

	// Define base block details
	public static final Block HAZARD = new Block(FabricBlockSettings.of(Material.METAL).strength(1f, 1f));
	public static final BatteryBlock BATTERY = new BatteryBlock(FabricBlockSettings.of(Material.METAL).strength(3f, 3f));
	
	
	public static void AddLabBlock (Block block, String name, ItemGroup tab, int maxCount)
	{
		
		LabMain.LOGGER.info("Registering block: " + name);
		Identifier blockID = new Identifier(MODID, name);
		Registry.register(Registry.BLOCK,blockID,block);
		Registry.register(Registry.ITEM,blockID, new BlockItem(block, new FabricItemSettings().group(tab).maxCount(maxCount)));
	}
	
	public static void AddLabBlock (Block block, String name, ItemGroup tab)
	{
		AddLabBlock(block,name,tab,64);
	}
	
	public static void RegisterModBlocks()
	{
		AddLabBlock(HAZARD,"hazard",ModItemGroups.RPCLAB);
		AddLabBlock(BATTERY,"battery",ModItemGroups.RPCLAB,16);

	}

}
