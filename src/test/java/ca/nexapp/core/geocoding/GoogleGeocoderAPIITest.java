package ca.nexapp.core.geocoding;

import static com.google.common.truth.Truth.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import ca.nexapp.math.units.Coordinates;

public class GoogleGeocoderAPIITest {

    private Geocoder geocoder;

    @Before
    public void setUp() {
        String apiKey = System.getenv("NEXAPP_CORE_GOOGLE_MAPS_API_KEY");
        geocoder = new GoogleGeocoderAPI(apiKey);
    }

    @Test
    public void canFindAPublicPlace() {
        String bellCenter = "1909 Avenue des Canadiens-de-Montréal, Montréal, QC H4B 5G0";

        Optional<Coordinates> location = geocoder.lookup(bellCenter);

        assertThatLocationIsNearOf(location, 45.4960667, -73.5715093);
    }

    @Test
    public void canFindAPostalCode() {
        String stGeorgesPostalCode = "G5Y";

        Optional<Coordinates> location = geocoder.lookup(stGeorgesPostalCode);

        assertThatLocationIsNearOf(location, 46.113529205322, -70.665260314941);
    }

    @Test
    public void cannotFindAnInvalidAddress() {
        String unexistingAddress = "as-qw-bc-zob-120200";

        Optional<Coordinates> location = geocoder.lookup(unexistingAddress);

        assertThat(location).isEqualTo(Optional.empty());
    }

    private void assertThatLocationIsNearOf(Optional<Coordinates> location, double expectedLatitude, double expectedLongitude) {
        assertThat(location).isNotEqualTo(Optional.empty());

        double latitude = location.get().getLatitude().toDegrees();
        double longitude = location.get().getLongitude().toDegrees();

        double threshold = 0.20d;
        assertThat(latitude).isWithin(threshold).of(expectedLatitude);
        assertThat(longitude).isWithin(threshold).of(expectedLongitude);
    }

}
