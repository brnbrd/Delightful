package net.brdle.delightful.common.item.knife;

import net.brdle.delightful.Util;
import net.brdle.delightful.common.config.DelightfulConfig;
import net.brdle.delightful.common.item.IConfigured;
import net.brdle.delightful.common.item.ISingleIngredient;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.item.KnifeItem;
import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class DelightfulKnifeItem extends KnifeItem implements IConfigured, ISingleIngredient {
    private final Supplier<Ingredient> ingredient;

    public DelightfulKnifeItem(Supplier<Ingredient> ingredient, Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
        this.ingredient = ingredient;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tool, TooltipFlag pIsAdvanced) {
        if (!DelightfulConfig.CONFIG.stuff.get(Util.name(this)).get()) {
            tool.add(Component.literal("Disabled.").withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Override
    public Supplier<Ingredient> getIngredient() {
        return this.ingredient;
    }

    @Override
    public boolean isEnabled() {
        return DelightfulConfig.CONFIG.stuff.get(Util.name(this)).get();
    }

    public Supplier<Ingredient> getRod() {
        return () -> Ingredient.of(Tags.Items.RODS_WOODEN);
    }
}
