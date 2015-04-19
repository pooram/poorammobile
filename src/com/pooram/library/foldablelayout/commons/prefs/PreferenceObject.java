package com.pooram.library.foldablelayout.commons.prefs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks objects that can be stored in the {@link android.content.SharedPreferences} using {@link PreferencesHelper#save(Object)} method.<br/>
 * See also {@link com.pooram.library.foldablelayout.commons.prefs.Preferences#prefs()}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PreferenceObject {

    /**
     * Preference key
     */
    public String value();

}