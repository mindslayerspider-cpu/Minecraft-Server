package com.mssclient.mods;
import com.mssclient.config.LauncherProfile; import com.mssclient.config.ProfileStore; import java.util.ArrayList; import java.util.List;
/** Resolves optional OptiFine/Fabric performance mods and import/export modpack hooks. */
public final class ModManager { public ModManager(ProfileStore ignored) {}
    public List<String> enabledMods(LauncherProfile p) { var mods = new ArrayList<String>(); if (p.isOptifine() && p.getVersion().supportsOptiFine()) mods.add("OptiFine"); if (p.isSodium()) mods.add("Sodium"); if (p.isLithium()) mods.add("Lithium"); if (p.isFerriteCore()) mods.add("FerriteCore"); return mods; }
    public String exportModpack(LauncherProfile p) { return "msspack:" + p.getName() + ":" + String.join(",", enabledMods(p)); }
}
