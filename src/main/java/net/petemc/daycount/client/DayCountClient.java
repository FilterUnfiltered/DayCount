package net.petemc.daycount.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.config.MainConfig;

@OnlyIn(Dist.CLIENT)
public class DayCountClient {
    private static String currentTextColor = "FFFFFF";

    public static void setCurrentTextColor(String value) {
        currentTextColor = value;
    }

    public String getCurrentTextColor() {
        return currentTextColor;
    }

    public static final IGuiOverlay HUD_DAY_COUNTER = ((gui, guiGraphics, partialTick, width, height) -> {
        if (DayCount.dayCountEnabled) {
            Minecraft mc = Minecraft.getInstance();
            assert mc.level != null;
            int currentDay = (int) (mc.level.getDayTime() / 24000L);
            assert mc.gameMode != null;
            if (((mc.gameMode.getPlayerMode().isSurvival() || mc.gameMode.getPlayerMode().isCreative()) &&
                    !mc.options.renderDebug)) {
                PoseStack matrixStack = guiGraphics.pose();
                matrixStack.pushPose();
                matrixStack.translate(MainConfig.getLocationX(), MainConfig.getLocationY(), 0);
                matrixStack.scale(MainConfig.getSizeX(), MainConfig.getSizeY(), 2.5f);

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                guiGraphics.drawString(mc.font, MainConfig.getDayCounterString() + (currentDay + MainConfig.getDayOffset()), 2, 2, Integer.parseInt(currentTextColor, 16));
                matrixStack.popPose();
            }
        }
    });
}
