package dlovin.inventoryhud.gui;

import com.google.common.collect.Ordering;
import com.mojang.blaze3d.systems.RenderSystem;
import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.config.InvConfig;
import dlovin.inventoryhud.utils.ArmorStatus;
import dlovin.inventoryhud.utils.EachItem;
import dlovin.inventoryhud.utils.WidgetAligns;
import java.util.Collection;
import java.util.Iterator;
import net.minecraft.class_1041;
import net.minecraft.class_1058;
import net.minecraft.class_124;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1306;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4074;
import net.minecraft.class_4587;
import net.minecraft.class_918;

public class InventoryHUDGui {
  private static class_310 mc;
  
  private static class_918 ir;
  
  private static class_327 tr;
  
  private static final class_2960 PBG = new class_2960("inventoryhud", "textures/gui/potion_bg.png");
  
  private static final class_2960 PBGM = new class_2960("inventoryhud", "textures/gui/potion_bg_mini.png");
  
  private static final class_2960 PMT = new class_2960("inventoryhud", "textures/gui/potminitime.png");
  
  private static final class_2960 INVBG = new class_2960("inventoryhud", "textures/gui/inv_bg.png");
  
  private static final class_2960[] armorRL = new class_2960[] { new class_2960("inventoryhud", "textures/item/empty_armor_slot_helmet.png"), new class_2960("inventoryhud", "textures/item/empty_armor_slot_chestplate.png"), new class_2960("inventoryhud", "textures/item/empty_armor_slot_leggings.png"), new class_2960("inventoryhud", "textures/item/empty_armor_slot_boots.png"), new class_2960("inventoryhud", "textures/item/empty_armor_slot_shield.png"), new class_2960("inventoryhud", "textures/item/empty_main_hand_slot.png"), new class_2960("inventoryhud", "textures/item/inventory.png"), new class_2960("inventoryhud", "textures/item/arrows.png") };
  
  private final int[][] armorPosX = new int[][] { { -136, -136, 119, 119, 119, -136, 119, -136 }, { -119, -119, 94, 94, 94, -119, 94, -138 } };
  
  private final int[] armorPosY = new int[] { -56, -38, -56, -38, -74, -74, -20, -20 };
  
  public static WidgetAligns InvAligns;
  
  public static WidgetAligns PotAligns;
  
  public static WidgetAligns ArmAligns;
  
  public static int invX;
  
  public static int invY;
  
  public static int potX;
  
  public static int potY;
  
  public static int armX;
  
  public static int armY;
  
  private static int potSide;
  
  private static int potIconSide;
  
  private static int potTextSide;
  
  private static int potVert;
  
  public static boolean potMini;
  
  public static int potGap;
  
  public static boolean invMini;
  
  public static boolean invVert;
  
  public static float invAlpha;
  
  public static int armAbove;
  
  public static InvConfig.ArmorView armView;
  
  public static float potAlpha;
  
  public static boolean armBars;
  
  public static boolean showArmor;
  
  public static boolean showMain;
  
  public static boolean showOff;
  
  public static boolean showArrows;
  
  public static boolean showInv;
  
  public static boolean moveAll;
  
  public static boolean showEmpty;
  
  public static EachItem[] items = new EachItem[8];
  
  public InventoryHUDGui() {
    mc = class_310.method_1551();
    ir = mc.method_1480();
    tr = mc.field_1772;
    InitializeStatic();
    System.out.println("InvGUI Initialized");
  }
  
