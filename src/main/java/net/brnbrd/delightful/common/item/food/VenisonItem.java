package net.brnbrd.delightful.common.item.food;

import net.brnbrd.delightful.common.item.DItem;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class VenisonItem extends DItem {
	public VenisonItem(Properties prop) {
		super(prop);
	}

	@Override
	public boolean enabled() {
		return super.enabled() && this.isTag();
	}

	public boolean isTag() {
		var tags = ForgeRegistries.ITEMS.tags();
		return tags.isKnownTagName(DelightfulItemTags.RAW_VENISON_COMPAT) &&
			!tags.getTag(DelightfulItemTags.RAW_VENISON_COMPAT).isEmpty() &&
			tags.isKnownTagName(DelightfulItemTags.COOKED_VENISON_COMPAT) &&
			!tags.getTag(DelightfulItemTags.COOKED_VENISON_COMPAT).isEmpty();
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> comps, @NotNull TooltipFlag pIsAdvanced) {
		if (!this.isTag()) {
			comps.add(Component.translatable("tooltip.requires_tag"));
			comps.add(Component.literal(DelightfulItemTags.RAW_VENISON.location().toString()).withStyle(ChatFormatting.UNDERLINE));
			comps.add(Component.literal(DelightfulItemTags.COOKED_VENISON.location().toString()).withStyle(ChatFormatting.UNDERLINE));
		} else {
			super.appendHoverText(stack, level, comps, pIsAdvanced);
		}
	}
}
