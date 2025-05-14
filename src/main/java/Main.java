public class Main {
    public static void main(String[] args) {
        String url ="https://www.aviasales.ru/";
        String urlShere="https://www.aviasales.ru//airports/sheremetevo";
        Airoport airoport=new Airoport(url);
        Flight flight=new Flight(urlShere);
        flight.printAllFlight();

    }
}
