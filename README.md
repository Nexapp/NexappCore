[![Build Status](https://travis-ci.org/Nexapp/NexappCore.svg?branch=master)](https://travis-ci.org/Nexapp/NexappCore)

# Nexapp Core
Several Java mini-modules for REST APIs.

## Provided by the library
**DateProvider**
```java
// Useful for mocking purposes
DateProvider dateProvider = new JavaDateProvider();
ZoneId zone = ZoneId.of("America/Montreal");

Instant now = dateProvider.currentTimestamp();
ZonedDateTime now = dateProvider.currentDateTime(zone);
LocalDate today = dateProvider.currentDate(zone);
DayOfWeek day = dateProvider.currentDayOfWeek(zone);
``` 

**EmailValidator**
```java
EmailValidator validator = new WithRegexEmailValidator();
boolean valid = validator.isValid("john.doe@email.com");

validator.validate("john.doe@email.com"); // no exception
validator.validate("john..doe..@..email.com"); // throws an exception
```

**Range**
```java
Range<Integer> kids = Range.of(6, 12);
Range<Integer> adults = Range.of(18, 65);

boolean overlapping = kids.isOverlapping(adults); // false
boolean overlapping = kids.isOverlapping(Range.of(0, 12)); // true
```

**Pagination** <br />
Useful structure to represent pagination concept.
```java
Pagination page = Pagination.paged(1, 10); // First ten items
System.out.println(page.page()); // 1
System.out.println(page.itemPerPage()); // 10
System.out.println(page.startingIndex()); // 0

Pagination offset = Pagination.offsetted(50, 10); // Skip the 50 first items, then take 10 items
```

**Picker** <br />
A picker that can pick items randomly within a `Collection`.
```java
List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> picked = Picker.pickOne(items);
List<Integer> threePicks = Picker.pick(items, 3);
List<Integer> allPicked = Picker.pick(items, 1000); // Returns (1, 2, 3, 4, 5);  Cannot be picked more than once.

List<Integer> ignored = Arrays.asList(4, 5);
List<Integer> thousandPicks = Picker.pick(items, 1000, ignored); // Will not contains any 4 or 5
```

**WeightedPicker** <br />
A picker that applies a weight to each of its items. It is basically a lottery system where each item has X chance(s) of being picked.
```java
List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
Function<Integer, Number> weight = (item) -> {
  return item % 2 == 0 ? 10 : 1;
};

Optional<Integer> picked = WeightedPicker.pickOne(items, weight);
```

**Geocoder** <br />
```java
String googleMapsApiKey = "xxxx.xxxx.xxxx.xxxx";
Geocoder geocoder = new GoogleGeocoderAPI(googleMapsApiKey);

String address = "555 55th street, New York, NY";
Optional<Coordinates> location = geocoder.lookup(address);
```

**NumberPresenter**
```java
String display = new NumberPresenter(5.39332).numberOfDecimals(1).round().present();
System.out.println(display); // 5.4

String display = new NumberPresenter(6.9876).numberOfDecimals(1).present();
System.out.println(display); // 6.9
```

**ServiceLocator** <br />
Super lightweight anti-pattern to inject dependencies easily. Useful for light testing in shared contexts.
```java
ServiceLocator.locate().register(MyClass.class, new MyClass());

MyClass instance = ServiceLocator.locate().resolve(MyClass.class);
// do something with instance

// ...
ServiceLocator.reset(); // Clear all registrations
```

## How to use it
At the moment, this project is hosted on GitHub and not on Maven Central.

In your `pom.xml`, you must add the GitHub repository as follows:
```
<repositories>
	<repository>
		<id>nexapp-core-mvn-repo</id>
		<url>https://raw.github.com/nexapp/nexappcore/mvn-repo/</url>
	</repository>
</repositories>
```

Then, you simply add the dependency as follows:
```
<dependency>
	<groupId>ca.nexapp</groupId>
	<artifactId>core</artifactId>
	<version>0.0.8</version>
</dependency>
```

## How to contribute
In order to run integration tests, you need to request an API key from _Google Maps_, and then set `NEXAPP_CORE_GOOGLE_MAPS_API_KEY` as an environment variable.
