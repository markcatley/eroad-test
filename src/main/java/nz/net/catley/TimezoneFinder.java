package nz.net.catley;

import com.google.maps.GeoApiContext;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;
import java.util.TimeZone;

/**
 * Created by mark on 29/05/16.
 */
public class TimezoneFinder {
    private GeoApiContext apiContext;

    TimezoneFinder( String apiKey ) {
        this.apiContext = new GeoApiContext().setApiKey(apiKey);
    }

    public TimeZone find( DateTime dateTime ) throws Exception {
        return TimeZoneApi.getTimeZone(apiContext, new LatLng(dateTime.lat, dateTime.lng)).await();
    }
}
