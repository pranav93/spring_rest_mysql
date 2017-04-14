package locationapp.controllers;

import com.google.maps.model.Geometry;
import locationapp.Utils.GeoApi;
import locationapp.models.Shop;
import locationapp.models.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by pranav on 13/4/17.
 */
@Controller
@RequestMapping(path="/shops")
public class ShopController {
    @Autowired
    private ShopRepository shopRepository;

    @GetMapping(path="/add")
    public @ResponseBody Shop addNewShop (@RequestParam String name, @RequestParam String addressName) {
        List<Shop> x;


        x = shopRepository.getShopByName(name);

        System.out.println(x);

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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Shop> getAllUsers() {
        return this.shopRepository.findAll();
    }

}
