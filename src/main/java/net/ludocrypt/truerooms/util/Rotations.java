package net.ludocrypt.truerooms.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public enum Rotations {
	ROTATION_0("zero", 0, 0), ROTATION_90("ninety", 1, 90), ROTATION_180("one_eighty", 2, 180), ROTATION_270("two_seventy", 3, 270);

	private final String name;
	private final int place;
	private final int rot;

	public static final Rotations[] ALL = values();
	public static final Map<String, Rotations> NAME_MAP = Arrays.stream(ALL).collect(Collectors.toMap(Rotations::getName, (direction) -> {
		return direction;
	}));
	public static final Map<Integer, Rotations> ROTATION_MAP = Arrays.stream(ALL).collect(Collectors.toMap(Rotations::getRot, (direction) -> {
		return direction;
	}));
	public static final Rotations[] VALUES = Arrays.stream(ALL).sorted(Comparator.comparingInt((direction) -> {
		return direction.place;
	})).toArray((i) -> {
		return new Rotations[i];
	});

	private Rotations(String name, int place, int rot) {
		this.name = name;
		this.place = place;
		this.rot = rot;
	}

	public String getName() {
		return name;
	}

	public int getPlace() {
		return place;
	}

	public int getRot() {
		return rot;
	}

}
