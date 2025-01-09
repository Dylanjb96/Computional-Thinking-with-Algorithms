import java.util.Arrays;
import java.util.Random;

public class SortingBenchmark {

    // Bubble Sort algorithm
    public static void bubbleSort(int[] arr) {
        int n = arr.length; // Store the length of the array in variable n
        for (int i = 0; i < n - 1; i++) { // Outer loop iterating over each element of the array
            for (int j = 0; j < n - i - 1; j++) { // Inner loop to compare adjacent elements and swap if necessary
                if (arr[j] > arr[j + 1]) { // Check if current element is greater than the next element
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j]; // Store current element in temporary variable
                    arr[j] = arr[j + 1]; // Assign next element to current position
                    arr[j + 1] = temp; // Assign temporary variable to next position
                }
            }
        }
    }

    // Selection Sort algorithm
    public static void selectionSort(int[] arr) {
        int n = arr.length; // Store the length of the array in variable n
        for (int i = 0; i < n - 1; i++) { // Outer loop iterating over each element of the array
            int minIndex = i; // Assume the current index is the minimum index
            for (int j = i + 1; j < n; j++) { // Inner loop to find the index of the minimum element
                if (arr[j] < arr[minIndex]) { // Check if current element is smaller than the current minimum
                    minIndex = j; // Update the minimum index if a smaller element is found
                }
            }
            // swap arr[i] and arr[minIndex]
            int temp = arr[minIndex]; // Store the value of the minimum element
            arr[minIndex] = arr[i]; // Assign the value of the current element to the minimum index
            arr[i] = temp; // Assign the stored minimum value to the current index
        }
    }

    // Insertion Sort algorithm
    public static void insertionSort(int[] arr) {
        int n = arr.length; // Store the length of the array in variable n
        for (int i = 1; i < n; i++) { // Start iterating from the second element
            int key = arr[i]; // Store the current element as key
            int j = i - 1; // Initialize j to the index before i
            while (j >= 0 && arr[j] > key) { // Move elements of arr[0..i-1], that are greater than key, to one position
                                             // ahead of their current position
                arr[j + 1] = arr[j]; // Shift elements to the right
                j = j - 1; // Move to the previous element
            }
            arr[j + 1] = key; // Place the key at its correct position in the sorted array
        }
    }

    // Counting Sort algorithm
    public static void countingSort(int[] arr) {
        int n = arr.length; // Store the length of the array in variable n
        int[] output = new int[n]; // Create an output array to store sorted elements
        int[] count = new int[256]; // Create a count array to store frequency of each element

        for (int i = 0; i < 256; ++i) { // Initialize count array with all zeros
            count[i] = 0;
        }

        for (int i = 0; i < n; ++i) { // Store the count of each element in count array
            ++count[arr[i]];
        }

        for (int i = 1; i <= 255; ++i) { // Modify count array to store the cumulative count of each element
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) { // Build the output array
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]]; // Decrease count of element to handle duplicates
        }

        for (int i = 0; i < n; ++i) { // Copy the output array to original array
            arr[i] = output[i];
        }
    }

    // Merge Sort algorithm
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) { // If the left index is less than the right index
            int m = (l + r) / 2; // Calculate the middle index
            mergeSort(arr, l, m); // Sort the left half of the array
            mergeSort(arr, m + 1, r); // Sort the right half of the array
            merge(arr, l, m, r); // Merge the sorted halves
        }
    }

    // Helper function to merge two subarrays
    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1; // Length of the left subarray
        int n2 = r - m; // Length of the right subarray

        // Create temporary arrays to store the left and right subarrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temporary arrays back into the original array
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main method to test sorting algorithms
    public static void main(String[] args) {
        // Input sizes to test
        int[] inputSizes = { 100, 250, 500, 750, 1000, 1250, 2500, 3750, 5000, 6250, 7500, 8750, 10000 };

        // Number of runs to average
        int numRuns = 10;

        // Find the maximum length of sorting algorithm name
        int maxNameLength = Arrays
                .stream(new String[] { "Bubble Sort", "Selection Sort", "Insertion Sort", "Counting Sort",
                        "Merge Sort" })
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Find the maximum length of the size values
        int maxSizeLength = Arrays.stream(inputSizes)
                .mapToObj(String::valueOf)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Print header
        System.out.printf("%-" + maxNameLength + "s\t", "Size");
        for (int n : inputSizes) {
            System.out.printf("%-" + maxSizeLength + "d\t", n);
        }
        System.out.println();

        // Test each algorithm for each input size
        for (String sortName : new String[] { "Bubble Sort", "Selection Sort", "Insertion Sort", "Counting Sort",
                "Merge Sort" }) {
            System.out.printf("%-" + maxNameLength + "s\t", sortName);
            for (int n : inputSizes) {
                long totalTime = 0;

                for (int i = 0; i < numRuns; i++) {
                    int[] arr = generateRandomArray(n);
                    long startTime = System.nanoTime();

                    // Apply sorting algorithm based on the name
                    switch (sortName) {
                        case "Bubble Sort":
                            bubbleSort(arr);
                            break;
                        case "Selection Sort":
                            selectionSort(arr);
                            break;
                        case "Insertion Sort":
                            insertionSort(arr);
                            break;
                        case "Counting Sort":
                            countingSort(arr);
                            break;
                        case "Merge Sort":
                            mergeSort(arr, 0, arr.length - 1);
                            break;
                    }

                    long endTime = System.nanoTime();
                    totalTime += (endTime - startTime);
                }

                // Calculate average running time
                double avgTime = totalTime / (double) numRuns / 1e6; // milliseconds

                // Print the average running time
                System.out.printf("%.3f\t", avgTime);
            }
            System.out.println();
        }
    }

    // Helper method to generate a random array of given size
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(256); // Generate random integers between 0 and 255 for Counting Sort
        }
        return arr;
    }
}
