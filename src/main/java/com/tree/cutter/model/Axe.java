package com.tree.cutter.model;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class Axe {

    public static boolean isAxe(Material material) {
        List<Material> AXE_LIST = Arrays.asList(
                Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE,
                Material.GOLDEN_AXE, Material.DIAMOND_AXE
        );

        return AXE_LIST.contains(material);
    }
}
