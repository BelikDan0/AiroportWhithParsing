import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Flight {
    private String pathToHtmlCode = "src/main/resources/data/sheremetivoPage.html";
    public List<Flights> flights = new ArrayList<>();
    public Flight(String url){
        parsSheremetivoPage(url);
    }

    public void parsSheremetivoPage(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String strHtnlCode = document.toString();
            Elements elements=document.select(".hidden-link");
            FileWriter fileWriter = new FileWriter(pathToHtmlCode);
            fileWriter.write(strHtnlCode);


            Elements rows = document.select("tr"); // Получаем все строки таблицы


            for (Element row : rows) {
                Elements tds = row.select("td"); // Ячейки в строке
                if (tds.size() >= 5) {
                    // Парсим первую ячейку (авиакомпания + номер рейса)
                    String firstTd = tds.get(0).text();
                    String[] parts = firstTd.split("\\s+\\(");
                    String airline = parts[0].trim();
                    String flightNumber = parts[1].replace(")", "").trim();

                    // Остальные данные
                    String destination = tds.get(1).text();
                    String departureTime = tds.get(2).text();
                    String duration = tds.get(3).text();
                    String arrivalTime = tds.get(4).text();
                    flights.add(new Flights(airline,flightNumber,destination,departureTime,duration,arrivalTime));
                }
            }

        }catch (Exception e){
            e.getMessage();
        }


    }
    public void printAllFlight() {
        flights.forEach(System.out::println);
    }
}
