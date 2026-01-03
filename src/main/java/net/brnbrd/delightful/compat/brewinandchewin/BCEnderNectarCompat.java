package net.brnbrd.delightful.compat.brewinandchewin;

import net.brnbrd.delightful.common.fluid.DelightfulFluids;
import net.brnbrd.delightful.common.item.food.Nutrition;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import java.util.function.Supplier;

public class BCEnderNectarCompat {
	public static final Supplier<Item> ENDER_NECTAR = () ->
		new FermentedEnderNectarItem(DelightfulFluids.ENDER_NECTAR.get(),
			(new Item.Properties())
				.food(Nutrition.ENDER_NECTAR)
				.stacksTo(16)
				.craftRemainder(Items.GLASS_BOTTLE)
	);
}