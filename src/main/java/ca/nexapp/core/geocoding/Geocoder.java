package ca.nexapp.core.geocoding;

import java.util.Optional;

import ca.nexapp.math.units.Coordinates;

public interface Geocoder {

    Optional<Coordinates> lookup(String address);
}
