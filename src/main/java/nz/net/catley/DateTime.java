package nz.net.catley;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by mark on 29/05/16.
 */
public class DateTime {
    public double lat;
    public double lng;
    private static final DateTimeFormatter utcFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    private static final DateTimeFormatter zonedFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    private ZonedDateTime dateTime;
    private TimeZone tz;

    DateTime(String line) throws Exception {
        String[] data = line.split(",");
        if (data.length < 3) {
            throw new Exception("Not enough fields.");
        }

        dateTime = LocalDateTime.parse(data[0], utcFormatter).atZone(ZoneOffset.UTC);
        dateTime = dateTime.withZoneSameLocal(ZoneOffset.UTC);
        lat = Double.parseDouble(data[1]);
        lng = Double.parseDouble(data[2]);
        tz = null;
    }

    DateTime setTz(TimeZone tz) {
        this.tz = tz;
        return this;
    }

    String toCsvString() {
        return String.join(
                ",",
                dateTime.format(utcFormatter),
                Double.toString(lat),
                Double.toString(lng),
                tz.getID(),
                dateTime.withZoneSameInstant(tz.toZoneId()).format(zonedFormatter)
        );
    }
}
