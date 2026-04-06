import java.util.Scanner;

public class AttendanceCalculator {

    // Thresholds for eligibility and warnings
    private static final double ELIGIBILITY_THRESHOLD = 75.0;
    private static final double WARNING_THRESHOLD = 80.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Attendance Calculator ---");

        // 1. Get User Input
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Total Number of Classes Held: ");
        int totalClasses = scanner.nextInt();

        System.out.print("Enter Number of Classes Attended: ");
        int attendedClasses = scanner.nextInt();

        // Validate input
        if (totalClasses <= 0 || attendedClasses < 0 || attendedClasses > totalClasses) {
            System.out.println("Invalid input! Please ensure classes held is greater than 0 and attended classes do not exceed total classes.");
            return;
        }

        // 2. Calculate Attendance
        double attendancePercentage = calculateAttendance(totalClasses, attendedClasses);

        // 3. Determine Eligibility and Status
        boolean isEligible = checkEligibility(attendancePercentage);
        String warningStatus = getWarningStatus(attendancePercentage);

        // 4. Display Results
        System.out.println("\n--- Attendance Report ---");
        System.out.println("Student Name: " + studentName);
        System.out.printf("Attendance Percentage: %.2f%%\n", attendancePercentage);
        
        System.out.println("Exam Eligibility: " + (isEligible ? "ELIGIBLE" : "NOT ELIGIBLE"));
        System.out.println("Status/Warning: " + warningStatus);

        scanner.close();
    }

    /**
     * Calculates the attendance percentage.
     * Changed to 'public' so JUnit tests can access it.
     */
    public static double calculateAttendance(int totalClasses, int attendedClasses) {
        return ((double) attendedClasses / totalClasses) * 100;
    }

    /**
     * Checks if the student meets the minimum attendance required for exams.
     * Changed to 'public' so JUnit tests can access it.
     */
    public static boolean checkEligibility(double percentage) {
        return percentage >= ELIGIBILITY_THRESHOLD;
    }

    /**
     * Returns a warning status based on the attendance percentage.
     * Changed to 'public' so JUnit tests can access it.
     */
    public static String getWarningStatus(double percentage) {
        if (percentage >= WARNING_THRESHOLD) {
            return "Safe - Good Attendance.";
        } else if (percentage >= ELIGIBILITY_THRESHOLD) {
            return "Warning - Attendance is dropping close to the minimum requirement.";
        } else {
            return "CRITICAL - Attendance is below the required " + ELIGIBILITY_THRESHOLD + "%. Shortage of attendance.";
        }
    }
}
