import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 */

public class OffersLinksListBuilderImpl implements OffersLinksListBuilder {
    private PageCrawler pageCrawler;

    @Inject
    public OffersLinksListBuilderImpl(PageCrawler pageCrawler){
        this.pageCrawler = pageCrawler;
    }

    @Override
    public List<String> getList(String keyword) {

        List<String> offersLinksList = new ArrayList<>();
        List<String> searchRequestsList = this.generateListOfRequestsForKeyword(keyword);

        for(String searchRequest: searchRequestsList){
            offersLinksList.addAll(pageCrawler.getAllLinksByRequest(searchRequest));
        }

        return offersLinksList;
    }

    private List<String> generateListOfRequestsForKeyword(String keyword) {

        final String REQUEST_PREFIX = "https://www.aboutyou.de/suche?term=";
        RequestPostfixesEnum[] requestPostfixes = RequestPostfixesEnum.values();
        final int LIST_CAPACITY = requestPostfixes.length;

        //simple array would be more efficient, but less flexible for corrections
        ArrayList<String> requestsForKeywordList = new ArrayList<>(LIST_CAPACITY);

        for (RequestPostfixesEnum e : requestPostfixes) {
            String request = REQUEST_PREFIX + keyword + e.getRequestPostfix();
            requestsForKeywordList.add(request);
        }

        return requestsForKeywordList;
    }


}
