/*
 * Copyright 2016 ruckc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.ruck.prefsfx;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

/**
 *
 * @author ruckc
 */
public final class PreferencePropertyFactory {

    private static final Logger LOG = Logger.getLogger(PreferencePropertyFactory.class.getName());

    private PreferencePropertyFactory() {
    }

    public static BooleanProperty newBooleanProperty(String valuename, boolean def) {
        Preferences preferences = getPreferences();

        SimpleBooleanProperty prop = new SimpleBooleanProperty();
        prop.set(preferences.getBoolean(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putBoolean(valuename, nv);
            flush(preferences);
        });
        return prop;
    }

    public static DoubleProperty newDoubleProperty(String valuename, double def) {
        Preferences preferences = getPreferences();

        SimpleDoubleProperty prop = new SimpleDoubleProperty();
        prop.set(preferences.getDouble(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putDouble(valuename, nv.doubleValue());
            flush(preferences);
        });
        return prop;
    }

    public static FloatProperty newFloatProperty(String valuename, float def) {
        Preferences preferences = getPreferences();

        SimpleFloatProperty prop = new SimpleFloatProperty();
        prop.set(preferences.getFloat(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putFloat(valuename, nv.floatValue());
            flush(preferences);
        });
        return prop;
    }

    public static IntegerProperty newIntegerProperty(String valuename, int def) {
        Preferences preferences = getPreferences();

        SimpleIntegerProperty prop = new SimpleIntegerProperty();
        prop.set(preferences.getInt(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putInt(valuename, nv.intValue());
            flush(preferences);
        });
        return prop;
    }

    public static LongProperty newLongProperty(String valuename, long def) {
        Preferences preferences = getPreferences();

        SimpleLongProperty prop = new SimpleLongProperty();
        prop.set(preferences.getLong(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putLong(valuename, nv.longValue());
            flush(preferences);
        });
        return prop;
    }

    private static Preferences getPreferences() {
        Class<?> caller = StackTraceHelper.getCallerClass();
        PreferenceContext context = PreferenceContext.getContext(caller.getName());
        return context.getPreferences();
    }

    private static void flush(Preferences p) {
        try {
            p.flush();
        } catch (BackingStoreException ex) {
            LOG.log(Level.WARNING, ex.getMessage(), ex);
        }
    }
}
