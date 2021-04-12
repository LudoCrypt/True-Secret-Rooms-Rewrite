package net.ludocrypt.truerooms.block.camouflage;

import net.ludocrypt.truerooms.block.CamouflageBlock;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.ItemPlacementContext;

public class CamouflageTrapdoorBlock extends TrapdoorBlock implements SecretBlock, CamouflageBlock {

	public CamouflageTrapdoorBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
