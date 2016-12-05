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

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ruckc
 */
public class PreferencePropertyFactoryTest {

    private final PreferenceContext context = PreferenceContext.getContext("sun.reflect.nativemethodaccessorimpl");
    private final Preferences prefs = context.getPreferences();
    private final Random random = new Random();

    private void flush() {
        try {
            prefs.flush();
        } catch (BackingStoreException ex) {
            Logger.getLogger(PreferencePropertyFactoryTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testNewBooleanProperty() throws BackingStoreException {
        String name = "boolean";
        prefs.remove(name);
        flush();
        BooleanProperty prop = PreferencePropertyFactory.newBooleanProperty(name, false);
        prop.set(true);
        assertEquals(true, prefs.getBoolean(name, false));
        prop.set(false);
        assertEquals(false, prefs.getBoolean(name, true));
    }

    @Test
    public void testNewDoubleProperty() {
        String name = "double";
        prefs.remove(name);
        flush();
        DoubleProperty prop = PreferencePropertyFactory.newDoubleProperty(name, 0.0);
        double r = random.nextDouble();
        prop.set(r);
        assertEquals(r, prefs.getDouble(name, 0.0), 0);
        r = random.nextDouble();
        prop.set(r);
        assertEquals(r, prefs.getDouble(name, 0.0), 0);
    }

    @Test
    public void testNewFloatProperty() {
        String name = "float";
        prefs.remove(name);
        flush();
        FloatProperty prop = PreferencePropertyFactory.newFloatProperty(name, 0.0f);
        float f = random.nextFloat();
        prop.set(f);
        assertEquals(f, prefs.getFloat(name, 0.0f), 0);
        f = random.nextFloat();
        prop.set(f);
        assertEquals(f, prefs.getFloat(name, 0.0f), 0);
    }

    @Test
    public void testNewIntegerProperty() {
        String name = "boolean";
        prefs.remove(name);
        flush();
        IntegerProperty prop = PreferencePropertyFactory.newIntegerProperty(name, 0);
        int i = random.nextInt();
        prop.set(i);
        assertEquals(i, prefs.getInt(name, 0));
        i = random.nextInt();
        prop.set(i);
        assertEquals(i, prefs.getInt(name, 0));
    }

    @Test
    public void testNewLongProperty() {
        String name = "boolean";
        prefs.remove(name);
        flush();
        LongProperty prop = PreferencePropertyFactory.newLongProperty(name, 0l);
        long l = random.nextLong();
        prop.set(l);
        assertEquals(l, prefs.getLong(name, 0l));
        l = random.nextLong();
        prop.set(l);
        assertEquals(l, prefs.getLong(name, 0l));
    }

}
