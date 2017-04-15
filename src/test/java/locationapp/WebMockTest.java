package locationapp;

/**
 * Created by pranav on 15/4/17.
 */

import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import locationapp.Utils.GeoApi;
import locationapp.business.ShopBusiness;
import locationapp.controllers.ShopController;
import locationapp.models.Shop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class WebMockTest {

    // TODO: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type
    // TODO: 'locationapp.business.ShopBusiness' available:
    @Autowired
    private ShopBusiness service;

    @Autowired
    private ShopController sCtrl;

    @Before
    public void setup() {

        GeoApi geoApiMock = org.mockito.Mockito.mock(GeoApi.class);

        Double x = 10.0000;
        Double y = 20.0000;

        Geometry addressGeometry = new Geometry();
        addressGeometry.location = new LatLng(x, y);

        Mockito.when(geoApiMock .getGeoCodes("dmart")).thenReturn(addressGeometry);

    }

    @Test
    public void createShouldReturnShopFromService() throws Exception {

        Shop shop = new Shop();

        shop.setId(1);
        shop.setName("dmart");
        shop.setAddressName("dmart");
        shop.setAddressLat(10.00);
        shop.setAddressLong(20.00);

        Shop s = service.createOrUpdateShop("dmart", "dmart");
        assertNotNull("Person list is null.", s);
    }
}
