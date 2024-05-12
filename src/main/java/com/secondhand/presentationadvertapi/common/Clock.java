package com.secondhand.presentationadvertapi.common;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public final class Clock {
    private static boolean isFrozen;
    private static Instant timeSet;

    private Clock() {
    }

    public static synchronized void freeze() {
        isFrozen = true;
    }

    public static synchronized void freeze(Instant instant) {
        freeze();
        timeSet = instant;
    }

    public static synchronized void unfreeze() {
        isFrozen = false;
        timeSet = null;
    }

    public static Instant getInstant() {
        if (isFrozen) {
            if (timeSet == null) {
                timeSet = Instant.now();
            }

            return timeSet;
        } else {
            return Instant.now();
        }
    }

    public static ZonedDateTime nowUTC() {
        return getInstant().atZone(ZoneOffset.UTC);
    }

    public static ZonedDateTime nowTR() {
        return getInstant().atZone(DateUtils.TURKEY_ZONE_OFFSET);
    }
}
