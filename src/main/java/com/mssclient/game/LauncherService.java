package com.mssclient.game;

import com.mssclient.auth.AuthService; import com.mssclient.config.ClientModule; import com.mssclient.config.ProfileStore; import com.mssclient.mods.ModManager; import com.mssclient.process.ProcessOptimizer;
import java.util.stream.Collectors;

/** Builds and starts Minecraft launch plans; process execution is isolated for safety and testing. */
public final class LauncherService {
    private final ProfileStore profiles; private final AuthService auth; private final ModManager mods; private final ProcessOptimizer optimizer; private final JavaRuntimePlanner planner = new JavaRuntimePlanner();
    public LauncherService(ProfileStore profiles, AuthService auth, ModManager mods, ProcessOptimizer optimizer) { this.profiles = profiles; this.auth = auth; this.mods = mods; this.optimizer = optimizer; }
    public LaunchPlan dryRunPlan() { var p = profiles.selected(); var session = auth.currentSession().orElseGet(() -> auth.offline("Player")); return new LaunchPlan(p.getVersion().label(), session.username(), planner.jvmArgs(p), mods.enabledMods(p), p.getModules().stream().map(ClientModule::displayName).collect(Collectors.toList())); }
    public LaunchPlan launch() { var plan = dryRunPlan(); optimizer.enableGameMode(); return plan; }
}
