package net.cubiness.kits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Kit {
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
  }

  public void give(Player p) {
    items.forEach(i -> p.getInventory().addItem(i));
  }
}
