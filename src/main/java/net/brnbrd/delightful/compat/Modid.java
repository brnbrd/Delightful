package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Modid {
	LOADER(Util.LOADER),
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
	BOP("biomesoplenty"),
	BTA("botania"),
	BWG("biomeswevegone"),
	C("create"),
	CCK("create_central_kitchen"),
	CAD("casualness_delight"),
	CD("culturaldelights"),
	COOK("cookielicious"),
	COS("cosmopolitan"),
	CR("collectorsreap"),
	CRAB("crabbersdelight"),
	CSA("create_sa"),
	CT("croptopia"),
	DA("deep_aether"),
	DD("deeperdarker"),
	DTM("dropthemeat"),
	EE("endergetic"),
	ECO("ecologics"),
	EIO("enderio"),
	EP("phantasm"),
	EN("enlightened_end"),
	ENV("environmental"),
	EXC("extra_compat"),
	FA("forbidden_arcanus"),
	FOR("forestry"),
	FR("farmersrespite"),
	FRD("fruitsdelight"),
	FRIGHT("frightsdelight"),
	FU("frozenup"),
	FUS("fusion"),
	GO("goated"),
	HAB("habitat"),
	HH("hearthandharvest"),
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
	RA("redstone_arsenal"),
	RC("rootsclassic"),
	RF("respiteful"),
	RL("rottenleather"),
	S("salt"),
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
	VGND("vegandelight"),
	WB("wildberries"),
	WS("windswept"),
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
	public MobEffect effect(@NotNull String name) {
		return Util.effect(this.rl(name));
	}

	public MobEffect effect(@NotNull String name, MobEffect backup) {
		return Util.effect(this.rl(name), backup);
	}
}