  private void InitializeStatic() {
    invX = InventoryHUD.getConfig().getInvX();
    invY = InventoryHUD.getConfig().getInvY();
    potX = InventoryHUD.getConfig().getPotX();
    potY = InventoryHUD.getConfig().getPotY();
    armX = InventoryHUD.getConfig().getArmX();
    armY = InventoryHUD.getConfig().getArmY();
    InvAligns = new WidgetAligns(InventoryHUD.getConfig().getInvHal(), InventoryHUD.getConfig().getInvVal());
    PotAligns = new WidgetAligns(InventoryHUD.getConfig().getPotHal(), InventoryHUD.getConfig().getPotVal());
    ArmAligns = new WidgetAligns(InventoryHUD.getConfig().getArmHal(), InventoryHUD.getConfig().getArmVal());
    invMini = InventoryHUD.getConfig().getInvMini();
    invVert = InventoryHUD.getConfig().getInvVert();
    invAlpha = InventoryHUD.getConfig().getInvAlpha() / 100.0F;
    potMini = InventoryHUD.getConfig().getPotMini();
    potGap = InventoryHUD.getConfig().getPotGap();
    PotPosChanged();
    switch (PotAligns.VertAlign) {
      case PERCENTAGE:
        potVert = 1;
        break;
      case DAMAGE:
        if (potY - 12 < mc.method_22683().method_4502() / 2) {
          potVert = -1;
          break;
        } 
        potVert = 1;
        break;
      case DAMAGE_LEFT:
        potVert = -1;
        break;
    } 
    armAbove = InventoryHUD.getConfig().getArmAbove();
    potAlpha = InventoryHUD.getConfig().getPotAlpha() / 100.0F;
    armView = InventoryHUD.getConfig().getArmView();
    armBars = InventoryHUD.getConfig().getArmBars();
    showArmor = InventoryHUD.getConfig().isShowArmor();
    showMain = InventoryHUD.getConfig().isShowMain();
    showOff = InventoryHUD.getConfig().isShowOff();
    showArrows = InventoryHUD.getConfig().isShowArrows();
    showInv = InventoryHUD.getConfig().isShowInv();
    showEmpty = InventoryHUD.getConfig().isShowEmpty();
    moveAll = InventoryHUD.getConfig().getMoveAll();
    items[0] = new EachItem(InventoryHUD.getConfig().getHelmX(), InventoryHUD.getConfig().getHelmY(), new WidgetAligns(InventoryHUD.getConfig().getHelmHal(), InventoryHUD.getConfig().getHelmVal()));
    items[1] = new EachItem(InventoryHUD.getConfig().getChestX(), InventoryHUD.getConfig().getChestY(), new WidgetAligns(InventoryHUD.getConfig().getChestHal(), InventoryHUD.getConfig().getChestVal()));
    items[2] = new EachItem(InventoryHUD.getConfig().getLegX(), InventoryHUD.getConfig().getLegY(), new WidgetAligns(InventoryHUD.getConfig().getLegHal(), InventoryHUD.getConfig().getLegVal()));
    items[3] = new EachItem(InventoryHUD.getConfig().getBootsX(), InventoryHUD.getConfig().getBootsY(), new WidgetAligns(InventoryHUD.getConfig().getBootsHal(), InventoryHUD.getConfig().getBootsVal()));
    items[4] = new EachItem(InventoryHUD.getConfig().getOffX(), InventoryHUD.getConfig().getOffY(), new WidgetAligns(InventoryHUD.getConfig().getOffHal(), InventoryHUD.getConfig().getOffVal()));
    items[5] = new EachItem(InventoryHUD.getConfig().getMainX(), InventoryHUD.getConfig().getMainY(), new WidgetAligns(InventoryHUD.getConfig().getMainHal(), InventoryHUD.getConfig().getMainVal()));
    items[6] = new EachItem(InventoryHUD.getConfig().getInvIconX(), InventoryHUD.getConfig().getInvIconY(), new WidgetAligns(InventoryHUD.getConfig().getInvIconHal(), InventoryHUD.getConfig().getInvIconVal()));
    items[7] = new EachItem(InventoryHUD.getConfig().getArrX(), InventoryHUD.getConfig().getArrY(), new WidgetAligns(InventoryHUD.getConfig().getArrHal(), InventoryHUD.getConfig().getArrVal()));
  }
  
  public void render() {
    class_1041 mainWindow = mc.method_22683();
    int width = mainWindow.method_4486();
    int height = mainWindow.method_4502();
    if (InventoryHUD.PotHUD)
      renderPotions(width, height); 
    if (InventoryHUD.ArmHUD)
      if (moveAll) {
        RenderArmorStatus(width, height);
      } else {
        RenderEachArmor(width, height);
      }  
    this;
    if (mc.field_1755 instanceof net.minecraft.class_465)
      return; 
    if (InventoryHUD.InvHUD)
      renderInventory(width, height); 
  }
  
