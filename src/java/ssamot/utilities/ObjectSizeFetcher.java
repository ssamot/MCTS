/*
 *
 * E-EPNAC Poker A.I.
 * Spyridon Samothrakis ssamot@essex.com
 * All code (c) 2011 Spyridon Samothrakis All Rights Reserved
 *
 */

package ssamot.utilities;

import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}