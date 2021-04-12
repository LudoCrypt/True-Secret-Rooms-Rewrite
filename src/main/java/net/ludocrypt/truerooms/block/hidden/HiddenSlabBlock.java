package net.ludocrypt.truerooms.block.hidden;

import net.ludocrypt.truerooms.block.HiddenBlock;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HiddenSlabBlock extends SlabBlock implements SecretBlock, HiddenBlock {

	public HiddenSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return VoxelShapes.fullCube();
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return super.getOutlineShape(state, world, pos, context);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
