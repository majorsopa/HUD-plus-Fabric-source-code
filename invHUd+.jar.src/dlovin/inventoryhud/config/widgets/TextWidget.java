package dlovin.inventoryhud.config.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import dlovin.inventoryhud.utils.WidgetAligns;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_4587;

public class TextWidget extends Widget {
  private int color;
  
  private class_327 fontRenderer;
  
  private boolean withTooltip;
  
  private static final class_2960 TT_ICON = new class_2960("inventoryhud", "textures/gui/tt_icon.png");
  
  public TextWidget(int x, int y, int color, WidgetAligns.HAlign align, String text, class_327 fontRenderer, Widget.ITooltip tooltip) {
    super(x - 5, y - 9, fontRenderer.method_1727(text) + 10, 18, text, tooltip);
    switch (align) {
      case RIGHT:
        this.x -= this.width - 10;
        break;
      case MIDDLE:
        this.x -= (this.width - 10) / 2;
        break;
    } 
    this.color = color;
    this.fontRenderer = fontRenderer;
    this.withTooltip = true;
  }
  
  public void method_25394(class_4587 mat, int mouseX, int mouseY, float partialTick) {
    super.method_25394(mat, mouseX, mouseY, partialTick);
    this.fontRenderer.method_1720(mat, getMessage(), (this.x + 5), (this.y + 5), this.color);
    if (this.withTooltip) {
      class_310.method_1551().method_1531().method_22813(TT_ICON);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableAlphaTest();
      RenderSystem.enableBlend();
      this;
      method_25290(mat, this.x + this.width - 8, this.y, 8.0F, 8.0F, 8, 8, 8, 8);
      RenderSystem.disableBlend();
      RenderSystem.disableAlphaTest();
    } 
  }
  
  public boolean method_25402(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
    return false;
  }
}
