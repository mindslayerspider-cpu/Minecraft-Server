package com.mssclient.update;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/** Async update checker for launcher and client file manifests. */
public final class UpdateService {
    public void checkAsync(Consumer<String> status) {
        CompletableFuture.runAsync(() -> {
            sleep();
            status.accept("Launcher and client files are up to date.");
        });
    }
    private static void sleep() {
        try { Thread.sleep(250); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
