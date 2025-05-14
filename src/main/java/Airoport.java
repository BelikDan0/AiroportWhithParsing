import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Airoport {
    private String pathToHtmlCode="src/main/resources/data/mainPageAiroports.html";
    private HashMap<String,String>mapAirport;
    public Airoport(String url){
        mapAirport=new HashMap<>();
        parsMainPageAiroports(url);

    }
    public void putMapAirotopts(String name,String link){
        mapAirport.put(name,link);
    }

    public void printMapAirport() {
        for (Map.Entry<String,String >entry:mapAirport.entrySet()){
            System.out.println("\uD83E\uDD23"+entry.getKey()+"\n"+entry.getValue()+"\uD83E\uDD23");
        }
    }

    public HashMap<String, String> getMapAirport() {
        return mapAirport;
    }

    public void parsMainPageAiroports(String url){
        try{
            Document document= Jsoup.connect(url).get();
            Elements elements=document.select(".s__yB3EapYI1kWLVKzMbxuf");
            String strHtnlCode=document.toString();

            FileWriter fileWriter=new FileWriter(pathToHtmlCode);
            fileWriter.write(strHtnlCode);

            for (Element element:elements){
                String strelement=element.toString();
                if(strelement.contains("href=\"/airports\"")){

                    for (String line:strelement.split("</a>")) {
                        String teplateForLinkForAiroport = "href=\"";
                        int startIndexForLinlAiroport = line.indexOf(teplateForLinkForAiroport);
                        if (startIndexForLinlAiroport == -1) {
                            continue;
                        }
                        startIndexForLinlAiroport += teplateForLinkForAiroport.length();
                        int endlIndexForLink = line.indexOf("\"", startIndexForLinlAiroport);
                        String lingAiroport = url + line.substring(startIndexForLinlAiroport, endlIndexForLink);

                        String tamplateForNameAirport = "data-test-id=\"text\">";
                        int startIndexForNameairport = line.indexOf(tamplateForNameAirport);
                        if (startIndexForNameairport == -1) {
                            continue;
                        }
                        startIndexForNameairport += tamplateForNameAirport.length();
                        String nameAirport = line.substring(startIndexForNameairport);
                        if (nameAirport.contains("Аэропорты")) {
                            continue;
                        }
                        mapAirport.put(nameAirport,lingAiroport);
                    }

                }
            }
        }catch (Exception e){
            e.getMessage();
        }


    }
}
