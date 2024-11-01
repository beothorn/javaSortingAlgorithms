package com.github.beothorn;

import com.github.beothorn.sorts.algorithms.BubbleSort;
import com.github.beothorn.sorts.algorithms.InplaceQuickSort;
import com.github.beothorn.sorts.algorithms.InsertionSort;
import com.github.beothorn.sorts.algorithms.MergeSort;
import com.github.beothorn.tests.TestFiltersA;
import com.github.beothorn.tests.TestFiltersB;

import java.util.Arrays;
import java.util.function.Function;

public class App {

    public static void main(String[] args) throws InterruptedException {
        int[] randomUpTo20 = {10,14,7,11,8,5,15,12,1,9,
                     3,4,2,13,6}; // chosen by fair dice roll.
                                   // guaranteed to be random.

        Thread bubbleSort = runSortInThread(
            randomUpTo20,
            BubbleSort::sort,
            "BubbleSort"
        );

        Thread inPlaceQuickSort = runSortInThread(
            randomUpTo20,
            InplaceQuickSort::sort,
            "InPlaceQuickSort"
        );

        Thread mergeSort = runSortInThread(
            randomUpTo20,
            MergeSort::sort,
            "MergeSort"
        );

        Thread insertionSort = runSortInThread(
                randomUpTo20,
                InsertionSort::sort,
                "InsertionSort"
        );

        bubbleSort.join();
        inPlaceQuickSort.join();
        mergeSort.join();
        insertionSort.join();
        //--------------
        // Do some test some calls
        startRecording();
        TestFiltersA testFiltersA = new TestFiltersA();
        testFiltersA.functionAAA();
        testFiltersA.functionNonStatic("ewfh");
        stopRecording();
        testFiltersA.functionBBB();
        testFiltersA.functionCCC();
        TestFiltersB testFiltersB = new TestFiltersB();
        testFiltersB.functionXXX();
        testFiltersB.functionYYY();
        testFiltersB.functionZZZ();

        // Run indefinitely until interrupted

        if(args.length > 0 && Arrays.stream(args).anyMatch(s -> s.equals("nostop"))) {
            try {
                while (true) {
                    Thread.sleep(1000);
                    testFiltersA.threadLoop();
                }
            } catch (InterruptedException e) {
                // Handle the interruption if necessary
                System.out.println("Application interrupted.");
            }
        }
    }

    private static void stopRecording() {
        // does nothing, arguments on agent will use this method to start
    }

    private static void startRecording() {
        // does nothing, arguments on agent will use this method to start
    }

    private static Thread runSortInThread(int[] arrayToSort, Function<int[], int[]> sort, String sortName) {
        Thread sortThread = new Thread(() -> {
            try {
                int[] result = sort.apply(arrayToSort);
                System.out.println(sortName + ": " + Arrays.toString(result));
            } catch (Exception e) {
                System.err.println(e);
            }
        });
        sortThread.setName(sortName);
        sortThread.start();
        return sortThread;
    }
}
