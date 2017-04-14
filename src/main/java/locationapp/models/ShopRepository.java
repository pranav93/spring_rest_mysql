package locationapp.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends CrudRepository<Shop, Long> {

    @Query("select p from Shop p where p.name = :name")
    List<Shop> getShopByName(
            @Param("name") String name
    );

    @Query(value="select id, name from shop order by (6371 * acos(cos(radians(?1)) * cos(radians(address_lat)) * cos(radians(address_long) - radians(?2)) + sin(radians(?1)) * sin(radians(address_lat)))) desc", nativeQuery=true)
    List<Object[]> getClosestShop(Double selectedLat, Double selectedLong);

}
