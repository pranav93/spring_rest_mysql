package locationapp.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The model manager for model Shop
 */
public interface ShopRepository extends CrudRepository<Shop, Long> {

    String closestShopQuery = "select * from shop " +
            "order by (6371 * acos(cos(radians(?1)) * cos(radians(address_lat)) * cos(radians(address_long)" +
            " - radians(?2)) + sin(radians(?1)) * sin(radians(address_lat)))) asc limit 1";

    /**
     * It gets all shops by a name
     * @param name name of the shop
     * @return List<Shop> All shops with given name
     */
    @Query("select p from Shop p where p.name = :name")
    List<Shop> getShopByName(
            @Param("name") String name
    );

    /**
     * Query to get the nearest shop
     * @param selectedLat the latitude by user
     * @param selectedLong the longitude by user
     * @return List<Shop> the list of a single shop which is nearest
     */
    @Query(value=closestShopQuery, nativeQuery=true)
    List<Shop> getClosestShop(Double selectedLat, Double selectedLong);

}
