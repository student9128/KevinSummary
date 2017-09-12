package com.kevin.tech.kevinsummary.uitls;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * Created by <a href="http://blog.csdn.net/student9128">Kevin</a> for Project KevinSummary on 2017/9/12.
 * <h3>Description:</h3>
 * <div>
 * <div/>
 */


public class ColorUtils {

    public static int getColor(@NonNull Context context, int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static int getColorRed(int color) {
        return Color.red(color);
    }

    public static int getColorGreen(int color) {
        return Color.green(color);
    }

    public static int getColorBlue(int color) {
        return Color.blue(color);
    }


    /**
     * switch color1 to color2 with offset.
     *
     * @param color1
     * @param color2
     * @param offset
     * @return
     */
    public static int switchARGB(int color1, int color2, float offset) {
        return (int) (offset * (color2 - color1) + color1);
    }

    public static int switchColor(int red, int green, int blue) {
        return Color.argb(255, red, green, blue);
    }
}