  private void renderInventory(int width, int height) {
    int iMax = invVert ? 3 : 9;
    int jMax = invVert ? 9 : 3;
    double scale = invMini ? 0.75D : 1.0D;
    int px = getX(width, (int)((iMax * 18 - 2) * scale), invX, InvAligns.HorAlign);
    int py = getY(height, (int)((jMax * 18 - 2) * scale), invY, InvAligns.VertAlign);
    RenderSystem.pushMatrix();
    RenderSystem.translated(px, py, -255.0D);
    RenderSystem.scaled(scale, scale, scale);
    RenderSystem.enableBlend();
    RenderSystem.enableAlphaTest();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, invAlpha);
    this;
    mc.method_1531().method_22813(INVBG);
    class_332.method_25293(new class_4587(), -2, -2, iMax * 18 + 2, jMax * 18 + 2, 0.0F, (jMax == 3) ? 0.0F : 56.0F, iMax * 18 + 2, jMax * 18 + 2, 256, 256);
    RenderSystem.disableAlphaTest();
    RenderSystem.disableBlend();
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    for (int i = 0; i < iMax; i++) {
      for (int j = 0; j < jMax; j++) {
        int index = invVert ? ((3 - i) * 9 + j) : (i + (j + 1) * 9);
        class_1799 item = (class_1799)mc.field_1724.field_7514.field_7547.get(index);
        ir.method_4010(item, i * 18, j * 18);
        ir.method_4025(tr, item, i * 18, j * 18);
      } 
    } 
    RenderSystem.popMatrix();
  }
  
  private void renderPotions(int width, int height) {
    int y = 0;
    Collection<class_1293> collection = mc.field_1724.method_6026();
    if (collection.isEmpty())
      return; 
    int effCount = collection.size();
    class_4074 statusEffectSpriteManager = mc.method_18505();
    RenderSystem.pushMatrix();
    RenderSystem.translated(0.0D, 0.0D, -255.0D);
    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    int xPos = 0;
    int yPos = 0;
    switch (PotAligns.HorAlign) {
      case PERCENTAGE:
        xPos = potX;
        break;
      case DAMAGE:
        if (!potMini) {
          xPos = width / 2 - 30 - potX;
          break;
        } 
        if (potX > 0) {
          xPos = width / 2 - potX;
          break;
        } 
        if (potX < 0) {
          xPos = width / 2 - 22 - potX;
          break;
        } 
        xPos = width / 2 - 11 - potX;
        break;
      case DAMAGE_LEFT:
        if (!potMini) {
          xPos = width - potX;
          break;
        } 
        xPos = width - potX + 23;
        break;
    } 
    switch (PotAligns.VertAlign) {
      case PERCENTAGE:
        yPos = potY;
        break;
      case DAMAGE:
        if (!potMini) {
          if (potY > 0) {
            yPos = height / 2 - 12 - potY;
            break;
          } 
          if (potY < 0) {
            yPos = height / 2 - 12 - (effCount - 1) * (24 + potGap) - potY;
            break;
          } 
          yPos = (int)((height / 2 - 12) - (effCount - 1) * (potGap / 2.0F + 12.0F) - potY);
          break;
        } 
        if (potY > 0) {
          yPos = height / 2 - 9 - potY;
          break;
        } 
        if (potY < 0) {
          yPos = height / 2 - 9 - (effCount - 1) * (18 + potGap) - potY;
          break;
        } 
        yPos = (int)((height / 2 - 9) - (effCount - 1) * (potGap / 2.0F + 9.0F) - potY);
        break;
      case DAMAGE_LEFT:
        if (!potMini) {
          yPos = height - potY - (effCount - 1) * (potGap + 24);
          break;
        } 
        yPos = height - potY - (effCount - 1) * (potGap + 18);
        break;
    } 
    Iterator<class_1293> var7 = Ordering.natural().reverse().sortedCopy(collection).iterator();
    class_4587 mat = new class_4587();
    while (var7.hasNext()) {
      class_1293 statusEffectInstance = var7.next();
      class_1291 statusEffect = statusEffectInstance.method_5579();
      RenderSystem.enableAlphaTest();
      RenderSystem.enableBlend();
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, potAlpha);
      if (!potMini) {
        this;
        mc.method_1531().method_22813(PBG);
        class_332.method_25290(mat, xPos, yPos + y, 60.0F, 24.0F, 60, 24, 60 * potSide, 24);
      } else {
        this;
        mc.method_1531().method_22813(PBGM);
        class_332.method_25290(mat, xPos, yPos + y, 22.0F, 18.0F, 22, 18, 22 * potSide, 18);
      } 
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      class_1058 sprite = statusEffectSpriteManager.method_18663(statusEffect);
      mc.method_1531().method_22813(sprite.method_24119().method_24106());
      if (!potMini) {
        class_332.method_25298(mat, xPos + 3 + potIconSide, yPos + y + 3, 0, 18, 18, sprite);
      } else {
        class_332.method_25298(mat, xPos + 3 + potIconSide, yPos + y + 3, 0, 12, 12, sprite);
      } 
      class_2960 lev = (statusEffectInstance.method_5578() < 9 && statusEffectInstance.method_5578() >= 0) ? new class_2960("inventoryhud", "textures/gui/l_" + statusEffectInstance.method_5578() + ".png") : new class_2960("inventoryhud", "textures/gui/l_inf.png");
      this;
      mc.method_1531().method_22813(lev);
      if (!potMini) {
        class_332.method_25290(mat, xPos + 15 + potIconSide, yPos + y, 9.0F, 9.0F, 9, 9, 9, 9);
      } else {
        class_332.method_25290(mat, xPos + 9 + potIconSide, yPos + y, 9.0F, 9.0F, 9, 9, 9, 9);
      } 
      int dur = statusEffectInstance.method_5584() / 20;
      if (!potMini) {
        String time;
        if (dur > 1600) {
          time = " **:**";
        } else {
          String mins = (dur / 60) + "";
          String secs = (dur % 60) + "";
          if (dur / 60 < 10)
            mins = "0" + mins; 
          if (dur % 60 < 10)
            secs = "0" + secs; 
          if (dur > 10) {
            time = mins + ":" + secs;
          } else {
            time = class_124.field_1061 + mins + class_124.field_1068 + ":" + class_124.field_1061 + secs;
          } 
        } 
        tr.method_1720(mat, time, (xPos + 28 + potTextSide), (yPos + y + 8), 16777215);
        y += 24 + potGap;
        continue;
      } 
      int ysize = 12;
      this;
      mc.method_1531().method_22813(PMT);
      RenderSystem.color4f(0.0F, 1.0F, 0.0F, 1.0F);
      if (dur < 300) {
        float r = 1.0F;
        float g = 1.0F;
        ysize = dur / 25 + 1;
        if (dur < 150) {
          g = 0.006666667F * dur;
        } else {
          r = 0.006666667F * (300 - dur);
        } 
        RenderSystem.color4f(r, g, 0.0F, 1.0F);
      } 
      class_332.method_25290(mat, xPos + 17 + potTextSide, yPos + y + 15 - ysize, 3.0F, ysize, 3, ysize, 3, ysize);
      y += 18 + potGap;
    } 
    RenderSystem.disableAlphaTest();
    RenderSystem.disableBlend();
    RenderSystem.popMatrix();
  }
  
  private String getDamageText(String p, int d) {
    String result = p;
    if (d == 100) {
      result = class_124.field_1060 + result;
    } else if (d < 1) {
      result = class_124.field_1079 + result;
    } else if (d <= 10) {
      result = class_124.field_1061 + result;
    } else if (d <= 25) {
      result = class_124.field_1065 + result;
    } else if (d <= 50) {
      result = class_124.field_1054 + result;
    } 
    if (armView == InvConfig.ArmorView.PERCENTAGE)
      result = result + class_124.field_1068 + "%"; 
    return result;
  }
  
  private boolean isArrow(class_1799 stack) {
    return (!stack.method_7960() && stack.method_7909() instanceof net.minecraft.class_1744);
  }
  
  private boolean getRight(int x, WidgetAligns.HAlign hal) {
    switch (hal) {
      case PERCENTAGE:
        return false;
      case DAMAGE:
        if (x > 0)
          return true; 
        return false;
      case DAMAGE_LEFT:
        return true;
    } 
    return false;
  }
  
  private int getX(int width, int itemWidth, int x, WidgetAligns.HAlign hal) {
    int result = 0;
    switch (hal) {
      case PERCENTAGE:
        result = x;
        break;
      case DAMAGE:
        result = width / 2 - itemWidth / 2 - x;
        break;
      case DAMAGE_LEFT:
        result = width - x;
        break;
    } 
    if (result < 0) {
      result = 0;
    } else if (result > width - itemWidth) {
      result = width - itemWidth;
    } 
    return result;
  }
  
  private int getY(int height, int itemHeight, int y, WidgetAligns.VAlign val) {
    int result = 0;
    switch (val) {
      case PERCENTAGE:
        result = y;
        break;
      case DAMAGE:
        result = height / 2 - itemHeight / 2 - y;
        break;
      case DAMAGE_LEFT:
        result = height - y;
        break;
    } 
    if (result < 0) {
      result = 0;
    } else if (result > height - itemHeight) {
      result = height - itemHeight;
    } 
    return result;
  }
  
  private void renderElement(class_4587 mat, int x, int y, boolean right, class_2960 res, class_1799 item, String text, boolean overlay) {
    if (res != null) {
      this;
      mc.method_1531().method_22813(res);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      class_332.method_25290(mat, x, y, 16.0F, 16.0F, 16, 16, 16, 16);
    } else {
      ir.method_4010(item, x, y);
      if (overlay)
        ir.method_4022(tr, item, x, y, null); 
    } 
    if (text == null)
      return; 
    if (right) {
      tr.method_1720(mat, text, (x + 17), (y + 4), 16777215);
    } else {
      tr.method_1720(mat, text, (x - 1 - tr.method_1727(text)), (y + 4), 16777215);
    } 
  }
  
  private void RenderArmorStatus(int width, int height) {
    class_4587 mat = new class_4587();
    if (ArmAligns.HorAlign == WidgetAligns.HAlign.MIDDLE && ArmAligns.VertAlign == WidgetAligns.VAlign.BOTTOM && armY <= 90 && Math.abs(armX) <= 90) {
      int i = 5;
      RenderSystem.pushMatrix();
      RenderSystem.translated(0.0D, 0.0D, -320.0D);
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this;
      for (class_1799 item : mc.field_1724.method_5743()) {
        int yOff = 0;
        if (!showArmor) {
          if (i < 4) {
            i--;
            continue;
          } 
          yOff = 36;
        } 
        if (!showMain && i == 5) {
          i--;
          continue;
        } 
        if (!showOff && i == 4) {
          i--;
          continue;
        } 
        int Damage = ArmorStatus.GetDamage(item);
        int xOff = 0;
        if (armView.equals(InvConfig.ArmorView.OFF))
          if (i > 1 && i < 5) {
            xOff = -19;
          } else {
            xOff = 19;
          }  
        String text = null;
        if (Damage >= 0 && Damage <= armAbove) {
          if (!armView.equals(InvConfig.ArmorView.OFF))
            switch (armView) {
              case PERCENTAGE:
                text = getDamageText(String.valueOf(Damage), Damage);
                break;
              case DAMAGE:
                text = getDamageText(String.valueOf(item.method_7919()), Damage);
                break;
              case DAMAGE_LEFT:
                text = getDamageText(String.valueOf(item.method_7936() - item.method_7919()), Damage);
                break;
            }  
          renderElement(mat, width / 2 + this.armorPosX[0][i] + xOff, height + this.armorPosY[i] + yOff, (i < 2 || i > 4), null, item, text, armBars);
        } else if (Damage == -2 && armAbove == 100) {
          renderElement(mat, width / 2 + this.armorPosX[0][i] + xOff, height + this.armorPosY[i] + yOff, (i < 2 || i > 4), null, item, null, true);
        } else if (showEmpty) {
          renderElement(mat, width / 2 + this.armorPosX[0][i] + xOff, height + this.armorPosY[i] + yOff, (i < 2 || i > 4), armorRL[i], null, null, false);
        } 
        i--;
      } 
      if (showInv || showArrows) {
        int count = 0;
        int arrows = 0;
        this;
        for (class_1799 slot : mc.field_1724.field_7514.field_7547) {
          if (slot.method_7960())
            count++; 
          if (isArrow(slot))
            arrows += slot.method_7947(); 
        } 
        if (mc.field_1724.method_6068().equals(class_1306.field_6183)) {
          if (showInv)
            if (armView.equals(InvConfig.ArmorView.OFF)) {
              renderElement(mat, width / 2 + this.armorPosX[0][6] - 19, height + this.armorPosY[6], true, armorRL[6], null, count + "", false);
            } else {
              renderElement(mat, width / 2 + this.armorPosX[0][6], height + this.armorPosY[6], false, armorRL[6], null, count + "", false);
            }  
          if (showArrows)
            renderElement(mat, width / 2 + this.armorPosX[0][7], height + this.armorPosY[7], false, armorRL[7], null, arrows + "", false); 
        } else {
          if (showInv)
            renderElement(mat, width / 2 + this.armorPosX[0][6], height + this.armorPosY[6], true, armorRL[6], null, count + "", false); 
          if (showArrows)
            if (armView.equals(InvConfig.ArmorView.OFF)) {
              renderElement(mat, width / 2 + this.armorPosX[0][7] + 19, height + this.armorPosY[7], false, armorRL[7], null, arrows + "", false);
            } else {
              renderElement(mat, width / 2 + this.armorPosX[0][7], height + this.armorPosY[7], true, armorRL[7], null, arrows + "", false);
            }  
        } 
      } 
      RenderSystem.popMatrix();
    } else {
      int x = getX(width, 90, armX, ArmAligns.HorAlign);
      int y = getY(height, 70, armY, ArmAligns.VertAlign);
      boolean right = getRight(armX, ArmAligns.HorAlign);
      RenderSystem.pushMatrix();
      RenderSystem.translated(x, y, -320.0D);
      RenderItems(mat, 0, 0, right);
      RenderSystem.popMatrix();
    } 
  }
  
  private void RenderItems(class_4587 mat, int xpos, int ypos, boolean right) {
    int i = 5;
    int xOffsetT = right ? 75 : 0;
    this;
    for (class_1799 item : mc.field_1724.method_5743()) {
      if (!showMain && i == 5) {
        i--;
        continue;
      } 
      if (!showOff && i == 4) {
        i--;
        continue;
      } 
      if (!showArmor && i < 4) {
        i--;
        continue;
      } 
      int yOffset = i % 4 * 18;
      if (i == 4 && showMain)
        yOffset += 18; 
      if (i == 5)
        yOffset -= 18; 
      int xOffset = xOffsetT;
      if (i > 3 && showArmor)
        xOffset = right ? (xOffset - 42) : (xOffset + 42); 
      int Damage = ArmorStatus.GetDamage(item);
      String text = null;
      if (Damage >= 0 && Damage <= armAbove) {
        if (!armView.equals(InvConfig.ArmorView.OFF))
          switch (armView) {
            case PERCENTAGE:
              text = getDamageText(String.valueOf(Damage), Damage);
              break;
            case DAMAGE:
              text = getDamageText(String.valueOf(item.method_7919()), Damage);
              break;
            case DAMAGE_LEFT:
              text = getDamageText(String.valueOf(item.method_7936() - item.method_7919()), Damage);
              break;
          }  
        renderElement(mat, xpos + xOffset, ypos + yOffset, !right, null, item, text, armBars);
      } else if (Damage == -2 && armAbove == 100) {
        renderElement(mat, xpos + xOffset, ypos + yOffset, !right, null, item, null, true);
      } else if (showEmpty) {
        renderElement(mat, xpos + xOffset, ypos + yOffset, !right, armorRL[i], null, null, false);
      } 
      i--;
    } 
    if (showArrows || showInv) {
      int count = 0;
      int arrows = 0;
      this;
      for (class_1799 slot : mc.field_1724.field_7514.field_7547) {
        if (slot.method_7960())
          count++; 
        if (isArrow(slot))
          arrows += slot.method_7947(); 
      } 
      int xOffset = xOffsetT;
      int yOffset = 36;
      if (showArmor)
        xOffset = right ? (xOffset - 42) : (xOffset + 42); 
      if (!showMain)
        yOffset -= 18; 
      if (!showOff)
        yOffset -= 18; 
      if (showInv) {
        renderElement(mat, xpos + xOffset, ypos + yOffset, !right, armorRL[6], null, "" + count, false);
        yOffset += 18;
      } 
      if (showArrows)
        renderElement(mat, xpos + xOffset, ypos + yOffset, !right, armorRL[7], null, "" + arrows, false); 
    } 
  }
  
  private void RenderEachArmor(int width, int height) {
    class_4587 mat = new class_4587();
    int i = 5;
    RenderSystem.pushMatrix();
    RenderSystem.translated(0.0D, 0.0D, -320.0D);
    this;
    for (class_1799 item : mc.field_1724.method_5743()) {
      if (!showMain && i == 5) {
        i--;
        continue;
      } 
      if (!showOff && i == 4) {
        i--;
        continue;
      } 
      if (!showArmor && i < 4) {
        i--;
        continue;
      } 
      int x = getX(width, 16, (items[i]).x, (items[i]).aligns.HorAlign);
      int y = getY(height, 16, (items[i]).y, (items[i]).aligns.VertAlign);
      boolean right = getRight((items[i]).x, (items[i]).aligns.HorAlign);
      int Damage = ArmorStatus.GetDamage(item);
      String text = null;
      if (Damage >= 0 && Damage <= armAbove) {
        if (!armView.equals(InvConfig.ArmorView.OFF))
          switch (armView) {
            case PERCENTAGE:
              text = getDamageText(String.valueOf(Damage), Damage);
              break;
            case DAMAGE:
              text = getDamageText(String.valueOf(item.method_7919()), Damage);
              break;
            case DAMAGE_LEFT:
              text = getDamageText(String.valueOf(item.method_7936() - item.method_7919()), Damage);
              break;
          }  
        renderElement(mat, x, y, !right, null, item, text, armBars);
      } else if (Damage == -2 && armAbove == 100) {
        renderElement(mat, x, y, !right, null, item, null, true);
      } else if (showEmpty) {
        renderElement(mat, x, y, !right, armorRL[i], null, null, false);
      } 
      i--;
    } 
    if (showArrows || showInv) {
      int count = 0;
      int arrows = 0;
      this;
      for (class_1799 slot : mc.field_1724.field_7514.field_7547) {
        if (slot.method_7960())
          count++; 
        if (isArrow(slot))
          arrows += slot.method_7947(); 
      } 
      if (showInv) {
        int x = getX(width, 16, (items[6]).x, (items[6]).aligns.HorAlign);
        int y = getY(height, 16, (items[6]).y, (items[6]).aligns.VertAlign);
        boolean right = getRight((items[6]).x, (items[6]).aligns.HorAlign);
        renderElement(mat, x, y, !right, armorRL[6], null, "" + count, false);
      } 
      if (showArrows) {
        int x = getX(width, 16, (items[7]).x, (items[7]).aligns.HorAlign);
        int y = getY(height, 16, (items[7]).y, (items[7]).aligns.VertAlign);
        boolean right = getRight((items[7]).x, (items[7]).aligns.HorAlign);
        renderElement(mat, x, y, !right, armorRL[7], null, "" + arrows, false);
      } 
    } 
    RenderSystem.popMatrix();
  }
  
  public static void PotPosChanged() {
    switch (PotAligns.HorAlign) {
      case DAMAGE_LEFT:
        if (!potMini) {
          potSide = -1;
          potIconSide = 36;
          potTextSide = -22;
          break;
        } 
        potSide = -1;
        potIconSide = 4;
        potTextSide = -15;
        break;
      case DAMAGE:
        if (potX > 0) {
          if (!potMini) {
            potSide = -1;
            potIconSide = 36;
            potTextSide = -22;
            break;
          } 
          potSide = -1;
          potIconSide = 4;
          potTextSide = -15;
          break;
        } 
        potSide = 1;
        potIconSide = 0;
        potTextSide = 0;
        break;
      case PERCENTAGE:
        potSide = 1;
        potIconSide = 0;
        potTextSide = 0;
        break;
    } 
  }
  
  public static void PotPosYChanged(boolean top) {
    potVert = top ? 1 : -1;
  }
}
