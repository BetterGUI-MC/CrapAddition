package me.hsgamer.bettergui.crapaddition;

import me.hsgamer.bettergui.builder.CommandBuilder;
import me.hsgamer.bettergui.builder.IconBuilder;
import me.hsgamer.bettergui.crapaddition.command.GiveThisCommand;
import me.hsgamer.bettergui.crapaddition.command.TakeFromHandCommand;
import me.hsgamer.bettergui.crapaddition.icon.HandIcon;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  @Override
  public void onEnable() {
    IconBuilder.register("hand", HandIcon.class);
    CommandBuilder.register("give-this", GiveThisCommand.class);
    CommandBuilder.register("take-this-from-hand", TakeFromHandCommand.class);
  }
}
