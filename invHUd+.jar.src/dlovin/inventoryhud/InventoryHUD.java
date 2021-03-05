package dlovin.inventoryhud;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dlovin.inventoryhud.config.InvConfig;
import dlovin.inventoryhud.config.InvConfigManager;
import dlovin.inventoryhud.keybinds.KeyBinds;
import net.fabricmc.api.ClientModInitializer;

public class InventoryHUD implements ClientModInitializer {
  private KeyBinds keyBinds;
  
  public static final String MOD_ID = "inventoryhud";
  
  public static final Gson GSON = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
  
  public static boolean InvHUD = false;
  
  public static boolean PotHUD = false;
  
  public static boolean ArmHUD = false;
  
  private static InvConfigManager CONFIG = new InvConfigManager();
  
  public static InvConfig getConfig() {
    return InvConfigManager.getConfig();
  }
  
  public static void save() {
    InvConfigManager.save();
  }
  
  public void onInitializeClient() {
    InvConfigManager.initializeConfig();
    InvHUD = InvConfigManager.getConfig().getInv();
    PotHUD = InvConfigManager.getConfig().getPot();
    ArmHUD = InvConfigManager.getConfig().getArm();
    this.keyBinds = new KeyBinds();
    System.out.println("Inventory HUD + has been initialized!");
  }
}
