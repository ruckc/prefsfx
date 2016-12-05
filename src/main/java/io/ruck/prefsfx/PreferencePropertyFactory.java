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

    /**
     * Unused "locked" constructor.
     */
    private PreferencePropertyFactory() {
    }

    /**
     *
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     * @return a new SimpleBooleanProperty
     */
    public static BooleanProperty newBooleanProperty(final String valuename,
            final boolean def) {
        SimpleBooleanProperty prop = new SimpleBooleanProperty();
        PreferencePropertyBinder.bind(prop, valuename, def);
        return prop;
    }

    /**
     *
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     * @return a new SimpleBooleanProperty
     */
    public static DoubleProperty newDoubleProperty(final String valuename,
            final double def) {
        SimpleDoubleProperty prop = new SimpleDoubleProperty();
        PreferencePropertyBinder.bind(prop, valuename, def);
        return prop;
    }

    /**
     *
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     * @return a new SimpleBooleanProperty
     */
    public static FloatProperty newFloatProperty(final String valuename,
            final float def) {
        SimpleFloatProperty prop = new SimpleFloatProperty();
        PreferencePropertyBinder.bind(prop, valuename, def);
        return prop;
    }

    /**
     *
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     * @return a new SimpleBooleanProperty
     */
    public static IntegerProperty newIntegerProperty(final String valuename,
            final int def) {
        SimpleIntegerProperty prop = new SimpleIntegerProperty();
        PreferencePropertyBinder.bind(prop, valuename, def);
        return prop;
    }

    /**
     *
     * @param valuename to bind property value to
     * @param def default value if not in Preferences
     * @return a new SimpleBooleanProperty
     */
    public static LongProperty newLongProperty(final String valuename,
            final long def) {
        SimpleLongProperty prop = new SimpleLongProperty();
        PreferencePropertyBinder.bind(prop, valuename, def);
        return prop;
    }
}
