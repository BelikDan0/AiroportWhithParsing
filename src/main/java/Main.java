import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String url ="https://www.aviasales.ru/";
        Airoport airoport=new Airoport(url);

        for (Map.Entry<String,String >entry:airoport.getMapAirport().entrySet()){
            Flight flight =new Flight(entry.getValue());
            System.out.println("\nВылет из "+entry.getKey());
            flight.printAllFlight();
        }
    }
}
