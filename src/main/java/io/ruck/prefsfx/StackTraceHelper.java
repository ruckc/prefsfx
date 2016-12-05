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

/**
 *
 * @author ruckc
 */
public final class StackTraceHelper {

    public static Class<?> getCallerClass() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i = 1; i < stElements.length; i++) {
            try {
                StackTraceElement ste = stElements[i];
                String className = ste.getClassName();
                Class<?> cls = Class.forName(className);
                Package pkg = cls.getPackage();
                if (!pkg.equals(StackTraceHelper.class.getPackage())) {
                    return cls;
                }
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        return null;
    }
}
