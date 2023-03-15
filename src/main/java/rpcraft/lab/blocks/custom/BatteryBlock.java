package rpcraft.lab.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BatteryBlock extends Block {
	
	public static final BooleanProperty ON = BooleanProperty.of("on");

	public BatteryBlock (Settings settings) {
		super (settings);
		setDefaultState(this.stateManager.getDefaultState().with(ON, false));
	}
	
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
	
		if (!world.isClient) {
			player.sendMessage(Text.of("Bzzzzzzzz..."), false);
		}
		
		return ActionResult.SUCCESS;
	}
	
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
    {
        if (!world.isClient)
        {
            if (world.isReceivingRedstonePower(pos))
            {
            	if (!world.getBlockState(pos).get(ON) && !world.isClient) { 
            		world.playSound(null, pos, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 1f, 1f);
            	}
            	world.setBlockState(pos, state.with(ON, true));
            }
            else
            {
                world.setBlockState(pos, state.with(ON, false));
            }
        }
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
        builder.add(ON);
    }
	
}
