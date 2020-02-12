package me.hsgamer.bettergui.crapaddition.command;

import me.hsgamer.bettergui.crapaddition.Utils;
import me.hsgamer.bettergui.lib.taskchain.TaskChain;
import me.hsgamer.bettergui.object.Command;
import me.hsgamer.bettergui.util.Validate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TakeFromHandCommand extends Command {

  public TakeFromHandCommand(String string) {
    super(string);
  }

  @Override
  public void addToTaskChain(Player player, TaskChain<?> taskChain) {
    String a = getParsedCommand(player);
    if (Validate.isValidPositiveInteger(a)) {
      taskChain.sync(() -> {
        int amount = Integer.parseInt(a);
        ItemStack item = Utils.getItem(player);
        if (item.getAmount() > amount) {
          item.setAmount(item.getAmount() - amount);
          Utils.setItemInHand(player, item);
        } else {
          Utils.setItemInHand(player, null);
        }
      });
    }
  }
}
