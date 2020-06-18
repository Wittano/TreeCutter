package com.tree.cutter.listener;

import com.tree.cutter.model.Axe;
import com.tree.cutter.model.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.tree.cutter.listener.TreeListener.players;

public class PlayerListener implements Listener {


    @EventHandler
    public void onPlayerClickedRightMouseButton(PlayerInteractEvent event) {
        org.bukkit.entity.Player bukkitPlayer = event.getPlayer();
        ItemStack tool = bukkitPlayer.getInventory().getItemInMainHand();
        Action action = event.getAction();
        String username = bukkitPlayer.getName();

        if (action == Action.RIGHT_CLICK_AIR && Axe.isAxe(tool.getType())) {
            ExecutorService service = Executors.newSingleThreadExecutor();

            service.execute(() -> {
                Player player = players.stream().filter(
                        (element) -> element.getName().equals(username))
                        .findFirst().orElse(null);

                if (player == null) {
                    players.add(new Player(username, true));
                    bukkitPlayer.sendMessage("Enable TreeCutter plugin");
                } else {
                    int index = players.indexOf(player);
                    player.setEnable(!player.isEnable());
                    players.set(index, player);

                    bukkitPlayer.sendMessage(player.isEnable() ?
                            "Enable TreeCutter plugin" : "Disable TreeCutter plugin");
                }
            });
        }
    }
}
