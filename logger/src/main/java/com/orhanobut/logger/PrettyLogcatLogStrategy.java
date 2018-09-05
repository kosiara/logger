package com.orhanobut.logger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Prints log to logcat
 *
 * HINT: This class is redundant and can be removed as soon as issue
 * https://github.com/orhanobut/logger/issues/191
 *
 * is resolved
 *
 * @version 9/4/18
 * @author Bartosz Kosarzycki
 */
public class PrettyLogcatLogStrategy extends LogcatLogStrategy {

    public String DEFAULT_TAG = "NO_TAG";
    public String START_PREFIX = PrettyFormatStrategy.TOP_BORDER;
    public String END_PREFIX = PrettyFormatStrategy.BOTTOM_BORDER;

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        logInternal(priority, tag == null ? DEFAULT_TAG : tag, message);
    }

    String logContent = "";

    public void logInternal(Integer priority, String tag, String message) {
        if (message.startsWith(START_PREFIX)) {
            logContent = "";
            logContent += ": \n" + message;
        } else if (message.startsWith(END_PREFIX)) {
            logContent += "\n" + message;
            writeToLogcatInternal(priority, tag, logContent);
        } else {
            logContent += "\n" + message;
        }
    }

    public void writeToLogcatInternal(Integer priority, String tag, String message) {
        Log.println(priority, tag, message);
    }
}
