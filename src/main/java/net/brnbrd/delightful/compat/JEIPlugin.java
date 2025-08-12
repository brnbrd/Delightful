package net.brnbrd.delightful.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.fluid.DelightfulFluids;
import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.IConfigured;
import net.brnbrd.delightful.common.item.food.GreenTeaLeavesItem;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.common.utility.TextUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
@ParametersAreNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin {
	private static final ResourceLocation ID = Util.delight("jei_plugin");

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		// Hide all disabled items from JEI
		List<ItemStack> hidden = new ArrayList<>(
			DelightfulItems.ITEMS.getEntries()
				.stream()
				.filter(i -> { // Keep disabled items (to add to hidden list)
					return !(i.get() instanceof IConfigured c ? c.enabled() : Util.enabled(i));
				})
				.map(Util::gs)
				.toList());
		List<FluidStack> hiddenFluids = new ArrayList<>();

		// Delightful conflicts
		this.hide(hidden, Modid.SOB, "pbnj");
		this.hide(hidden, Modid.VD, "pb_j");
		this.hide(hidden, Modid.HH, "peanut_butter_and_jelly_sandwich");
		this.hide(hidden, Modid.HH, "smore");
		this.hide(hidden, Modid.UGD, "gloomgourd_pie_slice");

		// FD conflicts
		this.hide(hidden, Modid.NA, "cooked_egg");
		this.hide(hidden, Modid.AA, "fried_egg");
		this.hide(hidden, Modid.TH, "beetroot_block");
		this.hide(hidden, Modid.TH, "carrot_block");
		this.hide(hidden, Modid.TH, "potato_block");

		// Abnormals conflicts
		this.hide(hidden, Modid.HH, "chocolate_bar", Modid.N);
		this.hide(hidden, Modid.HH, "egg_crate", Modid.IN);
		this.hide(hidden, Modid.HH, "turtle_egg_crate", Modid.IN);
		this.hide(hidden, Modid.HH, "sweet_berry_crate", Modid.BG);
		this.hide(hidden, Modid.HH, "glow_berry_crate", Modid.BG);

		// Other
		this.hideAnd(hidden, Modid.CTD, "tequila", Modid.SOB, Modid.AT);
		this.hide(hidden, Modid.HH, "salt", Modid.S);
		this.hide(hidden, Modid.HH, "salt_bag", Modid.S);
		this.hide(hidden, Modid.HH, "sugar_bag", Modid.SUP);
		this.hide(hidden, Modid.HH, "mashed_potatoes", Modid.COS);
		this.hide(hidden, Modid.TH, "syrup_bottle", Modid.AUT);
		this.hide(hidden, Modid.AE2, "ender_dust", Modid.TH);
		this.hide(hidden, Modid.EIO, "powdered_ender_pearl", Modid.TH);
		this.hide(hidden, Modid.EIO, "cake_base");
		this.hide(hidden, Modid.EIO, "silicon", Modid.AE2);
		this.hide(hidden, Modid.MOD, "bread_slice", Modid.SAS);
		this.hide(hidden, Modid.MOD, "toast", Modid.SAS);
		this.hide(hidden, Modid.AA, "honeyed_apple", Modid.BB);

		if (!hidden.isEmpty()) {
			registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, hidden);
		}

		// Hide fluids
		boolean farmersRespiteLoaded = Modid.FR.loaded();
		if (!farmersRespiteLoaded || Util.tagEmpty(DelightfulItemTags.LAVENDER)) {
			hiddenFluids.add(new FluidStack(DelightfulFluids.LAVENDER_TEA.get(), 1000));
		}
		if (!farmersRespiteLoaded || Util.tagEmpty(DelightfulItemTags.FLOWERS_AZALEA)) {
			hiddenFluids.add(new FluidStack(DelightfulFluids.AZALEA_TEA.get(), 1000));
		}
		if (Util.tagEmpty(DelightfulItemTags.FRUITS_PRICKLY_PEAR)) {
			hiddenFluids.add(new FluidStack(DelightfulFluids.PRICKLY_PEAR_JUICE.get(), 1000));
		}

		// Add Knife translations
		DelightfulItems.ITEMS.getEntries().stream()
				.map(RegistryObject::get)
				.filter(k -> k instanceof DKnifeItem dk && dk.enabled())
				.map(ItemStack::new)
				.forEach(knifeStack -> registration.addIngredientInfo(
					knifeStack,
					VanillaTypes.ITEM_STACK,
					TextUtils.getTranslation("jei.info.knife")
				));

		// Add other descriptions
		if (Util.enabled(DelightfulItems.SALMONBERRIES)) {
			registration.addIngredientInfo(
					List.of(
						Util.gs(DelightfulItems.SALMONBERRIES),
						Util.gs(DelightfulItems.WILD_SALMONBERRIES)
					),
					VanillaTypes.ITEM_STACK,
				Util.description("salmonberries")
			);
		}
		if (((GreenTeaLeavesItem) DelightfulItems.GREEN_TEA_LEAF.get()).enabled()) {
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.GREEN_TEA_LEAF),
				VanillaTypes.ITEM_STACK,
				Util.description("green_tea_leaf")
			);
		}
		if (Util.enabled(DelightfulItems.ACORN)) {
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.ACORN),
				VanillaTypes.ITEM_STACK,
				Util.description("acorn")
			);
		}
		if (Util.enabled(DelightfulItems.ANIMAL_FAT)) {
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.ANIMAL_FAT),
				VanillaTypes.ITEM_STACK,
				Util.description("animal_fat")
			);
		}
		if (Util.enabled(DelightfulItems.ANIMAL_OIL_BOTTLE)) {
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.ANIMAL_OIL_BOTTLE),
				VanillaTypes.ITEM_STACK,
				Util.description("animal_oil_bottle")
			);
		}
		if (Util.enabled(DelightfulItems.CANTALOUPE)) {
			if (Util.enabled(DelightfulItems.CANTALOUPE_SEEDS)) {
				registration.addIngredientInfo(
					Util.gs(DelightfulItems.CANTALOUPE_SEEDS),
					VanillaTypes.ITEM_STACK,
					Util.description("cantaloupe_seeds")
				);
			}
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.CANTALOUPE),
				VanillaTypes.ITEM_STACK,
				Util.description("cantaloupe")
					.append(" ")
					.append(Util.description("sliceable"))
			);
		}
		if (Util.enabled(DelightfulItems.MINI_MELON)) {
			registration.addIngredientInfo(
				Util.gs(DelightfulItems.MINI_MELON),
				VanillaTypes.ITEM_STACK,
				Util.description("mini_melon")
					.append(" ")
					.append(Util.description("sliceable"))
			);
		}
		registration.addIngredientInfo(
			new ItemStack(Items.MELON),
			VanillaTypes.ITEM_STACK,
			Util.description("sliceable")
		);
		registration.addIngredientInfo(
			new ItemStack(Items.PUMPKIN),
			VanillaTypes.ITEM_STACK,
			Util.description("sliceable")
		);
		registration.getIngredientManager().removeIngredientsAtRuntime(ForgeTypes.FLUID_STACK, hiddenFluids);
	}

	private void hide(List<ItemStack> hiddenList, Modid modid, String name, Modid... conflicts) {
		if (
			modid.loaded() &&
			Mods.loaded(Strategy.OR, conflicts)
		) {
			Item found = modid.item(name);
			if (found != null) {
				hiddenList.add(new ItemStack(found));
			}
		}
	}

	private void hideAnd(List<ItemStack> hiddenList, Modid modid, String name, Modid... conflicts) {
		if (
			modid.loaded() &&
			Mods.loaded(Strategy.AND, conflicts)
		) {
			Item found = modid.item(name);
			if (found != null) {
				hiddenList.add(new ItemStack(found));
			}
		}
	}

	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return ID;
	}
}