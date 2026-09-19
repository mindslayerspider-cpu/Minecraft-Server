package com.mssclient.config;

/** Toggleable PvP-client module exposed by the Right Shift click GUI. */
public enum ClientModule {
    FPS_BOOST(ModuleCategory.PERFORMANCE), FULLBRIGHT(ModuleCategory.RENDER), TOGGLE_SPRINT(ModuleCategory.UTILITY),
    KEYSTROKES(ModuleCategory.HUD), CPS_COUNTER(ModuleCategory.HUD), COORDINATES(ModuleCategory.HUD),
    ARMOR_HUD(ModuleCategory.HUD), CROSSHAIR(ModuleCategory.RENDER);
    private final ModuleCategory category; ClientModule(ModuleCategory category) { this.category = category; }
    public ModuleCategory category() { return category; }
    public String displayName() { var n = name().toLowerCase().replace('_',' '); return Character.toUpperCase(n.charAt(0)) + n.substring(1); }
}
