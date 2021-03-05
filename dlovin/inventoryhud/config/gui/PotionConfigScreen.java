package dlovin.inventoryhud.config.gui;

import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.config.widgets.CustomButton;
import dlovin.inventoryhud.config.widgets.NumericTextField;
import dlovin.inventoryhud.config.widgets.TextWidget;
import dlovin.inventoryhud.config.widgets.Widget;
import dlovin.inventoryhud.gui.InventoryHUDGui;
import dlovin.inventoryhud.references.Translation;
import dlovin.inventoryhud.utils.WidgetAligns;
import net.minecraft.class_124;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class PotionConfigScreen extends ConfigScreen {
  private CustomButton ToggleBtn;
  
  private CustomButton MiniModeBtn;
  
  private NumericTextField AlphaField;
  
  private NumericTextField GapField;
  
  public PotionConfigScreen(class_437 prev, boolean inGame) {
    super(prev, 2, inGame);
  }
  
  protected void method_25426() {
    super.method_25426();
    String toggle = InventoryHUD.getConfig().getPot() ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.OptionList.addWidget((Widget)(this.ToggleBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, toggle, null, false)), 0);
    this.ToggleBtn.addListener(this::ToggleSwitch);
    this.OptionList.addWidget((Widget)(this.MiniModeBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().getPotMini() + "", null, false)), 2);
    this.MiniModeBtn.addListener(this::MiniModeSwitch);
    this.OptionList.addWidget((Widget)(this.AlphaField = new NumericTextField(this.field_22793, this.field_22789 / 2 + 20, 0, 40, 20, 0, 100, InventoryHUD.getConfig().getPotAlpha(), tf -> AlphaChanged())), 1);
    this.OptionList.addWidget((Widget)(this.GapField = new NumericTextField(this.field_22793, this.field_22789 / 2 + 20, 0, 40, 20, -5, 5, InventoryHUD.getConfig().getPotGap(), tf -> GapChanged())), 3);
    this.AlphaField.setMaxStringLength(5);
    this.GapField.setMaxStringLength(3);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.POT_TOGGLE.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.POT_TOGGLE_TT, x, y)), 0);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.POT_ALPHA.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.POT_ALPHA_TT, x, y)), 1);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.POT_MINI.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.POT_MINI_TT, x, y)), 2);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.POT_GAP.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.POT_GAP_TT, x, y)), 3);
  }
  
  private void AlphaChanged() {
    int a;
    try {
      a = Integer.parseInt(this.AlphaField.getText());
    } catch (NumberFormatException ex) {
      a = 100;
    } 
    InventoryHUDGui.potAlpha = a / 100.0F;
    InventoryHUD.getConfig().setPotAlpha(a);
    InventoryHUD.save();
  }
  
  private void GapChanged() {
    int a;
    try {
      a = Integer.parseInt(this.GapField.getText());
    } catch (NumberFormatException ex) {
      a = 100;
    } 
    InventoryHUDGui.potGap = a;
    InventoryHUD.getConfig().setPotGap(a);
  }
  
  private void ToggleSwitch() {
    boolean potion = !InventoryHUD.getConfig().getPot();
    InventoryHUD.PotHUD = potion;
    InventoryHUD.getConfig().setPot(potion);
    String toggle = potion ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.ToggleBtn.setMessage(toggle);
  }
  
  private void MiniModeSwitch() {
    boolean mini = !InventoryHUD.getConfig().getPotMini();
    InventoryHUDGui.potMini = mini;
    InventoryHUD.getConfig().setPotMini(mini);
    this.MiniModeBtn.setMessage(mini + "");
    InventoryHUDGui.PotPosChanged();
  }
}
