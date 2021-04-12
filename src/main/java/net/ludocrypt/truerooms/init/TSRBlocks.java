package net.ludocrypt.truerooms.init;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.ludocrypt.truerooms.TrueSecretRooms;
import net.ludocrypt.truerooms.block.SecretBlock;
import net.ludocrypt.truerooms.block.camouflage.CamouflageBlock;
import net.ludocrypt.truerooms.block.camouflage.CamouflageDoorBlock;
import net.ludocrypt.truerooms.block.camouflage.CamouflageSlabBlock;
import net.ludocrypt.truerooms.block.camouflage.CamouflageStairsBlock;
import net.ludocrypt.truerooms.block.camouflage.CamouflageTrapdoorBlock;
import net.ludocrypt.truerooms.block.hidden.HiddenDoorBlock;
import net.ludocrypt.truerooms.block.hidden.HiddenSlabBlock;
import net.ludocrypt.truerooms.block.hidden.HiddenStairsBlock;
import net.ludocrypt.truerooms.block.hidden.HiddenTrapdoorBlock;
import net.ludocrypt.truerooms.block.secret.GhostBlock;
import net.ludocrypt.truerooms.block.secret.OneWayGlassBlock;
import net.ludocrypt.truerooms.util.Rotations;
import net.ludocrypt.truerooms.util.StateDirRotPair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import virtuoel.statement.api.StateRefresher;

public class TSRBlocks {

	private static final Map<Identifier, BlockItem> ITEMS = new LinkedHashMap<>();
	private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();
	private static final Map<Identifier, Block> FULL_CAMOUFLAGE_BLOCKS = new LinkedHashMap<>();

	public static final Block CAMOUFLAGE_BLOCK = add("camouflage_block", new CamouflageBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block CAMOUFLAGE_DOOR_BLOCK = add("camouflage_door_block", new CamouflageDoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block CAMOUFLAGE_SLAB_BLOCK = add("camouflage_slab_block", new CamouflageSlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block CAMOUFLAGE_STAIRS_BLOCK = add("camouflage_stairs_block", new CamouflageStairsBlock(CAMOUFLAGE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block CAMOUFLAGE_TRAPDOOR_BLOCK = add("camouflage_trapdoor_block", new CamouflageTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);

	public static final Block HIDDEN_DOOR_BLOCK = add("hidden_block", new HiddenDoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block HIDDEN_SLAB_BLOCK = add("hidden_slab_block", new HiddenSlabBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block HIDDEN_STAIRS_BLOCK = add("hidden_stairs_block", new HiddenStairsBlock(CAMOUFLAGE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block HIDDEN_TRAPDOOR_BLOCK = add("hidden_trapdoor_block", new HiddenTrapdoorBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);

	public static final Block GHOST_BLOCK = add("ghost_block", new GhostBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);
	public static final Block ONE_WAY_GLASS_BLOCK = addNonFull("one_way_glass_block", new OneWayGlassBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)), ItemGroup.BUILDING_BLOCKS);

	private static <B extends Block> B add(String name, B block, ItemGroup tab) {
		return add(name, block, new BlockItem(block, new Item.Settings().group(tab)), true);
	}

	private static <B extends Block> B addNonFull(String name, B block, ItemGroup tab) {
		return add(name, block, new BlockItem(block, new Item.Settings().group(tab)), false);
	}

	private static <B extends Block> B add(String name, B block, BlockItem item, boolean isFull) {
		add(name, block, isFull);
		if (item != null) {
			item.appendBlocks(Item.BLOCK_ITEMS, item);
			ITEMS.put(TrueSecretRooms.id(name), item);
		}
		return block;
	}

	private static <B extends Block> B add(String name, B block, boolean isFull) {
		BLOCKS.put(TrueSecretRooms.id(name), block);
		if (isFull) {
			FULL_CAMOUFLAGE_BLOCKS.put(TrueSecretRooms.id(name), block);
		}
		return block;
	}

	public static void init() {
		for (Identifier id : ITEMS.keySet()) {
			Registry.register(Registry.ITEM, id, ITEMS.get(id));
		}
		for (Identifier id : BLOCKS.keySet()) {
			Registry.register(Registry.BLOCK, id, BLOCKS.get(id));
		}
		for (Identifier id : FULL_CAMOUFLAGE_BLOCKS.keySet()) {
			doBlock(FULL_CAMOUFLAGE_BLOCKS.get(id));
		}
		SecretBlock.PROPERTIES.stream().filter((property) -> property != SecretBlock.NORTH).forEach((property) -> {
			StateRefresher.INSTANCE.addBlockProperty(ONE_WAY_GLASS_BLOCK, property, StateDirRotPair.DEFAULT);
			RegistryEntryAddedCallback.event(Registry.BLOCK).register((rawId, identifier, object) -> {
				StateRefresher.INSTANCE.refreshBlockStates(property, getStateDirRotPairs(), ImmutableSet.of());
			});
		});
	}

	public static void doBlock(Block block) {
		SecretBlock.PROPERTIES.forEach((property) -> {
			StateRefresher.INSTANCE.addBlockProperty(block, property, StateDirRotPair.DEFAULT);
			RegistryEntryAddedCallback.event(Registry.BLOCK).register((rawId, identifier, object) -> {
				StateRefresher.INSTANCE.refreshBlockStates(property, getStateDirRotPairs(), ImmutableSet.of());
			});
		});
	}

	public static Set<StateDirRotPair> getStateDirRotPairs() {
		Set<StateDirRotPair> set = Sets.newHashSet();
		Block.STATE_IDS.forEach((state) -> {
			Lists.newArrayList(Direction.values()).forEach((dir) -> {
				Lists.newArrayList(Rotations.values()).forEach((rot) -> {
					set.add(new StateDirRotPair(state, dir, rot));
				});
			});
		});
		return set;
	}
}
