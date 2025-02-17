package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;

public enum Modid {
	LOADER(Util.LOADER),
	FD(FarmersDelight.MODID),
	D(Delightful.MODID),
	AA("additionaladditions"),
	AAE("ancient_aether"),
	AD("abnormals_delight"),
	AE("aether"),
	AE2("ae2"),
	AER("aether_redux"),
	AN("ars_nouveau"),
	AND("arsdelight"),
	AS("appleskin"),
	ATM("allthemodium"),
	BB("buzzier_bees"),
	BC("brewinandchewin"),
	BG("berry_good"),
	BOP("biomesoplenty"),
	BTA("botania"),
	BWG("biomeswevegone"),
	C("create"),
	CCK("create_central_kitchen"),
	CAD("casualness_delight"),
	CD("culturaldelights"),
	CR("collectorsreap"),
	CRAB("crabbersdelight"),
	CRD("cratedelight"),
	CSA("create_sa"),
	CT("croptopia"),
	DA("deep_aether"),
	DD("deeperdarker"),
	DTM("dropthemeat"),
	ECO("ecologics"),
	EP("phantasm"),
	EN("enlightened_end"),
	ENV("environmental"),
	FA("forbidden_arcanus"),
	FR("farmersrespite"),
	FRD("fruitsdelight"),
	FRIGHT("frightsdelight"),
	FU("frozenup"),
	FUD("frozen_delight"),
	FUS("fusion"),
	GO("goated"),
	HAB("habitat"),
	IN("incubation"),
	LAC("lost_aether_content"),
	LE("lolenderite"),
	MB("mythicbotany"),
	MEKT("mekanismtools"),
	MD("miners_delight"),
	MND("mynethersdelight"),
	MOD("moredelight"),
	N("neapolitan"),
	NA("naturalist"),
	NF("nutritious_feast"),
	ND("nethersdelight"),
	NE("nethers_exoticism"),
	NN("nourished_nether"),
	OAD("oresabovediamonds"),
	OG("oreganized"),
	Q("quark"),
	RC("rootsclassic"),
	RF("respiteful"),
	RL("rottenleather"),
	SAS("some_assembly_required"),
	SE("seeds"),
	SM("sullysmod"),
	SO("simpleores"),
	SP("spirit"),
	SUP("supplementaries"),
	TF("twilightforest"),
	TFD("twilightdelight"),
	TH("thermal"),
	UA("upgrade_aquatic"),
	UE("unusualend"),
	UG("undergarden"),
	UGD("undergardendelight"),
	VD("vintagedelight"),
	WB("wildberries"),
	YH("youkaishomecoming");

	@NotNull private final String id;
	Modid(@NotNull final String id) {
		this.id = id;
	}

	public @NotNull String get() {
		return this.id;
	}

	public boolean loaded() {
		return Mods.stringLoaded(this.get());
	}

	public ResourceLocation rl(String path) {
		return Util.rl(this, path);
	}

	public TagKey<Item> it(String tag) {
		return ItemTags.create(this.rl(tag));
	}
}