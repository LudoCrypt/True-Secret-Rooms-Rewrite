package net.ludocrypt.truerooms.block.camouflage;

import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;

public class CamouflageBlock extends Block implements SecretBlock {

	public CamouflageBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
