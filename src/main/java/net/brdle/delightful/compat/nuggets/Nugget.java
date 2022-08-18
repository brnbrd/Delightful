package net.brdle.delightful.compat.nuggets;

import com.google.common.collect.ImmutableList;

public enum Nugget implements ISupportNuggets {
    //COPPERIZED("copperized", TYPICAL, ImmutableList.of("copper")),
    //THERMAL("thermal", TYPICAL, ImmutableList.of("copper", "tin", "lead", "silver", "nickel", "bronze", "electrum", "invar", "constantan")),
    //IMMERSIVEENGINEERING("immersiveengineering", REVERSED, ImmutableList.of("copper", "lead", "silver", "nickel", "electrum", "steel", "constantan")),
    MEKANISM("mekanism", REVERSED, ImmutableList.of("bronze", "steel", "tin", "osmium"));

    private final String modid;
    private final String format;
    private final ImmutableList<String> metals;

    Nugget(String modid, String format, ImmutableList<String> metals) {
        this.modid = modid;
        this.format = format;
        this.metals = metals;
    }

    /**
     * ID of Compat Mod
     **/
    @Override
    public String getModid() {
        return this.modid;
    }

    /**
     * How their nugget item IDs are formatted, with METAL being the metal
     **/
    @Override
    public String getFormat() {
        return this.format;
    }

    /**
     * All the metals this mod has nuggets for
     **/
    @Override
    public ImmutableList<String> getMetals() {
        return this.metals;
    }
}
