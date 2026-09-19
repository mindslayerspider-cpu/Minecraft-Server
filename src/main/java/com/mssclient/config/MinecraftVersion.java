package com.mssclient.config;

/** Supported Minecraft generation with safe optimization defaults. */
public enum MinecraftVersion {
    V1_8_9("1.8.9", true, false), V1_21_PLUS("Latest release", false, true);
    private final String label; private final boolean optifine; private final boolean fabric;
    MinecraftVersion(String label, boolean optifine, boolean fabric) { this.label = label; this.optifine = optifine; this.fabric = fabric; }
    public String label() { return label; } public boolean supportsOptiFine() { return optifine; } public boolean supportsFabricPerformanceMods() { return fabric; }
}
