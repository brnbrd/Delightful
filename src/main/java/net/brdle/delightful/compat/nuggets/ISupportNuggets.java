package net.brdle.delightful.compat.nuggets;

import com.google.common.collect.ImmutableList;

public interface ISupportNuggets {
    String TYPICAL = "METAL_nugget";
    String REVERSED = "nugget_METAL";

    /** ID of Compat Mod **/
    String getModid();

    /** How their nugget item IDs are formatted, with METAL being the metal **/
    String getFormat();

    /**
     * The format with METAL replaced by @param metal
     *
     * @param metal
     **/
    default String formatMetal(String metal) {
        return this.getFormat().replace("METAL", metal);
    }

    /** All the metals this mod has nuggets for **/
    ImmutableList<String> getMetals();
}