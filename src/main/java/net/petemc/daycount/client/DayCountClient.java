package net.petemc.daycount.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.config.MainConfig;

public class DayCountClient {

    public static final IGuiOverlay HUD_DAY_COUNTER = ((gui, guiGraphics, partialTick, width, height) -> {
        if (DayCount.dayCountEnabled) {
            Minecraft mc = Minecraft.getInstance();
            assert mc.level != null;
            int currentDay = (int) (mc.level.getDayTime() / 24000L);
            assert mc.gameMode != null;
            if (mc.gameMode.hasExperience() || mc.gameMode.hasInfiniteItems()) {
                PoseStack matrixStack = guiGraphics.pose();
                matrixStack.pushPose();
                matrixStack.translate(MainConfig.getLocationX(), MainConfig.getLocationY(), 0);
                matrixStack.scale(MainConfig.getSizeX(), MainConfig.getSizeY(), 2.5f);

                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                guiGraphics.drawString(mc.font, "Day: " + (currentDay + MainConfig.getDayOffset()), 2, 2, MainConfig.getTextColor());
                matrixStack.popPose();
            }
        }
    });
}
