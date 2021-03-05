package dlovin.inventoryhud.config.widgets;

import com.google.common.base.Predicates;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import net.minecraft.class_155;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_437;
import net.minecraft.class_4493;
import net.minecraft.class_4587;

public class NumericTextField extends Widget {
  protected final IPressable onChange;
  
  private final class_327 fontRenderer;
  
  private String text;
  
  private int maxStringLength = 32;
  
  private boolean enableBackgroundDrawing = true;
  
  private boolean canLoseFocus = true;
  
  private boolean isFocused;
  
  private boolean isEnabled = true;
  
  private boolean field_212956_h;
  
  private int lineScrollOffset;
  
  private int cursorPosition;
  
  private int selectionEnd;
  
  private int enabledColor = 14737632;
  
  private int disabledColor = 7368816;
  
  private int minValue;
  
  private int maxValue;
  
  private int defValue;
  
  private String prevText;
  
  private final class_2960 BG = new class_2960("inventoryhud", "textures/gui/tf_bg.png");
  
  private Predicate<String> validator = (Predicate<String>)Predicates.alwaysTrue();
  
  private BiFunction<String, Integer, String> textFormatter;
  
  public NumericTextField(class_327 tr, int x, int y, int w, int h, int minValue, int maxValue, int defValue, IPressable onChange) {
    super(x, y, w, h, defValue + "");
    this.textFormatter = ((p_195610_0_, p_195610_1_) -> p_195610_0_);
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.defValue = defValue;
    this.prevText = defValue + "";
    this.text = this.prevText;
    this.onChange = onChange;
    this.fontRenderer = tr;
  }
  
  public void setText(String textIn) {
    if (this.validator.test(textIn)) {
      if (textIn.length() > this.maxStringLength) {
        this.text = textIn.substring(0, this.maxStringLength);
      } else {
        this.text = textIn;
      } 
      setCursorPositionEnd();
      setSelectionPos(this.cursorPosition);
      onTextChanged(textIn);
    } 
  }
  
  public String getText() {
    return this.text;
  }
  
  public String getSelectedText() {
    int i = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
    int j = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
    return this.text.substring(i, j);
  }
  
  public void writeText(String textToWrite) {
    int i = (this.cursorPosition < this.selectionEnd) ? this.cursorPosition : this.selectionEnd;
    int j = (this.cursorPosition < this.selectionEnd) ? this.selectionEnd : this.cursorPosition;
    int k = this.maxStringLength - this.text.length() - i - j;
    String s = class_155.method_644(textToWrite);
    int l = s.length();
    if (k < l) {
      s = s.substring(0, k);
      l = k;
    } 
    String s1 = (new StringBuilder(this.text)).replace(i, j, s).toString();
    try {
      int num = Integer.parseInt(s1);
      s1 = num + "";
      if (num < this.minValue) {
        s1 = this.minValue + "";
      } else if (num > this.maxValue) {
        s1 = this.maxValue + "";
      } 
    } catch (NumberFormatException ex) {
      if (!s.equalsIgnoreCase("-"))
        s1 = this.prevText; 
    } 
    if (this.validator.test(s1)) {
      this.text = s1;
      clampCursorPosition(i + l);
      setSelectionPos(this.cursorPosition);
      onTextChanged(this.text);
    } 
  }
  
  private void onTextChanged(String newText) {
    if (!newText.equals(this.prevText) && 
      isNumeric(newText)) {
      this.prevText = newText;
      this.onChange.onChanged(this);
    } 
  }
  
  private void delete(int p_212950_1_) {
    if (class_437.method_25441()) {
      deleteWords(p_212950_1_);
    } else {
      deleteFromCursor(p_212950_1_);
    } 
  }
  
  public void deleteWords(int num) {
    if (!this.text.isEmpty())
      if (this.selectionEnd != this.cursorPosition) {
        writeText("");
      } else {
        deleteFromCursor(getNthWordFromCursor(num) - this.cursorPosition);
      }  
  }
  
  public void deleteFromCursor(int num) {
    if (!this.text.isEmpty())
      if (this.selectionEnd != this.cursorPosition) {
        writeText("");
      } else {
        int i = func_238516_r_(num);
        int j = Math.min(i, this.cursorPosition);
        int k = Math.max(i, this.cursorPosition);
        if (j != k) {
          String s = (new StringBuilder(this.text)).delete(j, k).toString();
          if (this.validator.test(s)) {
            this.text = s;
            setCursorPosition(j);
          } 
        } 
      }  
  }
  
