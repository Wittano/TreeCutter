package com.tree.cutter.model;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Wood {

    public static boolean isWood(Material material) {
        final List<Material> WOOD_LIST = Arrays.asList(
                Material.ACACIA_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG,
                Material.OAK_LOG, Material.DARK_OAK_LOG);

        return WOOD_LIST.contains(material);
    }
}
