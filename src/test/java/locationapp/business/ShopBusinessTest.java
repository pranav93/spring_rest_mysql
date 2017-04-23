package locationapp.business;

import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import locationapp.Utils.GeoApi;
import locationapp.models.Shop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class ShopBusinessTest {

    @Autowired
    @InjectMocks
    private ShopBusiness shopService;

    @Mock
    private GeoApi geoApi;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createShouldReturnShopFromService() throws Exception {
        Double x = 10.0000;
        Double y = 20.0000;

        Geometry addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApi.getGeoCodes("dmart")).thenReturn(addressGeometry);


        Shop shop = new Shop();

        shop.setId(1);
        shop.setName("dmart");
        shop.setAddressName("dmart");
        shop.setAddressLat(10.00);
        shop.setAddressLong(20.00);

        Shop shop_dmart = shopService.createOrUpdateShop("dmart", "dmart");
        assertEquals(new Integer(1), shop_dmart.getId());
        assertEquals("dmart", shop_dmart.getName());
        assertEquals("dmart", shop_dmart.getAddressName());
        assertEquals(10.00, shop_dmart.getAddressLat());
        assertEquals(20.00, shop_dmart.getAddressLong());

        x = 20.0000;
        y = 30.0000;

        addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApi.getGeoCodes("shoppers")).thenReturn(addressGeometry);

        shop.setId(1);
        shop.setName("dmart");
        shop.setAddressName("shoppers");
        shop.setAddressLat(20.00);
        shop.setAddressLong(30.00);

        Shop shop_shoppers = shopService.createOrUpdateShop("dmart", "shoppers");
        assertEquals(new Integer(1), shop_shoppers.getId());
        assertEquals("dmart", shop_shoppers.getName());
        assertEquals("shoppers", shop_shoppers.getAddressName());
        assertEquals(20.00, shop_shoppers.getAddressLat());
        assertEquals(30.00, shop_shoppers.getAddressLong());

        x = 30.0000;
        y = 40.0000;

        addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApi.getGeoCodes("shoppers")).thenReturn(addressGeometry);

        shop.setId(1);
        shop.setName("shoppers");
        shop.setAddressName("shoppers");
        shop.setAddressLat(30.00);
        shop.setAddressLong(40.00);

        Shop shop_shoppers_new = shopService.createOrUpdateShop("shoppers", "shoppers");
        assertEquals(new Integer(2), shop_shoppers_new.getId());
        assertEquals("shoppers", shop_shoppers_new.getName());
        assertEquals("shoppers", shop_shoppers_new.getAddressName());
        assertEquals(30.00, shop_shoppers_new.getAddressLat());
        assertEquals(40.00, shop_shoppers_new.getAddressLong());
    }
    @Test
    public void getAllShopsShouldReturnShopFromService() throws Exception {
        Double x = 10.0000;
        Double y = 20.0000;

        Geometry addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApi.getGeoCodes("dmart")).thenReturn(addressGeometry);


        Shop shop = new Shop();

        shop.setId(1);
        shop.setName("dmart");
        shop.setAddressName("dmart");
        shop.setAddressLat(10.00);
        shop.setAddressLong(20.00);

        Shop shop_dmart = shopService.createOrUpdateShop("dmart", "dmart");

        x = 20.0000;
        y = 30.0000;

        addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApi.getGeoCodes("shoppers")).thenReturn(addressGeometry);

        Shop shop2 = new Shop();
        shop2.setId(2);
        shop2.setName("shoppers");
        shop2.setAddressName("shoppers");
        shop2.setAddressLat(20.00);
        shop2.setAddressLong(30.00);

        Shop shop_shoppers = shopService.createOrUpdateShop("shoppers", "shoppers");

        Iterable<Shop> shopIterable;
        List<Shop> shopList = new ArrayList<Shop>();
        List<Shop> expectedShopList = new ArrayList<Shop>();

        shopIterable = shopService.getAllShops();

        shopIterable.forEach(shopList::add);

        expectedShopList.add(shop);
        expectedShopList.add(shop2);

        assertArrayEquals(expectedShopList.toArray(), shopList.toArray());
    }
}