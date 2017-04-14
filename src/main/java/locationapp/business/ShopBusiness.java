package locationapp.business;

import com.google.maps.model.Geometry;
import locationapp.Utils.GeoApi;
import locationapp.models.Shop;
import locationapp.models.ShopRepository;

import java.util.List;

/**
 * Created by pranav on 15/4/17.
 */
public class ShopBusiness {
    private ShopRepository shopRepository;

    public ShopBusiness(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public Shop createOrUpdateShop (String name, String addressName) {
        List<Shop> x;

        x = this.shopRepository.getShopByName(name);

        GeoApi geoApi = new GeoApi();
        Geometry addressGeometry = geoApi.getGeoCodes(addressName);

        if(x.isEmpty()) {
            Shop n = new Shop();
            n.setName(name);
            n.setAddressName(addressName);
            n.setAddressLang(addressGeometry.location.lng);
            n.setAddressLat(addressGeometry.location.lat);
            this.shopRepository.save(n);
            return n;
        }
        else {
            Shop s = x.get(0);
            s.setAddressName(addressName);
            s.setAddressLang(addressGeometry.location.lng);
            s.setAddressLat(addressGeometry.location.lat);
            this.shopRepository.save(s);
            return s;
        }
    }

}
