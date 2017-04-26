package com.parser.utils;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 *         8:52;
 *         PACKAGE_NAME;
 */
public enum RequestPostfixesEnum {


    GENDER_REQUEST_POSTFIX_WOMEN("&category=20201"),
    GENDER_REQUEST_POSTFIX_MEN("&category=20202"),
    GENDER_REQUEST_POSTFIX_CHILDREN("&category=138113");

    String url_fragment;

    RequestPostfixesEnum(String url_fragment){this.url_fragment = url_fragment;}

    public String getRequestPostfix(){
        return url_fragment;
    }
}
