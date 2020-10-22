package com.cz.android.media.ffmpeg.video.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author Created by cz
 * @date 2020/10/22 3:07 PM
 * @email bingo110@126.com
 */
public class Util {
    private static final StringBuilder formatBuilder = new StringBuilder();
    private static Formatter formatter = new Formatter(formatBuilder, Locale.getDefault());
    /**
     * Returns the specified millisecond time formatted as a string.
     *
     * @param timeMs The time to format as a string, in milliseconds.
     * @return The time formatted as a string.
     */
    public static String getStringForTime(long timeMs) {
        long totalSeconds = (timeMs + 500) / 1000;
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        formatBuilder.setLength(0);
        return hours > 0 ? formatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
                : formatter.format("%02d:%02d", minutes, seconds).toString();
    }

    public static File copyTempFileAsset(Context context, String assetFileName) {
        File file = null;
        AssetManager assets = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assets.open(assetFileName);
            byte[] bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            file = File.createTempFile("tmp", ".mp4");
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(bytes);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

}
