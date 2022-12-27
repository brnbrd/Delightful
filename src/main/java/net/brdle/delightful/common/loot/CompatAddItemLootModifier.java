package net.brdle.delightful.common.loot;

import net.brdle.delightful.compat.Mods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CompatAddItemLootModifier extends AddItemLootModifier {
	String modid;
	boolean enable;

	public CompatAddItemLootModifier(LootItemCondition[] conditions, Item item, int minAmount, int maxAmount, boolean unique, String modid, boolean enable) {
		super(conditions, item, minAmount, maxAmount, unique);
		this.modid = modid;
		this.enable = enable;
	}

	@NotNull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (Mods.loaded(this.modid) && this.enable || ((!Mods.loaded(this.modid)) && !this.enable)) {
			return super.doApply(generatedLoot, context);
		}
		return generatedLoot;
	}
}
