package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.compat.Mods;
import net.brnbrd.delightful.compat.UnusualEndCompat;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ChorusMuffinItem extends CompatConsumableItem {
	public ChorusMuffinItem(Properties properties) {
		super(properties, true, false, Mods.UE);
	}

	@SuppressWarnings("NoTranslation")
	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (this.enabled()) {
			comps.add(Component.translatable("lore.unusualend.clear_infection").withStyle(ChatFormatting.BLUE));
		}
		super.appendHoverText(stack, level, comps, pIsAdvanced);
	}

	@Override
	public void affectConsumer(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity consumer) {
		super.affectConsumer(stack, level, consumer);
		if (this.enabled()) {
			MobEffect infection = UnusualEndCompat.getEnderInfection();
			if (consumer.hasEffect(infection)) {
				consumer.removeEffect(infection);
			}
		}
	}
}