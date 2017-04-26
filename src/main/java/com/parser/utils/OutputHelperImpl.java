package com.parser.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Mykola Khazanovych
 *         24.04.2017;
 */
public class OutputHelperImpl implements OutputHelper {
    private static int amountOfExtractedProducts;


    @Override
    public void printOffersToFile(String keyword, List<Offer> offersList) {

        final String FILENAME = "./" + keyword + ".xml";
        File file = new File(FILENAME);

        try (FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true)) {

            this.printHeader(fileWriter);

            for (Offer offer : offersList) {
                this.printBody(fileWriter, offer);
            }

            this.printFooter(fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File " + FILENAME + " with scan results has been created!");
        System.out.println("---------------------------------------------------------");

        amountOfExtractedProducts = offersList.size();
    }

    @Override
    public void printStatistics(Long startTimeMillis) {

        System.out.println("Amount of triggered HTTP-requests: " + PageCrawlerImpl.requestsCounter);
        System.out.println("Runtime: " + (System.currentTimeMillis() - startTimeMillis) + " millis");

        Long memoryUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory used: " + memoryUsed + " bytes");
        System.out.println("Amount of extracted products: " + amountOfExtractedProducts);
    }

    private void printHeader(FileWriter fileWriter) throws IOException {
        fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        fileWriter.write("  <offers>\n");
    }

    private void printBody(FileWriter fileWriter, Offer offer) throws IOException {
        fileWriter.write("    <offer>\n");
        fileWriter.write("      <name>");
        fileWriter.write(offer.getName());
        fileWriter.write("</name>\n");
        fileWriter.write("      <brand>");
        fileWriter.write(offer.getBrand());
        fileWriter.write("</brand>\n");
        fileWriter.write("      <color>");
        fileWriter.write(offer.getColor());
        fileWriter.write("</color>\n");
        fileWriter.write("      <allColors>");
        fileWriter.write(offer.getAllColors());
        fileWriter.write("</allColors>\n");
        fileWriter.write("      <price>");
        fileWriter.write(offer.getPrice());
        fileWriter.write("</price>\n");
        fileWriter.write("      <initialPrice>");
        fileWriter.write(offer.getInitialPrice());
        fileWriter.write("</initialPrice>\n");
        fileWriter.write("      <description>");
        fileWriter.write(offer.getDescription());
        fileWriter.write("</description>\n");
        fileWriter.write("      <articleId>");
        fileWriter.write(offer.getArticleId());
        fileWriter.write("</articleId>\n");
        fileWriter.write("      <shippingCosts>");
        fileWriter.write(offer.getShippingCosts());
        fileWriter.write("</shippingCosts>\n");
        fileWriter.write("    </offer>\n");
    }

    private void printFooter(FileWriter fileWriter) throws IOException {
        fileWriter.write("  </offers>\n");
    }
}

