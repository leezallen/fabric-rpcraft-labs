package rpcraft.lab;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static rpcraft.lab.LabMain.MODID;


public class RegisterBlocks {
	
	public static final Block HAZARD = new Block(FabricBlockSettings.of(Material.METAL));
	
	public static void AddLabBlock (Block block, String name)
	{
		Identifier blockID = new Identifier(MODID, name);
		Registry.register(Registry.BLOCK,blockID,block);
		Registry.register(Registry.ITEM,blockID, new BlockItem(block, new FabricItemSettings()));
	}
	
	public static void RegisterAllBlocks()
	{
		AddLabBlock(HAZARD,"hazard");
	}

}
