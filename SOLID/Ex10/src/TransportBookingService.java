public class TransportBookingService {
    // DIP violation: direct concretes
    private final DistanceCalculation distanceCalculation;
    private final PaymentMethod paymentMethod;
    private final DriverAllocation driverAllocation;

    public TransportBookingService(DistanceCalculation distanceCalculation, PaymentMethod paymentMethod, DriverAllocation driverAllocation){
        this.distanceCalculation = distanceCalculation;
        this.paymentMethod = paymentMethod;
        this.driverAllocation = driverAllocation;
    }


    public void book(TripRequest req) {
        double km = distanceCalculation.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverAllocation.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667; // messy pricing
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paymentMethod.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
