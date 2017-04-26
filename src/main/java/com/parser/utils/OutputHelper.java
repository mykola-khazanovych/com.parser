package com.parser.utils;

import com.parser.entities.Offer;

import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 */
public interface OutputHelper {
    void printOffersToFile(String keyword, List<Offer> offersList);
    void printStatistics(Long startTimeMillis);
}
