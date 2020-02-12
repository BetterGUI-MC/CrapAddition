package me.hsgamer.bettergui.crapaddition.command;

import java.util.Optional;
import me.hsgamer.bettergui.lib.taskchain.TaskChain;
import me.hsgamer.bettergui.object.Command;
import me.hsgamer.bettergui.object.Icon;
import org.bukkit.entity.Player;

public class GiveThisCommand extends Command {

  public GiveThisCommand(String string) {
    super(string);
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    Optional<Icon> icon = getIcon();
    icon.flatMap(value -> value.createClickableItem(player)).ifPresent(clickableItem -> taskChain
        .sync(() -> player.getInventory().addItem(clickableItem.getItem())));
  }
}
