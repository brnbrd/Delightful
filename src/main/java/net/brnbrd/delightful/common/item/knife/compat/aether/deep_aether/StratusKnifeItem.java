package net.brnbrd.delightful.common.item.knife.compat.aether.deep_aether;

import net.brnbrd.delightful.common.item.knife.compat.aether.AetherKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.RecipeType;
import com.aetherteam.aether.item.combat.abilities.weapon.GravititeWeapon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StratusKnifeItem extends AetherKnifeItem implements GravititeWeapon {
	public StratusKnifeItem(Properties props, Tier tier) {
		super(DelightfulItemTags.INGOTS_STRATUS, tier, props, Modid.DA);
	}

	@Override
	public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, @NotNull LivingEntity attacker) {
		this.launchEntity(target, attacker);
		return super.hurtEnemy(stack, target, attacker);
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}
}