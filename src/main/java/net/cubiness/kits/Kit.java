package net.cubiness.kits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Kit implements ConfigurationSerializable {
  private final List<ItemStack> items = new ArrayList<>();
  private String name;
  private int power;

  public Kit(String name, FileConfiguration config) {

  }

  public Kit(String name, int power, Player from) {
    this.name = name;
    this.power = power;
    items.addAll(Arrays.asList(from.getInventory().getContents()));
    items.removeAll(Collections.singleton(null));
    from.sendMessage(items + "");
  }

  public void save(FileConfiguration config) {
    config.set("kit." + name + ".items", items);
    config.set("kit." + name + ".power", power);
  }

  public void give(Player p) {
    items.forEach(i -> p.getInventory().addItem(i));
  }

  @Override
  public Map<String, Object> serialize() {
    HashMap<String, Object> data = new HashMap<>();
    data.put("name", name);
    data.put("power", power);
    data.put("items", items);
    return data;
  }
}
