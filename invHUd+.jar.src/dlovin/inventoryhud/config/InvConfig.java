package dlovin.inventoryhud.config;

import com.google.gson.annotations.SerializedName;
import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.utils.WidgetAligns;

public class InvConfig {
  private boolean invToggle = true;
  
  private boolean invMini = false;
  
  private boolean invVert = false;
  
  private int invAlpha = 0;
  
  private boolean potToggle = false;
  
  private int potAlpha = 100;
  
  private int potGap = 0;
  
  private boolean potMini = false;
  
  private boolean armToggle = false;
  
  private int armAbove = 100;
  
  private ArmorView armView = ArmorView.PERCENTAGE;
  
  private boolean moveAll = true;
  
  private boolean showEmpty = true;
  
  private boolean showArmor = true;
  
  private boolean showMain = true;
  
  private boolean showOff = true;
  
  private boolean showArrows = true;
  
  private boolean showInv = true;
  
  private boolean armBars = false;
  
  private int invX = 0;
  
  private int invY = 150;
  
  private WidgetAligns.VAlign invVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.HAlign invHal = WidgetAligns.HAlign.MIDDLE;
  
  private int potX = 30;
  
  private int potY = 0;
  
  private WidgetAligns.VAlign potVal = WidgetAligns.VAlign.CENTER;
  
  private WidgetAligns.HAlign potHal = WidgetAligns.HAlign.LEFT;
  
  private int armX = 0;
  
  private int armY = 70;
  
  private WidgetAligns.VAlign armVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.HAlign armHal = WidgetAligns.HAlign.MIDDLE;
  
  private int helmX = 103;
  
  private int helmY = 54;
  
  private int chestX = 103;
  
  private int chestY = 37;
  
  private int legX = -103;
  
  private int legY = 54;
  
  private int bootsX = -103;
  
  private int bootsY = 37;
  
  private int mainX = 103;
  
  private int mainY = 71;
  
  private int offX = -103;
  
  private int offY = 71;
  
  private int arrX = 103;
  
  private int arrY = 20;
  
  private int invIconX = -103;
  
  private int invIconY = 20;
  
  private WidgetAligns.HAlign helmHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign chestHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign legHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign bootsHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign mainHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign offHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign arrHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.HAlign invIconHal = WidgetAligns.HAlign.MIDDLE;
  
  private WidgetAligns.VAlign helmVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign chestVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign legVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign bootsVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign mainVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign offVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign arrVal = WidgetAligns.VAlign.BOTTOM;
  
  private WidgetAligns.VAlign invIconVal = WidgetAligns.VAlign.BOTTOM;
  
  public boolean getInv() {
    return this.invToggle;
  }
  
  public boolean getPot() {
    return this.potToggle;
  }
  
  public boolean getArm() {
    return this.armToggle;
  }
  
  public boolean getInvMini() {
    return this.invMini;
  }
  
  public int getInvX() {
    return this.invX;
  }
  
  public int getInvY() {
    return this.invY;
  }
  
  public int getPotX() {
    return this.potX;
  }
  
  public int getPotY() {
    return this.potY;
  }
  
  public int getArmX() {
    return this.armX;
  }
  
  public int getArmY() {
    return this.armY;
  }
  
  public WidgetAligns.VAlign getInvVal() {
    return this.invVal;
  }
  
  public WidgetAligns.HAlign getInvHal() {
    return this.invHal;
  }
  
  public WidgetAligns.VAlign getPotVal() {
    return this.potVal;
  }
  
  public WidgetAligns.HAlign getPotHal() {
    return this.potHal;
  }
  
  public WidgetAligns.VAlign getArmVal() {
    return this.armVal;
  }
  
  public WidgetAligns.HAlign getArmHal() {
    return this.armHal;
  }
  
  public boolean getInvVert() {
    return this.invVert;
  }
  
  public int getPotAlpha() {
    return this.potAlpha;
  }
  
  public int getPotGap() {
    return this.potGap;
  }
  
  public boolean getPotMini() {
    return this.potMini;
  }
  
  public int getArmAbove() {
    return this.armAbove;
  }
  
  public ArmorView getArmView() {
    return this.armView;
  }
  
  public boolean getArmBars() {
    return this.armBars;
  }
  
  public void setArmBars(boolean armBars) {
    this.armBars = armBars;
    InventoryHUD.save();
  }
  
  public void setInv(boolean val) {
    this.invToggle = val;
    InventoryHUD.save();
  }
  
  public void setInvMini(boolean val) {
    this.invMini = val;
    InventoryHUD.save();
  }
  
  public void setInvVert(boolean val) {
    this.invVert = val;
    InventoryHUD.save();
  }
  
