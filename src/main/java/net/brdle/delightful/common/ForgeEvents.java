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
import net.brdle.delightful.data.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
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
import vectorwing.farmersdelight.common.block.PieBlock;
import java.util.Objects;

@Mod.EventBusSubscriber(modid= Delightful.MODID)
public class ForgeEvents {

	@SubscribeEvent
	public static void burnTime(FurnaceFuelBurnTimeEvent e) {
		if (e.getItemStack().getItem() instanceof FurnaceFuelItem fuel) {
			e.setBurnTime(fuel.getFuelTime());
		}
	}

	// Twilight Forest Compat (and Nether's Exoticism)
	@SubscribeEvent
	public static void fieryToolSetFire(LivingAttackEvent e) {
		if (e.getSource().getEntity() instanceof LivingEntity living &&
			(living.getMainHandItem().is(DelightfulItems.FIERY_KNIFE.get()) || living.getMainHandItem().is(DelightfulItems.KIWANO_KNIFE.get())) &&
			!e.getEntity().fireImmune()) {
			e.getEntity().setSecondsOnFire(1);
		}
	}

	// Twilight Forest Compat
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

	// Right slick slicing an Item from a Block
	@SubscribeEvent (priority = EventPriority.HIGHEST)
	public static void onInteract(PlayerInteractEvent.RightClickBlock e) {
		Level world = e.getLevel();
		BlockPos pos = e.getPos();
		if (e.getEntity().getItemInHand(e.getHand()).is(DelightfulItemTags.SCAVENGING_TOOLS)) {
			BlockState current = world.getBlockState(pos);
			boolean client = world.isClientSide();
			if (current.getBlock() == Blocks.MELON) {
				SlicedMelonBlock sliced = (SlicedMelonBlock) DelightfulBlocks.SLICED_MELON.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (current.getBlock() == Blocks.PUMPKIN && !e.getEntity().isCrouching()) {
				SlicedPumpkinBlock sliced = (SlicedPumpkinBlock) DelightfulBlocks.SLICED_PUMPKIN.get();
				slice(sliced.defaultBlockState(), sliced.getSliceItem(), world, pos, SoundEvents.BAMBOO_BREAK, e, client);
			} else if (ModList.get().isLoaded("frozenup") && ModList.get().isLoaded("frozen_delight") &&
				Util.name(current.getBlock()).equals("truffle_cake")) {
				int currentBites = current.getValue(BlockStateProperties.BITES);
				ItemStack slice = Objects.requireNonNull(Util.item("frozen_delight", "truffle_cake_slice")).getDefaultInstance();
				if (currentBites >= 3) {
					world.removeBlock(pos, false);
					world.gameEvent(e.getEntity(), GameEvent.BLOCK_DESTROY, pos);
					Util.dropOrGive(slice, world, pos, e.getEntity());
					world.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
					e.getEntity().getItemInHand(e.getHand()).hurtAndBreak(1, e.getEntity(), onBroken -> {});
					e.setCancellationResult(InteractionResult.sidedSuccess(client));
					e.setCanceled(true);
					return;
				}
				slice(current.setValue(BlockStateProperties.BITES, currentBites + 1), slice, world, pos, SoundEvents.WOOL_PLACE, e, client);
			}
		}
	}

	// Replaces Block in world, drops Item, cancels interaction event
	public static void slice(BlockState block, ItemStack slice, Level world, BlockPos pos, SoundEvent sound, PlayerInteractEvent.RightClickBlock e, boolean client) {
		if (!client) {
			world.setBlock(pos, block, 2);
			Util.dropOrGive(slice, world, pos, e.getEntity());
			world.playSound(null, pos, sound, SoundSource.PLAYERS, 0.8F, 0.8F);
			e.getEntity().getItemInHand(e.getHand()).hurtAndBreak(1, e.getEntity(), onBroken -> {});
		}
		e.setCancellationResult(InteractionResult.sidedSuccess(client));
		e.setCanceled(true);
	}

	// Right click placing a pie Block using Item
	@SubscribeEvent
	public static void onPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		ItemStack stack = e.getEntity().getItemInHand(e.getHand());
		if (!stack.isEmpty()) {
			BlockPlaceContext context = new BlockPlaceContext(e.getEntity(), e.getHand(), stack, e.getHitVec());
			if (DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() &&
				stack.is(Items.PUMPKIN_PIE)) {
				tryPlacePie((PieBlock) DelightfulBlocks.PUMPKIN_PIE.get(), context, e);
			} else if (ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				DelightfulConfig.verify(ArsNouveauCompat.slice) &&
					isPie(stack, ArsNouveauCompat.modid, ArsNouveauCompat.pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.SOURCE_BERRY_PIE.get(), context, e);
			} else if (ModList.get().isLoaded(BYGCompat.modid)) {
				if (DelightfulConfig.verify(BYGCompat.blueberry_pie_slice) &&
					isPie(stack, BYGCompat.modid, BYGCompat.blueberry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.BLUEBERRY_PIE.get(), context, e);
				} else if (DelightfulConfig.verify(BYGCompat.crimson_berry_pie_slice) &&
					isPie(stack, BYGCompat.modid, BYGCompat.crimson_berry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.CRIMSON_BERRY_PIE.get(), context, e);
				} else if (DelightfulConfig.verify(BYGCompat.green_apple_pie_slice) &&
					isPie(stack, BYGCompat.modid, BYGCompat.green_apple_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.GREEN_APPLE_PIE.get(), context, e);
				} else if (DelightfulConfig.verify(BYGCompat.nightshade_berry_pie_slice) &&
					isPie(stack, BYGCompat.modid, BYGCompat.nightshade_berry_pie)) {
					tryPlacePie((PieBlock) DelightfulBlocks.NIGHTSHADE_BERRY_PIE.get(), context, e);
				}
			}
		}
	}

