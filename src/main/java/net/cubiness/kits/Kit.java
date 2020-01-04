package net.cubiness.kits;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Kit {
  private String name;
  private int power;
  private List<ItemStack> items;

  public Kit(String name, FileConfiguration config) {

  }

  public Kit(String name, int power, Player from) {
    this.name = name;
    this.power = power;
    items = Arrays.asList(from.getInventory().getContents());
  }

  public void save(FileConfiguration config) {

  }
}
