package StrellaMC.MuchDan.ShowItems.Util;

import StrellaMC.MuchDan.ShowItems.ShowItems;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class EnchantmentDisplayName {
    private Map<String, String> getbyName;
    private ShowItems plugin;
    //Thousands place (M, 1000), (MM, 2000), (M, 3000)
    private final String[] ThousandsPlace = {"", "M", "MM", "MMM"};
    //Hunders Place (C, 100), (CC, 200), (CD, 400), (D, 500), (DC, 600), (DCC, 700), (DCCC, 800), (CM, 900)
    private final String[] HundredsPlace = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    //Tens place (X, 10), (XX, 20), (XXX, 30), (XL, 40), (L, 50), (LX, 60), (LXX, 70),(LXXX, 80), (XC, 90)
    private final String[] TensPlace = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    //Ones place (I, 1), (II, 2), (III, 3), (IV, 4), (V, 5), (VI, 6), (VII, 7), (VIII, 8), (IX, 9)
    private final String[] OnesPlace = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public EnchantmentDisplayName(ShowItems plugin){
        this.plugin = plugin;
        getbyName = new HashMap<>();
        //Setting key with user friendly version of enchantment in what is essentially, a cache of information.
        getbyName.put("protection", "Protection");
        getbyName.put("fire_protection","Fire Protection");
        getbyName.put("feather_falling", "Feather Falling");
        getbyName.put("blast_protection", "Blast Protection");
        getbyName.put("projectile_protection", "Projectile Protection");
        getbyName.put("respiration","Respiration");
        getbyName.put("aqua_affinity","Aqua Affinity");
        getbyName.put("thorns","Thorns");
        getbyName.put("depth_strider","Depth Strider");
        getbyName.put("frost_walker","Frost Walker");
        getbyName.put("binding_curse","Curse of Binding");
        getbyName.put("sharpness","Sharpness");
        getbyName.put("smite","Smite");
        getbyName.put("bane_of_arthropods","Bane of Arthropods");
        getbyName.put("knockback","Knockback");
        getbyName.put("fire_aspect","Fire Aspect");
        getbyName.put("looting","Looting");
        getbyName.put("sweeping","Sweeping Edge");
        getbyName.put("efficiency","Efficiency");
        getbyName.put("silk_touch","Silk Touch");
        getbyName.put("unbreaking","Unbreaking");
        getbyName.put("fortune","Fortune");
        getbyName.put("power","Power");
        getbyName.put("punch","Punch");
        getbyName.put("flame","Flame");
        getbyName.put("infinity","Infinity");
        getbyName.put("luck_of_the_sea","Luck of the Sea");
        getbyName.put("lure","Lure");
        getbyName.put("loyalty","Loyalty");
        getbyName.put("impaling","Impaling");
        getbyName.put("riptide","Riptide");
        getbyName.put("channeling","Channeling");
        getbyName.put("multishot","Multishot");
        getbyName.put("quick_charge","Quick Charge");
        getbyName.put("piercing","Piercing");
        getbyName.put("mending","Mending");
        getbyName.put("vanishing_curse","Curse of Vanishing");
        getbyName.put("soul_speed","Soul Speed");

    }
    public String buildEnchant(String Enchant, int ELevel){
        if(!getbyName.containsKey(Enchant)){
            plugin.getServer().getLogger().log(Level.SEVERE, "Unable to find enchantment in enchantment map.");
            return Enchant + " " + ELevel;
        }

        return getbyName.get(Enchant) + " " + MakeRomanNumberNumber(ELevel);
    }
    //Adapted Algorithm
    private String MakeRomanNumberNumber(int ELevel){
        //Converting values to roman numerials
        String thousands = ThousandsPlace[ELevel/1000];
        String hundereds = HundredsPlace[(ELevel%1000)/100];
        String tens = TensPlace[(ELevel%100)/10];
        String ones = OnesPlace[ELevel%10];

        return thousands + hundereds + tens + ones;
    }
}
