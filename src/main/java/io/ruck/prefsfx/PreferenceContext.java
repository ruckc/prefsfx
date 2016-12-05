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

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *
 * @author ruckc
 */
public final class PreferenceContext {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(
            PreferenceContext.class.getName());

    /**
     * Static cache of PreferenceContext.
     */
    private static final Map<String, PreferenceContext> CONTEXTS
            = new ConcurrentHashMap<>();

    /**
     * Preferences instance.
     */
    private final Preferences preferences;

    /**
     * Constructor.
     *
     * @param name of the Preferences.
     */
    private PreferenceContext(final String name) {
        LOG.log(Level.FINE, "Creating {0} for {1}",
                new Object[]{getClass().getName().replaceAll("\n", "\\n"),
                    name.replaceAll("\n", "\\n")});
        preferences = Preferences.userRoot().node(name);
    }

    /**
     * @return the Preferences object in this Context
     */
    public Preferences getPreferences() {
        return preferences;
    }

    /**
     *
     * @param name the Preferences name
     * @return the new or existing PreferenceContext associated with the @param
     * name
     */
    public static PreferenceContext getContext(final String name) {
        return CONTEXTS.computeIfAbsent(name.toLowerCase(Locale.getDefault()),
                PreferenceContext::new);
    }
}
