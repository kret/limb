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
