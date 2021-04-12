package net.ludocrypt.truerooms.block.camouflage;

import net.ludocrypt.truerooms.block.CamouflageBlock;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.item.ItemPlacementContext;

public class CamouflageDoorBlock extends DoorBlock implements SecretBlock, CamouflageBlock {

	public CamouflageDoorBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return SecretBlock.defaultPlacementState(ctx, super.getPlacementState(ctx));
	}

}
