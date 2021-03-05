package dlovin.inventoryhud.config.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1109;
import net.minecraft.class_1113;
import net.minecraft.class_1144;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_4587;

public class CheckBox extends Widget {
  protected final IPressable onChange;
  
  public boolean checked;
  
  private final class_2960 BG;
  
  public CheckBox(int p_i232254_1_, int p_i232254_2_, int p_i232254_3_, int p_i232254_4_, IPressable onpressed, boolean checked, class_2960 bg) {
    super(p_i232254_1_, p_i232254_2_, p_i232254_3_, p_i232254_4_, "");
    this.onChange = onpressed;
    this.checked = checked;
    this.BG = bg;
  }
  
  public boolean method_25402(double mouseX, double mouseY, int button) {
    if (this.hovered) {
      playDownSound(class_310.method_1551().method_1483());
      this.checked = !this.checked;
      this.onChange.onChanged(this);
      return true;
    } 
    return false;
  }
  
  public void playDownSound(class_1144 soundManager) {
    soundManager.method_4873((class_1113)class_1109.method_4758(class_3417.field_15015, 1.0F));
  }
  
  public void method_25394(class_4587 mat, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
    super.method_25394(mat, p_230430_2_, p_230430_3_, p_230430_4_);
    class_310.method_1551().method_1531().method_22813(this.BG);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    if (this.hovered) {
      renderBtn(mat, 20, 0);
    } else {
      renderBtn(mat, 0, 0);
    } 
    if (this.checked) {
      renderBtn(mat, 20, 20);
    } else {
      renderBtn(mat, 0, 20);
    } 
    RenderSystem.disableBlend();
    RenderSystem.disableAlphaTest();
  }
  
  private void renderBtn(class_4587 mat, int offsetX, int offsetY) {
    this;
    method_25293(mat, this.x, this.y, 2, 2, (0 + offsetX), (0 + offsetY), 2, 2, 40, 40);
    this;
    method_25293(mat, this.x + this.width - 2, this.y, 2, 2, (18 + offsetX), (0 + offsetY), 2, 2, 40, 40);
    this;
    method_25293(mat, this.x, this.y + this.height - 2, 2, 2, (0 + offsetX), (18 + offsetY), 2, 2, 40, 40);
    this;
    method_25293(mat, this.x + this.width - 2, this.y + this.height - 2, 2, 2, (18 + offsetX), (18 + offsetY), 2, 2, 40, 40);
    this;
    method_25293(mat, this.x + 2, this.y, this.width - 4, 2, (2 + offsetX), (0 + offsetY), 16, 2, 40, 40);
    this;
    method_25293(mat, this.x + this.width - 2, this.y + 2, 2, this.height - 4, (18 + offsetX), (2 + offsetY), 2, 16, 40, 40);
    this;
    method_25293(mat, this.x + 2, this.y + this.height - 2, this.width - 4, 2, (2 + offsetX), (18 + offsetY), 16, 2, 40, 40);
    this;
    method_25293(mat, this.x, this.y + 2, 2, this.height - 4, (0 + offsetX), (2 + offsetY), 2, 16, 40, 40);
    this;
    method_25293(mat, this.x + 2, this.y + 2, this.width - 4, this.height - 4, (2 + offsetX), (2 + offsetY), 16, 16, 40, 40);
  }
  
  public static interface IPressable {
    void onChanged(CheckBox param1CheckBox);
  }
}
