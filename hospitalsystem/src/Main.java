import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();

        // Predefined doctors
        doctors.add(new Doctor(1, "Dr. Prashad sir", "All Rounder"));
        doctors.add(new Doctor(4, "Dr. Niraj sir", "Medical Store"));
        doctors.add(new Doctor(2, "Dr. Mehta", "Neurology"));
        doctors.add(new Doctor(3, "Dr. Khan", "Orthopedics"));
        doctors.add(new Doctor(4, "Dr. Khan", "Cardiology"));


        while (true) {
            System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. View Appointments");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addPatient(scanner, patients);
                    break;

                case 2:
                    viewPatients(patients);
                    break;

                case 3:
                    viewDoctors(doctors);
                    break;

                case 4:
                    bookAppointment(scanner, patients, doctors, appointments);
                    break;

                case 5:
                    viewAppointments(appointments);
                    break;

                case 6:
                    System.out.println("THANK YOU FOR USING THE SYSTEM!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ---------------- PATIENT FUNCTIONS ----------------

    static void addPatient(Scanner scanner, ArrayList<Patient> patients) {
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Gender: ");
        String gender = scanner.next();

        int id = patients.size() + 1;
        patients.add(new Patient(id, name, age, gender));

        System.out.println("Patient added successfully with ID: " + id);
    }

    static void viewPatients(ArrayList<Patient> patients) {
        System.out.println("\n+----------------------- PATIENT LIST ------------------------+");
        System.out.printf("|%-5s | %-15s | %-10s | %-22s|\n", "ID", "Name", "Age", "Gender");
        System.out.println("+-------------------------------------------------------------+");

        for (Patient p : patients) {
            System.out.printf("|%-5d | %-15s | %-10d | %-22s|\n",
                    p.id, p.name, p.age, p.gender);
        }

        System.out.println("+-------------------------------------------------------------+");
    }

    // ---------------- DOCTOR FUNCTIONS ----------------

    static void viewDoctors(ArrayList<Doctor> doctors) {
        System.out.println("\n+----------------------- DOCTOR LIST -----------------------+");
        System.out.printf("|%-5s | %-15s | %-20s|\n", "ID", "Name", "Specialization");
        System.out.println("+-----------------------------------------------------------+");

        for (Doctor d : doctors) {
            System.out.printf("|%-5d | %-15s | %-20s|\n",
                    d.id, d.name, d.specialization);
        }

        System.out.println("+-----------------------------------------------------------+");
    }

    // ---------------- APPOINTMENT FUNCTIONS ----------------

    static void bookAppointment(Scanner scanner,
                                ArrayList<Patient> patients,
                                ArrayList<Doctor> doctors,
                                ArrayList<Appointment> appointments) {

        System.out.print("Enter Patient ID: ");
        int pid = scanner.nextInt();

        System.out.print("Enter Doctor ID: ");
        int did = scanner.nextInt();

        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.next();

        Patient p = getPatientById(pid, patients);
        Doctor d = getDoctorById(did, doctors);

        if (p == null || d == null) {
            System.out.println("Invalid patient or doctor ID!");
            return;
        }

        for (Appointment a : appointments) {
            if (a.doctorId == did && a.date.equals(date)) {
                System.out.println("Doctor is not available on this date!");
                return;
            }
        }

        appointments.add(new Appointment(pid, did, date));
        System.out.println("Appointment booked successfully!");
    }

    static void viewAppointments(ArrayList<Appointment> appointments) {
        System.out.println("\n+----------------------- APPOINTMENTS -----------------------+");
        System.out.printf("|%-12s | %-12s | %-12s |\n", "Patient ID", "Doctor ID", "Date");
        System.out.println("+------------------------------------------------------------+");

        for (Appointment a : appointments) {
            System.out.printf("| %-12d | %-12d | %-12s |\n",
                    a.patientId, a.doctorId, a.date);
        }

        System.out.println("+------------------------------------------------------------+");
    }

    // ---------------- FIND FUNCTIONS ----------------

    static Patient getPatientById(int id, ArrayList<Patient> list) {
        for (Patient p : list) if (p.id == id) return p;
        return null;
    }

    static Doctor getDoctorById(int id, ArrayList<Doctor> list) {
        for (Doctor d : list) if (d.id == id) return d;
        return null;
    }
}

// ---------------- CLASSES ----------------

class Patient {
    int id;
    String name;
    int age;
    String gender;

    Patient(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

class Appointment {
    int patientId;
    int doctorId;
    String date;

    Appointment(int patientId, int doctorId, String date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
}
