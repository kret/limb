# LIMB

List Intersection Micro Benchmarker ;)

[![Travis build status](https://travis-ci.org/kret/limb.svg?branch=master)](https://travis-ci.org/kret/limb)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=pl.lanuda.kret.limb:limb)](https://sonarqube.com/dashboard/index/pl.lanuda.kret.limb:limb)
[![Code test coverage](https://sonarqube.com/api/badges/measure?metric=coverage&key=pl.lanuda.kret.limb:limb)](https://sonarqube.com/dashboard/index/pl.lanuda.kret.limb:limb)
[![Technical debt ratio](https://sonarqube.com/api/badges/measure?metric=sqale_debt_ratio&key=pl.lanuda.kret.limb:limb)](https://sonarqube.com/dashboard/index/pl.lanuda.kret.limb:limb)

## Initial questions

To compute the intersection of two lists A and B,
the elements of one list are put into a `HashSet`
while the other list is iterated over to test for
each element whether it's contained in the `HashSet`.
If one list is significantly larger than the other,
which one would you put into the `HashSet`?

## Java Application

The application should provide a user interface which
can be used to enter the following parameters:
* Size of list A
* Size of list B
* Choose which list is put into a `HashSet`, which one is iterated over
* A Run-button to start the computation
* An output fields to show the size of the result set
* A second output field to show the time it took to run the algorithm
(generating the input lists should not be counted)

If the Run-button is pressed, the two lists are populated
with random numbers. Based on the user input, one of the
lists is put into a `HashSet`, the other one is iterated
over to test for each element if it is contained
in the `HashSet`. Matching elements are put into the result set.

## Manual

### Requirements
In order to build it you need the following:
- Git,
- Internet connection (to download dependencies),
- JDK 1.8.0_40 or newer (tested on Oracle JDK),
- macOS (though it should not have any issues running on Windows or Linux),
- Gradle (automatically downloaded using Gradle Wrapper, see below in [Building and running](#building_and_running) section).

### Getting sources
Sources can be downloaded from [GitHub repository](https://github.com/kret/limb).

### <a name="building_and_running"></a> Building and running
_All below commands are to be executed in project's root directory._

_LIMB_ uses Gradle build system, and its Java plugin for compilation, assembly and tests.
It has been preconfigured with Gradle Wrapper, so that there is no need to download Gradle distribution into
your system - it will be downloaded automatically into project directory first time you use `./gradlew` script
provided in project's root directory.

#### Packaging
To build and package _web-server_ execute `./gradlew jar`. Resulting jar file can be found in `./build/libs/` directory.
This jar does not include any 3rd party dependencies.

#### Run application
Launch using this command: `./gradlew run` or by running `LimbApplication` class from IntelliJ IDEA.

After application starts, you can enter the input data in the 2 text fields with appropriate labels (_known issue_
the application hangs with edge case inputs: empty fields and sizes bigger than Java's Integer maximum value).

Press 'Swap' button to swap the values in the input fields.

Press 'Run' button to execute the algorithm. Status is going to be displayed below the buttons. UI will be disabled
until current execution ends.

### Tests
To execute unit tests execute `./gradlew test`. You can append `jacocoTestReport` task to the command
(so `./gradlew test jacocoTestReport`), to also generate JaCoCo test coverage report after the tests' run.
Reports from tests can be found in `./build/reports/tests/` directory,
while JaCoCo report in `./build/reports/jacoco/` directory.

### Javadoc
To generate project's Javadoc run `./gradlew javadoc`. Generated documentation can be found
in `./build/docs/` directory.

### SonarQube
To generate SonarQube report run `./gradlew sonarqube`. SonarQube plugin expects that
Sonar server is available at [localhost:9000](http://localhost:9000) and no credentials are required. To adjust that
to your setup please consult SonarQube and SonarQube Gradle plugin documentation.
