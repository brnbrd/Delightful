package net.brnbrd.delightful.common.item.food;

import com.mojang.datafixers.util.Pair;
import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RockCandyItem extends DConsumableItem {
	public RockCandyItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public @Nullable TagKey<Item> getDependencyTag() {
		return DelightfulItemTags.GEMS_ROSE_QUARTZ;
	}

	@Override
	public boolean hurtEnemy(@NotNull final ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		boolean sup = super.hurtEnemy(stack, target, attacker);
		if (!attacker.level().isClientSide()) {
			target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
			Util.addEffects(target, Nutrition.ROCK_CANDY.getEffects().stream().map(Pair::getFirst).toList());
			if (attacker instanceof Player player) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.STICK));
				player.getCooldowns().addCooldown(this, 40);
			}
			stack.shrink(1);
			return true;
		}
		return sup;
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