  public int getNthWordFromCursor(int numWords) {
    return getNthWordFromPos(numWords, getCursorPosition());
  }
  
  private int getNthWordFromPos(int n, int pos) {
    return getNthWordFromPosWS(n, pos, true);
  }
  
  private int getNthWordFromPosWS(int n, int pos, boolean skipWs) {
    int i = pos;
    boolean flag = (n < 0);
    int j = Math.abs(n);
    for (int k = 0; k < j; k++) {
      if (!flag) {
        int l = this.text.length();
        i = this.text.indexOf(' ', i);
        if (i == -1) {
          i = l;
        } else {
          while (skipWs && i < l && this.text.charAt(i) == ' ')
            i++; 
        } 
      } else {
        while (skipWs && i > 0 && this.text.charAt(i - 1) == ' ')
          i--; 
        while (i > 0 && this.text.charAt(i - 1) != ' ')
          i--; 
      } 
    } 
    return i;
  }
  
  public void moveCursorBy(int num) {
    setCursorPosition(func_238516_r_(num));
  }
  
  public void setCursorPosition(int pos) {
    clampCursorPosition(pos);
    if (!this.field_212956_h)
      setSelectionPos(this.cursorPosition); 
    onTextChanged(this.text);
  }
  
  private int func_238516_r_(int p_238516_1_) {
    return func_240980_a_(this.text, this.cursorPosition, p_238516_1_);
  }
  
  public static int func_240980_a_(String p_240980_0_, int p_240980_1_, int p_240980_2_) {
    int i = p_240980_0_.length();
    if (p_240980_2_ >= 0) {
      for (int j = 0; p_240980_1_ < i && j < p_240980_2_; j++) {
        if (Character.isHighSurrogate(p_240980_0_.charAt(p_240980_1_++)) && p_240980_1_ < i && Character.isLowSurrogate(p_240980_0_.charAt(p_240980_1_)))
          p_240980_1_++; 
      } 
    } else {
      for (int k = p_240980_2_; p_240980_1_ > 0 && k < 0; k++) {
        p_240980_1_--;
        if (Character.isLowSurrogate(p_240980_0_.charAt(p_240980_1_)) && p_240980_1_ > 0 && Character.isHighSurrogate(p_240980_0_.charAt(p_240980_1_ - 1)))
          p_240980_1_--; 
      } 
    } 
    return p_240980_1_;
  }
  
  public void clampCursorPosition(int pos) {
    this.cursorPosition = class_3532.method_15340(pos, 0, this.text.length());
  }
  
  public void setCursorPositionZero() {
    setCursorPosition(0);
  }
  
  public void setCursorPositionEnd() {
    setCursorPosition(this.text.length());
  }
  
  public boolean method_25404(int p_231046_1_, int p_231046_2_, int p_231046_3_) {
    if (!canWrite())
      return false; 
    this.field_212956_h = class_437.method_25442();
    if (class_437.method_25439(p_231046_1_)) {
      setCursorPositionEnd();
      setSelectionPos(0);
      return true;
    } 
    if (class_437.method_25438(p_231046_1_)) {
      (class_310.method_1551()).field_1774.method_1455(getSelectedText());
      return true;
    } 
    if (class_437.method_25437(p_231046_1_)) {
      if (this.isEnabled && 
        isNumeric((class_310.method_1551()).field_1774.method_1460()))
        writeText((class_310.method_1551()).field_1774.method_1460()); 
      return true;
    } 
    if (class_437.method_25436(p_231046_1_)) {
      (class_310.method_1551()).field_1774.method_1455(getSelectedText());
      if (this.isEnabled)
        writeText(""); 
      return true;
    } 
    switch (p_231046_1_) {
      case 256:
        setFocused2(false);
        return true;
      case 259:
        if (this.isEnabled) {
          this.field_212956_h = false;
          delete(-1);
          this.field_212956_h = class_437.method_25442();
        } 
        return true;
      default:
        return false;
      case 261:
        if (this.isEnabled) {
          this.field_212956_h = false;
          delete(1);
          this.field_212956_h = class_437.method_25442();
        } 
        return true;
      case 262:
        if (class_437.method_25441()) {
          setCursorPosition(getNthWordFromCursor(1));
        } else {
          moveCursorBy(1);
        } 
        return true;
      case 263:
        if (class_437.method_25441()) {
          setCursorPosition(getNthWordFromCursor(-1));
        } else {
          moveCursorBy(-1);
        } 
        return true;
      case 268:
        setCursorPositionZero();
        return true;
      case 269:
        break;
    } 
    setCursorPositionEnd();
    return true;
  }
  
