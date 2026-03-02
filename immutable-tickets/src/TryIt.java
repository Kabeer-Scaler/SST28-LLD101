import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket(
                "TCK-1001",
                "reporter@example.com",
                "Payment failing on checkout"
        );

        System.out.println("Created: " + t);

        t = service.assign(t, "agent@example.com");
        t = service.escalateToCritical(t);

        System.out.println("\nAfter service updates: " + t);

        // Attempt external mutation
        try {
            t.getTags().add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTags are immutable (cannot modify externally).");
        }

        System.out.println("\nFinal ticket: " + t);
    }
}
