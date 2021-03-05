package dlovin.inventoryhud.config;

import com.mojang.blaze3d.systems.RenderSystem;
import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.config.gui.ArmorConfigScreen;
import dlovin.inventoryhud.config.gui.InventoryConfigScreen;
import dlovin.inventoryhud.config.gui.PotionConfigScreen;
import dlovin.inventoryhud.config.widgets.ConfigWidget;
import dlovin.inventoryhud.gui.InventoryHUDGui;
import dlovin.inventoryhud.references.Translation;
import dlovin.inventoryhud.utils.Color4F;
import dlovin.inventoryhud.utils.WidgetAligns;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_2561;
import net.minecraft.class_2585;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_4587;

public class InGameConfigScreen extends class_437 {
  private class_2960 INV_BG = new class_2960("inventoryhud", "textures/gui/inv_config_bg.png");
  
  private class_2960 CONF_BG = new class_2960("inventoryhud", "textures/gui/conf_bg.png");
  
  private ConfigWidget ICW;
  
  private ConfigWidget PCW;
  
  private ConfigWidget ACW;
  
  private List<ConfigWidget> listForRender;
  
  private class_4185 backButton;
  
  private int menu;
  
  private boolean inGame;
  
  private final class_310 mc;
  
  private class_437 prevScreen;
  
  public InGameConfigScreen(class_437 prev, int menu, boolean inGame) {
    super((class_2561)new class_2585("InventoryHUD Config Screen"));
    this.menu = menu;
    this.inGame = inGame;
    this.mc = class_310.method_1551();
    this.prevScreen = prev;
  }
  
