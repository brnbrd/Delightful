package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import java.util.function.Consumer;

public class AgedPrawnRoeFluidType extends FluidType {
	public static final ResourceLocation AGED_PRAWN_ROE_STILL_TEXTURE = Util.delight("block/aged_prawn_roe_still");
	public static final ResourceLocation AGED_PRAWN_ROE_FLOWING_TEXTURE = Util.delight("block/aged_prawn_roe_flow");

	public AgedPrawnRoeFluidType() {
		super(Properties.create()
			.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
			.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
			.sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
		);
	}

	@Override
	public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		consumer.accept(new IClientFluidTypeExtensions() {
			@Override
			public ResourceLocation getStillTexture() {
				return AGED_PRAWN_ROE_STILL_TEXTURE;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return AGED_PRAWN_ROE_FLOWING_TEXTURE;
			}
		});
	}
}