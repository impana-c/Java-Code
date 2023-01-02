# FracCalc
FracCalc Project v1.2.0

## Introduction
First Semester AP Computer Science A assignment in 2020. The objective of this assignment is to create a calculator to compute arithmetic
operations between integers and/or fractions and output the result as a reduced mixed
fraction.

## Specifications
### Input
* Input will be in the form of a value, followed by an arithmetic operator, and then
another value. Values and operators will be separated by a single space. Values will
contain no spaces.

* Input values may be in the form of mixed fractions, proper fractions, improper
fractions or integers. The integer and fraction parts of a mixed fraction will be
separated by an underscore (_) (e.g., `"1_3/4"` is one and three fourths to
distinguish it from `"13/4"` which is thirteen fourths).

* The calculator will support the 4 basic arithmetic operations: add (+), subtract
(-), multiply (*), and divide (/).

* The program should accept an equation, calculate the result in the proper form,
print it to screen, and then be ready to accept another equation. The user should be
able to exit the program by entering the command `"quit"` instead of an equation.

### Output
* The output value must always be reduced and never improper (it may be an integer,
fraction, or mixed fraction, as appropriate). Example: a result of `"10/4"` should be
printed to the screen as `"2_1/2"`).

### Examples
| Input             | Output    | Notes                                         |
|-------------------|-----------|-----------------------------------------------|
| `1/4 + 1_1/2`     | `1_3/4`   |                                               |
| `8/4 + 2`         | `4`       | Input may be an improper fraction.            |
| `-1 * -1/2`       | `1/2`     |                                               |
| `-11/17 + -1/17`  | `-12/17`  |                                               |
| `0 * 25_462/543`  | `0`       | Remember to check for border/special cases.   |
