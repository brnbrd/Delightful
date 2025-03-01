package net.brnbrd.delightful.common.item.knife;

import net.brnbrd.delightful.common.item.DelightfulItems;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.compat.DummyKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.ElectrumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.SilverKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.additionaladditions.GildedNetheriteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.additionaladditions.RoseGoldKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.ae2.FluixKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.GravititeKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.HolystoneKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.SkyrootKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.ZaniteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.allthemodium.AllthemodiumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.ancient_aether.ValkyrumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.botania.ElementiumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.botania.ManasteelKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.botania.TerraKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.create_sa.BlazingKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.create_sa.ExperienceKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.create_sa.GildedQuartzKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.aether.deep_aether.DummyStratusKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.deeperdarker.ResonariumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.deeperdarker.WardenKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.forbidden_arcanus.DracoArcanusKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.lolenderite.EnderiteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.lolenderite.ObsdianInfusedEnderiteKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.lost_aether_content.PhoenixKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.botania.mythicbotany.AlfKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.nethers_exoticism.KiwanoKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.nourished_nether.NecroniumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds.BlackOpalKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds.LargeAmethystKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.oresabovediamonds.NetheriteOpalKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.rootsclassic.LivingKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.seeds.LeafKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.spirit.SoulSteelKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.twilightforest.FieryKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.twilightforest.IronwoodKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.twilightforest.KnightmetalKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.twilightforest.SteeleafKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.undergarden.CloggrumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.undergarden.ForgottenKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.undergarden.FroststeelKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.undergarden.UtheriumKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.unusualend.PearlescentKnifeItem;
import net.brnbrd.delightful.compat.aether.AetherReduxCompat;
import net.brnbrd.delightful.compat.botania.BotaniaCompat;
import net.brnbrd.delightful.compat.aether.DeepAetherCompat;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

public class Knives extends DelightfulItems {

	// Knives
	public static final RegistryObject<Item> BONE = registerKnife("bone", Tags.Items.BONES);
	public static final RegistryObject<Item> LAPIS_LAZULI = registerKnife("lapis_lazuli", Tags.Items.GEMS_LAPIS);
	public static final RegistryObject<Item> AMETHYST = registerGemKnife("amethyst");
	public static final RegistryObject<Item> EMERALD = registerGemKnife("emerald");
	public static final RegistryObject<Item> COPPER = registerIngotKnife("copper");
	public static final RegistryObject<Item> NETHER_QUARTZ = registerKnife("nether_quartz", Tags.Items.GEMS_QUARTZ);

	public static final RegistryObject<Item> TIN = registerIngotKnife("tin");
	public static final RegistryObject<Item> STEEL = registerIngotKnife("steel");
	public static final RegistryObject<Item> ALUMINUM = registerIngotKnife("aluminum");
	public static final RegistryObject<Item> BRASS = registerIngotKnife("brass");
	public static final RegistryObject<Item> BRONZE = registerIngotKnife("bronze");
	public static final RegistryObject<Item> CONSTANTAN = registerIngotKnife("constantan");
	public static final RegistryObject<Item> INVAR = registerIngotKnife("invar");
	public static final RegistryObject<Item> LEAD = registerIngotKnife("lead");
	public static final RegistryObject<Item> NICKEL = registerIngotKnife("nickel");
	public static final RegistryObject<Item> ZINC = registerIngotKnife("zinc");

