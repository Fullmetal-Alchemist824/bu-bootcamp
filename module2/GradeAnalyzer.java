import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        // Test: small hardcoded list
        ArrayList<Integer> testScores = new ArrayList<>();
        testScores.add(90);
        testScores.add(80);
        testScores.add(70);

        double testAverage = calculateAverage(testScores);
        System.out.println("Test average: " + testAverage);

        // Read scores from file
        String inputFile = "scores.txt";
        ArrayList<Integer> scores = readScores(inputFile);

        // Handle empty list so highest/lowest are not left at
        // Integer.MIN_VALUE / Integer.MAX_VALUE
        if (scores.isEmpty()) {
            System.out.println("No valid scores were found.");
            writeReport(scores, 0.0, 0, 0, "report.txt");
            return;
        }

        // Calculate average
        double avg = calculateAverage(scores);

        // Find highest and lowest
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }

            if (score < lowest) {
                lowest = score;
            }
        }

        // Count grade bands
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        // Write and print report
        writeReport(scores, avg, highest, lowest, "report.txt");

        // Print grade band counts
        System.out.println();
        System.out.println("Grade Bands:");
        System.out.println(String.format("A: %d", countA));
        System.out.println(String.format("B: %d", countB));
        System.out.println(String.format("C: %d", countC));
        System.out.println(String.format("D: %d", countD));
        System.out.println(String.format("F: %d", countF));
    }

    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Remove whitespace
                line = line.trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }

                try {
                    int score = Integer.parseInt(line);
                    scores.add(score);
                } catch (NumberFormatException e) {
                    System.out.println("Warning: Invalid score skipped: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (int score : scores) {
            total += score;
        }

        return total / scores.size();
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {

        // Calculate grade band counts again for the report
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for (int score : scores) {
            if (score >= 90) {
                countA++;
            } else if (score >= 80) {
                countB++;
            } else if (score >= 70) {
                countC++;
            } else if (score >= 60) {
                countD++;
            } else {
                countF++;
            }
        }

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(outputFile))) {

            writer.write("Grade Report");
            writer.newLine();
            writer.write("============");
            writer.newLine();

            writer.write(String.format("Number of scores: %d%n", scores.size()));
            writer.write(String.format("Average score:   %.2f%n", avg));
            writer.write(String.format("Highest score:   %d%n", high));
            writer.write(String.format("Lowest score:    %d%n", low));

            writer.newLine();
            writer.write("Grade Bands");
            writer.newLine();
            writer.write("-----------");
            writer.newLine();

            writer.write(String.format("A (90+):     %d%n", countA));
            writer.write(String.format("B (80-89):   %d%n", countB));
            writer.write(String.format("C (70-79):   %d%n", countC));
            writer.write(String.format("D (60-69):   %d%n", countD));
            writer.write(String.format("F (below 60): %d%n", countF));

            // Print the same report to the terminal
            System.out.println();
            System.out.println("Grade Report");
            System.out.println("============");
            System.out.println(String.format("Number of scores: %d", scores.size()));
            System.out.println(String.format("Average score:   %.2f", avg));
            System.out.println(String.format("Highest score:   %d", high));
            System.out.println(String.format("Lowest score:    %d", low));

            System.out.println();
            System.out.println("Grade Bands");
            System.out.println("-----------");
            System.out.println(String.format("A (90+):      %d", countA));
            System.out.println(String.format("B (80-89):    %d", countB));
            System.out.println(String.format("C (70-79):    %d", countC));
            System.out.println(String.format("D (60-69):    %d", countD));
            System.out.println(String.format("F (below 60): %d", countF));

        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
}