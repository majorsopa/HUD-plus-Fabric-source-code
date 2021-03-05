package dlovin.inventoryhud.config.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import dlovin.inventoryhud.utils.Color4F;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_339;
import net.minecraft.class_4587;

public class ConfigWidget extends class_339 {
  private final class_310 mc;
  
  protected class_2960 resourceLocation;
  
  protected class_2960 icon;
  
  protected float texOffX;
  
  protected float texOffY;
  
  protected float scale;
  
  protected Color4F color;
  
  protected boolean isShow;
  
  protected boolean isDisableable;
  
  private final List<ConfigWidgetListener> listeners = new ArrayList<>();
  
  private final List<ConfigWidgetPosListener> posListeners = new ArrayList<>();
  
  private double d_x;
  
  private double d_y;
  
  public ConfigWidget(int xIn, int yIn, int widthIn, int heightIn, float scaleIn, String msg, boolean isShow) {
    this(xIn, yIn, widthIn, heightIn, scaleIn, msg, isShow, true);
  }
  
  public ConfigWidget(int xIn, int yIn, int widthIn, int heightIn, float scaleIn, String msg, boolean isShow, boolean isDisableable) {
    super(xIn, yIn, (int)(widthIn * scaleIn), (int)(heightIn * scaleIn), (class_2561)new class_2585(msg));
    this.d_x = xIn;
    this.d_y = yIn;
    this.isShow = isShow;
    this.scale = scaleIn;
    this.mc = class_310.method_1551();
    this.isDisableable = isDisableable;
  }
  
  public void addListener(ConfigWidgetListener act) {
    this.listeners.add(act);
  }
  
  public void addPosListener(ConfigWidgetPosListener act) {
    this.posListeners.add(act);
  }
  
  public boolean getShow() {
    return this.isShow;
  }
  
  public void initTextureValues(int xTexStartIn, int yTexStartIn, Color4F color, class_2960 resourceLocationIn) {
    initTextureValues(xTexStartIn, yTexStartIn, color, resourceLocationIn, (class_2960)null);
  }
  
  public void initTextureValues(int xTexStartIn, int yTexStartIn, Color4F color, class_2960 resourceLocationIn, class_2960 iconIn) {
    this.resourceLocation = resourceLocationIn;
    this.texOffX = xTexStartIn * this.scale;
    this.texOffY = yTexStartIn * this.scale;
    this.color = color;
    this.icon = iconIn;
  }
  
  public void method_25359(class_4587 mat, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
    this.mc.method_1531().method_22813(this.resourceLocation);
    RenderSystem.disableDepthTest();
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    RenderSystem.color4f(this.color.r, this.color.g, this.color.b, this.color.a);
    this;
    method_25290(mat, this.field_22760, this.field_22761, this.texOffX, this.texOffY, this.field_22758, this.field_22759, (int)(this.scale * 256.0F), (int)(this.scale * 256.0F));
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    if (this.icon != null) {
      this.mc.method_1531().method_22813(this.icon);
      method_25290(mat, this.field_22760, this.field_22761, 16.0F, 16.0F, 16, 16, 16, 16);
    } 
    if (this.isDisableable)
      if (this.isShow) {
        method_25302(mat, this.field_22760 + this.field_22758 - 12, this.field_22761 + 2, 162, 0, 10, 10);
      } else {
        method_25302(mat, this.field_22760 + this.field_22758 - 12, this.field_22761 + 2, 172, 0, 10, 10);
      }  
    RenderSystem.disableBlend();
    RenderSystem.disableAlphaTest();
    RenderSystem.enableDepthTest();
    drawMiddleAlignedString(mat, this.mc.field_1772, method_25369().method_10851(), this.field_22760 + this.field_22758 / 2, this.field_22761 + this.field_22759 / 2 - 3, 16777215);
  }
  
  public void method_25357(double p_onRelease_1_, double p_onRelease_3_) {
    this.d_x = this.field_22760;
    this.d_y = this.field_22761;
  }
  
  public boolean method_25402(double x, double y, int p_mouseClicked_5_) {
    if (this.field_22763 && this.field_22764 && 
      method_25351(p_mouseClicked_5_)) {
      boolean flag = method_25361(x, y);
      if (flag) {
        if (this.isDisableable && x >= (this.field_22760 + this.field_22758 - 12) && x < (this.field_22758 + this.field_22760 - 2) && y >= (this.field_22761 + 2) && y < (this.field_22761 + 12)) {
          method_25354(this.mc.method_1483());
          this.isShow = !this.isShow;
          for (ConfigWidgetListener hl : this.listeners)
            hl.onShowChanged(); 
        } 
        method_25348(x, y);
        return true;
      } 
    } 
    return false;
  }
  
  protected void method_25349(double p_onDrag_1_, double p_onDrag_3_, double p_onDrag_5_, double p_onDrag_7_) {
    super.method_25349(p_onDrag_1_, p_onDrag_3_, p_onDrag_5_, p_onDrag_7_);
    this.d_x += p_onDrag_5_;
    this.d_y += p_onDrag_7_;
    if (this.d_x + (this.field_22758 / 2) <= (this.mc.field_1755.field_22789 / 2 - 10) || this.d_x + (this.field_22758 / 2) >= (this.mc.field_1755.field_22789 / 2 + 10)) {
      if (this.d_x < 0.0D) {
        this.field_22760 = 0;
      } else if (this.d_x > (this.mc.field_1755.field_22789 - this.field_22758)) {
        this.field_22760 = this.mc.field_1755.field_22789 - this.field_22758;
      } else {
        this.field_22760 = (int)this.d_x;
      } 
    } else {
      this.field_22760 = this.mc.field_1755.field_22789 / 2 - this.field_22758 / 2;
    } 
    if (this.d_y + (this.field_22759 / 2) <= (this.mc.field_1755.field_22790 / 2 - 10) || this.d_y + (this.field_22759 / 2) >= (this.mc.field_1755.field_22790 / 2 + 10)) {
      if (this.d_y < 0.0D) {
        this.field_22761 = 0;
      } else if (this.d_y > (this.mc.field_1755.field_22790 - this.field_22759)) {
        this.field_22761 = this.mc.field_1755.field_22790 - this.field_22759;
      } else {
        this.field_22761 = (int)this.d_y;
      } 
    } else {
      this.field_22761 = this.mc.field_1755.field_22790 / 2 - this.field_22759 / 2;
    } 
    for (ConfigWidgetPosListener hl : this.posListeners)
      hl.onPosChanged(this); 
  }
  
  private void drawMiddleAlignedString(class_4587 mat, class_327 fontRenderer, String text, int x, int y, int color) {
    fontRenderer.method_1720(mat, text, (x - fontRenderer.method_1727(text) / 2), y, color);
  }
  
  public static interface ConfigWidgetPosListener {
    void onPosChanged(ConfigWidget param1ConfigWidget);
  }
  
  public static interface ConfigWidgetListener {
    void onShowChanged();
  }
}
