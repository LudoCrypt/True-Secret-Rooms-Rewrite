package net.ludocrypt.truerooms.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.Direction;
import virtuoel.statement.api.property.MutableProperty;

public class StateDirRotPairProperty extends Property<StateDirRotPair> implements MutableProperty<StateDirRotPair> {
	private final Collection<StateDirRotPair> values = new LinkedHashSet<>();

	public StateDirRotPairProperty(String name) {
		super(name, StateDirRotPair.class);
	}

	public static final StateDirRotPairProperty of(String name) {
		return new StateDirRotPairProperty(name);
	}

	@Override
	public Collection<StateDirRotPair> getValues() {
		return values;
	}

	@Override
	public Optional<StateDirRotPair> parse(String valueName) {
		BlockState state = Blocks.STONE.getDefaultState();
		Direction direction = Direction.NORTH;
		Rotations rotation = Rotations.ROTATION_0;

		int dollarCount = 0;
		for (int i = 0; i < valueName.length(); i++) {
			char c = valueName.charAt(i);
			if (c == '$') {
				dollarCount++;
			}
			if (dollarCount > 2) {
				break;
			}
		}

		if (dollarCount == 2 && valueName.length() >= 6) {
			String stateString = valueName.substring(0, valueName.indexOf('$'));
			String directionString = valueName.substring(valueName.indexOf('$') + 1, valueName.lastIndexOf('$'));
			String rotationString = valueName.substring(valueName.lastIndexOf('$') + 1, valueName.length());

			state = parseBlockState(stateString);
			direction = parseDirection(directionString);
			rotation = parseRotation(rotationString);
		}

		StateDirRotPair sdrp = new StateDirRotPair(state, direction, rotation);
		return Optional.of(sdrp);
	}

	public BlockState parseBlockState(String valueName) {
		return Block.STATE_IDS.get(Integer.parseInt(valueName));
	}

	public Direction parseDirection(String valueName) {
		Direction dir = Direction.byName(valueName);
		if (dir == null) {
			dir = Direction.NORTH;
		}
		return dir;
	}

	public Rotations parseRotation(String valueName) {
		return Rotations.ROTATION_MAP.getOrDefault(Integer.parseInt(valueName), Rotations.ROTATION_0);
	}

	@Override
	public String name(StateDirRotPair value) {

		BlockState state = Blocks.STONE.getDefaultState();
		Direction dir = Direction.NORTH;
		Rotations rot = Rotations.ROTATION_0;

		if (value != null) {
			if (value.getState() != null) {
				state = value.getState();
				if (state.getBlock() instanceof SecretBlock) {
					state = Blocks.STONE.getDefaultState();
				}
			}
			if (value.getFace() != null) {
				dir = value.getFace();
			}
			if (value.getRotation() != null) {
				rot = value.getRotation();
			}
		}

		String blockStateString = Integer.valueOf(Block.STATE_IDS.getRawId(state)).toString();
		String faceString = dir.getName();
		String rotationString = Integer.valueOf(rot.getRot()).toString();

		return blockStateString + "$" + faceString + "$" + rotationString;
	}
}
