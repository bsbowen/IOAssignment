package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IOAssignment {

    public static void main(String[] args) {
        // Relative paths to the input files based on your project structure
        String inputFilePath1 = "src/main/java/org/example/input1.txt";
        String inputFilePath2 = "src/main/java/org/example/input2.txt";
        String mergedFilePath = "src/main/java/org/example/merged.txt";
        String commonFilePath = "src/main/java/org/example/common.txt";

        // Create readers and writers with the specified paths
        try (
                BufferedReader br1 = new BufferedReader(new FileReader(inputFilePath1));
                BufferedReader br2 = new BufferedReader(new FileReader(inputFilePath2));
                BufferedWriter mergedWriter = new BufferedWriter(new FileWriter(mergedFilePath));
                BufferedWriter commonWriter = new BufferedWriter(new FileWriter(commonFilePath))
        ) {
            // Write contents of input1.txt to merged.txt
            String line;
            while ((line = br1.readLine()) != null) {
                mergedWriter.write(line + "\n");
            }

            // Write contents of input2.txt to merged.txt and find common integers
            while ((line = br2.readLine()) != null) {
                mergedWriter.write(line + "\n");

                // Check if this line exists in input1.txt
                try (BufferedReader brCheck = new BufferedReader(new FileReader(inputFilePath1))) {
                    String checkLine;
                    while ((checkLine = brCheck.readLine()) != null) {
                        if (checkLine.equals(line)) {
                            commonWriter.write(line + "\n");
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading input1.txt for common check.");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        } catch (IOException e) {
            System.err.println("Error during file operation.");
        }
    }
}