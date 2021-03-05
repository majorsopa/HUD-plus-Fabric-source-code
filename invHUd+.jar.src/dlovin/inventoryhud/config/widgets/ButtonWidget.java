package dlovin.inventoryhud.config.widgets;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_1144;
import net.minecraft.class_310;
import net.minecraft.class_3417;

public class ButtonWidget extends Widget {
  private List<ButtonClickListener> listeners = new ArrayList<>();
  
  public void addListener(ButtonClickListener act) {
    this.listeners.add(act);
  }
  
  public ButtonWidget(int x, int y, int widthIn, int heightIn, String buttonText) {
    super(x, y, widthIn, heightIn, buttonText);
  }
  
  public ButtonWidget(int x, int y, int widthIn, int heightIn, String buttonText, Widget.ITooltip iTooltip) {
    super(x, y, widthIn, heightIn, buttonText, iTooltip);
  }
  
  public boolean method_25402(double mouseX, double mouseY, int button) {
    if (this.hovered) {
      playDownSound(class_310.method_1551().method_1483());
      for (ButtonClickListener hl : this.listeners)
        hl.onClick(); 
      return true;
    } 
    return false;
  }
  
  public void playDownSound(class_1144 soundManager) {
    soundManager.method_4873((class_1113)class_1109.method_4758(class_3417.field_15015, 1.0F));
  }
  
  public static interface ButtonClickListener {
    void onClick();
  }
}
