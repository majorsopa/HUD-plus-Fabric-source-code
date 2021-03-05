package dlovin.inventoryhud.config.gui;

import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.config.InvConfig;
import dlovin.inventoryhud.config.widgets.CheckBox;
import dlovin.inventoryhud.config.widgets.CustomButton;
import dlovin.inventoryhud.config.widgets.NumericTextField;
import dlovin.inventoryhud.config.widgets.TextWidget;
import dlovin.inventoryhud.config.widgets.Widget;
import dlovin.inventoryhud.gui.InventoryHUDGui;
import dlovin.inventoryhud.references.Translation;
import dlovin.inventoryhud.utils.WidgetAligns;
import net.minecraft.class_124;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class ArmorConfigScreen extends ConfigScreen {
  private CustomButton ToggleBtn;
  
  private CustomButton ViewBtn;
  
  private CustomButton BarsBtn;
  
  private CustomButton MoveAllBtn;
  
  private CustomButton ShowEmpty;
  
  private NumericTextField AboveField;
  
  public ArmorConfigScreen(class_437 prev, boolean inGame) {
    super(prev, 1, inGame);
  }
  
  protected void method_25426() {
    super.method_25426();
    String toggle = InventoryHUD.getConfig().getArm() ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.OptionList.addWidget((Widget)(this.ToggleBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, toggle, null, false)), 0);
    this.ToggleBtn.addListener(() -> ToggleSwitch());
    this.OptionList.addWidget((Widget)(this.AboveField = new NumericTextField(this.field_22793, this.field_22789 / 2 + 20, 0, 40, 20, 0, 100, InventoryHUD.getConfig().getArmAbove(), tf -> AboveChanged())), 1);
    this.OptionList.addWidget((Widget)new CheckBox(this.field_22789 / 2 + 20, 0, 20, 20, cb -> TypeArmorChanged(cb), InventoryHUD.getConfig().isShowArmor(), new class_2960("inventoryhud", "textures/gui/cb_arm.png")), 2);
    this.OptionList.addWidget((Widget)new CheckBox(this.field_22789 / 2 + 42, 0, 20, 20, cb -> TypeMainHandChanged(cb), InventoryHUD.getConfig().isShowMain(), new class_2960("inventoryhud", "textures/gui/cb_mh.png")), 2);
    this.OptionList.addWidget((Widget)new CheckBox(this.field_22789 / 2 + 64, 0, 20, 20, cb -> TypeOffHandChanged(cb), InventoryHUD.getConfig().isShowOff(), new class_2960("inventoryhud", "textures/gui/cb_oh.png")), 2);
    this.OptionList.addWidget((Widget)new CheckBox(this.field_22789 / 2 + 86, 0, 20, 20, cb -> TypeArrowChanged(cb), InventoryHUD.getConfig().isShowArrows(), new class_2960("inventoryhud", "textures/gui/cb_ar.png")), 2);
    this.OptionList.addWidget((Widget)new CheckBox(this.field_22789 / 2 + 108, 0, 20, 20, cb -> TypeInvChanged(cb), InventoryHUD.getConfig().isShowInv(), new class_2960("inventoryhud", "textures/gui/cb_inv.png")), 2);
    this.OptionList.addWidget((Widget)(this.ViewBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 75, 20, InventoryHUD.getConfig().getArmView().name(), null, false)), 3);
    this.ViewBtn.addListener(() -> ViewSwitch());
    this.OptionList.addWidget((Widget)(this.BarsBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().getArmBars() + "", null, false)), 4);
    this.BarsBtn.addListener(() -> BarsSwitch());
    this.OptionList.addWidget((Widget)(this.MoveAllBtn = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().getMoveAll() + "", null, false)), 5);
    this.MoveAllBtn.addListener(() -> MoveSwitch());
    this.OptionList.addWidget((Widget)(this.ShowEmpty = new CustomButton(this.field_22789 / 2 + 20, 0, 40, 20, InventoryHUD.getConfig().isShowEmpty() + "", null, false)), 6);
    this.ShowEmpty.addListener(() -> EmptySwitch());
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_EMPTY.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.ARM_EMPTY_TT, x, y)), 6);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_MOVE.getString(), this.field_22793, (w, m, x, y) -> method_30901(m, Translation.ARM_MOVE_TT, x, y)), 5);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_BARS.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.ARM_BARS_TT, x, y)), 4);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_VIEW.getString(), this.field_22793, (w, m, x, y) -> method_30901(m, Translation.ARM_VIEW_TT, x, y)), 3);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_TYPE.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.ARM_TYPE_TT, x, y)), 2);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_HIDE.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.ARM_HIDE_TT, x, y)), 1);
    this.OptionList.addWidget((Widget)new TextWidget(this.field_22789 / 2 - 20, 10, 16777215, WidgetAligns.HAlign.RIGHT, Translation.ARM_TOGGLE.getString(), this.field_22793, (w, m, x, y) -> method_25424(m, Translation.ARM_TOGGLE_TT, x, y)), 0);
    this.AboveField.setMaxStringLength(5);
  }
  
  private void TypeArmorChanged(CheckBox cb) {
    InventoryHUDGui.showArmor = cb.checked;
    InventoryHUD.getConfig().setShowArmor(cb.checked);
  }
  
  private void TypeMainHandChanged(CheckBox cb) {
    InventoryHUDGui.showMain = cb.checked;
    InventoryHUD.getConfig().setShowMain(cb.checked);
  }
  
  private void TypeOffHandChanged(CheckBox cb) {
    InventoryHUDGui.showOff = cb.checked;
    InventoryHUD.getConfig().setShowOff(cb.checked);
  }
  
  private void TypeArrowChanged(CheckBox cb) {
    InventoryHUDGui.showArrows = cb.checked;
    InventoryHUD.getConfig().setShowArrows(cb.checked);
  }
  
  private void TypeInvChanged(CheckBox cb) {
    InventoryHUDGui.showInv = cb.checked;
    InventoryHUD.getConfig().setShowInv(cb.checked);
  }
  
  private void AboveChanged() {
    int a;
    try {
      a = Integer.parseInt(this.AboveField.getText());
    } catch (NumberFormatException ex) {
      a = 100;
    } 
    InventoryHUDGui.armAbove = a;
    InventoryHUD.getConfig().setArmAbove(a);
  }
  
  private void MoveSwitch() {
    Boolean bd = Boolean.valueOf(!InventoryHUD.getConfig().getMoveAll());
    InventoryHUDGui.moveAll = bd.booleanValue();
    InventoryHUD.getConfig().setMoveAll(bd.booleanValue());
    this.MoveAllBtn.setMessage(bd.toString());
  }
  
  private void EmptySwitch() {
    Boolean bd = Boolean.valueOf(!InventoryHUD.getConfig().isShowEmpty());
    InventoryHUDGui.showEmpty = bd.booleanValue();
    InventoryHUD.getConfig().setShowEmpty(bd.booleanValue());
    this.ShowEmpty.setMessage(bd.toString());
  }
  
  private void ViewSwitch() {
    InvConfig.ArmorView at = InventoryHUD.getConfig().getArmView();
    switch (at) {
      case PERCENTAGE:
        at = InvConfig.ArmorView.DAMAGE;
        break;
      case DAMAGE:
        at = InvConfig.ArmorView.DAMAGE_LEFT;
        break;
      case DAMAGE_LEFT:
        at = InvConfig.ArmorView.OFF;
        break;
      case OFF:
        at = InvConfig.ArmorView.PERCENTAGE;
        break;
    } 
    InventoryHUDGui.armView = at;
    InventoryHUD.getConfig().setArmView(at);
    this.ViewBtn.setMessage(at.name());
  }
  
  private void ToggleSwitch() {
    Boolean armor = Boolean.valueOf(!InventoryHUD.getConfig().getArm());
    InventoryHUD.ArmHUD = armor.booleanValue();
    InventoryHUD.getConfig().setArm(armor.booleanValue());
    String armString = armor.booleanValue() ? (class_124.field_1077 + "ON") : (class_124.field_1079 + "OFF");
    this.ToggleBtn.setMessage(armString);
  }
  
  private void BarsSwitch() {
    Boolean bars = Boolean.valueOf(!InventoryHUD.getConfig().getArmBars());
    InventoryHUDGui.armBars = bars.booleanValue();
    InventoryHUD.getConfig().setArmBars(bars.booleanValue());
    this.BarsBtn.setMessage(bars.toString());
  }
}
