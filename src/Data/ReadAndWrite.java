package Data;

import java.io.*;
import java.util.Scanner;

public class ReadAndWrite {
    // Write details to a file
    public static void WriteDetails(String file, String input) {
        try {
            // Create a filewriter  with the file as input and a PrintWriter with the filewriter as input
            FileWriter fwriter = new FileWriter(file, true);
            PrintWriter output = new java.io.PrintWriter(fwriter);
            // Write formatted output to the file
            output.println(input);
            output.close();
        } catch (IOException ex) {
            // if the file is not accessible, print this message
            System.out.println("Error writing to file '" + file + "'");
        }
    }

    // Read details from a file
    public static Scanner readDetails(String file) {
        Scanner input = new Scanner(System.in);
        try {
            // Create a file with file as input, read the file
            File ReadFile = new java.io.File(file);
            input = new Scanner(ReadFile);
            // Close the file
        } catch (FileNotFoundException ex) {
            System.out.println("Error reading the file'" + file + "'");
        }
        return input;
    }

    // Copy a file to another file
    public static void copyToOtherFile(String readFrom, String writeTo) {
        // Take input from the file to read from
        Scanner input = ReadAndWrite.readDetails(readFrom);
        // While it has lines
        while (input.hasNextLine()) {
            // Write details to the other file
            ReadAndWrite.WriteDetails(writeTo, input.nextLine());
        }
    }

    // Empty a file
    public static void emptyFile(String file) {
        try {
            // Close the file
            new FileOutputStream(file).close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove a line from a file, taking input file, temporary file to store input, and line to remove as parameters
    public static void removeLine(String input, String temp, String remove) throws IOException {
        // Create two new files of input and temp
        File inputFile = new File(input);
        File tempFile = new File(temp);

        BufferedReader reader = null;
        try {
            // Create a new buffered reader of a new file reader of the input file
            reader = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            // same for writer
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String currentLine;
        // While currentLine, which we set equal to the line from the reader is not empty
        while ((currentLine = reader.readLine()) != null) {
            // Trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            // If this line is same as line to remove, continue, ie. do not write this line to the file
            if (trimmedLine.equals(remove))
                continue;
            // else write to the file
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        // Empty the input file, copy from temporary to input and empty temp file
        emptyFile(input);
        copyToOtherFile(temp, input);
        emptyFile(temp);
    }
}
