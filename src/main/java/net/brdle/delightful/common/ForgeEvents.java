package net.brdle.delightful.common;

import net.brdle.delightful.Delightful;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.block.DelightfulBlocks;
import net.brdle.delightful.common.block.SlicedMelonBlock;
import net.brdle.delightful.common.block.SlicedPumpkinBlock;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.DelightfulItems;
import net.brdle.delightful.common.item.FurnaceFuelItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
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
				var remap = new ResourceLocation(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.BLOCKS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.BLOCKS.getValue(remap));
				} else {
					mapping.warn();
				}
			}
		}
		for (var mapping : e.getAllMappings(ForgeRegistries.ITEMS.getRegistryKey())) {
			if (portedMods.contains(mapping.getKey().getNamespace())) {
				var remap = new ResourceLocation(Delightful.MODID, mapping.getKey().getPath());
				if (ForgeRegistries.ITEMS.containsKey(remap)) {
					mapping.remap(ForgeRegistries.ITEMS.getValue(remap));
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
		}
		e.setCancellationResult(InteractionResult.sidedSuccess(client));
		e.setCanceled(true);
	}

	@SubscribeEvent
	public static void onPumpkinPieOverhaul(PlayerInteractEvent.RightClickBlock e) {
		Level world = e.getLevel();
		BlockPos pos = e.getPos();
		InteractionHand hand = e.getHand();
		ItemStack stack = e.getEntity().getItemInHand(hand);
		if (DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() && stack.is(Items.PUMPKIN_PIE)) {
			e.setCanceled(true);
			Player player = e.getEntity();
			if (world.getBlockState(pos).canBeReplaced(
				new BlockPlaceContext(player, hand, stack, e.getHitVec()))) {
				placePie(pos, player, stack, world);
				e.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide()));
			} else if (world.getBlockState(pos.above()).canBeReplaced(
				new BlockPlaceContext(player, hand, stack, e.getHitVec()))) {
				placePie(pos.above(), player, stack, world);
				e.setCancellationResult(InteractionResult.sidedSuccess(world.isClientSide()));
			} else {
				e.setCancellationResult(InteractionResult.FAIL);
			}
		}
	}

	public static void placePie(BlockPos pos, Player player, ItemStack stack, Level world) {
		if (!player.getAbilities().instabuild) {
			stack.shrink(1);
		}
		BlockState state = DelightfulBlocks.PUMPKIN_PIE.get().defaultBlockState();
		world.setBlock(pos, state, 2);
		state.getBlock().setPlacedBy(world, pos, state, player, stack);
		world.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, state));
		world.playSound(null, pos, SoundEvents.WOOL_PLACE, SoundSource.PLAYERS, 0.8F, 0.8F);
	}

	@SubscribeEvent
	public static void onPumpkinPieOverhaul(PlayerInteractEvent.RightClickItem e) {
		e.setCanceled(DelightfulConfig.PUMPKIN_PIE_OVERHAUL.get() && e.getEntity().getItemInHand(e.getHand()).is(Items.PUMPKIN_PIE));
	}
}