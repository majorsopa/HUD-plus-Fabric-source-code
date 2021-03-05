package dlovin.inventoryhud.config;

import dlovin.inventoryhud.InventoryHUD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.fabricmc.loader.api.FabricLoader;

public class InvConfigManager {
  private static File file;
  
  private static InvConfig config;
  
  private static void prepareConfigFile() {
    if (file != null)
      return; 
    file = new File(FabricLoader.getInstance().getConfigDir().toString(), "inventoryhud.json");
  }
  
  public static InvConfig initializeConfig() {
    if (config != null)
      return config; 
    config = new InvConfig();
    load();
    return config;
  }
  
  public static void save() {
    prepareConfigFile();
    String jsonString = InventoryHUD.GSON.toJson(config);
    try (FileWriter fileWriter = new FileWriter(file)) {
      fileWriter.write(jsonString);
    } catch (IOException e) {
      System.err.println("Couldn't save InventoryHUD configuration file");
      e.printStackTrace();
    } 
  }
  
  private static void load() {
    prepareConfigFile();
    try {
      if (!file.exists())
        save(); 
      if (file.exists()) {
        BufferedReader br = new BufferedReader(new FileReader(file));
        InvConfig parsed = (InvConfig)InventoryHUD.GSON.fromJson(br, InvConfig.class);
        if (parsed != null)
          config = parsed; 
      } 
    } catch (FileNotFoundException e) {
      System.err.println("Couldn't load InventoryHUD configuration file; reverting to defaults");
      e.printStackTrace();
    } 
  }
  
  public static InvConfig getConfig() {
    if (config == null)
      config = new InvConfig(); 
    return config;
  }
}
