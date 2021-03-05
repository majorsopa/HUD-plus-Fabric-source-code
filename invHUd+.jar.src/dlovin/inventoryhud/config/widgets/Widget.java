package dlovin.inventoryhud.config.widgets;

import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_4587;

public class Widget extends class_332 implements class_4068, class_364 {
  private static final ITooltip field_1 = (w, m, x, y) -> {
    
    };
  
  public int x;
  
  public int y;
  
  public int width;
  
  public int height;
  
  private String msg;
  
  protected boolean hovered;
  
  private final ITooltip iTooltip;
  
  public Widget(int x, int y, int width, int height, String msg) {
    this(x, y, width, height, msg, field_1);
  }
  
  public Widget(int x, int y, int width, int height, String msg, ITooltip iTooltip) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.msg = msg;
    this.iTooltip = iTooltip;
  }
  
  public void setMessage(String msg) {
    this.msg = msg;
  }
  
  public String getMessage() {
    return this.msg;
  }
  
  public void renderTooltip(class_4587 mat, int mx, int my) {
    if (this.hovered)
      this.iTooltip.onTooltip(this, mat, mx, my); 
  }
  
  public void method_25394(class_4587 matrices, int mouseX, int mouseY, float delta) {
    this.hovered = (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height);
  }
  
  public void method_16014(double mouseX, double mouseY) {}
  
  public boolean method_25402(double mouseX, double mouseY, int button) {
    return false;
  }
  
  public boolean method_25406(double mouseX, double mouseY, int button) {
    return false;
  }
  
  public boolean method_25403(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
    return false;
  }
  
  public boolean method_25401(double mouseX, double mouseY, double amount) {
    return false;
  }
  
  public boolean method_25404(int keyCode, int scanCode, int modifiers) {
    return false;
  }
  
  public boolean method_16803(int keyCode, int scanCode, int modifiers) {
    return false;
  }
  
  public boolean method_25400(char chr, int keyCode) {
    return false;
  }
  
  public boolean method_25407(boolean lookForwards) {
    return false;
  }
  
  public boolean method_25405(double mouseX, double mouseY) {
    return false;
  }
  
  public static interface ITooltip {
    void onTooltip(Widget param1Widget, class_4587 param1class_4587, int param1Int1, int param1Int2);
  }
}
