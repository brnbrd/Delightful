package net.brnbrd.delightful.common.fluid;

import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import java.util.function.Consumer;

public class DFluidType extends FluidType {
	private final int tint;

	public DFluidType(int tint) {
		super(FluidType.Properties.create()
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
		);
		this.tint = tint;
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			@Override
			public int getTintColor() {
				return tint;
			}

			@Override
			public ResourceLocation getStillTexture() {
				return Util.rl("block/water_still");
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return Util.rl("block/water_flow");
			}
		});
	}
}