package com.mssclient.config;

import java.nio.file.Path;
import java.util.EnumSet;

/** Immutable-ish profile model for launches and modpacks. */
public final class LauncherProfile {
    private String name; private MinecraftVersion version; private boolean offlineMode; private boolean fpsBoost = true;
    private boolean lowMemoryMode; private boolean optifine = true; private boolean sodium; private boolean lithium; private boolean ferriteCore;
    private Path gameDirectory = Path.of(System.getProperty("user.home"), "AppData", "Roaming", ".mss-client");
    private final EnumSet<ClientModule> modules = EnumSet.of(ClientModule.FPS_BOOST, ClientModule.FULLBRIGHT, ClientModule.TOGGLE_SPRINT, ClientModule.KEYSTROKES);
    public LauncherProfile(String name, MinecraftVersion version) { this.name = name; this.version = version; }
    public String getName() { return name; } public MinecraftVersion getVersion() { return version; } public boolean isOfflineMode() { return offlineMode; }
    public boolean isFpsBoost() { return fpsBoost; } public boolean isLowMemoryMode() { return lowMemoryMode; } public boolean isOptifine() { return optifine; }
    public boolean isSodium() { return sodium; } public boolean isLithium() { return lithium; } public boolean isFerriteCore() { return ferriteCore; }
    public Path getGameDirectory() { return gameDirectory; } public EnumSet<ClientModule> getModules() { return EnumSet.copyOf(modules); }
    public void setOfflineMode(boolean offlineMode) { this.offlineMode = offlineMode; } public void setFpsBoost(boolean fpsBoost) { this.fpsBoost = fpsBoost; }
    public void setLowMemoryMode(boolean lowMemoryMode) { this.lowMemoryMode = lowMemoryMode; } public void setOptifine(boolean optifine) { this.optifine = optifine; }
    public void setSodium(boolean sodium) { this.sodium = sodium; } public void setLithium(boolean lithium) { this.lithium = lithium; } public void setFerriteCore(boolean ferriteCore) { this.ferriteCore = ferriteCore; }
    public void toggleModule(ClientModule module, boolean enabled) { if (enabled) modules.add(module); else modules.remove(module); }
}
