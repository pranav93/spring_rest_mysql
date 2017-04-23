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

import static junit.framework.TestCase.assertEquals;

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

        Shop s = shopService.createOrUpdateShop("dmart", "dmart");
        assertEquals("dmart", s.getName());
        assertEquals("dmart", s.getAddressName());
        assertEquals(10.00, s.getAddressLat());
        assertEquals(20.00, s.getAddressLong());
    }
}