  public boolean canWrite() {
    return (getVisible() && this.isFocused && isEnabled());
  }
  
  public boolean method_25400(char p_231042_1_, int p_231042_2_) {
    if (!canWrite())
      return false; 
    if (isNumeric(Character.toString(p_231042_1_))) {
      if (this.isEnabled)
        writeText(Character.toString(p_231042_1_)); 
      return true;
    } 
    if (p_231042_1_ == '-') {
      if (!getText().contains("-") && 
        this.cursorPosition == 0 && 
        this.isEnabled)
        writeText(Character.toString(p_231042_1_)); 
      return true;
    } 
    return false;
  }
  
  public boolean method_25402(double p_231044_1_, double p_231044_3_, int p_231044_5_) {
    if (!getVisible())
      return false; 
    boolean flag = (p_231044_1_ >= this.x && p_231044_1_ < (this.x + this.width) && p_231044_3_ >= this.y && p_231044_3_ < (this.y + this.height));
    if (this.canLoseFocus)
      setFocused2(flag); 
    if (this.isFocused && flag && p_231044_5_ == 0) {
      int i = class_3532.method_15357(p_231044_1_) - this.x;
      if (this.enableBackgroundDrawing)
        i -= 4; 
      String s = this.fontRenderer.method_27523(this.text.substring(this.lineScrollOffset), getAdjustedWidth());
      setCursorPosition(this.fontRenderer.method_27523(s, i).length() + this.lineScrollOffset);
      return true;
    } 
    return false;
  }
  
  public void setFocused2(boolean isFocusedIn) {
    this.isFocused = isFocusedIn;
    if (!isFocusedIn) {
      this.field_212956_h = false;
      setCursorPosition(0);
      if (!isNumeric(this.text) || this.text == "")
        setText(this.defValue + ""); 
    } 
  }
  
  public void method_25394(class_4587 mat, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
    super.method_25394(mat, p_230431_2_, p_230431_3_, p_230431_4_);
    if (getVisible()) {
      class_310 mc = class_310.method_1551();
      mc.method_1531().method_22813(this.BG);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableAlphaTest();
      RenderSystem.enableBlend();
      if (this.isFocused) {
        renderBtnBg(mat, 0, 16);
      } else if (this.hovered) {
        renderBtnBg(mat, 16, 0);
      } else {
        renderBtnBg(mat, 0, 0);
      } 
      RenderSystem.disableBlend();
      RenderSystem.disableAlphaTest();
      int i2 = this.isEnabled ? this.enabledColor : this.disabledColor;
      int j = this.cursorPosition - this.lineScrollOffset;
      int k = this.selectionEnd - this.lineScrollOffset;
      String s = this.fontRenderer.method_27523(this.text.substring(this.lineScrollOffset), getAdjustedWidth());
      boolean flag = (j >= 0 && j <= s.length());
      boolean flag1 = (this.isFocused && flag);
      int l = this.enableBackgroundDrawing ? (this.x + 4) : this.x;
      int i1 = this.enableBackgroundDrawing ? (this.y + (this.height - 8) / 2) : this.y;
      int j1 = l;
      if (k > s.length())
        k = s.length(); 
      if (!s.isEmpty()) {
        String s1 = flag ? s.substring(0, j) : s;
        j1 = this.fontRenderer.method_1720(mat, this.textFormatter.apply(s1, Integer.valueOf(this.lineScrollOffset)), l, i1, i2);
      } 
      boolean flag2 = (this.cursorPosition < this.text.length() || this.text.length() >= getMaxStringLength());
      int k1 = j1;
      if (!flag) {
        k1 = (j > 0) ? (l + this.width) : l;
      } else if (flag2) {
        k1 = j1 - 1;
        j1--;
      } 
      if (!s.isEmpty() && flag && j < s.length())
        this.fontRenderer.method_1720(mat, this.textFormatter.apply(s.substring(j), Integer.valueOf(this.cursorPosition)), j1, i1, i2); 
      if (flag1)
        if (flag2) {
          class_332.method_25294(mat, k1, i1 - 1, k1 + 1, i1 + 1 + 9, -3092272);
        } else {
          this.fontRenderer.method_1720(mat, "_", k1, i1, i2);
        }  
      if (k != j) {
        int l1 = l + this.fontRenderer.method_1727(s.substring(0, k));
        drawSelectionBox(k1, i1 - 1, l1 - 1, i1 + 1 + 9);
      } 
    } 
  }
  
