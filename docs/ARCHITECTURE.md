# MSS CLIENT Architecture

MSS CLIENT is a Windows-first Java/JavaFX launcher scaffold for Minecraft 1.8.9 through the latest release. It separates launcher concerns into small OOP services so authentication, launch planning, mod management, updates, crash reporting, and JavaFX UI can evolve independently.

## Launcher features

- Microsoft login integration point and offline-mode sessions are isolated in `AuthService`.
- `LauncherProfile` stores version, RAM/performance options, PvP modules, and optional OptiFine/Fabric performance mods.
- `JavaRuntimePlanner` computes RAM allocation and JVM arguments for FPS Boost and low-memory mode.
- `ModManager` resolves OptiFine for 1.8.9 and Sodium, Lithium, and FerriteCore for modern Fabric versions.
- `ProcessOptimizer` is the Windows game-mode facade used while Minecraft is running.
- `UpdateService` performs asynchronous launcher/client manifest checks.
- `CrashReporter` records uncaught exceptions and launcher events for the log viewer.

## Right Shift GUI client design

The launcher stores modules for a companion Minecraft client mod. The in-game implementation should be delivered as a Forge 1.8.9 mod and Fabric 1.21+ mod that read the profile module manifest and open a click GUI on Right Shift. Required categories are Render, HUD, Utility, and Performance. HUD modules should support draggable anchors and smooth transitions.

This repository includes the launcher-side model and configuration UI. Shipping production Minecraft injection code requires version-specific Forge/Fabric modules and compliance with Microsoft/Mojang authentication and server rules.

## Security and stability priorities

- Never store plaintext Microsoft credentials; use OAuth/device-code tokens and OS-protected storage in production.
- Keep offline mode clearly labeled and do not use it to bypass server authentication.
- Download mods only from pinned manifests with SHA-256 verification.
- Run process optimization through reversible Windows APIs and always restore settings after Minecraft exits.
