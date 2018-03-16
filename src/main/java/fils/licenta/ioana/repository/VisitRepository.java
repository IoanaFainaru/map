package fils.licenta.ioana.repository;

import fils.licenta.ioana.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Optional<Visit> findByUserIdAndCityId(final Long userId, final Long cityId);
    List<Visit> findAllByUser_Id(final Long userId);
}
