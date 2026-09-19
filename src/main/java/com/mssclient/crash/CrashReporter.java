package com.mssclient.crash;
import java.io.PrintWriter; import java.io.StringWriter; import java.time.Instant; import java.util.ArrayDeque; import java.util.Deque;
/** Captures uncaught errors for the built-in crash reporter and log viewer. */
public final class CrashReporter { private static final Deque<String> LOGS = new ArrayDeque<>(); private CrashReporter() {}
    public static void installGlobalHandler() { Thread.setDefaultUncaughtExceptionHandler((t,e) -> record("[" + Instant.now() + "] " + t.getName() + "\n" + stack(e))); }
    public static void record(String message) { if (LOGS.size() > 200) LOGS.removeFirst(); LOGS.addLast(message); }
    public static String logs() { return String.join("\n", LOGS); }
    private static String stack(Throwable e) { var sw = new StringWriter(); e.printStackTrace(new PrintWriter(sw)); return sw.toString(); }
}
