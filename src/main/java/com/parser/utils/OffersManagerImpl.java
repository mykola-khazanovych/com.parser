package com.parser.utils;

import com.google.inject.Inject;
import com.parser.entities.Offer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 */
public class OffersManagerImpl implements OffersManager {
    private OffersLinksListBuilder offersLinksListBuilder;
    private PageCrawler pageCrawler;

    @Inject
    public OffersManagerImpl(OffersLinksListBuilder offersLinksListBuilder, PageCrawler pageCrawler) {
        this.offersLinksListBuilder = offersLinksListBuilder;
        this.pageCrawler = pageCrawler;
    }

    @Override
    public List<Offer> getOffersList(String keyword) {

        List<Offer> offersList = new ArrayList<>();

        List<String> offersLinksList = offersLinksListBuilder.getList(keyword);
        for (String link : offersLinksList) {
            offersList.add(getOfferAttributes(link));
        }

        return offersList;
    }

    private Offer getOfferAttributes(String offerLink) {

        Offer offer = new Offer();
        Document offerPage = pageCrawler.getPageWithOffersByRequest(offerLink);


        Elements offerAttributes = offerPage.getElementsByTag("head");
        offer.setName(offerAttributes.tagName("title").text());

        offerAttributes = offerPage.getElementsByTag("meta");
        double priceD = 0;
        //this attribute extraction module ought to be more elegant :)
        for (Element e : offerAttributes) {
            if (e.toString().contains("og:brand")) {
                //not-elegant way to get info from form like <meta property="og:brand" content="NIKE" />
                offer.setBrand(e.toString().split("\"")[3]);
                continue;
            }
            if (e.toString().contains("product:color")) {
                offer.setColor(e.toString().split("\"")[3]);
                continue;
            }
            if (e.toString().contains("og:price:amount")) {
                String price = e.toString().split("\"")[3];
                offer.setPrice(price);
                priceD = Double.valueOf(price);//we need price value to calculate if SHIPPING could be FREE
                continue;
            }
            if (e.toString().contains("og:price:standard_amount")) {
                offer.setInitialPrice(e.toString().split("\"")[3]);
                continue;
            }
            if (e.toString().contains("og:description")) {
                offer.setDescription(e.toString().split("\"")[3]);
                continue;
            }
            if (e.toString().contains("og:isbn")) {
                offer.setArticleId(e.toString().split("\"")[3]);
                continue;
            }
            if (e.toString().contains("og:isbn")) {
                offer.setArticleId(e.toString().split("\"")[3]);
            }
        }
        if ((long) priceD >= 50) {
            offer.setShippingCosts("Shipping costs: free!");
        } else {
            offer.setShippingCosts("In accordance with shipping service you choose.");
        }

        offer.setAllColors(this.getAllColorsForThisOffer(offerPage));

        return offer;
    }

    private String getAllColorsForThisOffer(Document offerPage){

        final String CLASS_NAME_TO_SELECT_COLOR_ELEMENT_IN_HTML =
                "js-adp-stylepickerImage btn btnSecondary btnSecondary--grey V3adp__btnSelector btn-square";
        Elements offersOnPage = offerPage.getElementsByClass(CLASS_NAME_TO_SELECT_COLOR_ELEMENT_IN_HTML);

        String allColors = "";
        for (Element offer : offersOnPage) {
            //very short loops can use simple concatenation
            allColors += offer.getElementsByTag("a").attr("title");
            allColors += " || ";
        }

        final String CLASS_NAME_TO_SELECT_ACTIVE_COLOR_ELEMENT_IN_HTML =
                "js-adp-stylepickerImage btn btnSecondary btnSecondary--grey " +
                        "V3adp__btnSelector btn-square adp-stylepicker-selected is-active";
        offersOnPage = offerPage.getElementsByClass(CLASS_NAME_TO_SELECT_ACTIVE_COLOR_ELEMENT_IN_HTML);

        for (Element offer : offersOnPage) {
            allColors += offer.getElementsByTag("a").attr("title");
        }

        return allColors;
    }
}
