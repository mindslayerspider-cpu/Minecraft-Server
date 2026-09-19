package com.mssclient.app;

import com.mssclient.auth.AuthService;
import com.mssclient.config.ProfileStore;
import com.mssclient.crash.CrashReporter;
import com.mssclient.game.LauncherService;
import com.mssclient.mods.ModManager;
import com.mssclient.process.ProcessOptimizer;
import com.mssclient.ui.MainView;
import com.mssclient.update.UpdateService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Entry point for MSS CLIENT, a Windows-first JavaFX Minecraft launcher. */
public final class MssClientApp extends Application {
    @Override public void start(Stage stage) {
        CrashReporter.installGlobalHandler();
        var profileStore = new ProfileStore();
        var authService = new AuthService();
        var modManager = new ModManager(profileStore);
        var optimizer = new ProcessOptimizer();
        var launcher = new LauncherService(profileStore, authService, modManager, optimizer);
        var updates = new UpdateService();
        var view = new MainView(profileStore, authService, modManager, launcher, updates);
        var scene = new Scene(view.create(), 1180, 760);
        scene.getStylesheets().add(getClass().getResource("/com/mssclient/assets/theme.css").toExternalForm());
        stage.setTitle("MSS CLIENT");
        stage.setMinWidth(1000);
        stage.setMinHeight(680);
        stage.setScene(scene);
        stage.show();
        updates.checkAsync(view::showStatus);
    }

    public static void main(String[] args) { launch(args); }
}
