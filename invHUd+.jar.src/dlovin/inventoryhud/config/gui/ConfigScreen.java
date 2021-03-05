package dlovin.inventoryhud.config.gui;

import dlovin.inventoryhud.config.InGameConfigScreen;
import dlovin.inventoryhud.config.widgets.CustomButton;
import dlovin.inventoryhud.config.widgets.CustomOptionList;
import dlovin.inventoryhud.config.widgets.Widget;
import dlovin.inventoryhud.references.Translation;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class ConfigScreen extends class_437 {
  protected CustomButton InventoryBtn;
  
  protected CustomButton ArmorBtn;
  
  protected CustomButton PotionBtn;
  
  protected CustomButton PositionBtn;
  
  protected CustomOptionList OptionList;
  
  private boolean[] pressedButtons = new boolean[] { false, false, false, false };
  
  private int menu;
  
  protected boolean inGame;
  
  protected List<Widget> widgetList = new ArrayList<>();
  
  private class_437 prevScreen;
  
  public ConfigScreen(class_437 prev, int menu, boolean inGame) {
    super((class_2561)new class_2585("Inventory HUD+ Config"));
    this.inGame = inGame;
    this.menu = menu;
    this.pressedButtons[menu] = true;
    this.prevScreen = prev;
  }
  
  protected void method_25426() {
    this.OptionList = new CustomOptionList(this.field_22787, this);
    this.widgetList = new ArrayList<>();
    this.widgetList.add(this.InventoryBtn = new CustomButton(this.field_22789 / 2 - 90, 8, 36, 36, "InvHUD", new class_2960("inventoryhud", "textures/gui/inv_btn.png"), this.pressedButtons[0], (w, m, x, y) -> method_25424(m, Translation.INV_TT, x, y)));
    this.InventoryBtn.addListener(() -> class_310.method_1551().method_1507(new InventoryConfigScreen(this.prevScreen, this.inGame)));
    this.widgetList.add(this.ArmorBtn = new CustomButton(this.field_22789 / 2 - 42, 8, 36, 36, "ArmHUD", new class_2960("inventoryhud", "textures/gui/arm_btn.png"), this.pressedButtons[1], (w, m, x, y) -> method_25424(m, Translation.ARM_TT, x, y)));
    this.ArmorBtn.addListener(() -> class_310.method_1551().method_1507(new ArmorConfigScreen(this.prevScreen, this.inGame)));
    this.widgetList.add(this.PotionBtn = new CustomButton(this.field_22789 / 2 + 6, 8, 36, 36, "PotHUD", new class_2960("inventoryhud", "textures/gui/pot_btn.png"), this.pressedButtons[2], (w, m, x, y) -> method_25424(m, Translation.POT_TT, x, y)));
    this.PotionBtn.addListener(() -> class_310.method_1551().method_1507(new PotionConfigScreen(this.prevScreen, this.inGame)));
    this.widgetList.add(this.PositionBtn = new CustomButton(this.field_22789 / 2 + 54, 8, 36, 36, "InvHUD", new class_2960("inventoryhud", "textures/gui/pos_btn.png"), false, (w, m, x, y) -> method_25424(m, Translation.POS_TT, x, y)));
    this.PositionBtn.addListener(() -> class_310.method_1551().method_1507((class_437)new InGameConfigScreen(this.prevScreen, this.menu, this.inGame)));
    this.field_22786.add(this.OptionList);
    for (Widget w : this.widgetList)
      this.field_22786.add(w); 
  }
  
  public void method_25394(class_4587 mat, int mouseX, int mouseY, float partialTick) {
    method_25420(mat);
    this.OptionList.method_25394(mat, mouseX, mouseY, partialTick);
    for (Widget widget : this.widgetList)
      widget.method_25394(mat, mouseX, mouseY, partialTick); 
    this.OptionList.renderTooltips(mat, mouseX, mouseY);
    for (Widget widget : this.widgetList)
      widget.renderTooltip(mat, mouseX, mouseY); 
  }
  
  public boolean method_25402(double m1, double m2, int m3) {
    boolean result = super.method_25402(m1, m2, m3);
    if (result)
      for (Widget w : this.widgetList) {
        if (w instanceof dlovin.inventoryhud.config.widgets.NumericTextField)
          w.method_25402(m1, m2, m3); 
      }  
    return result;
  }
  
  public boolean method_25400(char p_231042_1_, int p_231042_2_) {
    if (this.OptionList.method_25400(p_231042_1_, p_231042_2_))
      return true; 
    return super.method_25400(p_231042_1_, p_231042_2_);
  }
  
  public boolean method_25403(double p_231045_1_, double p_231045_3_, int p_231045_5_, double p_231045_6_, double p_231045_8_) {
    this.OptionList.method_25403(p_231045_1_, p_231045_3_, p_231045_5_, p_231045_6_, p_231045_8_);
    return false;
  }
  
  public boolean method_25404(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
    if (this.OptionList.method_25404(p_231046_1_, p_231046_2_, p_231046_3_))
      return true; 
    return super.method_25404(p_231046_1_, p_231046_2_, p_231046_3_);
  }
  
  public void method_25419() {
    if (!this.inGame) {
      this.field_22787.method_1507(this.prevScreen);
    } else {
      this.field_22787.method_1507(null);
    } 
  }
}
