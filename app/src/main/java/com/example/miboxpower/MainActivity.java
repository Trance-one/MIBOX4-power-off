package com.example.miboxpower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(48, 32, 48, 32);
        layout.setBackgroundColor(0xFF101820);

        TextView title = new TextView(this);
        title.setText("Mi Box Power");
        title.setTextSize(28);
        title.setTextColor(0xFFFFFFFF);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        message = new TextView(this);
        message.setTextSize(16);
        message.setTextColor(0xFFCFD8DC);
        message.setGravity(Gravity.CENTER);
        message.setPadding(0, 24, 0, 24);
        layout.addView(message);

        Button power = new Button(this);
        power.setText(getString(R.string.power_button));
        power.setTextSize(18);
        power.setOnClickListener(v -> {
            if (!PowerAccessibilityService.isEnabled()) {
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            } else if (!PowerAccessibilityService.showPowerDialog()) {
                message.setText("Не удалось открыть системное меню питания на этой прошивке.");
            }
        });
        layout.addView(power);

        Button settings = new Button(this);
        settings.setText(getString(R.string.open_settings));
        settings.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
        layout.addView(settings);

        setContentView(layout);
        updateMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateMessage();
    }

    private void updateMessage() {
        if (message == null) return;
        message.setText(PowerAccessibilityService.isEnabled()
                ? "Нажмите кнопку и выберите выключение в системном меню."
                : getString(R.string.enable_accessibility));
    }
}
