package net.brdle.delightful.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.PieBlock;
import java.util.function.Supplier;

public class DelightfulPieBlock extends PieBlock {

	Supplier<MobEffectInstance> effect;

	public DelightfulPieBlock(Properties properties, Supplier<Item> pieSlice) {
		super(properties, pieSlice);
		this.effect = () -> null;
	}

	public DelightfulPieBlock(Properties properties, Supplier<Item> pieSlice, Supplier<MobEffectInstance> eating) {
		super(properties, pieSlice);
		this.effect = eating;
	}

	public MobEffectInstance getEffect() {
		return this.effect.get();
	}

	@Override
	protected InteractionResult consumeBite(Level level, BlockPos pos, BlockState state, Player player) {
		InteractionResult superResult = super.consumeBite(level, pos, state, player);
		if (!level.isClientSide() && this.getEffect() != null && superResult == InteractionResult.SUCCESS) {
			player.addEffect(this.getEffect());
		}
		return superResult;
	}

}
