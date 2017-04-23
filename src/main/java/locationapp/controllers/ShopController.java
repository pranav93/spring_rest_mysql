package locationapp.controllers;

import locationapp.business.ShopBusiness;
import locationapp.models.Shop;
import locationapp.requestObjects.ShopRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pranav on 13/4/17.
 */


@Controller
@RequestMapping(path = "/shops")
public class ShopController {
    @Autowired
    ShopBusiness shopService;

    /**
     * Api to add new shop
     *
     * @param shopRequestBody ShopRequestBody with name and address name
     * @return Shop the shop that is added
     */
    @PostMapping(path = "/add")
    public @ResponseBody
    Shop addNewShop(@RequestBody ShopRequestBody shopRequestBody) {
        return shopService.createOrUpdateShop(shopRequestBody.name, shopRequestBody.addressName);
    }

    /**
     * Gets all the shops
     *
     * @return List<Shop> shop list
     */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Shop> getAllShops() {
        return this.shopService.getAllShops();
    }

    /**
     * This api returns the nearest shop
     *
     * @param selectedLat  the latitude given by user
     * @param selectedLong the longitude given by user
     * @return Shop the nearest shop
     */
    @GetMapping(path = "/closest")
    public @ResponseBody
    Iterable<Shop> getClosest(@RequestParam Double selectedLat, @RequestParam Double selectedLong) {
        return this.shopService.findClosest(selectedLat, selectedLong);
    }

}
