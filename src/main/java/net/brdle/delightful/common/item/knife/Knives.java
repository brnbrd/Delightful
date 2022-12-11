package net.brdle.delightful.common.item.knife;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.DelightfulTiers;
import net.brdle.delightful.common.item.knife.allthemodium.AllthemodiumKnifeItem;
import net.brdle.delightful.common.item.knife.create_sa.ExperienceKnifeItem;
import net.brdle.delightful.common.item.knife.create_sa.GildedQuartzKnifeItem;
import net.brdle.delightful.common.item.knife.forbidden_arcanus.DracoArcanusKnifeItem;
import net.brdle.delightful.common.item.knife.lolenderite.ObsdianInfusedEnderiteKnifeItem;
import net.brdle.delightful.common.item.knife.nethers_exoticism.KiwanoKnifeItem;
import net.brdle.delightful.common.item.knife.oresabovediamonds.LargeAmethystKnifeItem;
import net.brdle.delightful.common.item.knife.rootsclassic.LivingKnifeItem;
import net.brdle.delightful.common.item.knife.seeds.LeafKnifeItem;
import net.brdle.delightful.common.item.knife.twilightforest.FieryKnifeItem;
import net.brdle.delightful.common.item.knife.twilightforest.IronwoodKnifeItem;
import net.brdle.delightful.common.item.knife.twilightforest.SteeleafKnifeItem;
import net.brdle.delightful.compat.BYGCompat;
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModItems;
import java.util.Locale;
import java.util.function.Supplier;

public class Knives extends DelightfulItems {

	// Knives
	public static final RegistryObject<Item> BONE = registerKnife("bone", Tags.Items.BONES);
	public static final RegistryObject<Item> AMETHYST = registerGemKnife("amethyst");
	public static final RegistryObject<Item> EMERALD = registerGemKnife("emerald");
	public static final RegistryObject<Item> COPPER = registerIngotKnife("copper");
	public static final RegistryObject<Item> LAPIS_LAZULI = registerKnife("lapis_lazuli", Tags.Items.GEMS_LAPIS);

	public static final RegistryObject<Item> BLACK_OPAL = registerGemKnife("black_opal");
	public static final RegistryObject<Item> TIN = registerIngotKnife("tin");
	public static final RegistryObject<Item> STEEL = registerIngotKnife("steel");
	public static final RegistryObject<Item> SILVER = registerIngotKnife("silver");
	public static final RegistryObject<Item> BRASS = registerIngotKnife("brass");
	public static final RegistryObject<Item> BRONZE = registerIngotKnife("bronze");
	public static final RegistryObject<Item> CONSTANTAN = registerIngotKnife("constantan");
	public static final RegistryObject<Item> ELECTRUM = registerIngotKnife("electrum");
	public static final RegistryObject<Item> INVAR = registerIngotKnife("invar");
	public static final RegistryObject<Item> LEAD = registerIngotKnife("lead");
	public static final RegistryObject<Item> NICKEL = registerIngotKnife("nickel");
	public static final RegistryObject<Item> ZINC = registerIngotKnife("zinc");

