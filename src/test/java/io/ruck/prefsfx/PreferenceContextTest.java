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

import java.util.prefs.Preferences;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author ruckc
 */
public class PreferenceContextTest {

    @Test
    public void testGetPreferences() {
        Preferences a = PreferenceContext.getContext("prefsfx.test").getPreferences();
        Preferences b = PreferenceContext.getContext("prefsfx.test").getPreferences();
        assertEquals("failed to get identifical Preferences objects", a, b);
    }

    @Test
    public void testGetContext() {
        PreferenceContext a = PreferenceContext.getContext("prefsfx.test");
        PreferenceContext b = PreferenceContext.getContext("prefsfx.test");
        assertEquals("failed to get identifical PreferenceContext objects", a, b);
    }

}
