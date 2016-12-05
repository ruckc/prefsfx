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

/**
 *
 * @author ruckc
 */
public final class PreferencePropertyBinder {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(
            PreferencePropertyFactory.class.getName());

    /**
     * Unused "locked" constructor.
     */
    private PreferencePropertyBinder() {
    }

    /**
     *
     * @param prop property to be bound
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     */
    public static void bind(final BooleanProperty prop,
            final String valuename, final boolean def) {
        Preferences preferences = getPreferences();
        prop.set(preferences.getBoolean(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putBoolean(valuename, nv);
            flush(preferences);
        });
    }

    /**
     *
     * @param prop property to be bound
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     */
    public static void bind(final DoubleProperty prop,
            final String valuename, final double def) {
        Preferences preferences = getPreferences();
        prop.set(preferences.getDouble(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putDouble(valuename, nv.doubleValue());
            flush(preferences);
        });
    }

    /**
     *
     * @param prop property to be bound
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     */
    public static void bind(final FloatProperty prop,
            final String valuename, final float def) {
        Preferences preferences = getPreferences();
        prop.set(preferences.getFloat(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putFloat(valuename, nv.floatValue());
            flush(preferences);
        });
    }

    /**
     *
     * @param prop property to be bound
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     */
    public static void bind(final IntegerProperty prop,
            final String valuename, final int def) {
        Preferences preferences = getPreferences();
        prop.set(preferences.getInt(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putInt(valuename, nv.intValue());
            flush(preferences);
        });
    }

    /**
     *
     * @param prop property to be bound
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     */
    public static void bind(final LongProperty prop,
            final String valuename, final long def) {
        Preferences preferences = getPreferences();
        prop.set(preferences.getLong(valuename, def));
        prop.addListener((o, ov, nv) -> {
            preferences.putLong(valuename, nv.longValue());
            flush(preferences);
        });
    }

    /**
     * @return Preferences based on the current Thread's stacktrace,
     */
    private static Preferences getPreferences() {
        Class<?> caller = StackTraceHelper.getCallerClass();
        PreferenceContext context =
                PreferenceContext.getContext(caller.getName());
        return context.getPreferences();
    }

    /**
     * Persist's preferences change while logging (at WARN) a
     * BackingStoreException.  I believe it would be better to log the failure
     * than cause a user's application to potentially fail on an unhandled
     * exception.
     *
     * @param p is the preferences to flush.
     */
    private static void flush(final Preferences p) {
        try {
            p.flush();
        } catch (BackingStoreException ex) {
            LOG.log(Level.WARNING, ex.getMessage().replaceAll("\n", "\\n"), ex);
        }
    }
}
