package com.mssclient.process;
/** Windows process optimization facade for pausing background work while Minecraft runs. */
public final class ProcessOptimizer { private boolean active; public void enableGameMode() { active = true; } public void restore() { active = false; } public boolean isActive() { return active; } }
