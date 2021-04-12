package net.ludocrypt.truerooms.block;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.ludocrypt.truerooms.util.StateDirRotPair;
import net.ludocrypt.truerooms.util.StateDirRotPairProperty;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;

public interface SecretBlock {

	public static final StateDirRotPairProperty UP = StateDirRotPairProperty.of("up");
	public static final StateDirRotPairProperty DOWN = StateDirRotPairProperty.of("down");
	public static final StateDirRotPairProperty NORTH = StateDirRotPairProperty.of("north");
	public static final StateDirRotPairProperty SOUTH = StateDirRotPairProperty.of("south");
	public static final StateDirRotPairProperty EAST = StateDirRotPairProperty.of("east");
	public static final StateDirRotPairProperty WEST = StateDirRotPairProperty.of("west");

	public static final List<StateDirRotPairProperty> PROPERTIES = Lists.newArrayList(UP, DOWN, NORTH, SOUTH, EAST, WEST);

	public static final Map<StateDirRotPairProperty, Direction> STATE_TO_DIR = completeMap();
	public static final Map<Direction, StateDirRotPairProperty> DIR_TO_STATE = completeBackwardsMap();

	public static BlockState defaultPlacementState(ItemPlacementContext ctx, BlockState baseState) {
		BlockState state = baseState;

		for (StateDirRotPairProperty property : SecretBlock.PROPERTIES.toArray(new StateDirRotPairProperty[0])) {
			BlockState attemption = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
			if (attemption.getBlock() instanceof SecretBlock) {
				attemption = attemption.get(DIR_TO_STATE.get(ctx.getSide())).getState();
			}
			state = state.with(property, StateDirRotPair.DEFAULT.setState(attemption).setFace(STATE_TO_DIR.get(property)));
		}

		return state;
	}

	static Map<StateDirRotPairProperty, Direction> completeMap() {
		Map<StateDirRotPairProperty, Direction> map = Maps.newHashMap();
		map.put(UP, Direction.UP);
		map.put(DOWN, Direction.DOWN);
		map.put(NORTH, Direction.NORTH);
		map.put(SOUTH, Direction.SOUTH);
		map.put(EAST, Direction.EAST);
		map.put(WEST, Direction.WEST);
		return map;
	}

	static Map<Direction, StateDirRotPairProperty> completeBackwardsMap() {
		Map<Direction, StateDirRotPairProperty> map = Maps.newHashMap();
		map.put(Direction.UP, UP);
		map.put(Direction.DOWN, DOWN);
		map.put(Direction.NORTH, NORTH);
		map.put(Direction.SOUTH, SOUTH);
		map.put(Direction.EAST, EAST);
		map.put(Direction.WEST, WEST);
		return map;
	}
}
