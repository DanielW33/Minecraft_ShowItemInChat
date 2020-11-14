package StrellaMC.MuchDan.ShowItems.Commands;

import StrellaMC.MuchDan.ShowItems.ShowItems;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class onCommandShowItem implements CommandExecutor {
    private ShowItems plugin;

    public onCommandShowItem(ShowItems plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] args){
        if(label.equalsIgnoreCase("Show")) {
            if (!(Sender instanceof Player)) {
                this.plugin.getServer().getLogger().log(Level.SEVERE, "You are Bad.");
                return false;
            }

            Player player = (Player)Sender;
            ComponentBuilder Component = new ComponentBuilder();
            ItemStack hand = player.getInventory().getItemInMainHand();

            if(!hand.hasItemMeta() && hand.getType().isAir()){      //Checking to see if hand is empty
                Sender.sendMessage(ChatColor.AQUA + "You need to be holding an item to show it off");
                return false;
            }
            //Checking to see if it is a default item, or if it is modified.
            if(hand.getItemMeta().getDisplayName().equalsIgnoreCase("") && hand.getEnchantments().isEmpty() && !hand.getItemMeta().hasLore()){
                Sender.sendMessage(ChatColor.AQUA + "You can only show off modified items!");
                return false;
            }
            /////////////////////BUILDING ITEM IN CHAT//////////////////////////////////
            Component.append(hand.getItemMeta().getDisplayName() + "\n");   //Setting display name

            if(!hand.getEnchantments().isEmpty()) { //Checking for enchants

                hand.getEnchantments().forEach((e, k) -> {  //If so, get enchant, by simple name, The first getkey is minecraft:Enchant the second getkey is Enchant
                    String Enchant = e.getKey().getKey();
                    //Uses custom function buildEnchant to concatenate the string together, There may be an easier way to do this.
                    String Concatenate = ChatColor.GRAY + ShowItems.getbyName.buildEnchant(Enchant, k) + "\n";  //This static Object uses a hashmap as a cache(Essentially)
                    Component.append(Concatenate);                                                              // so it is quite efficient.
                });
            }
            if(hand.getItemMeta().hasLore()) {  //Checking for lore
                for (String msg : hand.getItemMeta().getLore()) {
                    msg = msg + "\n";
                    Component.append(msg);
                }
            }
            TextComponent message = new TextComponent(ChatColor.BLUE + "Hover here to see " + player.getName() + "'s" + " item!"); //What the players see in chat, This can be anything.

            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Component.create()));

            for(Player play : Bukkit.getOnlinePlayers()){
                play.spigot().sendMessage(message);
            }
            return true;
        }
        return false;
    }
}