  protected void method_25426() {
    this.listForRender = new ArrayList<>();
    float invScale = InventoryHUD.getConfig().getInvMini() ? 0.75F : 1.0F;
    int invWidth = InventoryHUD.getConfig().getInvVert() ? 52 : 160;
    int invHeight = InventoryHUD.getConfig().getInvVert() ? 160 : 52;
    int x = getPosX((int)(invWidth * invScale), InventoryHUD.getConfig().getInvX(), InventoryHUD.getConfig().getInvHal());
    int y = getPosY((int)(invHeight * invScale), InventoryHUD.getConfig().getInvY(), InventoryHUD.getConfig().getInvVal());
    this.backButton = new class_4185(this.field_22789 - 60, 10, 50, 20, (class_2561)new class_2585("Back"), btn -> method_25419());
    this.listForRender.add(this.ICW = new ConfigWidget(x, y, invWidth, invHeight, invScale, Translation.INVGUI.getString(), InventoryHUD.getConfig().getInv()));
    if (!InventoryHUD.getConfig().getInvVert()) {
      this.ICW.initTextureValues(0, 0, new Color4F(0.5F, 1.0F, 0.5F, 1.0F), this.INV_BG);
    } else {
      this.ICW.initTextureValues(150, 54, new Color4F(0.5F, 1.0F, 0.5F, 1.0F), this.INV_BG);
    } 
    this.ICW.addListener(() -> onInvChanged());
    this.ICW.addPosListener(w -> onInvPosChanged());
    float potScale = InventoryHUD.getConfig().getPotMini() ? 0.75F : 1.0F;
    x = getPosX((int)(60.0F * potScale), InventoryHUD.getConfig().getPotX(), InventoryHUD.getConfig().getPotHal());
    y = getPosY((int)(24.0F * potScale), InventoryHUD.getConfig().getPotY(), InventoryHUD.getConfig().getPotVal());
    this.listForRender.add(this.PCW = new ConfigWidget(x, y, 60, 24, potScale, Translation.POTGUI.getString(), InventoryHUD.getConfig().getPot()));
    this.PCW.initTextureValues(0, 54, new Color4F(1.0F, 0.5F, 0.5F, 1.0F), this.INV_BG);
    this.PCW.addListener(() -> onPotionChanged());
    this.PCW.addPosListener(w -> onPotionPosChanged());
    if (InventoryHUD.getConfig().getMoveAll()) {
      x = getPosX(90, InventoryHUD.getConfig().getArmX(), InventoryHUD.getConfig().getArmHal());
      y = getPosY(70, InventoryHUD.getConfig().getArmY(), InventoryHUD.getConfig().getArmVal());
      this.listForRender.add(this.ACW = new ConfigWidget(x, y, 90, 70, 1.0F, Translation.ARMGUI.getString(), InventoryHUD.getConfig().getArm()));
      this.ACW.initTextureValues(60, 54, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG);
      this.ACW.addListener(() -> onArmorChanged());
      this.ACW.addPosListener(w -> onArmorPosChanged());
    } else {
      int xSize = 16;
      int ySize = 16;
      int textPos = 80;
      x = getPosX(xSize, InventoryHUD.getConfig().getHelmX(), InventoryHUD.getConfig().getHelmHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getHelmY(), InventoryHUD.getConfig().getHelmVal());
      ConfigWidget tmp;
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowArmor(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_armor_slot_helmet.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 0));
      x = getPosX(xSize, InventoryHUD.getConfig().getChestX(), InventoryHUD.getConfig().getChestHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getChestY(), InventoryHUD.getConfig().getChestVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowArmor(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_armor_slot_chestplate.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 1));
      x = getPosX(xSize, InventoryHUD.getConfig().getLegX(), InventoryHUD.getConfig().getLegHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getLegY(), InventoryHUD.getConfig().getLegVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowArmor(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_armor_slot_leggings.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 2));
      x = getPosX(xSize, InventoryHUD.getConfig().getBootsX(), InventoryHUD.getConfig().getBootsHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getBootsY(), InventoryHUD.getConfig().getBootsVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowArmor(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_armor_slot_boots.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 3));
      x = getPosX(xSize, InventoryHUD.getConfig().getMainX(), InventoryHUD.getConfig().getMainHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getMainY(), InventoryHUD.getConfig().getMainVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowMain(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_main_hand_slot.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 5));
      x = getPosX(xSize, InventoryHUD.getConfig().getOffX(), InventoryHUD.getConfig().getOffHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getOffY(), InventoryHUD.getConfig().getOffVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowOff(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_armor_slot_shield.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 4));
      x = getPosX(xSize, InventoryHUD.getConfig().getArrX(), InventoryHUD.getConfig().getArrHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getArrY(), InventoryHUD.getConfig().getArrVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowArrows(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_arrows.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 7));
      x = getPosX(xSize, InventoryHUD.getConfig().getInvIconX(), InventoryHUD.getConfig().getInvIconHal());
      y = getPosY(ySize, InventoryHUD.getConfig().getInvIconY(), InventoryHUD.getConfig().getInvIconVal());
      this.listForRender.add(tmp = new ConfigWidget(x, y, xSize, ySize, 1.0F, "", InventoryHUD.getConfig().isShowInv(), false));
      tmp.initTextureValues(textPos, 124, new Color4F(0.5F, 0.5F, 1.0F, 1.0F), this.INV_BG, new class_2960("inventoryhud", "textures/item/empty_inventory.png"));
      tmp.addPosListener(w -> onItemPosChanged(w, 6));
    } 
    this.field_22786.add(this.backButton);
    for (ConfigWidget w : this.listForRender)
      this.field_22786.add(w); 
  }
  
  private int getPosX(int width, int posX, WidgetAligns.HAlign hal) {
    int result = 0;
    switch (hal) {
      case TOP:
        result = posX;
        break;
      case BOTTOM:
        result = this.field_22789 - posX;
        break;
      case CENTER:
        result = this.field_22789 / 2 - width / 2 - posX;
        break;
    } 
    if (result < 0) {
      result = 0;
    } else if (result > this.field_22789 - width) {
      result = this.field_22789 - width;
    } 
    return result;
  }
  
  private int getPosY(int height, int posY, WidgetAligns.VAlign hal) {
    int result = 0;
    switch (hal) {
      case TOP:
        result = posY;
        break;
      case BOTTOM:
        result = this.field_22790 - posY;
        break;
      case CENTER:
        result = this.field_22790 / 2 - height / 2 - posY;
        break;
    } 
    if (result < 0) {
      result = 0;
    } else if (result > this.field_22790 - height) {
      result = this.field_22790 - height;
    } 
    return result;
  }
  
  public void method_25394(class_4587 mat, int mouseX, int mouseY, float partialTick) {
    method_25420(mat);
    RenderSystem.enableAlphaTest();
    RenderSystem.enableBlend();
    class_310.method_1551().method_1531().method_22813(this.CONF_BG);
    blit(mat, 0, this.field_22790 / 3 - 1, this.field_22789, 3.0F, this.field_22789, 3, 3, 3);
    blit(mat, 0, this.field_22790 / 3 * 2 - 1, this.field_22789, 3.0F, this.field_22789, 3, 3, 3);
    blit(mat, this.field_22789 / 3 - 1, 0, 3.0F, this.field_22790, 3, this.field_22790, 3, 3);
    blit(mat, this.field_22789 / 3 * 2 - 1, 0, 3.0F, this.field_22790, 3, this.field_22790, 3, 3);
    RenderSystem.disableAlphaTest();
    RenderSystem.disableBlend();
    for (ConfigWidget w : this.listForRender)
      w.method_25394(mat, mouseX, mouseX, partialTick); 
    this.backButton.method_25394(mat, mouseX, mouseY, partialTick);
  }
  
  private void onInvChanged() {
    InventoryHUD.InvHUD = this.ICW.getShow();
    InventoryHUD.getConfig().setInv(this.ICW.getShow());
  }
  
  private void onInvPosChanged() {
    if (this.ICW.field_22760 + this.ICW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 3) {
      InventoryHUDGui.InvAligns.HorAlign = WidgetAligns.HAlign.LEFT;
      InventoryHUDGui.invX = this.ICW.field_22760;
    } else if (this.ICW.field_22760 + this.ICW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 3 * 2) {
      InventoryHUDGui.InvAligns.HorAlign = WidgetAligns.HAlign.RIGHT;
      InventoryHUDGui.invX = this.mc.field_1755.field_22789 - this.ICW.field_22760;
    } else {
      InventoryHUDGui.InvAligns.HorAlign = WidgetAligns.HAlign.MIDDLE;
      if (this.ICW.field_22760 + this.ICW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 2 - 10) {
        InventoryHUDGui.invX = this.mc.field_1755.field_22789 / 2 - this.ICW.method_25368() / 2 - this.ICW.field_22760;
      } else if (this.ICW.field_22760 + this.ICW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 2 + 10) {
        InventoryHUDGui.invX = this.mc.field_1755.field_22789 / 2 - this.ICW.method_25368() / 2 - this.ICW.field_22760;
      } else {
        InventoryHUDGui.invX = 0;
      } 
    } 
    if (this.ICW.field_22761 + this.ICW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 3) {
      InventoryHUDGui.InvAligns.VertAlign = WidgetAligns.VAlign.TOP;
      InventoryHUDGui.invY = this.ICW.field_22761;
    } else if (this.ICW.field_22761 + this.ICW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 3 * 2) {
      InventoryHUDGui.InvAligns.VertAlign = WidgetAligns.VAlign.BOTTOM;
      InventoryHUDGui.invY = this.mc.field_1755.field_22790 - this.ICW.field_22761;
    } else {
      InventoryHUDGui.InvAligns.VertAlign = WidgetAligns.VAlign.CENTER;
      if (this.ICW.field_22761 + this.ICW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
        InventoryHUDGui.invY = this.mc.field_1755.field_22790 / 2 - this.ICW.method_25364() / 2 - this.ICW.field_22761;
      } else if (this.ICW.field_22761 + this.ICW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
        InventoryHUDGui.invY = this.mc.field_1755.field_22790 / 2 - this.ICW.method_25364() / 2 - this.ICW.field_22761;
      } else {
        InventoryHUDGui.invY = 0;
      } 
    } 
    InventoryHUD.getConfig().setInvX(InventoryHUDGui.invX);
    InventoryHUD.getConfig().setInvY(InventoryHUDGui.invY);
    InventoryHUD.getConfig().setInvHal(InventoryHUDGui.InvAligns.HorAlign);
    InventoryHUD.getConfig().setInvVal(InventoryHUDGui.InvAligns.VertAlign);
  }
  
  private void onPotionChanged() {
    InventoryHUD.PotHUD = this.PCW.getShow();
    InventoryHUD.getConfig().setPot(this.PCW.getShow());
  }
  
  private void onPotionPosChanged() {
    if (this.PCW.field_22760 + this.PCW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 3) {
      InventoryHUDGui.PotAligns.HorAlign = WidgetAligns.HAlign.LEFT;
      InventoryHUDGui.potX = this.PCW.field_22760;
      InventoryHUDGui.PotPosChanged();
    } else if (this.PCW.field_22760 + this.PCW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 3 * 2) {
      InventoryHUDGui.PotAligns.HorAlign = WidgetAligns.HAlign.RIGHT;
      InventoryHUDGui.potX = this.mc.field_1755.field_22789 - this.PCW.field_22760;
      InventoryHUDGui.PotPosChanged();
    } else {
      InventoryHUDGui.PotAligns.HorAlign = WidgetAligns.HAlign.MIDDLE;
      if (this.PCW.field_22760 + this.PCW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 2 - 10) {
        InventoryHUDGui.potX = this.mc.field_1755.field_22789 / 2 - this.PCW.method_25368() / 2 - this.PCW.field_22760;
        InventoryHUDGui.PotPosChanged();
      } else if (this.PCW.field_22760 + this.PCW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 2 + 10) {
        InventoryHUDGui.potX = this.mc.field_1755.field_22789 / 2 - this.PCW.method_25368() / 2 - this.PCW.field_22760;
        InventoryHUDGui.PotPosChanged();
      } else {
        InventoryHUDGui.potX = 0;
        InventoryHUDGui.PotPosChanged();
      } 
    } 
    if (this.PCW.field_22761 + this.PCW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 3) {
      InventoryHUDGui.PotAligns.VertAlign = WidgetAligns.VAlign.TOP;
      InventoryHUDGui.potY = this.PCW.field_22761;
      InventoryHUDGui.PotPosYChanged(true);
    } else if (this.PCW.field_22761 + this.PCW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 3 * 2) {
      InventoryHUDGui.PotAligns.VertAlign = WidgetAligns.VAlign.BOTTOM;
      InventoryHUDGui.potY = this.mc.field_1755.field_22790 - this.PCW.field_22761;
      InventoryHUDGui.PotPosYChanged(false);
    } else {
      InventoryHUDGui.PotAligns.VertAlign = WidgetAligns.VAlign.CENTER;
      if (this.PCW.field_22761 + this.PCW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
        InventoryHUDGui.potY = this.mc.field_1755.field_22790 / 2 - this.PCW.method_25364() / 2 - this.PCW.field_22761;
        InventoryHUDGui.PotPosYChanged(true);
      } else if (this.PCW.field_22761 + this.PCW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
        InventoryHUDGui.potY = this.mc.field_1755.field_22790 / 2 - this.PCW.method_25364() / 2 - this.PCW.field_22761;
        InventoryHUDGui.PotPosYChanged(false);
      } else {
        InventoryHUDGui.potY = 0;
        InventoryHUDGui.PotPosYChanged(true);
      } 
    } 
    InventoryHUD.getConfig().setPotX(InventoryHUDGui.potX);
    InventoryHUD.getConfig().setPotY(InventoryHUDGui.potY);
    InventoryHUD.getConfig().setPotHal(InventoryHUDGui.PotAligns.HorAlign);
    InventoryHUD.getConfig().setPotVal(InventoryHUDGui.PotAligns.VertAlign);
  }
  
  private void onArmorChanged() {
    InventoryHUD.ArmHUD = this.ACW.getShow();
    InventoryHUD.getConfig().setArm(this.ACW.getShow());
  }
  
  private void onArmorPosChanged() {
    if (this.ACW.field_22760 + this.ACW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 3) {
      InventoryHUDGui.ArmAligns.HorAlign = WidgetAligns.HAlign.LEFT;
      InventoryHUDGui.armX = this.ACW.field_22760;
    } else if (this.ACW.field_22760 + this.ACW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 3 * 2) {
      InventoryHUDGui.ArmAligns.HorAlign = WidgetAligns.HAlign.RIGHT;
      InventoryHUDGui.armX = this.mc.field_1755.field_22789 - this.ACW.field_22760;
    } else {
      InventoryHUDGui.ArmAligns.HorAlign = WidgetAligns.HAlign.MIDDLE;
      if (this.ACW.field_22760 + this.ACW.method_25368() / 2 <= this.mc.field_1755.field_22789 / 2 - 10) {
        InventoryHUDGui.armX = this.mc.field_1755.field_22789 / 2 - this.ACW.method_25368() / 2 - this.ACW.field_22760;
      } else if (this.ACW.field_22760 + this.ACW.method_25368() / 2 >= this.mc.field_1755.field_22789 / 2 + 10) {
        InventoryHUDGui.armX = this.mc.field_1755.field_22789 / 2 - this.ACW.method_25368() / 2 - this.ACW.field_22760;
      } else {
        InventoryHUDGui.armX = 0;
      } 
    } 
    if (this.ACW.field_22761 + this.ACW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
      InventoryHUDGui.ArmAligns.VertAlign = WidgetAligns.VAlign.TOP;
      InventoryHUDGui.armY = this.ACW.field_22761;
    } else if (this.ACW.field_22761 + this.ACW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
      InventoryHUDGui.ArmAligns.VertAlign = WidgetAligns.VAlign.BOTTOM;
      InventoryHUDGui.armY = this.mc.field_1755.field_22790 - this.ACW.field_22761;
    } else {
      InventoryHUDGui.ArmAligns.VertAlign = WidgetAligns.VAlign.CENTER;
      if (this.ACW.field_22761 + this.ACW.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
        InventoryHUDGui.armY = this.mc.field_1755.field_22790 / 2 - this.ACW.method_25364() / 2 - this.ACW.field_22761;
      } else if (this.ACW.field_22761 + this.ACW.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
        InventoryHUDGui.armY = this.mc.field_1755.field_22790 / 2 - this.ACW.method_25364() / 2 - this.ACW.field_22761;
      } else {
        InventoryHUDGui.armY = 0;
      } 
    } 
    InventoryHUD.getConfig().setArmX(InventoryHUDGui.armX);
    InventoryHUD.getConfig().setArmY(InventoryHUDGui.armY);
    InventoryHUD.getConfig().setArmHal(InventoryHUDGui.ArmAligns.HorAlign);
    InventoryHUD.getConfig().setArmVal(InventoryHUDGui.ArmAligns.VertAlign);
  }
  
  private void onItemPosChanged(ConfigWidget w, int i) {
    if (w.field_22760 + w.method_25368() / 2 <= this.mc.field_1755.field_22789 / 3) {
      (InventoryHUDGui.items[i]).aligns.HorAlign = WidgetAligns.HAlign.LEFT;
      (InventoryHUDGui.items[i]).x = w.field_22760;
    } else if (w.field_22760 + w.method_25368() / 2 >= this.mc.field_1755.field_22789 / 3 * 2) {
      (InventoryHUDGui.items[i]).aligns.HorAlign = WidgetAligns.HAlign.RIGHT;
      (InventoryHUDGui.items[i]).x = this.mc.field_1755.field_22789 - w.field_22760;
    } else {
      (InventoryHUDGui.items[i]).aligns.HorAlign = WidgetAligns.HAlign.MIDDLE;
      if (w.field_22760 + w.method_25368() / 2 <= this.mc.field_1755.field_22789 / 2 - 10) {
        (InventoryHUDGui.items[i]).x = this.mc.field_1755.field_22789 / 2 - w.method_25368() / 2 - w.field_22760;
      } else if (w.field_22760 + w.method_25368() / 2 >= this.mc.field_1755.field_22789 / 2 + 10) {
        (InventoryHUDGui.items[i]).x = this.mc.field_1755.field_22789 / 2 - w.method_25368() / 2 - w.field_22760;
      } else {
        (InventoryHUDGui.items[i]).x = 0;
      } 
    } 
    if (w.field_22761 + w.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
      (InventoryHUDGui.items[i]).aligns.VertAlign = WidgetAligns.VAlign.TOP;
      (InventoryHUDGui.items[i]).y = w.field_22761;
    } else if (w.field_22761 + w.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
      (InventoryHUDGui.items[i]).aligns.VertAlign = WidgetAligns.VAlign.BOTTOM;
      (InventoryHUDGui.items[i]).y = this.mc.field_1755.field_22790 - w.field_22761;
    } else {
      (InventoryHUDGui.items[i]).aligns.VertAlign = WidgetAligns.VAlign.CENTER;
      if (w.field_22761 + w.method_25364() / 2 <= this.mc.field_1755.field_22790 / 2 - 10) {
        (InventoryHUDGui.items[i]).y = this.mc.field_1755.field_22790 / 2 - w.method_25364() / 2 - w.field_22761;
      } else if (w.field_22761 + w.method_25364() / 2 >= this.mc.field_1755.field_22790 / 2 + 10) {
        (InventoryHUDGui.items[i]).y = this.mc.field_1755.field_22790 / 2 - w.method_25364() / 2 - w.field_22761;
      } else {
        (InventoryHUDGui.items[i]).y = 0;
      } 
    } 
    saveConfig();
  }
  
  private void saveConfig() {
    InventoryHUD.getConfig().setHelmX((InventoryHUDGui.items[0]).x);
    InventoryHUD.getConfig().setChestX((InventoryHUDGui.items[1]).x);
    InventoryHUD.getConfig().setLegX((InventoryHUDGui.items[2]).x);
    InventoryHUD.getConfig().setBootsX((InventoryHUDGui.items[3]).x);
    InventoryHUD.getConfig().setOffX((InventoryHUDGui.items[4]).x);
    InventoryHUD.getConfig().setMainX((InventoryHUDGui.items[5]).x);
    InventoryHUD.getConfig().setInvIconX((InventoryHUDGui.items[6]).x);
    InventoryHUD.getConfig().setArrX((InventoryHUDGui.items[7]).x);
    InventoryHUD.getConfig().setHelmY((InventoryHUDGui.items[0]).y);
    InventoryHUD.getConfig().setChestY((InventoryHUDGui.items[1]).y);
    InventoryHUD.getConfig().setLegY((InventoryHUDGui.items[2]).y);
    InventoryHUD.getConfig().setBootsY((InventoryHUDGui.items[3]).y);
    InventoryHUD.getConfig().setOffY((InventoryHUDGui.items[4]).y);
    InventoryHUD.getConfig().setMainY((InventoryHUDGui.items[5]).y);
    InventoryHUD.getConfig().setInvIconY((InventoryHUDGui.items[6]).y);
    InventoryHUD.getConfig().setArrY((InventoryHUDGui.items[7]).y);
    InventoryHUD.getConfig().setHelmHal((InventoryHUDGui.items[0]).aligns.HorAlign);
    InventoryHUD.getConfig().setChestHal((InventoryHUDGui.items[1]).aligns.HorAlign);
    InventoryHUD.getConfig().setLegHal((InventoryHUDGui.items[2]).aligns.HorAlign);
    InventoryHUD.getConfig().setBootsHal((InventoryHUDGui.items[3]).aligns.HorAlign);
    InventoryHUD.getConfig().setOffHal((InventoryHUDGui.items[4]).aligns.HorAlign);
    InventoryHUD.getConfig().setMainHal((InventoryHUDGui.items[5]).aligns.HorAlign);
    InventoryHUD.getConfig().setInvIconHal((InventoryHUDGui.items[6]).aligns.HorAlign);
    InventoryHUD.getConfig().setArrHal((InventoryHUDGui.items[7]).aligns.HorAlign);
    InventoryHUD.getConfig().setHelmVal((InventoryHUDGui.items[0]).aligns.VertAlign);
    InventoryHUD.getConfig().setChestVal((InventoryHUDGui.items[1]).aligns.VertAlign);
    InventoryHUD.getConfig().setLegVal((InventoryHUDGui.items[2]).aligns.VertAlign);
    InventoryHUD.getConfig().setBootsVal((InventoryHUDGui.items[3]).aligns.VertAlign);
    InventoryHUD.getConfig().setOffVal((InventoryHUDGui.items[4]).aligns.VertAlign);
    InventoryHUD.getConfig().setMainVal((InventoryHUDGui.items[5]).aligns.VertAlign);
    InventoryHUD.getConfig().setInvIconVal((InventoryHUDGui.items[6]).aligns.VertAlign);
    InventoryHUD.getConfig().setArrVal((InventoryHUDGui.items[7]).aligns.VertAlign);
  }
  
  public void blit(class_4587 mat, int in_1, int in_2, float in_3, float in_4, int in_5, int in_6, int in_7, int in_8) {
    method_25290(mat, in_1, in_2, in_3, in_4, in_5, in_6, in_7, in_8);
  }
  
  public void method_25419() {
    InventoryHUD.save();
    switch (this.menu) {
      case -1:
        this.mc.method_1507(null);
        break;
      case 0:
        this.mc.method_1507((class_437)new InventoryConfigScreen(this.prevScreen, this.inGame));
        break;
      case 1:
        this.mc.method_1507((class_437)new ArmorConfigScreen(this.prevScreen, this.inGame));
        break;
      case 2:
        this.mc.method_1507((class_437)new PotionConfigScreen(this.prevScreen, this.inGame));
        break;
    } 
  }
}
