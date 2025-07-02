package net.petemc.daycount.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.config.MainConfig;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3x2fStack;

public class DayCountHud implements Gui.RenderFunction {
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
            if (mc.gameMode.getPlayerMode().isSurvival() || mc.gameMode.getPlayerMode().isCreative()) {
                Matrix3x2fStack matrixStack = guiGraphics.pose();
                matrixStack.pushMatrix();
                matrixStack.translate(MainConfig.getLocationX(), MainConfig.getLocationY(), matrixStack);
                matrixStack.scale(MainConfig.getSizeX(), MainConfig.getSizeY(), matrixStack);
                guiGraphics.drawString(mc.font, MainConfig.getDayCounterString() + (currentDay + MainConfig.getDayOffset()), 1, 1, (int) Long.parseLong(MainConfig.getTextColorWithTransparency(), 16));
                matrixStack.popMatrix();
            }
        }
    }
}
