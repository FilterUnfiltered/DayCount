package net.petemc.daycount.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.client.renderer.GameRenderer;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.config.MainConfig;
import org.jetbrains.annotations.NotNull;

public class DayCountHud implements LayeredDraw.Layer {
    public static DayCountHud DAY_COUNT_HUD_INSTANCE;

    public static void init() {
        DAY_COUNT_HUD_INSTANCE = new DayCountHud();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
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
    }
}