  private void drawSelectionBox(int startX, int startY, int endX, int endY) {
    if (startX < endX) {
      int i = startX;
      startX = endX;
      endX = i;
    } 
    if (startY < endY) {
      int j = startY;
      startY = endY;
      endY = j;
    } 
    if (endX > this.x + this.width)
      endX = this.x + this.width; 
    if (startX > this.x + this.width)
      startX = this.x + this.width; 
    class_289 tessellator = class_289.method_1348();
    class_287 bufferbuilder = tessellator.method_1349();
    RenderSystem.color4f(0.0F, 0.0F, 255.0F, 255.0F);
    RenderSystem.disableTexture();
    RenderSystem.enableColorLogicOp();
    RenderSystem.logicOp(class_4493.class_1030.field_5110);
    bufferbuilder.method_1328(7, class_290.field_1592);
    bufferbuilder.method_22912(startX, endY, 0.0D).method_1344();
    bufferbuilder.method_22912(endX, endY, 0.0D).method_1344();
    bufferbuilder.method_22912(endX, startY, 0.0D).method_1344();
    bufferbuilder.method_22912(startX, startY, 0.0D).method_1344();
    tessellator.method_1350();
    RenderSystem.disableColorLogicOp();
    RenderSystem.enableTexture();
  }
  
  public void setMaxStringLength(int length) {
    this.maxStringLength = length;
    if (this.text.length() > length) {
      this.text = this.text.substring(0, length);
      onTextChanged(this.text);
    } 
  }
  
  private int getMaxStringLength() {
    return this.maxStringLength;
  }
  
  public int getCursorPosition() {
    return this.cursorPosition;
  }
  
  private boolean getEnableBackgroundDrawing() {
    return this.enableBackgroundDrawing;
  }
  
  public boolean method_25407(boolean p_231049_1_) {
    return this.isEnabled ? super.method_25407(p_231049_1_) : false;
  }
  
  public boolean method_25405(double p_231047_1_, double p_231047_3_) {
    return (p_231047_1_ >= this.x && p_231047_1_ < (this.x + this.width) && p_231047_3_ >= this.y && p_231047_3_ < (this.y + this.height));
  }
  
  private boolean isEnabled() {
    return this.isEnabled;
  }
  
  public int getAdjustedWidth() {
    return getEnableBackgroundDrawing() ? (this.width - 8) : this.width;
  }
  
  public void setSelectionPos(int position) {
    int i = this.text.length();
    this.selectionEnd = class_3532.method_15340(position, 0, i);
    if (this.fontRenderer != null) {
      if (this.lineScrollOffset > i)
        this.lineScrollOffset = i; 
      int j = getAdjustedWidth();
      String s = this.fontRenderer.method_27523(this.text.substring(this.lineScrollOffset), j);
      int k = s.length() + this.lineScrollOffset;
      if (this.selectionEnd == this.lineScrollOffset)
        this.lineScrollOffset -= this.fontRenderer.method_27524(this.text, j, true).length(); 
      if (this.selectionEnd > k) {
        this.lineScrollOffset += this.selectionEnd - k;
      } else if (this.selectionEnd <= this.lineScrollOffset) {
        this.lineScrollOffset -= this.lineScrollOffset - this.selectionEnd;
      } 
      this.lineScrollOffset = class_3532.method_15340(this.lineScrollOffset, 0, i);
    } 
  }
  
  public boolean getVisible() {
    return true;
  }
  
  public void setX(int xIn) {
    this.x = xIn;
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
  
  protected boolean isFocused() {
    return this.isFocused;
  }
  
  private boolean isNumeric(String text) {
    if (text == null)
      return false; 
    try {
      Integer.parseInt(text);
    } catch (NumberFormatException ex) {
      return false;
    } 
    return true;
  }
  
  public static interface IPressable {
    void onChanged(NumericTextField param1NumericTextField);
  }
}
