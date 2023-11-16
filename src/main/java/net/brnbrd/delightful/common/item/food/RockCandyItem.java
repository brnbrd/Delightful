package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class RockCandyItem extends DConsumableItem {
	public RockCandyItem(Properties properties) {
		super(properties, true, false);
	}

	@Override
	public boolean enabled() {
		return this.isTag() && Util.enabled(this);
	}

	public boolean isTag() {
		ITagManager<Item> tags = ForgeRegistries.ITEMS.tags();
		return tags != null && tags.isKnownTagName(DelightfulItemTags.GEMS_ROSE_QUARTZ) && !tags.getTag(DelightfulItemTags.GEMS_ROSE_QUARTZ).isEmpty();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isTag()) {
			comps.add(Component.translatable("tooltip.requires_tag"));
			comps.add(Component.literal(DelightfulItemTags.GEMS_ROSE_QUARTZ.location().toString()).withStyle(ChatFormatting.UNDERLINE));
		} else {
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}