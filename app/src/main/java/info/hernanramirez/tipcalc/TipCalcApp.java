package info.hernanramirez.tipcalc;

import android.app.Application;

/**
 * Created by hernanr on 6/3/16.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL = "https://about.me/hernanramirez";

    public String getAboutUrl(){
        return ABOUT_URL;
    }

}
