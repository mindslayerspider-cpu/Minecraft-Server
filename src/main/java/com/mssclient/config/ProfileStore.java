package com.mssclient.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** In-memory profile repository; persistence hooks are isolated for future JSON storage. */
public final class ProfileStore {
    private final List<LauncherProfile> profiles = new ArrayList<>(); private LauncherProfile selected;
    public ProfileStore() { profiles.add(new LauncherProfile("PvP 1.8.9", MinecraftVersion.V1_8_9)); var modern = new LauncherProfile("Modern FPS", MinecraftVersion.V1_21_PLUS); modern.setSodium(true); modern.setLithium(true); modern.setFerriteCore(true); profiles.add(modern); selected = profiles.getFirst(); }
    public List<LauncherProfile> profiles() { return List.copyOf(profiles); } public LauncherProfile selected() { return selected; }
    public void select(String name) { selected = profiles.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(selected); }
    public LauncherProfile create(String name, MinecraftVersion version) { var p = new LauncherProfile(name, version); profiles.add(p); selected = p; return p; }
    public Optional<LauncherProfile> find(String name) { return profiles.stream().filter(p -> p.getName().equals(name)).findFirst(); }
}
