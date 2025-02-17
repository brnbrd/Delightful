package net.brnbrd.delightful.common.item.knife.compat.undergarden;

import net.brnbrd.delightful.Util;
import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.common.item.knife.DKnifeItem;
import net.brnbrd.delightful.compat.Modid;
import net.brnbrd.delightful.data.tags.DelightfulItemTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class ForgottenKnifeItem extends DKnifeItem {

	public ForgottenKnifeItem(Properties properties) {
		super(DelightfulItemTags.ingot("forgotten_metal"), DelightfulTiers.FORGOTTEN, properties, Modid.UG);
		MinecraftForge.EVENT_BUS.addListener(this::onHurt);
		MinecraftForge.EVENT_BUS.addListener(this::onDig);
	}

	@Override
	public @NotNull Component getName(@NotNull ItemStack stack) {
		Component name = super.getName(stack);
		return this.enabled() ? name.copy().withStyle(ChatFormatting.GREEN) : name;
	}

	@Override
	public List<Component> getTools() {
		return List.of(Component.translatable("tooltip.forgotten_sword").withStyle(ChatFormatting.GREEN));
	}

	@Override
	public Modid[] getConflicts() {
		return new Modid[]{Modid.UGD};
	}

	@Override
	public @Nullable RecipeType<?> getRecipeType() {
		return RecipeType.SMITHING;
	}

	void onHurt(LivingHurtEvent e) {
		if (
			this.enabled() &&
			e.getSource().getEntity() instanceof Player player &&
			player.getMainHandItem().is(this) &&
			ForgeRegistries.ENTITY_TYPES.getKey(e.getEntity().getType()).getNamespace().equals(Modid.UG) &&
			e.getEntity().canChangeDimensions()
		) {
			e.setAmount(e.getAmount() * 1.5F);
		}
	}

	void onDig(PlayerEvent.BreakSpeed e) {
		BlockState state = e.getState();
		IForgeRegistry<Block> blocks = ForgeRegistries.BLOCKS;
		if (
			this.enabled() &&
			e.getEntity().getMainHandItem().is(this) &&
			state != null &&
			Util.nameSpace(state.getBlock()).equals(Modid.UG)
		) {
			e.setNewSpeed(e.getOriginalSpeed() * 1.5F);
		}
	}
}