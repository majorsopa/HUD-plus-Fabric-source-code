package dlovin.inventoryhud.config.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_4587;

public class CustomButton extends ButtonWidget {
  private class_2960 icon;
  
  private final class_2960 BG = new class_2960("inventoryhud", "textures/gui/btn_bg.png");
  
  private boolean pressed;
  
  public CustomButton(int p_i232256_1_, int p_i232256_2_, int p_i232256_3_, int p_i232256_4_, String p_i232256_5_, class_2960 icon, boolean pressed) {
    super(p_i232256_1_, p_i232256_2_, p_i232256_3_, p_i232256_4_, p_i232256_5_);
    this.icon = icon;
    this.pressed = pressed;
  }
  
  public CustomButton(int p_i232256_1_, int p_i232256_2_, int p_i232256_3_, int p_i232256_4_, String p_i232256_5_, class_2960 icon, boolean pressed, Widget.ITooltip iTooltip) {
    super(p_i232256_1_, p_i232256_2_, p_i232256_3_, p_i232256_4_, p_i232256_5_, iTooltip);
    this.icon = icon;
    this.pressed = pressed;
  }
  
  private void renderBtnBg(class_4587 mat, int offsetX, int offsetY) {
    this;
    method_25293(mat, this.x, this.y, 2, 2, (0 + offsetX), (0 + offsetY), 2, 2, 32, 32);
    this;
    method_25293(mat, this.x + this.width - 2, this.y, 2, 2, (14 + offsetX), (0 + offsetY), 2, 2, 32, 32);
    this;
    method_25293(mat, this.x, this.y + this.height - 2, 2, 2, (0 + offsetX), (14 + offsetY), 2, 2, 32, 32);
    this;
    method_25293(mat, this.x + this.width - 2, this.y + this.height - 2, 2, 2, (14 + offsetX), (14 + offsetY), 2, 2, 32, 32);
    this;
    method_25293(mat, this.x + 2, this.y, this.width - 4, 2, (2 + offsetX), (0 + offsetY), 12, 2, 32, 32);
    this;
    method_25293(mat, this.x + this.width - 2, this.y + 2, 2, this.height - 4, (14 + offsetX), (2 + offsetY), 2, 12, 32, 32);
    this;
    method_25293(mat, this.x + 2, this.y + this.height - 2, this.width - 4, 2, (2 + offsetX), (14 + offsetY), 12, 2, 32, 32);
    this;
    method_25293(mat, this.x, this.y + 2, 2, this.height - 4, (0 + offsetX), (2 + offsetY), 2, 12, 32, 32);
    this;
    method_25293(mat, this.x + 2, this.y + 2, this.width - 4, this.height - 4, (2 + offsetX), (2 + offsetY), 12, 12, 32, 32);
  }
  
  private void renderPressedBg(class_4587 mat, int offsetX, int offsetY) {
    this;
    method_25293(mat, this.x - 3, this.y - 3, 3, 3, (0 + offsetX), (0 + offsetY), 3, 3, 32, 32);
    this;
    method_25293(mat, this.x + this.width, this.y - 3, 3, 3, (13 + offsetX), (0 + offsetY), 3, 3, 32, 32);
    this;
    method_25293(mat, this.x - 3, this.y + this.height, 3, 3, (0 + offsetX), (13 + offsetY), 3, 3, 32, 32);
    this;
    method_25293(mat, this.x + this.width, this.y + this.height, 3, 3, (13 + offsetX), (13 + offsetY), 3, 3, 32, 32);
    this;
    method_25293(mat, this.x, this.y - 3, this.width, 3, (3 + offsetX), (0 + offsetY), 10, 3, 32, 32);
    this;
    method_25293(mat, this.x + this.width, this.y, 3, this.height, (13 + offsetX), (3 + offsetY), 3, 10, 32, 32);
    this;
    method_25293(mat, this.x, this.y + this.height, this.width, 3, (3 + offsetX), (13 + offsetY), 10, 3, 32, 32);
    this;
    method_25293(mat, this.x - 3, this.y, 3, this.height, (0 + offsetX), (3 + offsetY), 3, 10, 32, 32);
    this;
    method_25293(mat, this.x, this.y, this.width, this.height, (3 + offsetX), (3 + offsetY), 10, 10, 32, 32);
  }
  
  public void method_25394(class_4587 mat, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
    super.method_25394(mat, p_renderButton_1_, p_renderButton_2_, p_renderButton_3_);
    class_310 mc = class_310.method_1551();
    mc.method_1531().method_22813(this.BG);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    if (this.pressed)
      renderPressedBg(mat, 16, 16); 
    if (this.hovered) {
      renderBtnBg(mat, 16, 0);
    } else {
      renderBtnBg(mat, 0, 0);
    } 
    if (this.icon != null) {
      mc.method_1531().method_22813(this.icon);
      this;
      method_25290(mat, this.x + this.width / 2 - 16, this.y + this.height / 2 - 16, 32.0F, 32.0F, 32, 32, 32, 32);
    } else {
      drawCenterAlignedString(mat, mc.field_1772, getMessage(), this.x + this.width / 2, this.y + this.height / 2, -1);
    } 
    RenderSystem.disableBlend();
    RenderSystem.disableAlphaTest();
  }
  
  public boolean method_25402(double mx, double my, int md) {
    if (this.pressed)
      return false; 
    return super.method_25402(mx, my, md);
  }
  
  public void drawCenterAlignedString(class_4587 mat, class_327 fontRenderer, String text, int x, int y, int color) {
    fontRenderer.method_1720(mat, text, (x - fontRenderer.method_1727(text) / 2), y - 4.0F, color);
  }
}
