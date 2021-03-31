import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import Algorithms.MergeSortAlgorithms;

public class MergeSort{
    private static double[] inputFromFile;
    private static final String DEFAULT_INPUT_FILE = "inputHW02.txt";

    public static void main(String[] args) {
        //Initialize Variables used in program
        Scanner scan = new Scanner(System.in);
        MergeSortAlgorithms mergeSortAlgorithms = new MergeSortAlgorithms();
        String inputFileName = DEFAULT_INPUT_FILE;
        int bool_randomFileNeeded;

        System.out.println("COSC-336 Homework 2 Merge Sort Program");

        //Retrieves whether new file needs to be created
        do {
            System.out.println("Does a file of random doubles need to be created?\n1 - Yes\n2 - No");
            bool_randomFileNeeded = scan.nextInt();
            if(bool_randomFileNeeded !=1 && bool_randomFileNeeded != 2){
                System.out.println("ERROR: Invalid Input" + bool_randomFileNeeded);
            }
        } while(bool_randomFileNeeded !=1 && bool_randomFileNeeded != 2);
       
        //If new random double array is needed to be made these statements will execute
        if(bool_randomFileNeeded == 1){
            //Get size of array
            System.out.println("How many doubles should populate the file?");
            int arrayLength = scan.nextInt();
            System.out.println("What is the name of the new file? (DO NOT INCLUDE .txt)");
            inputFileName = scan.next() + ".txt";
            try {
                createRandomizedInputFile(arrayLength, inputFileName);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        //Populates array of doubles depending on default file or file or randomized numbers
        try {
            inputFromFile = getInputFromFile(inputFileName);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    
        double[] sortedArray = null;
        //Variables needed for timer
        long timerStart=0; long timerEnd=0; long timeElapsed;
        //Decides the merge sort algorithm used
        System.out.println("\nWhich Algorithm do you want to be used?\nA - Merge Sort A\nB - Merge Sort B\nC - Merge Sort C\nD - Merge Sort D");
        String algorithm = scan.next();
        switch(algorithm){
            case "A":
                timerStart = System.currentTimeMillis();
                sortedArray = mergeSortAlgorithms.mergeSortA(inputFromFile);
                timerEnd = System.currentTimeMillis();
                break;
            case "B":
                sortedArray = mergeSortAlgorithms.mergeSortB();
                break;
            case "C":
                sortedArray = mergeSortAlgorithms.mergeSortC();
                break;
            case "D":
                sortedArray = mergeSortAlgorithms.mergeSortD();
                break;
            default:
                System.out.println(algorithm+" Invalid Input");
        }   
    
        timeElapsed = timerEnd - timerStart;
        //Prints Array to check if it is correct
        boolean sortedCorrectly = true;
        for(int i=1; i<sortedArray.length; i++){
            if(sortedArray[i-1]>sortedArray[i]){
                sortedCorrectly=false;
            }
        }

        System.out.println("\n\tSorting Results");
        System.out.println("Status: "+sortedCorrectly);
        System.out.println("Algorithm: Merge Sort "+algorithm);
        System.out.println("Filename: "+inputFileName);
        System.out.println("#Elements: "+sortedArray.length);
        System.out.println("Time elapsed: "+timeElapsed+"ms");
        scan.close();
    }

    /**
     * Function will create/write to a new file the random double values
     * @param N size of array
     * @param name the file name that will be used
     * @throws IOException
     */
    private static void createRandomizedInputFile(int N, String fileName) throws IOException {
        Random randomGenerator = new Random();
        PrintWriter printWriter = new PrintWriter(new FileWriter(fileName));
        
        //writes random doubles to a text file max number is 3,000,000
        for(int i = 0; i<N; i++){
            double num = randomGenerator.nextDouble() * randomGenerator.nextInt(3000000);
            printWriter.println(num);
        }
        printWriter.close();
    }

    /**
    * Counts the amount of doubles in a file and populates an double array
    * runs at T(n) = 2n
    * Ex input: 1 2 323 53 1 43 
    */
    private static double[] getInputFromFile(String fileName) throws FileNotFoundException {
        Scanner counterScanner;
        Scanner fileScanner;
        File file = new File(fileName);
        
        //Counts amount of doubles
        counterScanner = new Scanner(file);
        int counter = 0;
        while(counterScanner.hasNextDouble()){
            counter++;
            counterScanner.nextDouble();
        }
        counterScanner.close();

        //Creates and populates double array
        fileScanner = new Scanner(file);
        double[] doubleArray = new double[counter];
        for(int i =0; i<counter; i++){
            doubleArray[i] = fileScanner.nextDouble();
        }
        fileScanner.close();

        return doubleArray;
    }
}