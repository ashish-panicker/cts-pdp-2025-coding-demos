import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeApi {
    public static void main(String[] args) {
        // 3 Design principles of Date and Time API
        // Immutable, Fluent, Thread-safe

        LocalDate date = LocalDate.of(2023, 10, 5);
        LocalDate now  = LocalDate.now();
        LocalDate parsedDate = LocalDate.parse("2023-10-05");
        System.out.println("Date: " + date);
        System.out.println("Now: " + now);
        System.out.println("Parsed Date: " + parsedDate);
        System.out.println();

        LocalTime time = LocalTime.of(14, 30, 0);
        LocalTime nowTime = LocalTime.now();
        System.out.println("Time: " + time);
        System.out.println("Now Time: " + nowTime);
        System.out.println();

        LocalDateTime dateTime = LocalDateTime.of(date, time);
        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println("Date Time: " + dateTime);
        System.out.println("Now Date Time: " + nowDateTime);
        System.out.println();

        ZonedDateTime asia = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime utc = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime gmt = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println("Asia Time: " + asia);
        System.out.println("UTC Time: " + utc);
        System.out.println("GMT Time: " + gmt);

        Instant instant = Instant.now();
        System.out.println("Instant: " + instant);
        System.out.println();
        System.out.println();

        // Example Scenario:
        // Flight from Delhi to New York
        // Date: 2025-12-15
        // Departure Time: 22:00 IST
        // Duration: 15 hours 30 minutes
        
        // Output
        // Take Of time
        // Ariival Time [Origin]
        // Ariival Time [Destination]
        
        ZonedDateTime takeOffTime = ZonedDateTime.of(
                LocalDate.of(2025, 12, 15),
                LocalTime.of(22, 0),
                ZoneId.of("Asia/Kolkata")
        );
        System.out.println("Take Off Time (IST): " + takeOffTime);

        Duration flightDuration = Duration.ofHours(15).plusMinutes(30);

        ZonedDateTime arrivalInOriginTime, arrivalInDestTime;

        arrivalInOriginTime = takeOffTime.plus(flightDuration);
        System.out.println("Arrival Time (IST): " + arrivalInOriginTime);

        arrivalInDestTime = arrivalInOriginTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("Arrival Time (EST): " + arrivalInDestTime);

        System.out.println();

        Instant departureInstant = takeOffTime.toInstant();
        Instant arrivanInstant = departureInstant.plus(flightDuration);
        ZonedDateTime arrivalEST = arrivanInstant.atZone(ZoneId.of("America/New_York"));
        System.out.println("Arrival Time using Instant (EST): " + arrivalEST);

        /**
         * NewYork to Paris (DST)
         * Take Off: 2025-03-09 20:00 EST (America/New_York)
         * Duration: 7 hours 30 minutes
         * Destination: Paris (Europe/Paris)
         */
    }
}
