package locationapp.controllers;

import locationapp.Application;
import locationapp.requestObjects.ShopRequestBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Josh Long
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@WebAppConfiguration
/**
 * Created by pranav on 24/4/17.
 */
public class ShopControllersTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createOrUpdateShop() throws Exception {
        ShopRequestBody shopRequestBody = new ShopRequestBody();
        shopRequestBody.name = "White House";
        shopRequestBody.addressName = "1600 Pennsylvania Ave NW, Washington, DC 20500, USA";
        String shopJson = json(shopRequestBody);

        this.mockMvc.perform(post("/shops/add")
                .contentType(contentType)
                .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("White House")))
                .andExpect(jsonPath("$.addressName",
                        is("1600 Pennsylvania Ave NW, Washington, DC 20500, USA")))
                .andExpect(jsonPath("$.addressLong", is(-77.0364827)))
                .andExpect(jsonPath("$.addressLat", is(38.89767579999999)));

    }

    @Test
    public void getClosestShop() throws Exception {
        ShopRequestBody shopRequestBody = new ShopRequestBody();
        shopRequestBody.name = "White House";
        shopRequestBody.addressName = "1600 Pennsylvania Ave NW, Washington, DC 20500, USA";
        String shopJson = json(shopRequestBody);

        this.mockMvc.perform(post("/shops/add")
                .contentType(contentType)
                .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("White House")))
                .andExpect(jsonPath("$.addressName",
                        is("1600 Pennsylvania Ave NW, Washington, DC 20500, USA")))
                .andExpect(jsonPath("$.addressLong", is(-77.0364827)))
                .andExpect(jsonPath("$.addressLat", is(38.89767579999999)));

        shopRequestBody.name = "Taj Mahal";
        shopRequestBody.addressName = "Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001";
        shopJson = json(shopRequestBody);

        this.mockMvc.perform(post("/shops/add")
                .contentType(contentType)
                .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is("Taj Mahal")))
                .andExpect(jsonPath("$.addressName",
                        is("Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001")))
                .andExpect(jsonPath("$.addressLong", is(78.0452217)))
                .andExpect(jsonPath("$.addressLat", is(27.1738047)));

        this.mockMvc.perform(get("/shops/closest?selectedLat=27.0999&selectedLong=78")
                .contentType(contentType)
                .content(shopJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].name", is("Taj Mahal")))
                .andExpect(jsonPath("$[0].addressName",
                        is("Dharmapuri, Forest Colony, Tajganj, Agra, Uttar Pradesh 282001")))
                .andExpect(jsonPath("$[0].addressLong", is(78.0452217)))
                .andExpect(jsonPath("$[0].addressLat", is(27.1738047)));

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
