import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import Algorithms.MergeSortAlgorithms;

public class MergeSort{
    private static int[] inputFromFile;

    public static void main(String[] args) {
        //Initialize Variables used in program
        Scanner scan = new Scanner(System.in);
        MergeSortAlgorithms mergeSortAlgorithms = new MergeSortAlgorithms();

        System.out.println("COSC-336 Homework 2 Merge Sort Program");

        //Decides how to declare input array and declares input array
        System.out.println("Where should the input come from?\n1 - inputHW02.txt\n2 - Randomized array of size n (will be asked for size n)");
        int input = scan.nextInt();
        switch(input){
            case 1:
                try {
                    inputFromFile = getInputFromFile();
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                break;
            default:
                System.out.println(input+" Invalid Input");
        }

        // //Decides the merge sort algorithm used
        System.out.println("\nWhich Algorithm do you want to be used?\nA - Merge Sort A\nB - Merge Sort B\nC - Merge Sort C\nD - Merge Sort D");
        String algorithm = scan.next();
        switch(algorithm){
            case "A":
                mergeSortAlgorithms.mergeSortA();
                break;
            case "B":
                mergeSortAlgorithms.mergeSortB();
                break;
            case "C":
                mergeSortAlgorithms.mergeSortC();
                break;
            case "D":
                mergeSortAlgorithms.mergeSortD();
                break;
            default:
                System.out.println(algorithm+" Invalid Input");
        }   

        scan.close();
    }

    /**
    * Counts the amount of integers in a file and populates an int array
    * runs at T(n) = 2n
    * Ex input: 1 2 323 53 1 43 
    */
    private static int[] getInputFromFile() throws FileNotFoundException {
        Scanner counterScanner;
        Scanner fileScanner;
        File file = new File("inputHW02.txt");
        
        //Counts amount of ints
        counterScanner = new Scanner(file);
        int counter = 0;
        while(counterScanner.hasNextInt()){
            counter++;
            counterScanner.nextInt();
        }
        counterScanner.close();

        //Creates and populates int array
        fileScanner = new Scanner(file);
        int[] intArray = new int[counter];
        for(int i =0; i<counter; i++){
            intArray[i] = fileScanner.nextInt();
        }
        fileScanner.close();
        return intArray;
    }
}