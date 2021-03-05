package dlovin.inventoryhud.mixin;

import dlovin.inventoryhud.InventoryHUD;
import dlovin.inventoryhud.gui.InventoryHUDGui;
import net.minecraft.class_329;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public class MixinOverlay {
  private InventoryHUDGui invGui = new InventoryHUDGui();
  
  @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusEffectOverlay(Lnet/minecraft/client/util/math/MatrixStack;)V")})
  public void renderInventoryHUD(class_4587 matrices, float tickDelta, CallbackInfo ci) {
    this.invGui.render();
  }
  
  @Inject(method = {"renderStatusEffectOverlay"}, at = {@At("HEAD")}, cancellable = true)
  public void renderPotions(class_4587 matrices, CallbackInfo ci) {
    if (InventoryHUD.getConfig().getPot())
      ci.cancel(); 
  }
}
