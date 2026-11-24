package com.iptv.glass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    private LinearLayout rowApps;
    private LinearLayout rowAnime;
    private LinearLayout rowMovies;
    private AppManager appManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rowApps = (LinearLayout) findViewById(R.id.row_apps);
        rowAnime = (LinearLayout) findViewById(R.id.row_anime);
        rowMovies = (LinearLayout) findViewById(R.id.row_movies);
        appManager = new AppManager(this);

        loadInstalledApps();
        addPlaceholderCard(rowAnime, "Anime Mini App", AnimeActivity.class);
        addPlaceholderCard(rowMovies, "Movies", MovieActivity.class);
    }

    private void loadInstalledApps() {
        PackageManager pm = getPackageManager();
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = pm.queryIntentActivities(launcherIntent, 0);

        for (final ResolveInfo ri : apps) {
            final String pkg = ri.activityInfo.packageName;
            if (pkg.equals(getPackageName())) continue;

            TextView card = makeCard(ri.loadLabel(pm).toString());
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent launch = getPackageManager().getLaunchIntentForPackage(pkg);
                    if (launch != null) {
                        appManager.markRecent(pkg);
                        startActivity(launch);
                    }
                }
            });
            rowApps.addView(card);
        }
    }

    private void addPlaceholderCard(LinearLayout row, String title, final Class<?> target) {
        TextView card = makeCard(title);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, target));
            }
        });
        row.addView(card);
    }

    private TextView makeCard(String text) {
        TextView card = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(220, 120);
        lp.setMargins(12, 0, 12, 0);
        card.setLayoutParams(lp);
        card.setBackgroundColor(getResources().getColor(R.color.glass));
        card.setTextColor(getResources().getColor(R.color.text_primary));
        card.setGravity(Gravity.CENTER);
        card.setText(text);
        card.setFocusable(true);
        card.setFocusableInTouchMode(false);
        card.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                v.setBackgroundColor(getResources().getColor(
                        hasFocus ? R.color.focus : R.color.glass));
            }
        });
        return card;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
