package me.hsgamer.bettergui.crapaddition.icon;

import java.util.Map;
import java.util.Optional;
import me.hsgamer.bettergui.builder.PropertyBuilder;
import me.hsgamer.bettergui.crapaddition.Utils;
import me.hsgamer.bettergui.object.ClickableItem;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.Menu;
import me.hsgamer.bettergui.object.Property;
import me.hsgamer.bettergui.object.property.icon.SimpleIconPropertyBuilder;
import me.hsgamer.bettergui.object.property.icon.impl.ViewRequirement;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HandIcon extends Icon {

  private Map<String, ItemProperty<?, ?>> itemProperties;
  private Map<String, Property<?>> otherProperties;

  private SimpleIconPropertyBuilder iconPropertyBuilder = new SimpleIconPropertyBuilder(this);

  public HandIcon(String name, Menu menu) {
    super(name, menu);
  }

  public HandIcon(Icon original) {
    super(original);
    if (original instanceof HandIcon) {
      this.itemProperties = ((HandIcon) original).itemProperties;
      this.otherProperties = ((HandIcon) original).otherProperties;
      this.iconPropertyBuilder = ((HandIcon) original).iconPropertyBuilder;
    }
  }

  @Override
  public void setFromSection(ConfigurationSection section) {
    itemProperties = PropertyBuilder.loadItemPropertiesFromSection(this, section);
    iconPropertyBuilder.init(section);
    otherProperties = PropertyBuilder.loadOtherPropertiesFromSection(section);
  }

  @Override
  public Optional<ClickableItem> createClickableItem(Player player) {
    ViewRequirement viewRequirement = iconPropertyBuilder.getViewRequirement();
    if (!viewRequirement.check(player)) {
      return Optional.empty();
    }
    viewRequirement.take(player);
    return Optional.of(getClickableItem(player));
  }

  @Override
  public Optional<ClickableItem> updateClickableItem(Player player) {
    if (!iconPropertyBuilder.getViewRequirement().check(player)) {
      return Optional.empty();
    }
    return Optional.of(getClickableItem(player));
  }

  private ClickableItem getClickableItem(Player player) {
    ItemStack itemStack = Utils.getItem(player).clone();
    for (ItemProperty<?, ?> itemProperty : itemProperties.values()) {
      itemStack = itemProperty.parse(player, itemStack);
    }
    return new ClickableItem(itemStack, iconPropertyBuilder.createClickEvent(player));
  }

  public Map<String, ItemProperty<?, ?>> getItemProperties() {
    return itemProperties;
  }

  public Map<String, Property<?>> getOtherProperties() {
    return otherProperties;
  }
}
