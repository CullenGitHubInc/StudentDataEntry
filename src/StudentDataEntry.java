import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

// Student class to hold student details
class Student implements Comparable<Student> {
    private String name;
    private String address;
    private double GPA;

    // Constructor
    public Student(String name, String address, double GPA) {
        this.name = name;
        this.address = address;
        this.GPA = GPA;
    }

    // Getters for accessing private fields
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getGPA() {
        return GPA;
    }

    // Compare students based on their names (used for sorting)
    @Override
    public int compareTo(Student other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    // String representation of the Student object
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", GPA: " + GPA;
    }
}

// Main class to handle data input, sorting, and file output
public class StudentDataEntry {
    public static void main(String[] args) {
        // LinkedList to store Student objects
        LinkedList<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        String continueEntry;

        // Loop to capture student data
        do {
            // Prompt and input for name
            System.out.print("Enter student's name: ");
            String name = scanner.nextLine();

            // Prompt and input for address
            System.out.print("Enter student's address: ");
            String address = scanner.nextLine();

            // Prompt for GPA with validation
            double GPA = 0.0;
            while (true) {
                try {
                    System.out.print("Enter student's GPA: ");
                    GPA = Double.parseDouble(scanner.nextLine());

                    // Check if the GPA is within a valid range
                    if (GPA < 0.0 || GPA > 4.0) {
                        System.out.println("GPA must be between 0.0 and 4.0. Please try again.");
                    } else {
                        break;  // Valid GPA entered
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value for GPA.");
                }
            }

            // Create a new Student object and add it to the LinkedList
            studentList.add(new Student(name, address, GPA));

            // Ask if the user wants to add more students
            System.out.print("Would you like to add another student? (yes/no): ");
            continueEntry = scanner.nextLine().trim().toLowerCase();
        } while (continueEntry.equals("yes"));

        // Close the Scanner
        scanner.close();

        // Sort the LinkedList by student names
        studentList.sort(null);  // Using the natural order defined by the compareTo method

        // Write the sorted student data to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudentData.txt"))) {
            writer.write("Student List (Sorted by Name):\n");
            for (Student student : studentList) {
                writer.write(student.toString() + "\n");
            }
            System.out.println("Student data successfully written to 'StudentData.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
