package net.ludocrypt.truerooms.statement;

import com.google.common.collect.ImmutableMap;

import net.ludocrypt.truerooms.block.SecretBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;
import net.minecraft.util.collection.IdList;
import virtuoel.statement.api.StatementApi;

public class TrueSecretRoomsStatement implements StatementApi {

	@Override
	public <S> boolean shouldDeferState(IdList<S> idList, S state) {
		if (idList == Block.STATE_IDS) {
			final BlockState blockState = ((BlockState) state);
			final ImmutableMap<Property<?>, Comparable<?>> entries = blockState.getEntries();
			boolean shouldDo = false;

			for (Property<?> property : SecretBlock.PROPERTIES.toArray(new Property[0])) {
				if (entries.containsKey(property)) {
					shouldDo = true;
					break;
				}
			}

			return shouldDo;
		}
		return false;
	}

}
