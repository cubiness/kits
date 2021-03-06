package net.cubiness.kits;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Kit implements ConfigurationSerializable {
  private final List<ItemStack> items = new ArrayList<>();
  private String name;
  private int power;

  public Kit(String name, int power, Player from) {
    this.name = name;
    this.power = power;
    items.addAll(Arrays.asList(from.getInventory().getContents()));
    items.removeAll(Collections.singleton(null));
    from.sendMessage(items + "");
  }

  private Kit(String name, int power, List<ItemStack> items) {
    this.name = name;
    this.power = power;
    this.items.addAll(items);
  }

  public static Kit deserialize(Map<String, Object> args) {
    String name = "";
    int power = 0;
    List<ItemStack> items = new ArrayList<>();
    if (args.containsKey("name")) {
      name = "" + args.get("name");
    }
    if (args.containsKey("power")) {
      power = (Integer) args.get("power");
    }
    if (args.containsKey("items")) {
      items = (ArrayList<ItemStack>) args.get("items");
    }
    return new Kit(name, power, items);
  }

  public void give(Player p) {
    p.getInventory().clear();
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

  public String getName() {
    return name;
  }
}
