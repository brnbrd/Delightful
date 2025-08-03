package net.brnbrd.delightful.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.brnbrd.delightful.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementFilter;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import org.jetbrains.annotations.NotNull;

public class ConfigEnabledFilter extends PlacementFilter {
	public static final Codec<ConfigEnabledFilter> CODEC = RecordCodecBuilder.create((builder) ->
		builder.group(
			Codec.STRING.fieldOf("item").forGetter((instance) -> instance.item)
		).apply(builder, ConfigEnabledFilter::new));
	private final @NotNull String item;

	private ConfigEnabledFilter(@NotNull final String item) {
		this.item = item;
	}

	public static ConfigEnabledFilter enabled(@NotNull final String item) {
		return new ConfigEnabledFilter(item);
	}

	@Override
	protected boolean shouldPlace(@NotNull PlacementContext context, @NotNull RandomSource random, @NotNull BlockPos pos) {
		return Util.enabled(this.item);
	}

	@Override
	public @NotNull PlacementModifierType<?> type() {
		return DelightfulPlacementModifiers.ENABLED.get();
	}
}