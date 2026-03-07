package net.brnbrd.delightful.common.item.knife;

import net.brnbrd.delightful.common.item.DelightfulTiers;
import net.brnbrd.delightful.compat.Modid;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class NetherQuartzKnifeItem extends DKnifeItem {
	public NetherQuartzKnifeItem(Properties properties) {
		super(Tags.Items.GEMS_QUARTZ, DelightfulTiers.NETHER_QUARTZ, properties);
	}

	@Override
	public @NotNull Modid[] getConflicts() {
		return new Modid[]{Modid.FAS};
	}
}