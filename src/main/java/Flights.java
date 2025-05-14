public class Flights {
    public String airline;
    public String flightNumber;
    public String destination;
    public String departureTime;
    public String duration ;
    public String arrivalTime;

    public Flights(String airline, String flightNumber, String destination, String departureTime, String duration, String arrivalTime) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return String.format(
                "Рейс: %s %s | Направление: %s | Вылет: %s | В пути: %s | Прилёт: %s",
                airline, flightNumber, destination, departureTime, duration, arrivalTime);
    }
}
