package net.petemc.daycount.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.config.MainConfig;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class DayCountHud implements LayeredDraw.Layer {
    public static DayCountHud DAY_COUNT_HUD_INSTANCE;
    private static String currentTextColor = "FFFFFF";

    public static void setCurrentTextColor(String value) {
        currentTextColor = value;
    }

    public String getCurrentTextColor() {
        return currentTextColor;
    }

    public static void init() {
        DAY_COUNT_HUD_INSTANCE = new DayCountHud();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, float v) {
        if (DayCount.dayCountEnabled) {
            Minecraft mc = Minecraft.getInstance();
            assert mc.level != null;
            int currentDay = (int) (mc.level.getDayTime() / 24000L);
            assert mc.gameMode != null;
            if (mc.gameMode.getPlayerMode().isSurvival() || mc.gameMode.getPlayerMode().isCreative()) {
                PoseStack matrixStack = guiGraphics.pose();
                matrixStack.pushPose();
                matrixStack.translate(MainConfig.getLocationX(), MainConfig.getLocationY(), 0);
                matrixStack.scale(MainConfig.getSizeX(), MainConfig.getSizeY(), 2.5f);

                guiGraphics.drawString(mc.font, MainConfig.getDayCounterString() + (currentDay + MainConfig.getDayOffset()), 1, 1, Integer.parseInt(currentTextColor, 16));
                matrixStack.popPose();
            }
        }
    }
}
