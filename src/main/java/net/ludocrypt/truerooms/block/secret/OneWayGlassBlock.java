package net.ludocrypt.truerooms.block.secret;

import net.ludocrypt.truerooms.block.SecretBlock;
import net.ludocrypt.truerooms.util.StateDirRotPairProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class OneWayGlassBlock extends FacingBlock implements SecretBlock {

	public OneWayGlassBlock(Settings settings) {
		super(settings);
		this.setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.empty();
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockState state = super.getPlacementState(ctx);

		for (Object property : SecretBlock.PROPERTIES.stream().filter((property) -> property != SecretBlock.NORTH).toArray()) {
			BlockState attemption = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
			if (attemption.getBlock() instanceof SecretBlock) {
				attemption = attemption.get(DIR_TO_STATE.get(ctx.getSide())).getState();
			}
			state = state.with((StateDirRotPairProperty) property, state.get((StateDirRotPairProperty) property).setState(attemption).setFace(STATE_TO_DIR.get((StateDirRotPairProperty) property)));
		}

		return state;
	}

}
