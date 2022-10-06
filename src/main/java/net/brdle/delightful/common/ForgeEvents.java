package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.SlicedMelonBlock;
import net.brdle.delightful.common.block.SlicedPumpkinBlock;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.brdle.delightful.compat.ArsNouveauCompat;
import net.brdle.delightful.compat.BYGCompat;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import java.util.List;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	private static final List<String> portedMods = List.of("coppersdelight", "steelsdelight", "enderitesdelight");

	// Remaps any block or item ids to use "delightful" namespace
	@SubscribeEvent
	public static void onMissingBlockMappings(MissingMappingsEvent e) {
		for (var mapping : e.getAllMappings(ForgeRegistries.BLOCKS.getRegistryKey())) {
			if (portedMods.contains(mapping.getKey().getNamespace())) {
				var remap = Util.rl(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.BLOCKS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
				} else {
					mapping.warn();
				}
			}
		}
		for (var mapping : e.getAllMappings(ForgeRegistries.ITEMS.getRegistryKey())) {
			if (portedMods.contains(mapping.getKey().getNamespace())) {
				var remap = Util.rl(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.ITEMS.containsKey(remap)) {
					mapping.remap(Util.item(remap));
				} else {
					mapping.warn();
				}
			} else if (mapping.getKey().getNamespace().equals(Delightful.MODID) &&
				mapping.getKey().getPath().equals("salmonberry")) {
				mapping.remap(DelightfulItems.SALMONBERRIES.get());
			}
		}
	}

	@SubscribeEvent
	public static void burnTime(FurnaceFuelBurnTimeEvent e) {
		if (e.getItemStack().getItem() instanceof FurnaceFuelItem fuel) {
			e.setBurnTime(fuel.getFuelTime());
		}
	}

	@SubscribeEvent
	public static void fieryToolSetFire(LivingAttackEvent e) {
		if (e.getSource().getEntity() instanceof LivingEntity living &&
			living.getMainHandItem().is(DelightfulItems.FIERY_KNIFE.get()) &&
			!e.getEntity().fireImmune()) {
			e.getEntity().setSecondsOnFire(1);
		}
	}

	@SubscribeEvent
	public static void onKnightmetalToolDamage(LivingHurtEvent e) {
		LivingEntity target = e.getEntity();
		if (!target.getLevel().isClientSide() && e.getSource().getDirectEntity() instanceof LivingEntity living) {
			ItemStack weapon = living.getMainHandItem();
			if (!weapon.isEmpty()) {
				if (target.getArmorValue() > 0 && weapon.is(DelightfulItems.KNIGHTMETAL_KNIFE.get())) {
					if (target.getArmorCoverPercentage() > 0) {
						int moreBonus = (int) (2 * target.getArmorCoverPercentage());
						e.setAmount(e.getAmount() + moreBonus);
					} else {
						e.setAmount(e.getAmount() + 2);
					}
					((ServerLevel) target.getLevel()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
				}
			}
		}
	}

	@SubscribeEvent
	public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
		Level world = e.getLevel();
		BlockPos pos = e.getPos();
		if (e.getEntity().getItemInHand(e.getHand()).is(ForgeTags.TOOLS_KNIVES)) {
			Block current = world.getBlockState(pos).getBlock();
			boolean client = world.isClientSide();
			if (current == Blocks.MELON) {
				SlicedMelonBlock sliced = (SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get();
				slice(sliced, sliced.getSliceItem(), world, pos, e, client);
			} else if (current == Blocks.PUMPKIN && !e.getEntity().isCrouching()) {
				SlicedPumpkinBlock sliced = (SlicedPumpkinBlock) DelightfulBlocks.SLICED_PUMPKIN.get();
				slice(sliced, sliced.getSliceItem(), world, pos, e, client);
			}
		}
	}

	public static void slice(Block block, ItemStack slice, Level world, BlockPos pos, PlayerInteractEvent.RightClickBlock e, boolean client) {
		if (!client) {
			world.setBlock(pos, block.defaultBlockState(), 2);
			Util.dropOrGive(slice, world, pos, e.getEntity());
			world.playSound(null, pos, SoundEvents.BAMBOO_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
			e.getEntity().getItemInHand(e.getHand()).hurtAndBreak(1, e.getEntity(), onBroken -> {});
		}
		e.setCancellationResult(InteractionResult.sidedSuccess(client));
		e.setCanceled(true);
	}

	@SubscribeEvent
	public static void onPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		ItemStack stack = e.getEntity().getItemInHand(e.getHand());
		if (!stack.isEmpty()) {
			BlockPlaceContext context = new BlockPlaceContext(e.getEntity(), e.getHand(), stack, e.getHitVec());
			if (DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() &&
				stack.is(Items.PUMPKIN_PIE)) {
					tryPlacePie((PieBlock) DelightfulBlocks.PUMPKIN_PIE.get(), context, e);
			} else if (ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				DelightfulConfig.stuff.get(ArsNouveauCompat.slice).get() &&
					isPie(stack, ArsNouveauCompat.modid, ArsNouveauCompat.pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.SOURCE_BERRY_PIE.get(), context, e);
			} else if (ModList.get().isLoaded(BYGCompat.modid)) {
				if (DelightfulConfig.stuff.get(BYGCompat.blueberry_pie_slice).get() &&
					isPie(stack, BYGCompat.modid, BYGCompat.blueberry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.BLUEBERRY_PIE.get(), context, e);
				} else if (DelightfulConfig.stuff.get(BYGCompat.crimson_berry_pie_slice).get() &&
					isPie(stack, BYGCompat.modid, BYGCompat.crimson_berry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.CRIMSON_BERRY_PIE.get(), context, e);
				} else if (DelightfulConfig.stuff.get(BYGCompat.green_apple_pie_slice).get() &&
					isPie(stack, BYGCompat.modid, BYGCompat.green_apple_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.GREEN_APPLE_PIE.get(), context, e);
				} else if (DelightfulConfig.stuff.get(BYGCompat.nightshade_berry_pie_slice).get() &&
					isPie(stack, BYGCompat.modid, BYGCompat.nightshade_berry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.NIGHTSHADE_BERRY_PIE.get(), context, e);
				}
			}
		}
	}

	public static void tryPlacePie(PieBlock pie, BlockPlaceContext context, PlayerInteractEvent.RightClickBlock e) {
		e.setUseItem(Event.Result.DENY);
		e.setCanceled(true);
		if (e.getUseBlock() != Event.Result.ALLOW) {
			if (placePie(pie, context)) {
				e.setCancellationResult(InteractionResult.sidedSuccess(context.getLevel().isClientSide()));
			}
		} else {
			e.setUseBlock(Event.Result.ALLOW);
		}
	}

	public static boolean placePie(PieBlock pie, BlockPlaceContext context) {
		if (context.canPlace() &&
			context.getLevel().getBlockState(context.getClickedPos().below()).getMaterial().isSolid()) {
			BlockState piestate = pie.getStateForPlacement(context);
			if (!context.getPlayer().getAbilities().instabuild) {
				context.getItemInHand().shrink(1);
			}
			context.getLevel().setBlock(context.getClickedPos(), piestate, 2);
			piestate.getBlock().setPlacedBy(context.getLevel(), context.getClickedPos(), piestate, context.getPlayer(), context.getItemInHand());
			context.getLevel().gameEvent(GameEvent.BLOCK_PLACE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), piestate));
			context.getLevel().playSound(null, context.getClickedPos(), SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
			return true;
		}
		return false;
	}

	@SubscribeEvent
	public static void onPieOverhaul(PlayerInteractEvent.RightClickItem e) {
		ItemStack stack = e.getEntity().getItemInHand(e.getHand());
		if ((DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() && stack.is(Items.PUMPKIN_PIE)) ||
			(ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				stack.is(Util.item(ArsNouveauCompat.modid, ArsNouveauCompat.pie)) &&
				DelightfulConfig.stuff.get(ArsNouveauCompat.slice).get()) ||
			(ModList.get().isLoaded(BYGCompat.modid) &&
				((isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.blueberry_pie) &&
					DelightfulConfig.stuff.get(BYGCompat.blueberry_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.crimson_berry_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.crimson_berry_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.green_apple_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.green_apple_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.nightshade_berry_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.nightshade_berry_pie_slice).get())))) {
			e.setCancellationResult(InteractionResult.FAIL);
			e.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onPieTooltip(ItemTooltipEvent e) {
		if ((e.getItemStack().is(Items.PUMPKIN_PIE) && DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get()) ||
			(e.getItemStack().getItem() instanceof BlockItem block && block.getBlock() instanceof PieBlock) ||
			(ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				isPie(e.getItemStack(), ArsNouveauCompat.modid, ArsNouveauCompat.pie) &&
				DelightfulConfig.stuff.get(ArsNouveauCompat.slice).get()) ||
			(ModList.get().isLoaded(BYGCompat.modid) &&
				((isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.blueberry_pie) &&
					DelightfulConfig.stuff.get(BYGCompat.blueberry_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.crimson_berry_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.crimson_berry_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.green_apple_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.green_apple_pie_slice).get()) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.nightshade_berry_pie) &&
						DelightfulConfig.stuff.get(BYGCompat.nightshade_berry_pie_slice).get())))) {
			e.getToolTip().add(Component.literal("Placeable").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
		}
	}

	private static boolean isPie(ItemStack stack, String modid, String pie) {
		return stack.is(Util.item(modid, pie));
	}

	/*@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBookTooltip(ItemTooltipEvent e) {
		ItemStack stack = e.getItemStack();
		if (stack.getItem() instanceof EnchantedBookItem book) {
			String modid = book.getCreatorModId(stack);
			List<Component> tooltip = e.getToolTip();
			if (tooltip.get(tooltip.size() - 1).getString().strip().equals("minecraft")) {
				e.getToolTip().set(tooltip.size() - 1, Component.translatable(ModList.get().getModFileById(modid).getMods().get(0).getDisplayName()));
			}
		}
	}*/
}