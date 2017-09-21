[![Build Status](https://travis-ci.org/Nexapp/NexappCore.svg?branch=master)](https://travis-ci.org/Nexapp/NexappCore)

# Nexapp Core
Several classes that we use everyday.

## Provided by the library
**ServiceLocator**: The super lightweight anti-pattern to inject dependencies easily. Generally combined with the BaseContext class. <br />
**EmailValidator**: Perform email validations. <br />
**NumberPresenter**: A helper to present numbers in various forms (rounded or not, with n decimals, etc.) <br />
**DateProvider**: An interface to provide date and timestamp. Mostly use for mocking. There is a Java implementation as well. <br />
**Range**: Can handle two comparables (overlapping for example). <br />
**Pagination**: Structure that represents the pagination concept (page, itemPerPage, startingIndex). <br />
**Picker**: Pick x item(s) randomly from a Collection. <br />
**WeightedSet**: Pick a random item from a Collection. Each item appear x times according to their weight in the Set. <br />

## How to use it
At the moment, this project is hosted on GitHub and not on Maven Central.

In your `pom.xml`, you must add the GitHub repository as follows:
```
<repositories>
	<repository>
		<id>nexapp-core-mvn-repo</id>
		<url>https://raw.github.com/nexapp/nexappcore/mvn-repo/</url>
		<snapshots>
			<enabled>true</enabled>
			<updatePolicy>always</updatePolicy>
		</snapshots>
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
