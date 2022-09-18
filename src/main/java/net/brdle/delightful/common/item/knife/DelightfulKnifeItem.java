package net.brdle.delightful.common.item.knife;

import com.google.common.collect.ImmutableList;
import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.IConfigured;
import net.brdle.delightful.common.item.ISingleIngredient;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.KnifeItem;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class DelightfulKnifeItem extends KnifeItem implements IConfigured, ISingleIngredient {
    private final Supplier<Ingredient> ingredient;
    protected final ImmutableList<CreativeModeTab> tabs = ImmutableList.of(CreativeModeTab.TAB_SEARCH, FarmersDelight.CREATIVE_TAB);

    public DelightfulKnifeItem(Supplier<Ingredient> ingredient, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.ingredient = ingredient;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        if (!this.config()) {
            tool.add(Component.literal("Disabled.").withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Override
    public Supplier<Ingredient> getIngredient() {
        return this.ingredient;
    }

    public boolean config() {
        return DelightfulConfig.CONFIG.stuff.get(Util.name(this)).get();
    }

    @Override
    public boolean isEnabled() {
        return this.config();
    }

    public Supplier<Ingredient> getRod() {
        return () -> Ingredient.of(Tags.Items.RODS_WOODEN);
    }

    @Override
    protected boolean allowedIn(CreativeModeTab cat) {
        return tabs.contains(cat) && this.config();
    }
}
