package com.tree.cutter.listener;

import com.tree.cutter.model.Axe;
import com.tree.cutter.model.Player;
import com.tree.cutter.model.Wood;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TreeListener implements Listener {

    public static List<com.tree.cutter.model.Player> players = new ArrayList<>();
    private final Logger logger;

    public TreeListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onTreeCut(BlockBreakEvent event) {
        if (!isPluginEnable(event.getPlayer().getName())) {
            return;
        }

        Block block = event.getBlock();

        if (!Wood.isWood(block.getType())) {
            return;
        }

        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();

        if (Axe.isAxe(tool.getType())) {
            // It's radius around block which was broken
            final int TREE_RADIUS = 4;
            final int TREE_HEIGHT = 32;
            final World world = event.getPlayer().getWorld();
            Location startLocation = new Location(world, block.getX(),
                    block.getY(),
                    block.getZ());
            double startPositionY = startLocation.getY();
            Location endLocation = new Location(
                    world,
                    startLocation.getX() + TREE_RADIUS,
                    startPositionY + TREE_HEIGHT,
                    startLocation.getZ() + TREE_RADIUS);

            for (double y = startPositionY; y < endLocation.getY(); y++) {
                boolean isNotFoundWoodLog = true;

                for (double x = startLocation.getX() - TREE_RADIUS; x <= endLocation.getX(); x++) {
                    for (double z = startLocation.getZ() - TREE_RADIUS; z <= endLocation.getZ(); z++) {
                        Block treeBlock = world.getBlockAt(new Location(world, x, y, z));

                        if (Wood.isWood(treeBlock.getType())) {
                            isNotFoundWoodLog = false;
                            treeBlock.breakNaturally(tool);
                        }
                    }
                }

                if (isNotFoundWoodLog) {
                    break;
                }
            }
        }
    }

    private boolean isPluginEnable(String name) {
        Optional<Player> optionalPlayer = TreeListener.players.
                stream().filter((element) -> element.getName().equals(name))
                .findFirst();

        return optionalPlayer.isPresent() && optionalPlayer.get().isEnable();
    }
}
