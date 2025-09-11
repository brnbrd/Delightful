package net.brnbrd.delightful.compat.aether;

import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.aether.aether_redux.VeridiumKnifeItem;
import java.util.function.Supplier;
import net.zepalesque.redux.item.util.ReduxItemTiers;

public class AetherReduxCompat {
	public static final Supplier<VeridiumKnifeItem.Uninfused> VERIDIUM = () -> new VeridiumKnifeItem.Uninfused(ReduxItemTiers.VERIDIUM, Knives.props());
	public static final Supplier<VeridiumKnifeItem> INFUSED_VERIDIUM = () -> new VeridiumKnifeItem(ReduxItemTiers.INFUSED_VERIDIUM, Knives.props(), VERIDIUM);
}