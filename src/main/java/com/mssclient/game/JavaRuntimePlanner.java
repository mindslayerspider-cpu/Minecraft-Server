package com.mssclient.game;

import com.mssclient.config.LauncherProfile;
import java.util.ArrayList;
import java.util.List;

/** Calculates RAM and JVM flags optimized for PvP stability rather than unsafe overclocking. */
public final class JavaRuntimePlanner {
    public int optimalRamMb(LauncherProfile profile) {
        long totalMb = Runtime.getRuntime().maxMemory() / (1024 * 1024);
        int detected = (int)Math.max(2048, Math.min(6144, totalMb));
        if (profile.isLowMemoryMode()) return 1024;
        return profile.getVersion().supportsFabricPerformanceMods() ? Math.max(3072, detected) : Math.max(2048, detected / 2);
    }
    public List<String> jvmArgs(LauncherProfile profile) {
        var args = new ArrayList<String>(); int ram = optimalRamMb(profile);
        args.add("-Xms" + Math.min(1024, ram) + "M"); args.add("-Xmx" + ram + "M");
        args.add("-XX:+UseG1GC"); args.add("-XX:+ParallelRefProcEnabled"); args.add("-XX:MaxGCPauseMillis=50");
        args.add("-Dsun.java2d.opengl=true"); args.add("-Dmssclient.fpsBoost=" + profile.isFpsBoost());
        if (profile.isLowMemoryMode()) args.add("-XX:G1HeapRegionSize=16M");
        return args;
    }
}
