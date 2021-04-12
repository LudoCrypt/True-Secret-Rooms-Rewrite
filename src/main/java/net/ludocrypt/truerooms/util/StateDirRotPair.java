package net.ludocrypt.truerooms.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.Direction;

public class StateDirRotPair extends TripplePair<BlockState, Direction, Rotations> implements Comparable<StateDirRotPair> {

	private BlockState state;
	private Direction face;
	private Rotations rotation;

	public StateDirRotPair(BlockState state, Direction face, Rotations rotation) {
		super(state, face, rotation);
	}

	public static final StateDirRotPair DEFAULT = new StateDirRotPair(Blocks.STONE.getDefaultState(), Direction.NORTH, Rotations.ROTATION_0);

	@Override
	public int compareTo(StateDirRotPair o) {
		int i = 0;

		if (state.equals(o.getState())) {
			i += 1;
		}
		if (face.equals(o.getFace())) {
			i += 2;
		}
		if (rotation.equals(o.getRotation())) {
			i += 3;
		}

		return i;
	}

	public BlockState getState() {
		return state;
	}

	public Direction getFace() {
		return face;
	}

	public Rotations getRotation() {
		return rotation;
	}

	public StateDirRotPair setState(BlockState state) {
		this.state = state;
		return this;
	}

	public StateDirRotPair setFace(Direction face) {
		this.face = face;
		return this;
	}

	public StateDirRotPair setRotation(Rotations rotation) {
		this.rotation = rotation;
		return this;
	}

}
