package net.brnbrd.delightful.common.item.food;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.utility.MathUtils;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RockCandyItem extends DConsumableItem {
	private static final List<MobEffectInstance> FED_EFFECTS = ImmutableList.of(
		new MobEffectInstance(MobEffects.WEAKNESS, 200, 0)
	);

	public RockCandyItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.GEMS_ROSE_QUARTZ;
	}

	public static List<MobEffectInstance> getFeedEffects() {
		List<MobEffectInstance> ALL_EFFECTS = Lists.newArrayList(Util.getFoodEffects(Nutrition.ROCK_CANDY));
		ALL_EFFECTS.addAll(FED_EFFECTS);
		return ALL_EFFECTS;
	}

	// Can be fed to tamed animals
	@Override
	public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity target, @NotNull InteractionHand hand) {
		boolean clientSide = player.level().isClientSide();
		if (
			!clientSide &&
			target.isAlive() &&
			target.isAffectedByPotions() &&
			target instanceof TamableAnimal tame &&
			tame.isTame()
		) {
			Util.addEffects(target, RockCandyItem.getFeedEffects());
			target.level().playSound(null, target.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
			for (int i = 0; i < 5; ++i) {
				double xSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				double ySpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				double zSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				target.level().addParticle(ModParticleTypes.STAR.get(), target.getRandomX(1.0D), target.getRandomY() + 0.5D, target.getRandomZ(1.0D), xSpeed, ySpeed, zSpeed);
			}
			player.getCooldowns().addCooldown(this, 40);
			if (!player.getAbilities().instabuild) {
				stack.shrink(1);
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.STICK));
			}
		}
		return InteractionResult.sidedSuccess(clientSide);
	}

	// Code adapted from: https://github.com/vectorwing/FarmersDelight/blob/1.20/src/main/java/vectorwing/farmersdelight/common/item/DogFoodItem.java
	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag isAdvanced) {
		super.appendHoverText(stack, level, comps, isAdvanced);
		if (this.enabled()) {
			comps.add(Component.empty());
			comps.add(Util.tooltip("rose_rock_candy.when_feeding").withStyle(ChatFormatting.GRAY));
			for (MobEffectInstance effectInstance : RockCandyItem.getFeedEffects()) {
				MutableComponent effectDescription = Component.literal(" ");
				effectDescription.append(Component.translatable(effectInstance.getDescriptionId()));
				MobEffect effect = effectInstance.getEffect();
				if (effectInstance.getAmplifier() > 0) {
					effectDescription.append(" ").append(Component.translatable("potion.potency." + effectInstance.getAmplifier()));
				}
				if (effectInstance.getDuration() > 20) {
					effectDescription.append(" (").append(MobEffectUtil.formatDuration(effectInstance, 1.0F)).append(")");
				}
				comps.add(effectDescription.withStyle(effect.getCategory().getTooltipFormatting()));
			}
		}
	}

	@Override
	public int getUseDuration(@NotNull ItemStack stack) {
		return 48;
	}

	@Override
	public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player) {
		return !player.isCreative();
	}
}