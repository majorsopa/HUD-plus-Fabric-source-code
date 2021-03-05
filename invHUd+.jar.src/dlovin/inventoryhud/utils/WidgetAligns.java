package dlovin.inventoryhud.utils;

public class WidgetAligns {
  public VAlign VertAlign;
  
  public HAlign HorAlign;
  
  public WidgetAligns(HAlign hAlign, VAlign vAlign) {
    this.VertAlign = vAlign;
    this.HorAlign = hAlign;
  }
  
  public enum VAlign {
    TOP, CENTER, BOTTOM;
  }
  
  public enum HAlign {
    LEFT, MIDDLE, RIGHT;
  }
}
