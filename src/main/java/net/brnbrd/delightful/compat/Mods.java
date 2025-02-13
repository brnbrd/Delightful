package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.Util;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.FarmersDelight;
import java.util.Optional;
import java.util.function.Supplier;

public class Mods {
	public static final String AA = "additionaladditions";
	public static final String AD = "abnormals_delight";
	public static final String AE = "aether";
	public static final String AE2 = "ae2";
	public static final String AER = "aether_redux";
	public static final String AN = "ars_nouveau";
	public static final String AND = "arsdelight";
	public static final String AS = "appleskin";
	public static final String BB = "buzzier_bees";
	public static final String BC = "brewinandchewin";
	public static final String BG = "berry_good";
	public static final String BOP = "biomesoplenty";
	public static final String BTA = "botania";
	public static final String BWG = "biomeswevegone";
	public static final String C = "create";
	public static final String CCK = "create_central_kitchen";
	public static final String CAD = "casualness_delight";
	public static final String CD = "culturaldelights";
	public static final String CR = "collectorsreap";
	public static final String CRAB = "crabbersdelight";
	public static final String CT = "croptopia";
	public static final String DD = "deeperdarker";
	public static final String ECO = "ecologics";
	public static final String EP = "phantasm";
	public static final String EN = "enlightened_end";
	public static final String ENV = "environmental";
	public static final String FA = "forbidden_arcanus";
	public static final String FR = "farmersrespite";
	public static final String FRD = "fruitsdelight";
	public static final String FRIGHT = "frightsdelight";
	public static final String FU = "frozenup";
	public static final String FUD = "frozen_delight";
	public static final String FUS = "fusion";
	public static final String HAB = "habitat";
	public static final String IN = "incubation";
	public static final String LE = "lolenderite";
	public static final String MEKT = "mekanismtools";
	public static final String MD = "miners_delight";
	public static final String MND = "mynethersdelight";
	public static final String MOD = "moredelight";
	public static final String N = "neapolitan";
	public static final String NA = "naturalist";
	public static final String ND = "nethersdelight";
	public static final String Q = "quark";
	public static final String RC = "rootsclassic";
	public static final String RF = "respiteful";
	public static final String RL = "rottenleather";
	public static final String SAS = "some_assembly_required";
	public static final String SM = "sullysmod";
	public static final String SO = "simpleores";
	public static final String SUP = "supplementaries";
	public static final String TF = "twilightforest";
	public static final String TFD = "twilightdelight";
	public static final String TH = "thermal";
	public static final String UA = "upgrade_aquatic";
	public static final String UE = "unusualend";
	public static final String UG = "undergarden";
	public static final String UGD = "undergardendelight";
	public static final String VD = "vintagedelight";
	public static final String WB = "wildberries";
	public static final String YH = "youkaishomecoming";

	// A single mod must be loaded
	public static boolean loaded(@NotNull String modid) {
		return (
			modid.equals(Delightful.MODID) ||
			modid.equals(FarmersDelight.MODID) ||
			ModList.get().isLoaded(modid)
		);
	}

	// Can check for a single mod (strategy ignored) or multiple mods
	public static boolean loaded(Strategy strategy, @NotNull String... modids) {
		if (modids.length < 1) return true;
		return switch (strategy) {
			case AND -> { // All must be loaded
				for (String mod : modids) if (!loaded(mod)) yield false;
				yield true;
			}
			case OR -> { // Only one must be loaded
				for (String mod : modids) if (loaded(mod)) yield true;
				yield false;
			}
		};
	}

	public static Supplier<MobEffect> getGreenTeaEffect() {
		if (loaded(RF)) {
			Optional<Holder<MobEffect>> vitality = ForgeRegistries.MOB_EFFECTS.getHolder(Util.rl(Mods.RF, "vitality"));
			if (vitality.isPresent()) {
				return vitality.get();
			}
		} else if (loaded(Mods.YH)) {
			Optional<Holder<MobEffect>> polyphenols = ForgeRegistries.MOB_EFFECTS.getHolder(Util.rl(Mods.YH, "tea_polyphenols"));
			if (polyphenols.isPresent()) {
				return polyphenols.get();
			}
		}
		return () -> MobEffects.REGENERATION;
	}

	public static Supplier<MobEffect> getCaffeinated() {
		if (loaded(FR)) {
			Optional<Holder<MobEffect>> caffeinated = ForgeRegistries.MOB_EFFECTS.getHolder(Util.rl(Mods.FR, "caffeinated"));
			if (caffeinated.isPresent()) {
				return caffeinated.get();
			}
		} else if (loaded(Mods.YH)) {
			Optional<Holder<MobEffect>> sober = ForgeRegistries.MOB_EFFECTS.getHolder(Util.rl(Mods.YH, "sober"));
			if (sober.isPresent()) {
				return sober.get();
			}
		}
		return () -> MobEffects.DIG_SPEED;
	}
}