package locationapp.Utils;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by pranav on 15/4/17.
 */
@Component
public class GeoApi {

    private static GeoApiContext context;

    @Autowired
    public GeoApi(@Value("${gmaps.secret}") String prop) {
        context = new GeoApiContext().setApiKey(prop);
    }

    public Geometry getGeoCodes(String addressName) {
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, addressName).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(results[0].geometry.location.lat);
        System.out.println(results[0].geometry.location.lng);

        return results[0].geometry;

    }

}
