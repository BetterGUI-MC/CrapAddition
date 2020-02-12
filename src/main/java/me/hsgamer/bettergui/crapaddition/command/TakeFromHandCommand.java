package me.hsgamer.bettergui.crapaddition.command;

import java.util.Optional;
import me.hsgamer.bettergui.crapaddition.Utils;
import me.hsgamer.bettergui.lib.taskchain.TaskChain;
import me.hsgamer.bettergui.object.ClickableItem;
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
    String parsed = getParsedCommand(player);
    int amount = 0;
    Optional<ClickableItem> clickableItem =
        getIcon().isPresent() ? getIcon().get().createClickableItem(player) : Optional.empty();
    if (Validate.isValidPositiveInteger(parsed)) {
      amount = Integer.parseInt(parsed);
    } else if (parsed.equalsIgnoreCase("this") && clickableItem.isPresent()) {
      amount = clickableItem.get().getItem().getAmount();
    }
    if (amount > 0) {
      int finalAmount = amount;
      taskChain.sync(() -> {
        ItemStack item = Utils.getItem(player);
        if (item.getAmount() > finalAmount) {
          item.setAmount(item.getAmount() - finalAmount);
          Utils.setItemInHand(player, item);
        } else {
          Utils.setItemInHand(player, null);
        }
      });
    }
  }
}
