package locationapp.business;

import com.google.maps.model.Geometry;
import locationapp.Utils.GeoApi;
import locationapp.models.Shop;
import locationapp.models.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pranav on 15/4/17.
 */
@Service
public class ShopBusiness {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private GeoApi geoApi;

    /**
     * Methos that creates a new shop, or updates it if it exists
     *
     * @param name        the name of the shop
     * @param addressName the address line of the shop
     * @return Shop the shop that is added or updated
     */
    public Shop createOrUpdateShop(String name, String addressName) {
        List<Shop> x;

        x = this.shopRepository.getShopByName(name);

        Geometry addressGeometry = this.geoApi.getGeoCodes(addressName);

        if (x.isEmpty()) {
            Shop n = new Shop();
            n.setName(name);
            n.setAddressName(addressName);
            n.setAddressLong(addressGeometry.location.lng);
            n.setAddressLat(addressGeometry.location.lat);
            this.shopRepository.save(n);
            return n;
        } else {
            Shop s = x.get(0);
            s.setAddressName(addressName);
            s.setAddressLong(addressGeometry.location.lng);
            s.setAddressLat(addressGeometry.location.lat);
            this.shopRepository.save(s);
            return s;
        }
    }

    /**
     * Method to get all the shops
     *
     * @return Iterable<Shop> shop list
     */
    public Iterable<Shop> getAllShops() {
        return this.shopRepository.findAll();
    }

    /**
     * This methods returns the nearest shop
     *
     * @param selectedLat  the latitude given by user
     * @param selectedLong the longitude given by user
     * @return <Iterable>Shop the nearest shop list
     */
    public Iterable<Shop> findClosest(Double selectedLat, Double selectedLong) {
        return this.shopRepository.getClosestShop(selectedLat, selectedLong);
    }

}
