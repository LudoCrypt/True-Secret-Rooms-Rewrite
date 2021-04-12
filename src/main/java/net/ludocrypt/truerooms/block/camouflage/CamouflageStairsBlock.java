package net.ludocrypt.truerooms.block.camouflage;

import net.ludocrypt.truerooms.block.CamouflageBlock;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.ItemPlacementContext;

public class CamouflageStairsBlock extends StairsBlock implements SecretBlock, CamouflageBlock {

	public CamouflageStairsBlock(BlockState baseBlockState, Settings settings) {
		super(baseBlockState, settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
