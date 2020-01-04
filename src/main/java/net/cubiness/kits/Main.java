package net.cubiness.kits;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin implements Listener {
  private FileConfiguration config;
  private HashMap<String, Kit> kits;

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    Bukkit.broadcastMessage("Kits plugin loaded!");
    config = getConfig();
    kits = new HashMap<>();
  }

  @Override
  public void onDisable() {
    Bukkit.broadcastMessage("Kits plugin disabled");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (label.equals("kitcreate")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage("Only players can use this command!");
        return true;
      }
      if (args.length != 2) {
        return false;
      }
      String name = args[0];
      int power = Integer.parseInt(args[1]);
      kits.put(name, new Kit(name, power, (Player) sender));
    } else if (label.equals("kit")) {
      if (!(sender instanceof Player)) {
        sender.sendMessage("Only players can use this command!");
        return true;
      }
      if (args.length != 1) {
        return false;
      }
      String name = args[0];
      kits.get(name).give((Player) sender);
    } else {
      return false;
    }
    return true;
  }
}
