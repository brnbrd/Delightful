package net.brdle.delightful.compat;

import elucent.rootsclassic.registry.RootsRegistry;
import elucent.rootsclassic.ritual.RitualRegistry;
import elucent.rootsclassic.ritual.rituals.RitualCrafting;
import net.brdle.delightful.common.item.knife.Knives;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RootsCompat {

	public void init() {
		RitualRegistry.RITUALS.register("living_knife_crafting", () ->
			new RitualCrafting(2, 146, 214, 43)
				.setResult(new ItemStack(Knives.LIVING.get(), 1))
				.addIncense(new ItemStack(RootsRegistry.OAK_BARK.get(), 1))
				.addIncense(new ItemStack(RootsRegistry.OAK_BARK.get(), 1))
				.addIngredient(new ItemStack(Items.STICK, 1))
				.addIngredient(new ItemStack(RootsRegistry.VERDANT_SPRIG.get(), 1))
				.addIngredient(new ItemStack(Items.GOLD_NUGGET, 1)));
	}

}
