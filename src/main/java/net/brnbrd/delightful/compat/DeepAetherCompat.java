package net.brnbrd.delightful.compat;

import net.brnbrd.delightful.common.item.knife.Knives;
import net.brnbrd.delightful.common.item.knife.compat.deep_aether.SkyjadeKnifeItem;
import net.brnbrd.delightful.common.item.knife.compat.deep_aether.StratusKnifeItem;
import net.minecraft.world.item.Item;
import java.util.function.Supplier;

public class DeepAetherCompat {
	public static Supplier<Item> SKYJADE = () -> new SkyjadeKnifeItem(Knives.props());
	public static Supplier<Item> STRATUS = () -> new StratusKnifeItem(Knives.props());
}
