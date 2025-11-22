package net.brnbrd.delightful.common.fluid;

import net.brnbrd.delightful.Delightful;
import net.brnbrd.delightful.compat.brewinandchewin.AgedRoeFluidType;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DelightfulFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Delightful.MODID);
	public static final DeferredRegister<FluidType> TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Delightful.MODID);

	public static final RegistryObject<FluidType> AGED_ROE_TYPE = TYPES.register("aged_roe_type",
		AgedRoeFluidType::new);
	public static final RegistryObject<FlowingFluid> AGED_ROE = FLUIDS.register("aged_roe",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.AGED_ROE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_AGED_ROE = FLUIDS.register("flowing_aged_roe",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.AGED_ROE_PROPERTIES));
	public static final ForgeFlowingFluid.Properties AGED_ROE_PROPERTIES = new ForgeFlowingFluid.Properties(
		AGED_ROE_TYPE,
		AGED_ROE,
		FLOWING_AGED_ROE
	);

	public static final RegistryObject<FluidType> MATCHA_LATTE_TYPE = TYPES.register("matcha_latte_type",
		() -> new DFluidType(0xff7FA036));
	public static final RegistryObject<FlowingFluid> MATCHA_LATTE = FLUIDS.register("matcha_latte",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.MATCHA_LATTE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_MATCHA_LATTE = FLUIDS.register("flowing_matcha_latte",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.MATCHA_LATTE_PROPERTIES));
	public static final ForgeFlowingFluid.Properties MATCHA_LATTE_PROPERTIES = new ForgeFlowingFluid.Properties(
		MATCHA_LATTE_TYPE,
		MATCHA_LATTE,
		FLOWING_MATCHA_LATTE
	);

	public static final RegistryObject<FluidType> ENDER_NECTAR_TYPE = TYPES.register("ender_nectar_type",
		() -> new DFluidType(0xff316364));
	public static final RegistryObject<FlowingFluid> ENDER_NECTAR = FLUIDS.register("ender_nectar",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.ENDER_NECTAR_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_ENDER_NECTAR = FLUIDS.register("flowing_ender_nectar",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.ENDER_NECTAR_PROPERTIES));
	public static final ForgeFlowingFluid.Properties ENDER_NECTAR_PROPERTIES = new ForgeFlowingFluid.Properties(
		ENDER_NECTAR_TYPE,
		ENDER_NECTAR,
		FLOWING_ENDER_NECTAR
	);
	
	public static final RegistryObject<FluidType> AZALEA_TEA_TYPE = TYPES.register("azalea_tea_type",
		() -> new DFluidType(0xffd3619c));
	public static final RegistryObject<FlowingFluid> AZALEA_TEA = FLUIDS.register("azalea_tea",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.AZALEA_TEA_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_AZALEA_TEA = FLUIDS.register("flowing_azalea_tea",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.AZALEA_TEA_PROPERTIES));
	public static final ForgeFlowingFluid.Properties AZALEA_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(
		AZALEA_TEA_TYPE,
		AZALEA_TEA,
		FLOWING_AZALEA_TEA
	);

	public static final RegistryObject<FluidType> LAVENDER_TEA_TYPE = TYPES.register("lavender_tea_type",
		() -> new DFluidType(0xff9e79a2));
	public static final RegistryObject<FlowingFluid> LAVENDER_TEA = FLUIDS.register("lavender_tea",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.LAVENDER_TEA_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_LAVENDER_TEA = FLUIDS.register("flowing_lavender_tea",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.LAVENDER_TEA_PROPERTIES));
	public static final ForgeFlowingFluid.Properties LAVENDER_TEA_PROPERTIES = new ForgeFlowingFluid.Properties(
		LAVENDER_TEA_TYPE,
		LAVENDER_TEA,
		FLOWING_LAVENDER_TEA
	);

	public static final RegistryObject<FluidType> PRICKLY_PEAR_JUICE_TYPE = TYPES.register("prickly_pear_juice_type",
		() -> new DFluidType(0xffb83546));
	public static final RegistryObject<FlowingFluid> PRICKLY_PEAR_JUICE = FLUIDS.register("prickly_pear_juice",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.PRICKLY_PEAR_JUICE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_PRICKLY_PEAR_JUICE = FLUIDS.register("flowing_prickly_pear_juice",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.PRICKLY_PEAR_JUICE_PROPERTIES));
	public static final ForgeFlowingFluid.Properties PRICKLY_PEAR_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
		PRICKLY_PEAR_JUICE_TYPE,
		PRICKLY_PEAR_JUICE,
		FLOWING_PRICKLY_PEAR_JUICE
	);

	public static final RegistryObject<FluidType> LONG_PRICKLY_PEAR_JUICE_TYPE = TYPES.register("long_prickly_pear_juice_type",
		() -> new DFluidType(0xffb83546));
	public static final RegistryObject<FlowingFluid> LONG_PRICKLY_PEAR_JUICE = FLUIDS.register("long_prickly_pear_juice",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.LONG_PRICKLY_PEAR_JUICE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_LONG_PRICKLY_PEAR_JUICE = FLUIDS.register("flowing_long_prickly_pear_juice",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.LONG_PRICKLY_PEAR_JUICE_PROPERTIES));
	public static final ForgeFlowingFluid.Properties LONG_PRICKLY_PEAR_JUICE_PROPERTIES = new ForgeFlowingFluid.Properties(
		LONG_PRICKLY_PEAR_JUICE_TYPE,
		LONG_PRICKLY_PEAR_JUICE,
		FLOWING_LONG_PRICKLY_PEAR_JUICE
	);

	public static final RegistryObject<FluidType> EGGNOG_TYPE = TYPES.register("eggnog_type",
		() -> new DFluidType(0xffD8D5B6));
	public static final RegistryObject<FlowingFluid> EGGNOG = FLUIDS.register("eggnog",
		() -> new ForgeFlowingFluid.Source(DelightfulFluids.EGGNOG_PROPERTIES));
	public static final RegistryObject<FlowingFluid> FLOWING_EGGNOG = FLUIDS.register("flowing_eggnog",
		() -> new ForgeFlowingFluid.Flowing(DelightfulFluids.EGGNOG_PROPERTIES));
	public static final ForgeFlowingFluid.Properties EGGNOG_PROPERTIES = new ForgeFlowingFluid.Properties(
		EGGNOG_TYPE,
		EGGNOG,
		FLOWING_EGGNOG
	);

	public static void create(IEventBus bus) {
		FLUIDS.register(bus);
		TYPES.register(bus);
	}
}