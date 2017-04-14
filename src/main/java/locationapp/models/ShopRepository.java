package locationapp.models;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Long> {
    @Modifying
    @Transactional
    @Query("update Shop p set p.addressLang= :addressLang, p.addressLat= :addressLat, p.addressName= :addressName where p.name = :name")
    Integer setNewDescriptionForProduct(
            @Param("addressLang") Double addressLang,
            @Param("addressLat") Double addressLat,
            @Param("addressName") String addressName,
            @Param("name") String name
    );

    @Query("select p from Shop p where p.name = :name")
    List<Shop> getShopByName(
            @Param("name") String name
    );

}
