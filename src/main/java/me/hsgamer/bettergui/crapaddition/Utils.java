package me.hsgamer.bettergui.crapaddition;

import me.hsgamer.bettergui.lib.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecated")
public class Utils {

  public static ItemStack getItem(Player player) {
    try {
      return
          XMaterial.matchXMaterial(player.getInventory().getItemInMainHand()).equals(XMaterial.AIR)
              ? XMaterial.STONE.parseItem() : player.getInventory().getItemInMainHand();
    } catch (Exception e) {
      return XMaterial.matchXMaterial(player.getItemInHand()).equals(XMaterial.AIR)
          ? XMaterial.STONE.parseItem() : player
          .getItemInHand();
    }
  }

  public static void setItemInHand(Player player, ItemStack itemStack) {
    try {
      player.getInventory().setItemInMainHand(itemStack);
    } catch (Exception e) {
      player.setItemInHand(itemStack);
    }
  }
}
