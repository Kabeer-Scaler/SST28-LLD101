public class DriverAllocator implements DriverAllocation {
    public String allocate(String studentId) {
        // fake deterministic driver
        return "DRV-17";
    }
}
