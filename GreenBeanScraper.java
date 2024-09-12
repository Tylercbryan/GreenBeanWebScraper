//Written by Tyler Bryan - Class Java244
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GreenBeanScraper {
    public static void main(String[] args) throws IOException {

        //Selects the website to scrape from
        Document doc = Jsoup.connect("https://redfoxcoffeemerchants.com/store/").get();
        int coffeeCount = 1;
        ArrayList<String> title = new ArrayList();
        ArrayList<String> origin = new ArrayList();
        ArrayList<String> urls = new ArrayList();
        String prefCof = prefCoffee();



        //This website is set up a bit weird with half the information on a "top-half" class
        //and the rest on a "bottom-half" class.
        //To scrape all the information needed I've used 2 for loops
        Elements divsT = doc.select("div[class=product-preview-top-half]");
        for (Element div : divsT) {
            //Scrapes the coffee names from the website
            title.add(div.getElementsByTag("h2").text());

            //Scrapes the coffee origin from the website
            origin.add(div.getElementsByClass("archive-product-cat").text());
            }

        Elements divsB = doc.select("div[class=product-preview-bottom-half]");
        for (Element a : divsB) {
            //Scrapes the coffee url from the website
            urls.add(a.getElementsByClass("seemax-button").attr("href"));
        }
            //I had really hoped to scrape more from this but the classes become too generically named to select

        //Print the Coffees from scraped array lists
        for (int i = 0; i < origin.size(); i++) {
            if(origin.get(i).toLowerCase().contains(prefCof.toLowerCase())){
                System.out.println("Coffee " + (coffeeCount) + ": " + title.get(i) + "\n from: " + origin.get(i) + "\n link: " +
                        urls.get(i) + "\n");
                coffeeCount++;
            }else if(prefCof.equalsIgnoreCase("any")){
                System.out.println("Coffee " + (coffeeCount) + ": " + title.get(i) + "\n from: " + origin.get(i) + "\n link: " +
                        urls.get(i) + "\n");
                coffeeCount++;
            }
        }if(coffeeCount == 1){
            System.out.println("Sorry, there are no coffees from that origin.");
        }

        }


        //Prompts the user for a coffee origin.
    public static String prefCoffee() {
        Scanner scan = new Scanner(System.in);
        System.out.println("What origin are you looking for? " + "(Colombia, Mexico, Honduras, etc, or ANY) -> ");
        String coffee = scan.nextLine();

        return coffee;
    }
}


