package rpcraft.lab.items;

import static rpcraft.lab.LabMain.MODID;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import rpcraft.lab.blocks.ModBlocks;

public class ModItemGroups {

	public static final ItemGroup RPCLAB = FabricItemGroupBuilder.build(
	        new Identifier(MODID, "rpclab")
	        ,() -> new ItemStack(ModBlocks.BATTERY));
	        

	
}
