package locationapp.controllers;

import com.google.maps.errors.ApiException;
import locationapp.models.Shop;
import locationapp.models.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.GeocodingApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;
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

        GeoApiContext context = new GeoApiContext().setApiKey("apiKey");
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context, addressName).await();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(results[0].geometry.location.lat);
        System.out.println(results[0].geometry.location.lng);

        if(x.isEmpty()) {
            Shop n = new Shop();
            n.setName(name);
            n.setAddressName(addressName);
            n.setAddressLang(results[0].geometry.location.lng);
            n.setAddressLat(results[0].geometry.location.lat);
            this.shopRepository.save(n);
            return n;
        }
        else {
            Shop s = x.get(0);
            s.setAddressName(addressName);
            s.setAddressLang(results[0].geometry.location.lng);
            s.setAddressLat(results[0].geometry.location.lat);
            this.shopRepository.save(s);
            return s;
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Shop> getAllUsers() {
        return this.shopRepository.findAll();
    }

}
