# SortingBenchmark

This Java program benchmarks the performance of various sorting algorithms on randomly generated arrays of different sizes. It provides a clear comparison of execution times, helping to understand the efficiency of each sorting algorithm under different conditions.

---

## Features

- Implements five sorting algorithms:
  - Bubble Sort
  - Selection Sort
  - Insertion Sort
  - Counting Sort
  - Merge Sort
- Compares performance across a range of input sizes.
- Measures average execution time (in milliseconds) over multiple runs to account for variability.
- Uses a formatted table to display results for easy comparison.

---

## Table of Contents

- [Technologies Used](#technologies-used)
- [How It Works](#how-it-works)
- [How to Run](#how-to-run)
- [Customization](#customization)

---

## Technologies Used

- **Java**: Language for implementing sorting algorithms and benchmarking.
- **Random**: Generates random data for benchmarking.
- **System.nanoTime()**: Measures execution time.

---

## How It Works

1. **Algorithms Implemented**:
   - Each algorithm sorts an integer array using its specific methodology.

2. **Input Sizes**:
   - Benchmarked across various input sizes, from 100 to 10,000 elements.

3. **Benchmarking**:
   - Each algorithm runs 10 times per input size, and the average execution time is computed.

4. **Results Display**:
   - Results are printed in a formatted table, with each row representing an algorithm and columns showing average execution time for each input size.

---

## How to Run

1. **Prerequisites**:
   - Java Development Kit (JDK) installed.
   - A Java-compatible IDE or terminal.

2. **Clone the Repository**:
   ```bash
   git clone https://github.com/Dylanjb96/Computional-Thinking-with-Algorithms.git

3. **Commplie and Run**:
    ```bash
    javac SortingBenchmark.java
    java SortingBenchmark

4. **View Result**:
The program outputs the performance comparison table directly to the console.

---

## Customization

1. **Modifying Input Sizes**:
Edit the inputSizes array in the main method:
```java
int[] inputSizes = {100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000};
```

2. **Changing Number of Runs**:
Modify the numRuns variable in the main method
```java
int numRuns = 10;
```
3. **Adding More Algorithms**:
- Implement your custom sorting algorithm as a static method.
- Add it to the switch-case in the main method.

---