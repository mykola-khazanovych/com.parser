import com.google.inject.Injector;

import java.util.List;

/**
 * @author Mykola Khazanovych
 *         24.04.2017;
 */

public class Main {
    static long startTimeMillis;

    public static void main(String[] args) {

        startTimeMillis = System.currentTimeMillis();

        String keyword = args[0];
        System.out.println("Searching site for '" + keyword + "' please wait (it takes apr. 7 min for 2000 products scan)...");

        Injector injector = InjectorBuilder.getInjector();
        OffersManager offersManager = injector.getInstance(OffersManager.class);
        OutputHelper helper = injector.getInstance(OutputHelper.class);

        List<Offer> offersList = offersManager.getOffersList(keyword);

        helper.printOffersToFile(keyword, offersList);
    }
}
