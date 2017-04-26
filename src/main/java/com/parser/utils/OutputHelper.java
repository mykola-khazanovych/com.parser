package com.parser.utils;

import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 */
public interface OutputHelper {
    void printOffersToFile(String keyword, List<Offer> offersList);
    void printStatistics(Long startTimeMillis);
}
