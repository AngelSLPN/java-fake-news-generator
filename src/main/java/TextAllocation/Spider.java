package TextAllocation;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.Json;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Class-based representation of a spider
 *
 * @author Dahlitz
 */
public class Spider {

    private static final int MAX_PAGES = 10;
    private Set<String> pagesVisited = new HashSet<String>();
    private List<String> pagesToVisit = new LinkedList<String>();

    /**
     * Returns the next url to visit by removing the first
     * element of the list of pages to visit for.
     * Removes duplicates automatically.
     *
     * @return Next url to visit
     */
    private String getNextUrl() {
        String nextUrl;

        do {
            nextUrl = this.pagesToVisit.remove(0);
        } while(this.pagesToVisit.contains(nextUrl));

        this.pagesVisited.add(nextUrl);

        return nextUrl;
    }

    /**
     * Marks the entry point of the spider. Starts at a given base url
     * and searches for content containing the specified keywords.
     *
     * @param baseUrl Entry point for the spider
     * @param keywords Keywords a content needs to match
     */
    public JsonArray search(String baseUrl, String[] keywords) {

        JsonArrayBuilder articles = Json.createArrayBuilder();
        return Json.createArrayBuilder().build();

//       while(this.pagesVisited.size() < MAX_PAGES) {
//            String currentUrl;
//            Crawler crawler = new Crawler();
//
//            if(this.pagesToVisit.isEmpty()) {
//                currentUrl = baseUrl;
//                this.pagesVisited.add(baseUrl);
//            }
//            else {
//                currentUrl = this.getNextUrl();
//            }
//
//            crawler.crawl(currentUrl);
//            boolean success = crawler.searchForWord(keywords);
//
//            this.pagesToVisit.addAll(crawler.getLinks());
//        }

//        System.out.println(String.format("**Done** Visited %s web page(s)", this.pagesVisited.size()));


    }
}
