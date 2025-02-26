/*Write a java program that uses simple technique to search a key,to sort n elements,
 run the program for varied values of n>5000 and record the time taken to search /sort 
 plot a graph
of the time taken v/s n the elements can be read from a file or can be generated using the random number generator
Method: brute force-non recursive algo implementation, sequential search and selection sort */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class lab1 {
    // Generate an array where no two elements are the same
    public static int[] generateSequentialArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i; // Ensuring index and element are the same
        }
        return arr;
    }

    // Generate an array with random numbers
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // Random numbers between 0 and 9999
        }
        return arr;
    }

    // Sequential Search
    public static int sequentialSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // Run performance tests for linear search
    public static void runLinearSearchTest(int[] sizes, FileWriter writer) throws IOException {
        for (int size : sizes) {
            int[] arr = generateSequentialArray(size);
            int key = -1; // Key is always negative, ensuring it's never found

            // Measure search time
            long startSearch = System.nanoTime();
            sequentialSearch(arr, key);
            long searchTimeMs = (System.nanoTime() - startSearch) / 1_000_000; // Convert to ms

            System.out.printf("Search - Size: %d, Time: %d ms\n", size, searchTimeMs);
            writer.write(size + "," + key + "," + searchTimeMs + ",\n");
        }
    }

    // Run performance tests for sorting
    public static void runSortTest(int[] sizes, FileWriter writer) throws IOException {
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);

            // Measure sort time
            long startSort = System.nanoTime();
            selectionSort(arr);
            long sortTimeMs = (System.nanoTime() - startSort) / 1_000_000; // Convert to ms

            System.out.printf("Sort - Size: %d, Time: %d ms\n", size, sortTimeMs);
            writer.write(size + ",," + sortTimeMs + "\n"); // Only sort time, no key
        }
    }

    public static void main(String[] args) {
        int[] searchSizes = new int[50];
        int[] sortSizes = new int[75];

        for (int i = 0; i < 50; i++) {
            searchSizes[i] = 100000000 + (i * 10000000); // Array sizes for searching
        }
        for (int i = 0; i < 75; i++) {
            sortSizes[i] = 5000 + (i * 2000); // Array sizes for sorting
        }

        // CSV File Handling
        try (FileWriter writer = new FileWriter("C:\\Users\\user pc\\Documents\\timing_data.csv")) {
            writer.write("Size,Key,Search Time (ms),Sort Time (ms)\n"); // CSV Header
            runLinearSearchTest(searchSizes, writer);
            runSortTest(sortSizes, writer);
            System.out.println("Data exported to timing_data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
