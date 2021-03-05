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

public class InventoryConfigScreen extends ConfigScreen {
  private CustomButton ToggleBtn;
  
  private CustomButton MiniModeBtn;
  
  private CustomButton VerticalModeBtn;
  
  private NumericTextField AlphaField;
  
  public InventoryConfigScreen(class_437 prev, boolean inGame) {
    super(prev, 0, inGame);
  }
  
  protected void method_25426() {
    super.method_25426();
    String toggle = InventoryHUD.getConfig().getInv() ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.OptionList.addWidget((Widget)(this.ToggleBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, toggle, null, false)), 0);
    this.ToggleBtn.addListener(() -> ToggleSwitch());
    this.OptionList.addWidget((Widget)(this.MiniModeBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().getInvMini() + "", null, false)), 2);
    this.MiniModeBtn.addListener(() -> MiniModeSwitch());
    this.OptionList.addWidget((Widget)(this.VerticalModeBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().getInvVert() + "", null, false)), 1);
    this.VerticalModeBtn.addListener(() -> VerticalModeSwitch());
    this.OptionList.addWidget((Widget)(this.AlphaField = new NumericTextField(this.field_22793, this.field_22789 / 2 + 20, 0, 40, 20, 0, 100, InventoryHUD.getConfig().getInvAlpha(), tf -> AlphaChanged())), 3);
    this.AlphaField.setMaxStringLength(5);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.INV_ALPHA.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.INV_ALPHA_TT, x, y)), 3);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.INV_MINI.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.INV_MINI_TT, x, y)), 2);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.INV_VERT.getString(), this.field_22793, (w, m, x, y) -> method_30901(m, Translation.INV_VERT_TT, x, y)), 1);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.INV_TOGGLE.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.INV_TOGGLE_TT, x, y)), 0);
  }
  
  private void AlphaChanged() {
    int a;
    try {
      a = Integer.parseInt(this.AlphaField.getText());
    } catch (NumberFormatException ex) {
      a = 100;
    } 
    InventoryHUDGui.invAlpha = a / 100.0F;
    InventoryHUD.getConfig().setInvAlpha(a);
  }
  
  private void MiniModeSwitch() {
    Boolean mm = Boolean.valueOf(!InventoryHUD.getConfig().getInvMini());
    InventoryHUDGui.invMini = mm.booleanValue();
    InventoryHUD.getConfig().setInvMini(mm.booleanValue());
    this.MiniModeBtn.setMessage(mm.toString());
  }
  
  private void VerticalModeSwitch() {
    Boolean vm = Boolean.valueOf(!InventoryHUD.getConfig().getInvVert());
    InventoryHUDGui.invVert = vm.booleanValue();
    InventoryHUD.getConfig().setInvVert(vm.booleanValue());
    this.VerticalModeBtn.setMessage(vm.toString());
  }
  
  private void ToggleSwitch() {
    Boolean bd = Boolean.valueOf(!InventoryHUD.getConfig().getInv());
    InventoryHUD.InvHUD = bd.booleanValue();
    InventoryHUD.getConfig().setInv(bd.booleanValue());
    String toggle = bd.booleanValue() ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.ToggleBtn.setMessage(toggle);
  }
}
