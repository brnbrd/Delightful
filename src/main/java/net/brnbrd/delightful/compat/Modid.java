package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Modid {
	LOADER(Util.LOADER),
	C(Util.COMMON),
	MC(Util.MC),
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
	AT("atmospheric"),
	ATM("allthemodium"),
	AUT("autumnity"),
	BB("buzzier_bees"),
	BC("brewinandchewin"),
	BG("berry_good"),
	BM("biomemakeover"),
	BOP("biomesoplenty"),
	BS("blue_skies"),
	BTA("botania"),
	BWG("biomeswevegone"),
	CCK("create_central_kitchen"),
	CAC("caverns_and_chasms"),
	CAD("casualness_delight"),
	CD("culturaldelights"),
	COOK("cookielicious"),
	CORN("corn_delight"),
	COS("cosmopolitan"),
	CR("collectorsreap"),
	CRAB("crabbersdelight"),
	CRE("create"),
	CRIT("crittersandcompanions"),
	CSA("create_sa"),
	CT("croptopia"),
	CTD("brewincompatdelight"),
	DA("deep_aether"),
	DIET("diet"),
	DD("deeperdarker"),
	DOLT("dolt_mod_how"),
	DTM("dropthemeat"),
	DUNG("dungeonsdelight"),
	EE("endergetic"),
	ECO("ecologics"),
	EIO("enderio"),
	EP("phantasm"),
	EN("enlightened_end"),
	END("ends_delight"),
	ENDR("endersdelight"),
	ENV("environmental"),
	EXC("extra_compat"),
	EXQ("exquisito"),
	FA("forbidden_arcanus"),
	FAS("farmersassortment"),
	FOR("forestry"),
	FORA("foragersinsight"),
	FR("farmersrespite"),
	FRD("fruitsdelight"),
	FRIGHT("frightsdelight"),
	FU("frozenup"),
	FUNC("functionality"),
	FUS("fusion"),
	GO("goated"),
	GOOD("goodall"),
	HAB("habitat"),
	HAUN("hauntedharvest"),
	HED("hedgehog"),
	HH("hearthandharvest"),
	IN("incubation"),
	LAC("lost_aether_content"),
	LE("lolenderite"),
	LFL("letfishlove"),
	LMFL("letmorefishlove"),
	MB("mythicbotany"),
	MEKT("mekanismtools"),
	MD("miners_delight"),
	MND("mynethersdelight"),
	MOD("moredelight"),
	N("neapolitan"),
	NA("naturalist"),
	NF("nutritious_feast"),
	ND("nethersdelight"),
	NDU("netherdepthsupgrade"),
	NE("nethers_exoticism"),
	NN("nourished_nether"),
	OAD("oresabovediamonds"),
	OD("oceansdelight"),
	OG("oreganized"),
	PEC("peculiars"),
	PN("pneumaticcraft"),
	Q("quark"),
	RA("redstone_arsenal"),
	RC("rootsclassic"),
	RESP("respiteful"),
	RL("rottenleather"),
	RUST("rusticdelight"),
	S("salt"),
	SAS("some_assembly_required"),
	SE("seeds"),
	SEAS("seasonals"),
	SEED("seeddelight"),
	SF("stardew_fishing"),
	SM("sullysmod"),
	SO("simpleores"),
	SOB("sob"),
	SP("spirit"),
	SPWN("spawn"),
	SS("snowyspirit"),
	SUP("supplementaries"),
	TF("twilightforest"),
	TFD("twilightdelight"),
	TH("thermal"),
	THI("thicket"),
	TIDE("tide"),
	TWIG("twigs"),
	UA("upgrade_aquatic"),
	UE("unusualend"),
	UG("undergarden"),
	UGD("undergardendelight"),
	VB("vanillabackport"),
	VD("vintagedelight"),
	VEG("veggiesdelight"),
	VGND("vegandelight"),
	WB("wildberries"),
	WS("windswept"),
	WSD("windswept_delights"),
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

	// modid:item
	public String id(String itemName) {
		return rl(itemName).toString();
	}

	public TagKey<Item> it(String tag) {
		return ItemTags.create(this.rl(tag));
	}

	@Nullable
	public Item item(@NotNull String name) {
		return ForgeRegistries.ITEMS.getValue(this.rl(name));
	}

	public ItemStack itemStack(@NotNull String name) {
		if (this.loaded()) {
			Item i = this.item(name);
			if (i != null) return new ItemStack(i);
		}
		return ItemStack.EMPTY;
	}

	@NotNull
 	public Item item(String name, @NotNull Item backup) {
		if (this.itemExists(name)) {
			Item returnItem = ForgeRegistries.ITEMS.getValue(this.rl(name));
			if (returnItem != null) return returnItem;
		}
		return backup;
	}

	public boolean itemExists(@NotNull String name) {
		return this.loaded() && ForgeRegistries.ITEMS.containsKey(this.rl(name));
	}

	@Nullable
	public Block block(@NotNull String name) {
		return ForgeRegistries.BLOCKS.getValue(this.rl(name));
	}

	@NotNull
	public Block block(String name, @NotNull Block backup) {
		if (this.blockExists(name)) {
			Block returnBlock = ForgeRegistries.BLOCKS.getValue(this.rl(name));
			if (returnBlock != null) return returnBlock;
		}
		return backup;
	}

	public boolean blockExists(@NotNull String name) {
		return this.loaded() && ForgeRegistries.BLOCKS.containsKey(this.rl(name));
	}

	@Nullable
	public final MobEffect effect(@NotNull String name, MobEffect... backup) {
		return Util.effect(this.rl(name), backup);
	}

	@NotNull
	public final MobEffectInstance effectInstance(@NotNull final String name, int duration, int amplifier, @NotNull final MobEffect backup, int backupDur, int backupAmp) {
		final MobEffect effect = this.effect(name, backup);
		return (effect == null || effect == backup) ?
			new MobEffectInstance(backup, backupDur, backupAmp) :
			new MobEffectInstance(effect, duration, amplifier);
	}

	// Uses same duration and amplifier for backup
	@NotNull
	public final MobEffectInstance effectInstance(@NotNull final String name, int duration, int amplifier, @NotNull final MobEffect backup) {
		return this.effectInstance(name, duration, amplifier, backup, duration, amplifier);
	}
}