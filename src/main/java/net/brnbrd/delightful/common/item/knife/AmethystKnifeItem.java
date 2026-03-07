package net.brnbrd.delightful.common.item.knife;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.Modid;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class AmethystKnifeItem extends DKnifeItem {
	public AmethystKnifeItem(Properties properties) {
		super(Tags.Items.GEMS_AMETHYST, DelightfulTiers.AMETHYST, properties);
	}

	@Override
	public @NotNull Modid[] getConflicts() {
		return new Modid[]{Modid.FAS};
	}
}