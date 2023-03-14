package rpcraft.lab;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import rpcraft.lab.blocks.BatteryBlock;

import static rpcraft.lab.LabMain.MODID;


public class RegisterBlocks {
	
	// Define base block details
	public static final Block HAZARD = new Block(FabricBlockSettings.of(Material.METAL).strength(1f, 1f));
	public static final BatteryBlock BATTERY = new BatteryBlock(FabricBlockSettings.of(Material.METAL).strength(3f, 3f));

	// Define custom creative tabs. 
	public static final ItemGroup DECORATIVE = FabricItemGroupBuilder.create(
	        new Identifier(MODID, "rpcdecorative"))
	        .icon(() -> new ItemStack(HAZARD))
	        .build();
	
	public static final ItemGroup ELECTRICAL = FabricItemGroupBuilder.create(
	        new Identifier(MODID, "rpcelectrical"))
	        .icon(() -> new ItemStack(BATTERY))
	        .build();

	
	public static void AddLabBlock (Block block, String name, ItemGroup tab)
	{
		Identifier blockID = new Identifier(MODID, name);
		Registry.register(Registry.BLOCK,blockID,block);
		Registry.register(Registry.ITEM,blockID, new BlockItem(block, new FabricItemSettings().group(tab)));
	}
	
	public static void RegisterAllBlocks()
	{
		
		AddLabBlock(HAZARD,"hazard",DECORATIVE);
		AddLabBlock(BATTERY,"battery",ELECTRICAL);
		//AddLabBlock(HAZARD,"hazard",ItemGroup.DECORATIONS);
		//AddLabBlock(BATTERY,"battery",ItemGroup.DECORATIONS);
	}

}
