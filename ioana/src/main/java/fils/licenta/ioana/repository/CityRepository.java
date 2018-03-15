package fils.licenta.ioana.repository;

import fils.licenta.ioana.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value = "SELECT c.id, c.name, c.description\n" +
            "FROM cities c INNER JOIN\n" +
            "  (SELECT v.city_id, COUNT(*) AS size\n" +
            "   FROM visits v\n" +
            "   GROUP BY v.city_id\n" +
            "   LIMIT 10) AS v\n" +
            "    ON c.id = v.city_id\n" +
            "ORDER BY v.size DESC", nativeQuery = true)
    List<City> findTop10Cities();
}
