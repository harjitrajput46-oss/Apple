package com.iptv.glass;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class AppManager {

    private static final String PREFS = "glass_apps";
    private static final String KEY_FAVORITES = "favorites";
    private static final String KEY_RECENT = "recent";

    private final SharedPreferences prefs;

    public AppManager(Context context) {
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public void markRecent(String pkg) {
        Set<String> recent = new HashSet<String>(prefs.getStringSet(KEY_RECENT, new HashSet<String>()));
        recent.add(pkg);
        prefs.edit().putStringSet(KEY_RECENT, recent).apply();
    }

    public Set<String> getFavorites() {
        return new HashSet<String>(prefs.getStringSet(KEY_FAVORITES, new HashSet<String>()));
    }

    public void toggleFavorite(String pkg) {
        Set<String> favs = getFavorites();
        if (!favs.remove(pkg)) favs.add(pkg);
        prefs.edit().putStringSet(KEY_FAVORITES, favs).apply();
    }
}
