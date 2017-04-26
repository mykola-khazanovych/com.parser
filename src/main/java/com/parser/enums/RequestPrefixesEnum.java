package com.parser.enums;

/**
 * @author Mykola Khazanovych
 *         27.04.2017;
 *         0:05;
 *         com.parser.enums;
 */
public enum RequestPrefixesEnum {
    SEARCH_REQUEST_PREFIX("https://www.aboutyou.de");

    String url_fragment;

    RequestPrefixesEnum(String url_fragment){this.url_fragment = url_fragment;}

    public String getRequestPrefix(){
        return url_fragment;
    }
}
