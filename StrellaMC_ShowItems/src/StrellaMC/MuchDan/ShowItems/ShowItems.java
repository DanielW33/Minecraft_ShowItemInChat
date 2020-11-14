package StrellaMC.MuchDan.ShowItems;

import StrellaMC.MuchDan.ShowItems.Commands.onCommandShowItem;
import StrellaMC.MuchDan.ShowItems.Util.EnchantmentDisplayName;
import org.bukkit.plugin.java.JavaPlugin;

public class ShowItems extends JavaPlugin {
    public static EnchantmentDisplayName getbyName;

    @Override
    public void onEnable(){
        getbyName = new EnchantmentDisplayName(this);

        this.getCommand("Show").setExecutor(new onCommandShowItem(this));
    }
}
