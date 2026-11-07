package com.example.miboxpower;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class PowerAccessibilityService extends AccessibilityService {
    private static PowerAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) { }

    @Override
    public void onInterrupt() { }

    @Override
    public void onDestroy() {
        if (instance == this) instance = null;
        super.onDestroy();
    }

    public static boolean isEnabled() {
        return instance != null;
    }

    public static boolean showPowerDialog() {
        return instance != null && instance.performGlobalAction(GLOBAL_ACTION_POWER_DIALOG);
    }
}
