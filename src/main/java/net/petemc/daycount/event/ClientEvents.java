package net.petemc.daycount.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.petemc.daycount.DayCount;
import net.petemc.daycount.client.DayCountHud;
import net.petemc.daycount.util.KeyBinding;

public class ClientEvents {

    @EventBusSubscriber (value = Dist.CLIENT, modid = DayCount.MOD_ID)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KeyBinding.DAYCOUNT_KEY.consumeClick()) {
                DayCount.dayCountEnabled = !DayCount.dayCountEnabled;
            }
        }

        @SubscribeEvent
        public static void registerGuiOverlays(RenderGuiEvent.Post event) {
            DayCountHud.DAY_COUNT_HUD_INSTANCE.render(event.getGuiGraphics(), event.getPartialTick());
        }
    }

    @EventBusSubscriber(modid = DayCount.MOD_ID, value = Dist.CLIENT)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.DAYCOUNT_KEY);
        }
    }
}
