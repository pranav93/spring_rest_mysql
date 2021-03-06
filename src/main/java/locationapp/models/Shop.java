package locationapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by pranav on 13/4/17.
 */

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String addressName;

    private Double addressLong;

    private Double addressLat;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Double getAddressLong() {
        return this.addressLong;
    }

    public void setAddressLong(Double addressLong) {
        this.addressLong = addressLong;
    }

    public Double getAddressLat() {
        return this.addressLat;
    }

    public void setAddressLat(Double addressLat) {
        this.addressLat = addressLat;
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object obj) {
        Shop shop = (Shop) obj;
        boolean status = false;
        if (this.name.equalsIgnoreCase(shop.name)
                && Objects.equals(this.id, shop.id)
                && Objects.equals(this.addressLat, shop.addressLat)
                && Objects.equals(this.addressLong, shop.addressLong)
                && Objects.equals(this.addressName, shop.addressName)) {
            status = true;
        }
        return status;
    }

}
