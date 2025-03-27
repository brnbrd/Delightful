package net.brnbrd.delightful.common.block;

import net.brnbrd.delightful.compat.Modid;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public class PumpkinPieBlock extends DPieBlock {
	public PumpkinPieBlock(Supplier<Item> pieSlice) {
		super(pieSlice, Modid.MC.rl("pumpkin_pie"));
	}

	@Override
	public boolean enabled() {
		return !Modid.CCK.loaded() && super.enabled();
	}
}