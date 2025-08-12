package net.brnbrd.delightful.common.fluid;

import net.brnbrd.delightful.Delightful;
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

	public static void create(IEventBus bus) {
		FLUIDS.register(bus);
		TYPES.register(bus);
	}
}