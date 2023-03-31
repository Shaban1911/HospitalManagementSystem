import java.io.*;
import java.util.Scanner;

public class HospitalManagementSystem {

    static int bedsRemaining = 20;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to the hospital management system!");
            System.out.println("Please choose an option:");
            System.out.println("1. View patient records");
            System.out.println("2. Add new patient record");
            System.out.println("3. Update patient record");
            System.out.println("4. Delete patient record");
            System.out.println("5. View doctor records");
            System.out.println("6. Add new doctor record");
            System.out.println("7. Update doctor record");
            System.out.println("8. Delete doctor record");
            System.out.println("9. View remaining beds");
            System.out.println("10. Exit");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewPatients();
                    break;
                case 2:
                    addPatient();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    viewDoctors();
                    break;
                case 6:
                    addDoctor();
                    break;
                case 7:
                    updateDoctor();
                    break;
                case 8:
                    deleteDoctor();
                    break;
                case 9:
                    viewBedsRemaining();
                    break;
                case 10:
                    System.out.println("Thank you for using the hospital management system!");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }

        } while (choice != 10);

        sc.close();
    }

    public static void viewPatients() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("patients.txt"));
            String line;
            System.out.println("Patient records:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No patient records found.");
        }
    }

    public static void addPatient() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt", true));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter patient name: ");
            String name = sc.nextLine();
            System.out.println("Enter patient age: ");
            String age = sc.nextLine();
            System.out.println("Enter patient gender: ");
            String gender = sc.nextLine();
            System.out.println("Enter patient address: ");
            String address = sc.nextLine();
            System.out.println("Enter patient phone number: ");
            String phone = sc.nextLine();
            if (bedsRemaining > 0) {
                bw.write(name + ", " + age + ", " + gender + ", " + address + ", " + phone);
                bw.newLine();
                bedsRemaining--;
                System.out.println("Patient record added successfully.");
            } else {
                System.out.println("Sorry, no beds available.");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while adding patient record.");
        }
    }

    public static void updatePatient() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("patients.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("patients_tmp.txt"));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter patient name to update: ");
            String nameToUpdate = sc.nextLine();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                if (name.equalsIgnoreCase(nameToUpdate)) {
                    System.out.println("Enter new age: ");
                    String age = sc.nextLine();
                    System.out.println("Enter new gender: ");
                    String gender = sc.nextLine();
                    System.out.println("Enter new address: ");
                    String address = sc.nextLine();
                    System.out.println("Enter new phone number: ");
                    String phone = sc.nextLine();
                    bw.write(name + ", " + age + ", " + gender + ", " + address + ", " + phone);
                    bw.newLine();
                    found = true;
                    System.out.println("Patient record updated successfully.");
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            if (!found) {
                System.out.println("Patient record not found.");
            } else {
                File oldFile = new File("patients.txt");
                oldFile.delete();
                File newFile = new File("patients_tmp.txt");
                newFile.renameTo(oldFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating patient record.");
        }
    }

    public static void deletePatient() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("patients.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("patients_tmp.txt"));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter patient name to delete: ");
            String nameToDelete = sc.nextLine();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                if (name.equalsIgnoreCase(nameToDelete)) {
                    found = true;
                    System.out.println("Patient record deleted successfully.");
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            if (!found) {
                System.out.println("Patient record not found.");
            } else {
                File oldFile = new File("patients.txt");
                oldFile.delete();
                File newFile = new File("patients_tmp.txt");
                newFile.renameTo(oldFile);
                bedsRemaining++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting patient record.");
        }
    }

    public static void viewDoctors() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("doctors.txt"));
            String line;
            System.out.println("Doctor records:");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("No doctor records found.");
        }
    }
    
    public static void addDoctor() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("doctors.txt", true));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter doctor name: ");
            String name = sc.nextLine();
            System.out.println("Enter doctor specialization: ");
            String specialization = sc.nextLine();
            bw.write(name + ", " + specialization);
            bw.newLine();
            bw.close();
            System.out.println("Doctor record added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding doctor record.");
        }
    }
    
    public static void updateDoctor() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("doctors.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("doctors_tmp.txt"));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter doctor name to update: ");
            String nameToUpdate = sc.nextLine();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                if (name.equalsIgnoreCase(nameToUpdate)) {
                    System.out.println("Enter new specialization: ");
                    String specialization = sc.nextLine();
                    bw.write(name + ", " + specialization);
                    bw.newLine();
                    found = true;
                    System.out.println("Doctor record updated successfully.");
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            if (!found) {
                System.out.println("Doctor record not found.");
            } else {
                File oldFile = new File("doctors.txt");
                oldFile.delete();
                File newFile = new File("doctors_tmp.txt");
                newFile.renameTo(oldFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while updating doctor record.");
        }
    }
    
    public static void deleteDoctor() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("doctors.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("doctors_tmp.txt"));
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter doctor name to delete: ");
            String nameToDelete = sc.nextLine();
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                String name = parts[0];
                if (name.equalsIgnoreCase(nameToDelete)) {
                    found = true;
                    System.out.println("Doctor record deleted successfully.");
                } else {
                    bw.write(line);
                    bw.newLine();
                }
            }
            br.close();
            bw.close();
            if (!found) {
                System.out.println("Doctor record not found.");
            } else {
                File oldFile = new File("doctors.txt");
                oldFile.delete();
                File newFile = new File("doctors_tmp.txt");
                newFile.renameTo(oldFile);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while deleting doctor record.");
        }
    }
    
    public static void viewBedsRemaining() {
        System.out.println("Number of beds remaining: " + bedsRemaining);
    }
}    
