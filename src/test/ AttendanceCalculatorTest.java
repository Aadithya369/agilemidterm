import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttendanceCalculatorTest {

    @Test
    public void testCalculateAttendance() {
        // Test standard attendance
        assertEquals(80.0, AttendanceCalculator.calculateAttendance(100, 80), 0.01);
        
        // Test perfect attendance
        assertEquals(100.0, AttendanceCalculator.calculateAttendance(50, 50), 0.01);
        
        // Test zero attendance
        assertEquals(0.0, AttendanceCalculator.calculateAttendance(40, 0), 0.01);
    }

    @Test
    public void testCheckEligibility() {
        // Eligible cases (75% and above)
        assertTrue(AttendanceCalculator.checkEligibility(75.0));
        assertTrue(AttendanceCalculator.checkEligibility(85.5));
        assertTrue(AttendanceCalculator.checkEligibility(100.0));

        // Ineligible cases (below 75%)
        assertFalse(AttendanceCalculator.checkEligibility(74.9));
        assertFalse(AttendanceCalculator.checkEligibility(50.0));
        assertFalse(AttendanceCalculator.checkEligibility(0.0));
    }

    @Test
    public void testGetWarningStatusSafe() {
        // Safe zone (80% and above)
        String status = AttendanceCalculator.getWarningStatus(85.0);
        assertTrue(status.contains("Safe"));
        
        String boundaryStatus = AttendanceCalculator.getWarningStatus(80.0);
        assertTrue(boundaryStatus.contains("Safe"));
    }

    @Test
    public void testGetWarningStatusWarning() {
        // Warning zone (75% to just below 80%)
        String status = AttendanceCalculator.getWarningStatus(77.5);
        assertTrue(status.contains("Warning"));

        String boundaryStatus = AttendanceCalculator.getWarningStatus(75.0);
        assertTrue(boundaryStatus.contains("Warning"));
    }

    @Test
    public void testGetWarningStatusCritical() {
        // Critical zone (below 75%)
        String status = AttendanceCalculator.getWarningStatus(74.9);
        assertTrue(status.contains("CRITICAL"));

        String severeStatus = AttendanceCalculator.getWarningStatus(40.0);
        assertTrue(severeStatus.contains("CRITICAL"));
    }
}
