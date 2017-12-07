package ca.nexapp.core.geocoding;

import java.util.Optional;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import ca.nexapp.math.units.Coordinates;

public class GoogleGeocoderAPI implements Geocoder {

    private final String apiKey;

    public GoogleGeocoderAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Optional<Coordinates> lookup(String address) {
        Optional<LatLng> location = fetchGoogleAddress(address);
        return location.map(l -> Coordinates.locatedAt(l.lat, l.lng));
    }

    private Optional<LatLng> fetchGoogleAddress(String address) {
        // https://github.com/googlemaps/google-maps-services-java
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
        GeocodingResult[] results = GeocodingApi.geocode(context, address).awaitIgnoreError(); // synchronous
        if (results.length == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(results[0].geometry.location);
    }

}
