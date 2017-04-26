package com.parser.entities;

import lombok.*;

/**
 * @author Mykola Khazanovych
 *         23.04.2017;
 */

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Offer {
    private String name;
    private String brand;
    private String color;
    private String allColors;
    private String price;
    private String initialPrice;
    private String description;
    private String articleId;
    private String shippingCosts;

    @Override
    public String toString() {
        return "com.parser.entities.Offer{" + "\n" +
                "name='" + name + '\'' + "\n" +
                "brand='" + brand + '\'' + "\n" +
                "color='" + color + '\'' + "\n" +
                "allColors='" + allColors + '\'' + "\n" +
                "price='" + price + '\'' + "\n" +
                "initialPrice='" + initialPrice + '\'' + "\n" +
                "description='" + description + '\'' + "\n" +
                "articleId='" + articleId + '\'' + "\n" +
                "shippingCosts='" + shippingCosts + '\'' +
                '}';
    }
}
