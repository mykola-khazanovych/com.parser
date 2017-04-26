import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 */
public class PageCrawlerImpl implements PageCrawler {
    static int requestsCounter;

    @Override
    public List<String> getAllLinksByRequest(String searchRequest) {

        final String SEARCH_REQUEST_PREFIX = "https://www.aboutyou.de";
        List<String> linksFromAllNextPagesList = new ArrayList<>();
        String nextPageRequest = searchRequest;
        Document pageWithOffersJsoupDoc;
        String nextPageUrl = null;
        do {
            pageWithOffersJsoupDoc = this.getPageWithOffersByRequest(nextPageRequest);
            linksFromAllNextPagesList.addAll(this.getAllOffersLinksFromPage(pageWithOffersJsoupDoc));
            nextPageUrl = this.getNextPageRequest(pageWithOffersJsoupDoc);
            nextPageRequest = SEARCH_REQUEST_PREFIX + nextPageUrl;
        } while (nextPageUrl != null);
        return linksFromAllNextPagesList;
    }

    @Override
    public Document getPageWithOffersByRequest(String searchRequest) {
        Document pageWithOffersJsoupDoc = null;
        try {
            pageWithOffersJsoupDoc = Jsoup.connect(searchRequest).get();
            requestsCounter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageWithOffersJsoupDoc;
    }

    private List<String> getAllOffersLinksFromPage(Document pageWithOffersJsoupDoc) {

        if (pageWithOffersJsoupDoc == null) return null;

        final String SEARCH_REQUEST_PREFIX = "https://www.aboutyou.de";
        final int INITIAL_QUANTITY_OF_OFFER_LINKS_PER_PAGE = 100;
        List<String> OffersLinksOnPageList = new ArrayList<>(INITIAL_QUANTITY_OF_OFFER_LINKS_PER_PAGE);

        final String CLASS_NAME_TO_SELECT_OFFER_IN_HTML = "js-product-name product-name";
        Elements offersOnPage = pageWithOffersJsoupDoc.getElementsByClass(CLASS_NAME_TO_SELECT_OFFER_IN_HTML);

        for (Element offer : offersOnPage) {
            OffersLinksOnPageList.add(SEARCH_REQUEST_PREFIX + offer.getElementsByTag("a").attr("href"));
        }

        return OffersLinksOnPageList;
    }

    private String getNextPageRequest(Document pageWithOffers) {

        final String CLASS_NAME_TO_SELECT_NEXT_PAGE_BTN_IN_HTML = "btn btn-category-next";
        Elements nextPageDiv = pageWithOffers.getElementsByClass(CLASS_NAME_TO_SELECT_NEXT_PAGE_BTN_IN_HTML);
        if (nextPageDiv.size() == 0) return null;
        String nextPageUrl = nextPageDiv.attr("href");
        return nextPageUrl;
    }
}
