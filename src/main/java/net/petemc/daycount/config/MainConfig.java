package net.petemc.daycount.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.petemc.daycount.DayCount;

@Mod.EventBusSubscriber(modid = DayCount.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MainConfig
{
    public static boolean getDayCountEnabled() { return dayCountEnabled; }

    public static int getDayOffset() {
        return dayOffset;
    }

    public static float getSizeX() {
        return sizeX;
    }

    public static float getSizeY() {
        return sizeY;
    }

    public static float getLocationX() {
        return locationX;
    }

    public static float getLocationY() {
        return locationY;
    }

    public static int getTextColor() { return textColor; }

    // Server Config
    private static final ForgeConfigSpec.Builder BUILDER_SERVER = new ForgeConfigSpec.Builder();
    // no server config
    public static final ForgeConfigSpec SPEC_SERVER = BUILDER_SERVER.build();

    // Client Config
    private static final ForgeConfigSpec.Builder BUILDER_CLIENT = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DAY_COUNT_ENABLED = BUILDER_CLIENT
            .comment("If true, the Day Count will be displayed | default: true")
            .define("dayCountEnabled", true);

    private static final ForgeConfigSpec.IntValue DAY_OFFSET = BUILDER_CLIENT
            .comment("Offset to add to the Day Count | default: 1")
            .defineInRange("dayOffset", 1, 0, Integer.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue SIZE_X = BUILDER_CLIENT
            .comment("Horizontal size of the Day Counter | default: 2.0")
            .defineInRange("sizeX", 2.0, 0.0, 20000.0);

    private static final ForgeConfigSpec.DoubleValue SIZE_Y = BUILDER_CLIENT
            .comment("Vertical size of the Day Counter | default: 2.0")
            .defineInRange("sizeY", 2.0, 0.0, 20000.0);

    private static final ForgeConfigSpec.DoubleValue LOCATION_X = BUILDER_CLIENT
            .comment("Horizontal position of the Day Counter | default: 2.0")
            .defineInRange("locationX", 2.0, 0.0, 20000.0);

    private static final ForgeConfigSpec.DoubleValue LOCATION_Y = BUILDER_CLIENT
            .comment("Vertical position of the Day Counter | default: 2.0")
            .defineInRange("locationY", 2.0, 0.0, 20000.0);

    private static final ForgeConfigSpec.IntValue TEXT_COLOR = BUILDER_CLIENT
            .comment("Text color of the Day Counter | default: 16777215 (for white)")
            .defineInRange("textColor", 0xffffff, 0, Integer.MAX_VALUE);

    public static final ForgeConfigSpec SPEC_CLIENT = BUILDER_CLIENT.build();

    private static boolean dayCountEnabled = true;
    private static int dayOffset = 1;
    private static float sizeX = 2.0f;
    private static float sizeY = 2.0f;
    private static float locationX = 2.0f;
    private static float locationY = 2.0f;
    private static int textColor = 0xffffff;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        if (SPEC_SERVER.isLoaded()) {
            DayCount.LOGGER.info("Loading {} server config", DayCount.MOD_ID);
            // no server config
        }
        if (SPEC_CLIENT.isLoaded()) {
            DayCount.LOGGER.info("Loading {} client config", DayCount.MOD_ID);
            dayCountEnabled = DAY_COUNT_ENABLED.get();
            dayOffset = DAY_OFFSET.get();
            sizeX = SIZE_X.get().floatValue();
            sizeY = SIZE_Y.get().floatValue();
            locationX = LOCATION_X.get().floatValue();
            locationY = LOCATION_Y.get().floatValue();
            textColor = TEXT_COLOR.get();
        }
    }
}