  public void setPot(boolean val) {
    this.potToggle = val;
    InventoryHUD.save();
  }
  
  public void setArm(boolean val) {
    this.armToggle = val;
    InventoryHUD.save();
  }
  
  public void setPotAlpha(int alpha) {
    this.potAlpha = alpha;
    InventoryHUD.save();
  }
  
  public void setPotGap(int gap) {
    this.potGap = gap;
    InventoryHUD.save();
  }
  
  public void setPotMini(boolean val) {
    this.potMini = val;
    InventoryHUD.save();
  }
  
  public void setArmAbove(int above) {
    this.armAbove = above;
    InventoryHUD.save();
  }
  
  public void setArmView(ArmorView armView) {
    this.armView = armView;
    InventoryHUD.save();
  }
  
  public void setInvX(int val) {
    this.invX = val;
    InventoryHUD.save();
  }
  
  public void setInvY(int val) {
    this.invY = val;
    InventoryHUD.save();
  }
  
  public void setPotX(int val) {
    this.potX = val;
    InventoryHUD.save();
  }
  
  public void setPotY(int val) {
    this.potY = val;
    InventoryHUD.save();
  }
  
  public void setArmX(int val) {
    this.armX = val;
    InventoryHUD.save();
  }
  
  public void setArmY(int val) {
    this.armY = val;
    InventoryHUD.save();
  }
  
  public void setInvVal(WidgetAligns.VAlign val) {
    this.invVal = val;
    InventoryHUD.save();
  }
  
  public void setInvHal(WidgetAligns.HAlign val) {
    this.invHal = val;
    InventoryHUD.save();
  }
  
  public void setPotVal(WidgetAligns.VAlign val) {
    this.potVal = val;
    InventoryHUD.save();
  }
  
  public void setPotHal(WidgetAligns.HAlign val) {
    this.potHal = val;
    InventoryHUD.save();
  }
  
  public void setArmVal(WidgetAligns.VAlign val) {
    this.armVal = val;
    InventoryHUD.save();
  }
  
  public void setArmHal(WidgetAligns.HAlign val) {
    this.armHal = val;
    InventoryHUD.save();
  }
  
  public int getHelmX() {
    return this.helmX;
  }
  
  public void setHelmX(int helmX) {
    this.helmX = helmX;
    InventoryHUD.save();
  }
  
  public int getHelmY() {
    return this.helmY;
  }
  
  public void setHelmY(int helmY) {
    this.helmY = helmY;
    InventoryHUD.save();
  }
  
  public int getChestX() {
    return this.chestX;
  }
  
  public void setChestX(int chestX) {
    this.chestX = chestX;
    InventoryHUD.save();
  }
  
  public int getChestY() {
    return this.chestY;
  }
  
  public void setChestY(int chestY) {
    this.chestY = chestY;
    InventoryHUD.save();
  }
  
  public int getLegX() {
    return this.legX;
  }
  
  public void setLegX(int legX) {
    this.legX = legX;
    InventoryHUD.save();
  }
  
  public int getLegY() {
    return this.legY;
  }
  
  public void setLegY(int legY) {
    this.legY = legY;
    InventoryHUD.save();
  }
  
  public int getBootsX() {
    return this.bootsX;
  }
  
  public void setBootsX(int bootsX) {
    this.bootsX = bootsX;
    InventoryHUD.save();
  }
  
  public int getBootsY() {
    return this.bootsY;
  }
  
  public void setBootsY(int bootsY) {
    this.bootsY = bootsY;
    InventoryHUD.save();
  }
  
  public int getMainX() {
    return this.mainX;
  }
  
  public void setMainX(int mainX) {
    this.mainX = mainX;
    InventoryHUD.save();
  }
  
  public int getMainY() {
    return this.mainY;
  }
  
  public void setMainY(int mainY) {
    this.mainY = mainY;
    InventoryHUD.save();
  }
  
  public int getOffX() {
    return this.offX;
  }
  
  public void setOffX(int offX) {
    this.offX = offX;
    InventoryHUD.save();
  }
  
  public int getOffY() {
    return this.offY;
  }
  
  public void setOffY(int offY) {
    this.offY = offY;
    InventoryHUD.save();
  }
  
  public int getArrX() {
    return this.arrX;
  }
  
  public void setArrX(int arrX) {
    this.arrX = arrX;
    InventoryHUD.save();
  }
  
  public int getArrY() {
    return this.arrY;
  }
  
  public void setArrY(int arrY) {
    this.arrY = arrY;
    InventoryHUD.save();
  }
  
  public int getInvIconX() {
    return this.invIconX;
  }
  
