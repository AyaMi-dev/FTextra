package org.FT.server.music;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class MusicReload implements CommandExecutor, TabCompleter {

    private JavaPlugin plugin;

    public MusicReload(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public MusicReload() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            plugin.getLogger().info("Config 파일이 새로 로딩되었습니다.");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("am")) {
            if (args.length == 1) {
                return Arrays.asList("reload");
            }
        }
        return null;
    }
}