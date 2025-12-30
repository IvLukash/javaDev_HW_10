package goit.edu;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static final String UTC_ZONE = "UTC";
    public static final String UTC_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss 'UTC'";
    public static final String UTC_DATE_TIME_WITH_OFFSET_FORMAT = "yyyy-MM-dd HH:mm:ss 'UTC'X";
    public static final String UTC_WITH_OFFSET_REGEX = "UTC[ +\\-]\\d+";

    public static String getFormattedDateTime(String timezone) {
        if (timezone.equals(UTC_ZONE)) {
            return getFormattedUTC();
        }
        int offsetInHours = getTimeOffset(timezone);
        if (offsetInHours == 0) {
            return getFormattedUTC();
        }
        return ZonedDateTime.now(ZoneOffset.ofHours(offsetInHours))
                .format(DateTimeFormatter.ofPattern(UTC_DATE_TIME_WITH_OFFSET_FORMAT));
    }

    private static String getFormattedUTC() {
        return ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern(UTC_DATE_TIME_FORMAT));
    }

    public static int getTimeOffset(String timezone) {
        if (!timezone.matches(UTC_WITH_OFFSET_REGEX)) {
            throw new IllegalArgumentException("Invalid timezone");
        }
        int offsetInHours = Integer.parseInt(timezone.substring(3).trim());
        if (offsetInHours < -18 || offsetInHours > 18) {
            throw new IllegalArgumentException("Invalid timezone");
        }
        return offsetInHours;
    }
}