	public static final RegistryObject<Item> MYTHRIL = registerCompatKnife("mythril", "simpleores", ingot("mythril"));
	public static final RegistryObject<Item> ADAMANTIUM = registerCompatKnife("adamantium", "simpleores", ingot("adamantium"));
	public static final RegistryObject<Item> ONYX = registerCompatKnife("onyx", "simpleores", gem("onyx"));
	public static final RegistryObject<Item> THYRIUM = registerCompatKnife("thyrium", "fusion", ingot("thyrium"));
	public static final RegistryObject<Item> SINISITE = registerCompatKnife("sinisite", "fusion", ingot("sinisite"));
	public static final RegistryObject<Item> ALLTHEMODIUM = registerItem("allthemodium_knife", () -> new AllthemodiumKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> ENDERITE = registerSmithedKnife("enderite", Util.ing(ModItems.NETHERITE_KNIFE), ingot("enderite"));
	public static final RegistryObject<Item> DEORUM = registerCompatKnife("deorum", "forbidden_arcanus", ingot("deorum"));
	public static final RegistryObject<Item> REINFORCED_DEORUM = registerSmithedKnife("reinforced_deorum", Util.ing(DEORUM), DelightfulItemTags.STELLARITE_PIECE, "forbidden_arcanus");
	public static final RegistryObject<Item> DRACO_ARCANUS = registerItem("draco_arcanus_knife", () -> new DracoArcanusKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> OSMIUM = registerCompatKnife("osmium", "mekanismtools", ingot("osmium"));
	public static final RegistryObject<Item> REFINED_GLOWSTONE = registerCompatKnife("refined_glowstone", "mekanismtools", ingot("refined_glowstone"));
	public static final RegistryObject<Item> REFINED_OBSIDIAN = registerCompatKnife("refined_obsidian", "mekanismtools", ingot("refined_obsidian"));
	public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE = registerItem("obsidian_infused_enderite_knife", () -> new ObsdianInfusedEnderiteKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> NETHERITE_OPAL = registerSmithedKnife("netherite_opal", Util.ing(BLACK_OPAL), Tags.Items.INGOTS_NETHERITE, "oresabovediamonds");
	public static final RegistryObject<Item> LARGE_AMETHYST = registerItem("large_amethyst_knife", () -> new LargeAmethystKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> FIERY = registerItem("fiery_knife", () -> new FieryKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> IRONWOOD = registerItem("ironwood_knife", () -> new IronwoodKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> KNIGHTMETAL = registerCompatKnife("knightmetal", "twilightforest", ingot("knightmetal"), Component.translatable("item.twilightforest.knightmetal_sword.tooltip").withStyle(ChatFormatting.GRAY));
	public static final RegistryObject<Item> STEELEAF = registerItem("steeleaf_knife", () -> new SteeleafKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> LIVING = registerItem("living_knife", () -> new LivingKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> PENDORITE = registerSmithedKnife("pendorite", Util.ing(ModItems.NETHERITE_KNIFE), ingot("pendorite"), BYGCompat.modid);
	public static final RegistryObject<Item> WARDEN = registerItem("warden_knife", () -> new CompatKnifeItem("deeperdarker", DelightfulItemTags.REINFORCED_ECHO_SHARD, DelightfulTiers.WARDEN, (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), Util.ing(ModItems.NETHERITE_KNIFE), ChatFormatting.LIGHT_PURPLE));
	public static final RegistryObject<Item> EXPERIENCE = registerItem("experience_knife", () -> new ExperienceKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> GILDED_QUARTZ = registerItem("gilded_quartz_knife", () -> new GildedQuartzKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> LEAF = registerItem("leaf_knife", () -> new LeafKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));
	public static final RegistryObject<Item> KIWANO = registerItem("kiwano_knife", () -> new KiwanoKnifeItem((new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB)));

	// Registers a knife to Farmer's Delight tab, requiring modid
	public static RegistryObject<Item> registerCompatKnife(String name, String modid, TagKey<Item> tag, Component... tool) {
		if (tool.length > 0) {
			return registerItem(name + "_knife", () -> new CompatKnifeItem(modid, tag, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), tool[0], null));
		} else {
			return registerItem(name + "_knife", () -> new CompatKnifeItem(modid, tag, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), null));
		}
	}

	// Registers a knife to Farmer's Delight tab, requiring non-empty ingot tag
	public static RegistryObject<Item> registerIngotKnife(String name) {
		return registerItem(name + "_knife", () -> new DelightfulKnifeItem(ingot(name), DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), null));
	}

	// Registers a knife to Farmer's Delight tab, requiring non-empty gem tag
	public static RegistryObject<Item> registerGemKnife(String name) {
		return registerItem(name + "_knife", () -> new DelightfulKnifeItem(gem(name), DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), null));
	}

	// Registers a knife to Farmer's Delight tab, requiring non-empty ingot tag
	public static RegistryObject<Item> registerSmithedKnife(String name, Supplier<Ingredient> base, TagKey<Item> addition, String... modid) {
		if (modid.length > 0) {
			return registerItem(name + "_knife", () -> new CompatKnifeItem(modid[0], addition, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), base));
		} else {
			return registerItem(name + "_knife", () -> new DelightfulKnifeItem(addition, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), base));
		}
	}

	// Registers a knife to Farmer's Delight tab
	public static RegistryObject<Item> registerKnife(String name, TagKey<Item> tag) {
		return registerItem(name + "_knife", () -> new DelightfulKnifeItem(tag, DelightfulTiers.valueOf(name.toUpperCase(Locale.ROOT)), (new Item.Properties()).tab(FarmersDelight.CREATIVE_TAB), null));
	}

	public static void create() {
	}
	
}