	public static final RegistryObject<Item> MYTHRIL = registerIngotKnife("mythril", Modid.SO);
	public static final RegistryObject<Item> ADAMANTIUM = registerIngotKnife("adamantium", Modid.SO);
	public static final RegistryObject<Item> ONYX = registerGemKnife("onyx", Modid.SO);
	public static final RegistryObject<Item> THYRIUM = registerIngotKnife("thyrium", Modid.FUS);
	public static final RegistryObject<Item> SINISITE = registerIngotKnife("sinisite", Modid.FUS);
	public static final RegistryObject<Item> PEARLESCENT = registerItem("pearlescent_knife", () -> new PearlescentKnifeItem(props()));
	public static final RegistryObject<Item> ALLTHEMODIUM = registerItem("allthemodium_knife", () -> new AllthemodiumKnifeItem(props()));
	public static final RegistryObject<Item> SILVER = registerItem("silver_knife", () -> new SilverKnifeItem(props()));
	public static final RegistryObject<Item> ELECTRUM = registerItem("electrum_knife", () -> new ElectrumKnifeItem(props()));
	public static final RegistryObject<Item> ENDERITE = registerItem("enderite_knife", () -> new EnderiteKnifeItem(props()));
	public static final RegistryObject<Item> DRACO_ARCANUS = registerItem("draco_arcanus_knife", () -> new DracoArcanusKnifeItem(props()));
	public static final RegistryObject<Item> OSMIUM = registerIngotKnife("osmium", Modid.MEKT);
	public static final RegistryObject<Item> REFINED_GLOWSTONE = registerIngotKnife("refined_glowstone", Modid.MEKT);
	public static final RegistryObject<Item> REFINED_OBSIDIAN = registerIngotKnife("refined_obsidian", Modid.MEKT);
	public static final RegistryObject<Item> OBSIDIAN_INFUSED_ENDERITE = registerItem("obsidian_infused_enderite_knife", () -> new ObsdianInfusedEnderiteKnifeItem(props()));
	public static final RegistryObject<Item> BLACK_OPAL = registerItem("black_opal_knife", () -> new BlackOpalKnifeItem(props()));
	public static final RegistryObject<Item> NETHERITE_OPAL = registerItem("netherite_opal_knife", () -> new NetheriteOpalKnifeItem(props()));
	public static final RegistryObject<Item> LARGE_AMETHYST = registerItem("large_amethyst_knife", () -> new LargeAmethystKnifeItem(props()));
	public static final RegistryObject<Item> FIERY = registerItem("fiery_knife", () -> new FieryKnifeItem(props()));
	public static final RegistryObject<Item> IRONWOOD = registerItem("ironwood_knife", () -> new IronwoodKnifeItem(props()));
	public static final RegistryObject<Item> KNIGHTMETAL = registerItem("knightmetal_knife", () -> new KnightmetalKnifeItem(props()));
	public static final RegistryObject<Item> STEELEAF = registerItem("steeleaf_knife", () -> new SteeleafKnifeItem(props()));
	public static final RegistryObject<Item> LIVING = registerItem("living_knife", () -> new LivingKnifeItem(props()));
	public static final RegistryObject<Item> WARDEN = registerItem("warden_knife", () -> new WardenKnifeItem(props()));
	public static final RegistryObject<Item> RESONARIUM = registerItem("resonarium_knife", () -> new ResonariumKnifeItem(props()));
	public static final RegistryObject<Item> EXPERIENCE = registerItem("experience_knife", () -> new ExperienceKnifeItem(props()));
	public static final RegistryObject<Item> GILDED_QUARTZ = registerItem("gilded_quartz_knife", () -> new GildedQuartzKnifeItem(props()));
	public static final RegistryObject<Item> BLAZING = registerItem("blazing_knife", () -> new BlazingKnifeItem(props()));
	public static final RegistryObject<Item> LEAF = registerItem("leaf_knife", () -> new LeafKnifeItem(props()));
	public static final RegistryObject<Item> KIWANO = registerItem("kiwano_knife", () -> new KiwanoKnifeItem(props()));
	public static final RegistryObject<Item> CRYSTALLINE = registerKnife("crystalline", DelightfulItemTags.VOID_CRYSTAL_BLOCK, Modid.EP);
	public static final RegistryObject<Item> SOUL_STEEL = registerItem("soul_steel_knife", () -> new SoulSteelKnifeItem(props()));
	public static final RegistryObject<Item> ROSE_GOLD = registerItem("rose_gold_knife", () -> new RoseGoldKnifeItem(props()));
	public static final RegistryObject<Item> GILDED_NETHERITE = registerItem("gilded_netherite_knife", () -> new GildedNetheriteKnifeItem(props()));
	public static final RegistryObject<Item> NECRONIUM = registerItem("necronium_knife", () -> new NecroniumKnifeItem(props()));
	public static final RegistryObject<Item> CLOGGRUM = registerItem("cloggrum_knife", () -> new CloggrumKnifeItem(props()));
	public static final RegistryObject<Item> FROSTSTEEL = registerItem("froststeel_knife", () -> new FroststeelKnifeItem(props()));
	public static final RegistryObject<Item> UTHERIUM = registerItem("utherium_knife", () -> new UtheriumKnifeItem(props()));
	public static final RegistryObject<Item> FORGOTTEN = registerItem("forgotten_knife", () -> new ForgottenKnifeItem(props()));
	public static final RegistryObject<Item> GRAVITITE = registerItem("gravitite_knife", () -> new GravititeKnifeItem(props()));
	public static final RegistryObject<Item> HOLYSTONE = registerItem("holystone_knife", () -> new HolystoneKnifeItem(props()));
	public static final RegistryObject<Item> SKYROOT = registerItem("skyroot_knife", () -> new SkyrootKnifeItem(props()));
	public static final RegistryObject<Item> ZANITE = registerItem("zanite_knife", () -> new ZaniteKnifeItem(props()));
	public static final RegistryObject<Item> PHOENIX = registerItem("phoenix_knife", () -> new PhoenixKnifeItem(props()));
	public static final RegistryObject<Item> MANASTEEL = registerItem("manasteel_knife", () -> new ManasteelKnifeItem(
		props(),
		DelightfulItemTags.ingot("manasteel"),
		Modid.BTA.loaded() ? BotaniaCompat.manasteel().get() : DelightfulTiers.STEEL
	));
	public static final RegistryObject<Item> ELEMENTIUM = registerItem("elementium_knife", () -> new ElementiumKnifeItem(
		props(),
		DelightfulItemTags.ingot("elementium"),
		Modid.BTA.loaded() ? BotaniaCompat.elementium().get() : DelightfulTiers.STEEL
	));
	public static final RegistryObject<Item> TERRA = registerItem("terra_knife", () -> new TerraKnifeItem(
		props(),
		DelightfulItemTags.ingot("terrasteel"),
		Modid.BTA.loaded() ? BotaniaCompat.terrasteel().get() : DelightfulTiers.STEEL
	));
	public static final RegistryObject<Item> ALF = registerItem("alf_knife", () -> new AlfKnifeItem(props()));
	public static final RegistryObject<Item> VERIDIUM = registerItem("veridium_knife",
		() -> Modid.AER.loaded() ?
			AetherReduxCompat.VERIDIUM.get() :
			new DummyKnifeItem(
				DelightfulItemTags.INGOTS_VERIDIUM,
				Ingredient.of(DelightfulItemTags.SKYROOT_STICK),
				Modid.AE, Modid.AER
			)
	);
	public static final RegistryObject<Item> INFUSED_VERIDIUM = registerItem("infused_veridium_knife",
		() -> Modid.AER.loaded() ?
			AetherReduxCompat.INFUSED_VERIDIUM.get() :
			new DummyKnifeItem(
				DelightfulItemTags.INGOTS_VERIDIUM,
				Ingredient.of(DelightfulItemTags.SKYROOT_STICK),
				Modid.AE, Modid.AER
			)
	);
	public static final RegistryObject<Item> SKYJADE = registerItem("skyjade_knife",
		() -> Modid.DA.loaded() ?
			DeepAetherCompat.SKYJADE.get() :
			new DummyKnifeItem(
				DelightfulItemTags.GEMS_SKYJADE,
				Ingredient.of(DelightfulItemTags.SKYROOT_STICK),
				Modid.AE, Modid.DA
			)
	);
	public static final RegistryObject<Item> STRATUS = registerItem("stratus_knife",
		() -> Modid.DA.loaded() ?
			DeepAetherCompat.STRATUS.get() :
			new DummyStratusKnifeItem(props(), Tiers.IRON)
	);
	public static final RegistryObject<Item> VALKYRUM = registerItem("valkyrum_knife", () -> new ValkyrumKnifeItem(props()));
	public static final RegistryObject<Item> CERTUS_QUARTZ = registerKnife("certus_quartz", DelightfulItemTags.CERTUS_QUARTZ, Modid.AE2);
	public static final RegistryObject<Item> FLUIX = registerItem("fluix_knife", () -> new FluixKnifeItem(props()));

	// Registers a knife, requiring modid
	public static RegistryObject<Item> registerKnife(String name, TagKey<Item> tag, Modid... modid) {
		return registerItem(name + "_knife", () -> new DKnifeItem(tag, DelightfulTiers.get(name), props(), modid));
	}

	// Registers a knife, requiring non-empty ingot tag
	public static RegistryObject<Item> registerIngotKnife(String name, Modid... modid) {
		return registerKnife(name, DelightfulItemTags.ingot(name), modid);
	}

	// Registers a knife, requiring non-empty gem tag
	public static RegistryObject<Item> registerGemKnife(String name, Modid... modid) {
		return registerKnife(name, DelightfulItemTags.gem(name), modid);
	}

	public static Item.Properties props() {
		return (new Item.Properties());
	}

	public static void create() {
	}
}