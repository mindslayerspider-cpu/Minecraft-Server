# MSS CLIENT

MSS CLIENT is a modern custom Minecraft launcher scaffold for Windows built with Java 21 and JavaFX. It targets Minecraft 1.8.9 through the latest release and focuses on PvP-friendly performance, profiles, mod management, update checks, crash logs, and a dark animated UI.

The repository also retains the original Redmi Note 8 ROM starter under `rom/redmi-note8-gaming/`.

## Implemented launcher scaffold

- JavaFX dark UI with animated launch shell, glass-style cards, buttons, progress bars, news, Discord hook placeholder, and log viewer.
- Microsoft-account login integration point plus explicit offline mode.
- Profile support for 1.8.9 PvP and modern releases.
- FPS Boost planning with automatic RAM selection, custom JVM arguments, low-memory mode, and process optimization facade.
- OptiFine option for 1.8.9 and Sodium, Lithium, FerriteCore options for 1.21+ profiles.
- Launcher-side Right Shift GUI module model for FPS Boost, Fullbright, Toggle Sprint, Keystrokes, CPS Counter, Coordinates, Armor HUD, and Crosshair customization.
- Modpack export hook, automatic update-check hook, and crash/log reporting hook.

## Build and run

```bash
gradle build
gradle run
```

## Project structure

```text
src/main/java/com/mssclient/app      JavaFX application entry point
src/main/java/com/mssclient/auth     Microsoft/offline session services
src/main/java/com/mssclient/config   Profiles, versions, module model
src/main/java/com/mssclient/game     Launch planning and JVM optimization
src/main/java/com/mssclient/mods     Mod selection and modpack hooks
src/main/java/com/mssclient/process  Windows process optimization facade
src/main/java/com/mssclient/update   Launcher/client update checking
src/main/java/com/mssclient/crash    Crash reporting and log capture
src/main/java/com/mssclient/ui       Modern JavaFX UI
src/main/resources/com/mssclient     CSS assets
```

See `docs/ARCHITECTURE.md` for implementation notes and production hardening guidance.
