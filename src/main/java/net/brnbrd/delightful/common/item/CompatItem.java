package net.brnbrd.delightful.common.item;

public class CompatItem extends DItem implements ICompat {
	private final String[] modid;

	public CompatItem(Properties prop, String... modid) {
		super(prop);
		this.modid = modid;
	}

	@Override
	public String[] getModid() {
		return modid;
	}
}