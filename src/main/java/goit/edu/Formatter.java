package goit.edu;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static final String UTC = "UTC";

    public static String getFormattedDateTime(String timezone) {
        if (timezone.equals(UTC)) {
            return utcNow();
        }
        int offsetInHours = getOffset(timezone);
        if (offsetInHours == 0) {
            return utcNow();
        }
        return ZonedDateTime.now(ZoneOffset.ofHours(offsetInHours))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'X"));
    }

    private static String utcNow() {
        return ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'UTC'"));
    }

    public static int getOffset(String timezone) {
        if (!timezone.matches("UTC[ +\\-]\\d+")) {
            throw new IllegalArgumentException("Invalid timezone");
        }
        int offsetInHours = Integer.parseInt(timezone.substring(3).trim());
        if (offsetInHours < -18 || offsetInHours > 18) {
            throw new IllegalArgumentException("Invalid timezone");
        }
        return offsetInHours;
    }
}
