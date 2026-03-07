package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.common.fluid.DelightfulFluids;
import net.brnbrd.delightful.common.item.food.Nutrition;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.function.Supplier;

public class BCLFLCompat {
	public static final Supplier<Item> AGED_ROE = () ->
		new AgedRoeItem(DelightfulFluids.AGED_ROE.get(),
			(new Item.Properties())
				.stacksTo(16)
				.food(Nutrition.AGED_ROE)
				.craftRemainder(Items.GLASS_BOTTLE)
		);
	public static final Supplier<Item> CAVIAR = () ->
		new CaviarItem(DelightfulFluids.CAVIAR.get(),
			(new Item.Properties())
				.stacksTo(16)
				.food(Nutrition.CAVIAR)
				.craftRemainder(Items.GLASS_BOTTLE)
		);
}