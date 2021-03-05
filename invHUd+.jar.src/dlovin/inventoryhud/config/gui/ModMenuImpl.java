package dlovin.inventoryhud.config.gui;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.class_437;

public class ModMenuImpl implements ModMenuApi {
  public ConfigScreenFactory<?> getModConfigScreenFactory() {
    return parent -> new InventoryConfigScreen(parent, false);
  }
}
