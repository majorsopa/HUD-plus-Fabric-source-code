package dlovin.inventoryhud.keybinds;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.class_304;
import net.minecraft.class_310;
import net.minecraft.class_3675;

public class KeyBinds {
  private class_304[] keyBinding = new class_304[2];
  
  private KeyEvents keyEvents;
  
  public KeyBinds() {
    setKeybinds();
    this.keyEvents = new KeyEvents(this.keyBinding);
    registerKeybinds();
  }
  
  public void setKeybinds() {
    this.keyBinding[0] = KeyBindingHelper.registerKeyBinding(new class_304("key.inventoryhud.toggle", class_3675.class_307.field_1668, 73, "key.inventoryhud.category"));
    this.keyBinding[1] = KeyBindingHelper.registerKeyBinding(new class_304("key.inventoryhud.openconfig", class_3675.class_307.field_1668, 79, "key.inventoryhud.category"));
  }
  
  private void registerKeybinds() {
    ClientTickEvents.END_CLIENT_TICK.register(client -> this.keyEvents.KeyBindsEvents(client));
  }
}
