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
    public double[] mergeSortB(double[] inputArray){
        int length = inputArray.length;
        double[] temp = Arrays.copyOf(inputArray, inputArray.length);
        /**
         * Creates subsections of the array {1,2,4,8,..,n}
         */
		for (int i = 1; i <= length-1; i = 2*i)
		{   
            //Iterates through list merging 2 subsections of size i
			for (int j = 0; j < length-i; j += 2*i)
			{
				int from = j; int mid = j + i - 1;
				int to = Integer.min(j + 2*i - 1, length-1);
				merge(inputArray, temp, from, mid, to);
			}
        }
        return inputArray;
    }

    /**
     * Using recursive calls and INSERTION-SORT() as a sub-procedure.
     */
    public double[] mergeSortC(double[] inputArray){
        if(inputArray.length == 25){
            insertionSort(inputArray);
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
     *  Using ITERATIVE loops (i.e, NO recursion) and INSERTION-SORT() as a subprocedure.
     */
    public double[] mergeSortD(double[] inputArray){
        int length = inputArray.length;
        if(length<=25){
            insertionSort(inputArray);
        } else {
            /**
             * Assumption is that file is atleast 26 in length
             * Sorts as many subsections of 25 that are in the file of doubles
             */
            int end = 25; int start = 1;
            for(int i=length/25; i>0; i--){
                inputArray = insertionSort(inputArray, start, end);
                start+=25; end+=25; 
            }
            
            /**
             * Creates subsections of the array {25,50,..,n}
             * Since subsections of up to 25 are sorted 
             * This will begin with merging groups of 25
             */
            double[] temp = Arrays.copyOf(inputArray, length);
            for (int i = 25; i <= length-1; i = 2*i)
            {   
                //Iterates through list merging 2 subsections of size i
                for (int j = 0; j < length-i; j += 2*i)
                {
                    int from = j; int mid = j + i - 1;
                    int to = Integer.min(j + 2*i - 1, length-1);
                    merge(inputArray, temp, from, mid, to);
                }
            }
        }
        return inputArray;
    }

    /**
     *  Helper function that merges two arrays together in sorted order
     */
    private double[] merge(double[] leftHalfArray, double[] rightHalfArray){
        double[] mergedArray = new double[leftHalfArray.length+rightHalfArray.length];
        int i=0; int j=0; int k=0;
        //Checks which array to add a value to merged array while each list have elements left
        while(i<leftHalfArray.length && j<rightHalfArray.length){
            if(leftHalfArray[i]<rightHalfArray[j]){
                mergedArray[k]=leftHalfArray[i];
                i++;
            } else {
                mergedArray[k]=rightHalfArray[j];
                j++;
            }
            k++;
        }
        
        //One of these while loops will run adding remaining elements to mergred array
        while(i<leftHalfArray.length) {
            mergedArray[k]=leftHalfArray[i];
            i++; k++;
        }
        while(j<rightHalfArray.length) {
            mergedArray[k]=rightHalfArray[j];
            j++; k++;
        }

        return mergedArray;
    }

    //Merge function used for iterative techniques
    public void merge(double[] inputArray, double[] temp, int from, int mid, int to)
	{
		int k = from, i = from, j = mid + 1;
		while (i <= mid && j <= to)
		{
			if (inputArray[i] < inputArray[j]) {
                temp[k] = inputArray[i];
                k++; i++;
			}
			else {
                temp[k] = inputArray[j];
                k++; j++;
			}
		}

		while (i <= mid) {
            temp[k] = inputArray[i];
            k++; i++;
		}

		for (i = from; i <= to; i++) {
			inputArray[i] = temp[i];
		}
    } 

    /**
     *  a sub-procedure to sort any sub-array when its size is 25 numbers or less
     */
    private void insertionSort(double[] inputArray) {
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
    }

    /**
     *  a sub-procedure to sort any sub-array when its size is 25 numbers or less
     */
    private double[] insertionSort(double[] inputArray, int start, int to) {
        for (int i = start; i < to; i++) {
            double key = inputArray[i];
            int j = i - 1;

            while (j >= start-1 && inputArray[j] > key) {
                inputArray[j + 1] = inputArray[j];
                j = j - 1;
            }
            inputArray[j + 1] = key;
        }
        return inputArray;
    }
    

}
