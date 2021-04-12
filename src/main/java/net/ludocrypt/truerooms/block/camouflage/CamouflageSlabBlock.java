package net.ludocrypt.truerooms.block.camouflage;

import net.ludocrypt.truerooms.block.CamouflageBlock;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemPlacementContext;

public class CamouflageSlabBlock extends SlabBlock implements SecretBlock, CamouflageBlock {

	public CamouflageSlabBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
