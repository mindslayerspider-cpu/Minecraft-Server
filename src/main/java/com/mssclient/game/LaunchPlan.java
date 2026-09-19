package com.mssclient.game;
import java.util.List;
public record LaunchPlan(String version, String account, List<String> jvmArgs, List<String> mods, List<String> clientModules) {}
