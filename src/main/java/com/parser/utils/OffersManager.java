package com.parser.utils;

import com.parser.entities.Offer;

import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 *         11:02;
 *         PACKAGE_NAME;
 */
public interface OffersManager {
    List<Offer> getOffersList(String keyword);
}
