package com.parser.utils;

import org.jsoup.nodes.Document;

import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 *         10:55;
 *         PACKAGE_NAME;
 */
public interface PageCrawler {
    List<String> getAllLinksByRequest(String searchRequest);
    Document getPageWithOffersByRequest(String searchRequest);
}
