package dlovin.inventoryhud.keybinds;

import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.config.InvConfigManager;
import dlovin.inventoryhud.config.gui.InventoryConfigScreen;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_437;

public class KeyEvents {
  private class_304[] keyBinding;
  
  public KeyEvents(class_304[] keyBinding) {
    this.keyBinding = keyBinding;
  }
  
  public void KeyBindsEvents(class_310 client) {
    if (this.keyBinding[0].method_1436()) {
      InvConfigManager.getConfig().setInv(!InvConfigManager.getConfig().getInv());
      InventoryHUD.InvHUD = InvConfigManager.getConfig().getInv();
    } else if (this.keyBinding[1].method_1436()) {
      client.method_1507((class_437)new InventoryConfigScreen(null, true));
    } 
  }
}
