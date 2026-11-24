package com.iptv.glass;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static final String PREFS = "glass_settings";
    private static final String KEY_ANIMATIONS = "animations_enabled";

    private final SharedPreferences prefs;

    public SettingsManager(Context context) {
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public boolean isAnimationsEnabled() {
        return prefs.getBoolean(KEY_ANIMATIONS, true);
    }

    public void setAnimationsEnabled(boolean enabled) {
        prefs.edit().putBoolean(KEY_ANIMATIONS, enabled).apply();
    }
}
