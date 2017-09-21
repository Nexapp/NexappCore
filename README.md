[![Build Status](https://travis-ci.org/Nexapp/NexappCore.svg?branch=master)](https://travis-ci.org/Nexapp/NexappCore)

# Nexapp Core
Several Java mini-modules for REST APIs.

## Provided by the library
**DateProvider**
```java
// Useful for mocking purposes
DateProvider dateProvider = new JavaDateProvider();

// Without timezone
Instant now = dateProvider.currentTimestamp();
LocalDateTime now = dateProvider.currentDateTime();
LocalDate now = dateProvider.currentDate();

// With timezone
ZoneId zone = ZoneId.of("America/Montreal");
ZonedDateTime now = dateProvider.currentDateTime(zone);
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

**Pagination**
```java
// Useful structure to represent pagination concept
Pagination page = Pagination.paged(1, 10); // First ten items
System.out.println(page.page()); // 1
System.out.println(page.itemPerPage()); // 10
System.out.println(page.startingIndex()); // 0

Pagination offset = Pagination.offsetted(50, 10); // Skip the 50 first items, then take 10 items
```

**Picker**
```java
List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> picked = new Picker<>(items).pickOne();
List<Integer> threePicks = new Picker<>(items).pick(3);

List<Integer> ignored = Arrays.asList(4, 5);
List<Integer> thousandPickes = new Picker<>(items).pick(1000, ignored); // Will not contains any 4 or 5
```

**WeightedSet** <br />
A set that apply a weight to each of its items. It is basically a lottery system. Each item has X chance(s) of being picked.

```java
List<Integer> items = Arrays.asList(1, 2, 3, 4, 5);
Function<Integer, Number> weight = (item) -> {
  return item % 2 == 0 ? 10 : 1;
};

Optional<Integer> picked = new WeightedSet<>(items, weight).pickOne();
```

**NumberPresenter**
```java
String display = new NumberPresenter(5.39332).numberOfDecimals(1).round().present();
System.out.println(display); // 5.4
```

**ServiceLocator** <br />
Super lightweight anti-pattern to inject dependencies easily. Useful for light testing.
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
	<version>0.0.7</version>
</dependency>
```
