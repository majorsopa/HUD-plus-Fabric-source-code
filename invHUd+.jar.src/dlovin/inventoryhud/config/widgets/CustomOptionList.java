package dlovin.inventoryhud.config.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_350;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class CustomOptionList extends class_350 {
  private static final int ITEM_HEIGHT = 30;
  
  private final List<Widget> widgets = new ArrayList<>();
  
  private final class_437 parent;
  
  private int maxHeight;
  
  private int scroll = 0;
  
  private double tmp_scroll = 0.0D;
  
  private boolean isDragging = false;
  
  private final class_2960 SCROLL = new class_2960("inventoryhud", "textures/gui/scroll.png");
  
  public CustomOptionList(class_310 p_i51130_1_, class_437 screen) {
    super(p_i51130_1_, screen.field_22789, screen.field_22790, 55, screen.field_22790 - 32, 30);
    this.parent = screen;
  }
  
  public void addWidget(Widget widget, int row) {
    if (this.maxHeight < (row + 1) * 30 + 10)
      this.maxHeight = (row + 1) * 30 + 10; 
    widget.y = this.field_19085 + 10 + 30 * row + widget.y;
    this.widgets.add(widget);
  }
  
  public List<Widget> getWidgets() {
    return this.widgets;
  }
  
  public boolean method_25401(double p_231043_1_, double p_231043_3_, double p_231043_5_) {
    double tmp = 0.0D;
    if (this.maxHeight > this.field_19086 - this.field_19085) {
      tmp = p_231043_5_ * 5.0D;
      int diff = this.maxHeight - this.field_19086 - this.field_19085;
      this.scroll = (int)(this.scroll - tmp);
      if (this.scroll < 0) {
        tmp += this.scroll;
        this.scroll = 0;
      } else if (this.scroll > diff) {
        tmp += (this.scroll - diff);
        this.scroll = diff;
      } 
    } 
    for (Widget w : this.widgets) {
      if (w instanceof NumericTextField && (
        (NumericTextField)w).isFocused())
        ((NumericTextField)w).setFocused2(false); 
      w.y = (int)(w.y + tmp);
    } 
    return true;
  }
  
  public boolean method_25403(double p_231045_1_, double p_231045_3_, int p_231045_5_, double p_231045_6_, double p_231045_8_) {
    if (this.isDragging) {
      int height = this.field_19086 - this.field_19085;
      double scale = height / this.maxHeight;
      this.tmp_scroll += p_231045_8_ / scale;
      int tmp = (int)this.tmp_scroll;
      this.tmp_scroll -= tmp;
      int diff = this.maxHeight - this.field_19086 - this.field_19085;
      this.scroll += tmp;
      if (this.scroll < 0) {
        tmp -= this.scroll;
        this.scroll = 0;
      } else if (this.scroll > diff) {
        tmp -= this.scroll - diff;
        this.scroll = diff;
      } 
      for (Widget w : this.widgets) {
        if (w instanceof NumericTextField && (
          (NumericTextField)w).isFocused())
          ((NumericTextField)w).setFocused2(false); 
        w.y -= tmp;
      } 
      return true;
    } 
    return false;
  }
  
  public boolean method_25402(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
    if (!method_25405(p_231044_1_, p_231044_3_))
      return false; 
    if (this.maxHeight > this.field_19086 - this.field_19085 && 
      p_231044_5_ == 0) {
      int pos = (this.field_22742 > 309) ? (this.field_22742 / 2 + 150) : (this.field_22742 - 9);
      int height = this.field_19086 - this.field_19085;
      double scale = height / this.maxHeight;
      height = (int)(height * scale);
      int yPos = this.field_19085 + (int)(this.scroll * scale);
      if (isOver(p_231044_1_, p_231044_3_, pos + 1, pos + 8, yPos + 1, yPos + height - 1))
        this.isDragging = true; 
    } 
    for (Widget w : this.widgets) {
      if (w.hovered || w instanceof NumericTextField)
        w.method_25402(p_231044_1_, p_231044_3_, p_231044_5_); 
    } 
    return false;
  }
  
  public boolean method_25406(double p_231048_1_, double p_231048_3_, int p_231048_5_) {
    this.isDragging = false;
    return false;
  }
  
  public boolean method_25400(char p_231042_1_, int p_231042_2_) {
    for (Widget w : this.widgets) {
      if (w instanceof NumericTextField && (
        (NumericTextField)w).isFocused()) {
        w.method_25400(p_231042_1_, p_231042_2_);
        return true;
      } 
    } 
    return false;
  }
  
  public boolean method_25404(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
    for (Widget w : this.widgets) {
      if (w instanceof NumericTextField && (
        (NumericTextField)w).isFocused()) {
        w.method_25404(p_231046_1_, p_231046_2_, p_231046_3_);
        return true;
      } 
    } 
    super.method_25404(p_231046_1_, p_231046_2_, p_231046_3_);
    return false;
  }
  
  private void renderScrollBG(class_4587 mat, int xPos, int height) {
    this;
    method_25293(mat, xPos, this.field_19085, 3, 3, 0.0F, 0.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, this.field_19085, 3, 3, 13.0F, 0.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos, this.field_19086 - 3, 3, 3, 0.0F, 13.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, this.field_19086 - 3, 3, 3, 13.0F, 13.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 3, this.field_19085, 3, 3, 3.0F, 0.0F, 10, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, this.field_19085 + 3, 3, height - 6, 13.0F, 3.0F, 3, 10, 32, 32);
    this;
    method_25293(mat, xPos + 3, this.field_19086 - 3, 3, 3, 3.0F, 13.0F, 10, 3, 32, 32);
    this;
    method_25293(mat, xPos, this.field_19085 + 3, 3, height - 6, 0.0F, 3.0F, 3, 10, 32, 32);
    this;
    method_25293(mat, xPos + 3, this.field_19085 + 3, 3, height - 6, 3.0F, 3.0F, 10, 10, 32, 32);
  }
  
  private void renderScroll(class_4587 mat, int xPos, int height, int pos) {
    this;
    method_25293(mat, xPos, pos, 3, 3, 16.0F, 0.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, pos, 3, 3, 29.0F, 0.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos, -3 + height + pos, 3, 3, 16.0F, 13.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, -3 + height + pos, 3, 3, 29.0F, 13.0F, 3, 3, 32, 32);
    this;
    method_25293(mat, xPos + 3, pos, 3, 3, 19.0F, 0.0F, 10, 3, 32, 32);
    this;
    method_25293(mat, xPos + 6, 3 + pos, 3, (height - 6 > 0) ? (height - 6) : 0, 29.0F, 3.0F, 3, 10, 32, 32);
    this;
    method_25293(mat, xPos + 3, -3 + height + pos, 3, 3, 19.0F, 13.0F, 10, 3, 32, 32);
    this;
    method_25293(mat, xPos, 3 + pos, 3, (height - 6 > 0) ? (height - 6) : 0, 16.0F, 3.0F, 3, 10, 32, 32);
    this;
    method_25293(mat, xPos + 3, 3 + pos, 3, (height - 6 > 0) ? (height - 6) : 0, 19.0F, 3.0F, 10, 10, 32, 32);
  }
  
  private boolean isOver(double p_231047_1_, double p_231047_3_, int min_x, int max_x, int min_y, int max_y) {
    return (p_231047_3_ >= min_y && p_231047_3_ <= max_y && p_231047_1_ >= min_x && p_231047_1_ <= max_x);
  }
  
  protected void method_25311(class_4587 mat, int p_238478_2_, int p_238478_3_, int p_238478_4_, int p_238478_5_, float p_238478_6_) {
    if (this.maxHeight > this.field_19086 - this.field_19085) {
      int pos = (this.field_22742 > 309) ? (this.field_22742 / 2 + 150) : (this.field_22742 - 9);
      class_310.method_1551().method_1531().method_22813(this.SCROLL);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableAlphaTest();
      RenderSystem.enableBlend();
      int height = this.field_19086 - this.field_19085;
      renderScrollBG(mat, pos, height);
      double scale = height / this.maxHeight;
      height = (int)(height * scale);
      int yPos = this.field_19085 + (int)(this.scroll * scale);
      RenderSystem.color4f(0.8F, 0.8F, 0.8F, 1.0F);
      if (isOver(p_238478_4_, p_238478_5_, pos + 1, pos + 8, yPos + 1, yPos + height - 1) && this.isDragging)
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F); 
      renderScroll(mat, pos, height, yPos);
      RenderSystem.disableBlend();
      RenderSystem.disableAlphaTest();
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    } 
    for (Widget w : this.widgets)
      w.method_25394(mat, p_238478_4_, p_238478_5_, p_238478_6_); 
  }
  
  public void renderTooltips(class_4587 mat, int x, int y) {
    if (x < this.field_19088 || x > this.field_19087 || y < this.field_19085 || y > this.field_19086)
      return; 
    for (Widget w : this.widgets)
      w.renderTooltip(mat, x, y); 
  }
}
