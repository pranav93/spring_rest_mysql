package locationapp.controllers;

import locationapp.business.ShopBusiness;
import locationapp.models.Shop;
import locationapp.models.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        ShopBusiness sb = new ShopBusiness(shopRepository);
        Shop s = sb.createOrUpdateShop(name, addressName);
        return s;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Shop> getAllUsers() {
        return this.shopRepository.findAll();
    }

}
