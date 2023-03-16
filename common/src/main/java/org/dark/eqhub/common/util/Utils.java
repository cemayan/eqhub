package org.dark.eqhub.common.util;

import java.time.ZonedDateTime;
import java.util.UUID;

public class Utils {

    public static String GetUUID() {
        return UUID.randomUUID().toString();
    }

    public static Long GetDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        return zonedDateTime.toEpochSecond();
    }

}
