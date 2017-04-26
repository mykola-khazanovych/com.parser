import java.util.List;

/**
 * @author Mykola Khazanovych
 *         22.04.2017;
 *         11:02;
 *         PACKAGE_NAME;
 */
public interface OffersManager {
    List<Offer> getOffersList(String keyword);
    public Offer getOfferAttributes(String offerLink);//TODO remove it and make method private
}