  public void setInvIconX(int invX) {
    this.invIconX = invX;
    InventoryHUD.save();
  }
  
  public int getInvIconY() {
    return this.invIconY;
  }
  
  public void setInvIconY(int invY) {
    this.invIconY = invY;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getHelmHal() {
    return this.helmHal;
  }
  
  public void setHelmHal(WidgetAligns.HAlign helmHal) {
    this.helmHal = helmHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getChestHal() {
    return this.chestHal;
  }
  
  public void setChestHal(WidgetAligns.HAlign chestHal) {
    this.chestHal = chestHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getLegHal() {
    return this.legHal;
  }
  
  public void setLegHal(WidgetAligns.HAlign legHal) {
    this.legHal = legHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getBootsHal() {
    return this.bootsHal;
  }
  
  public void setBootsHal(WidgetAligns.HAlign bootsHal) {
    this.bootsHal = bootsHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getMainHal() {
    return this.mainHal;
  }
  
  public void setMainHal(WidgetAligns.HAlign mainHal) {
    this.mainHal = mainHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getOffHal() {
    return this.offHal;
  }
  
  public void setOffHal(WidgetAligns.HAlign offHal) {
    this.offHal = offHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getArrHal() {
    return this.arrHal;
  }
  
  public void setArrHal(WidgetAligns.HAlign arrHal) {
    this.arrHal = arrHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.HAlign getInvIconHal() {
    return this.invIconHal;
  }
  
  public void setInvIconHal(WidgetAligns.HAlign invHal) {
    this.invIconHal = invHal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getHelmVal() {
    return this.helmVal;
  }
  
  public void setHelmVal(WidgetAligns.VAlign helmVal) {
    this.helmVal = helmVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getChestVal() {
    return this.chestVal;
  }
  
  public void setChestVal(WidgetAligns.VAlign chestVal) {
    this.chestVal = chestVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getLegVal() {
    return this.legVal;
  }
  
  public void setLegVal(WidgetAligns.VAlign legVal) {
    this.legVal = legVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getBootsVal() {
    return this.bootsVal;
  }
  
  public void setBootsVal(WidgetAligns.VAlign bootsVal) {
    this.bootsVal = bootsVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getMainVal() {
    return this.mainVal;
  }
  
  public void setMainVal(WidgetAligns.VAlign mainVal) {
    this.mainVal = mainVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getOffVal() {
    return this.offVal;
  }
  
  public void setOffVal(WidgetAligns.VAlign offVal) {
    this.offVal = offVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getArrVal() {
    return this.arrVal;
  }
  
  public void setArrVal(WidgetAligns.VAlign arrVal) {
    this.arrVal = arrVal;
    InventoryHUD.save();
  }
  
  public WidgetAligns.VAlign getInvIconVal() {
    return this.invIconVal;
  }
  
  public void setInvIconVal(WidgetAligns.VAlign invVal) {
    this.invIconVal = invVal;
    InventoryHUD.save();
  }
  
  public boolean isShowEmpty() {
    return this.showEmpty;
  }
  
  public void setShowEmpty(boolean showEmpty) {
    this.showEmpty = showEmpty;
    InventoryHUD.save();
  }
  
  public boolean isShowArmor() {
    return this.showArmor;
  }
  
  public void setShowArmor(boolean showArmor) {
    this.showArmor = showArmor;
    InventoryHUD.save();
  }
  
  public boolean isShowMain() {
    return this.showMain;
  }
  
  public void setShowMain(boolean showMain) {
    this.showMain = showMain;
    InventoryHUD.save();
  }
  
  public boolean isShowOff() {
    return this.showOff;
  }
  
  public void setShowOff(boolean showOff) {
    this.showOff = showOff;
    InventoryHUD.save();
  }
  
  public boolean isShowArrows() {
    return this.showArrows;
  }
  
  public void setShowArrows(boolean showArrows) {
    this.showArrows = showArrows;
    InventoryHUD.save();
  }
  
  public boolean isShowInv() {
    return this.showInv;
  }
  
  public void setShowInv(boolean showInv) {
    this.showInv = showInv;
    InventoryHUD.save();
  }
  
  public boolean getMoveAll() {
    return this.moveAll;
  }
  
  public void setMoveAll(boolean moveAll) {
    this.moveAll = moveAll;
    InventoryHUD.save();
  }
  
  public int getInvAlpha() {
    return this.invAlpha;
  }
  
  public void setInvAlpha(int invAlpha) {
    this.invAlpha = invAlpha;
    InventoryHUD.save();
  }
  
  public enum ArmorView {
    PERCENTAGE, DAMAGE, DAMAGE_LEFT, OFF;
  }
}