	// Tries placement of pie, may cancel interaction event
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

	// Places pie Block in world using Item
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

	// Cancels right clicking overhauled pie items to eat them normally
	@SubscribeEvent
	public static void onPieOverhaul(PlayerInteractEvent.RightClickItem e) {
		ItemStack stack = e.getEntity().getItemInHand(e.getHand());
		if ((DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() && stack.is(Items.PUMPKIN_PIE)) ||
			(ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				stack.is(Util.item(ArsNouveauCompat.modid, ArsNouveauCompat.pie)) &&
				DelightfulConfig.verify(ArsNouveauCompat.slice)) ||
			(ModList.get().isLoaded(BYGCompat.modid) &&
				((isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.blueberry_pie) &&
					DelightfulConfig.verify(BYGCompat.blueberry_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.crimson_berry_pie) &&
						DelightfulConfig.verify(BYGCompat.crimson_berry_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.green_apple_pie) &&
						DelightfulConfig.verify(BYGCompat.green_apple_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.nightshade_berry_pie) &&
						DelightfulConfig.verify(BYGCompat.nightshade_berry_pie_slice))))) {
			e.setCancellationResult(InteractionResult.FAIL);
			e.setCanceled(true);
		}
	}

	// Adds "placeable" tooltip to compat pies
	@SubscribeEvent
	public static void onPieTooltip(ItemTooltipEvent e) {
		if ((e.getItemStack().is(Items.PUMPKIN_PIE) && DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get()) ||
			(e.getItemStack().getItem() instanceof BlockItem block && block.getBlock() instanceof PieBlock) ||
			(ModList.get().isLoaded(ArsNouveauCompat.modid) &&
				isPie(e.getItemStack(), ArsNouveauCompat.modid, ArsNouveauCompat.pie) &&
				DelightfulConfig.verify(ArsNouveauCompat.slice)) ||
			(ModList.get().isLoaded(BYGCompat.modid) &&
				((isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.blueberry_pie) &&
					DelightfulConfig.verify(BYGCompat.blueberry_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.crimson_berry_pie) &&
						DelightfulConfig.verify(BYGCompat.crimson_berry_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.green_apple_pie) &&
						DelightfulConfig.verify(BYGCompat.green_apple_pie_slice)) ||
					(isPie(e.getItemStack(), BYGCompat.modid, BYGCompat.nightshade_berry_pie) &&
						DelightfulConfig.verify(BYGCompat.nightshade_berry_pie_slice))))) {
			e.getToolTip().add(Component.literal("Placeable").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC));
		}
	}

	private static boolean isPie(ItemStack stack, String modid, String pie) {
		return stack.is(Util.item(modid, pie));
	}
}