package Algorithms;

import java.util.Arrays;

public class MergeSortAlgorithms {
    
    /**
     *  Using recursive calls and NO INSERTION-SORT() as a sub-procedure
     */
    public double[] mergeSortA(double[] inputArray){
        if(inputArray.length == 1){
            return inputArray;
        } else {
            int middle= ((inputArray.length)/2);
            double[] leftHalfArray = mergeSortA(Arrays.copyOfRange(inputArray, 0, middle));
            double[] rightHalfArray = mergeSortA(Arrays.copyOfRange(inputArray, middle, inputArray.length));
            double[] resultArray = merge(leftHalfArray, rightHalfArray);
            return resultArray;
        }
    }

    /**
     *  Using ITERATIVE loops (i.e, NO recursion) and NO INSERTION-SORT() as a subprocedure.
     */
    public double[] mergeSortB(){
        System.out.println("Method not yet implemented.");
        return null;
    }

    /**
     * Using recursive calls and INSERTION-SORT() as a sub-procedure.
     */
    public double[] mergeSortC(){
        System.out.println("Method not yet implemented.");
        return null;
    }

    /**
     *  Using ITERATIVE loops (i.e, NO recursion) and INSERTION-SORT() as a subprocedure.
     */
    public double[] mergeSortD(){
        System.out.println("Method not yet implemented.");
        return null;
    }

    /**
     *  Helper function that merges two arrays together in sorted order
     */
    private double[] merge(double[] leftHalfArray, double[] rightHalfArray){
        System.out.println("Method not yet implemented");
        return null;
    }

    /**
     *  a sub-procedure to sort any sub-array when its size is 25 numbers or less
     */
    private double[] insertionSort(double[] inputArray) {
        int n = inputArray.length;

        for (int i = 1; i < n; i++) {
            double key = inputArray[i];
            int j = i - 1;

            while (j >= 0 && inputArray[j] > key) {
                inputArray[j + 1] = inputArray[j];
                j = j - 1;
            }
            inputArray[j + 1] = key;
        }
        return inputArray;
    }
}
