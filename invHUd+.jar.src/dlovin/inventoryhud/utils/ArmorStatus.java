package dlovin.inventoryhud.utils;

import net.minecraft.class_1799;

public class ArmorStatus {
  public static int GetDamage(class_1799 item) {
    if (item.method_7947() == 0)
      return -1; 
    if (item.method_7936() == 0)
      return -2; 
    return (item.method_7936() - item.method_7919()) * 100 / item.method_7936();
  }
}
