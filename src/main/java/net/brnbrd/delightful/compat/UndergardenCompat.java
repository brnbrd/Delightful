package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Util;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StemGrownBlock;
import quek.undergarden.registry.UGBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.function.Supplier;

public class UndergardenCompat {

	public static final String pie = "gloomgourd_pie";

	public static Supplier<Item> gloomgourd_slice() {
		return Mods.loaded("undergardendelight") ?
			() -> Util.item("undergardendelight", "gloomgourd_slice") :
			ModItems.PUMPKIN_SLICE;
	}

	public static Supplier<StemGrownBlock> gloomgourd() {
		return UGBlocks.GLOOMGOURD;
	}

	public static Supplier<StemBlock> gloomgourd_stem() {
		return UGBlocks.GLOOMGOURD_STEM;
	}

	public static Supplier<AttachedStemBlock> gloomgourd_attached_stem() {
		return UGBlocks.GLOOMGOURD_STEM_ATTACHED;
	}

	public static final Supplier<FoodProperties> GLOOMGOURD_PIE_SLICE = () -> (new FoodProperties.Builder()).nutrition(3).saturationMod(0.3F).fast()
		.effect(() -> new MobEffectInstance(Mods.getVirulentResistance().get(), 160, 0), 1.0F).build();
}
