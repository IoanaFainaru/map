package fils.licenta.ioana.service;

import fils.licenta.ioana.entity.City;
import fils.licenta.ioana.entity.User;
import fils.licenta.ioana.entity.Visit;
import fils.licenta.ioana.mapper.CityMapper;
import fils.licenta.ioana.mapper.VisitMapper;
import fils.licenta.ioana.model.CityModel;
import fils.licenta.ioana.model.VisitModel;
import fils.licenta.ioana.repository.CityRepository;
import fils.licenta.ioana.repository.VisitRepository;
import fils.licenta.ioana.validator.VisitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static fils.licenta.ioana.mapper.CityMapper.toModel;

@Service
public class CityService {
    private CityRepository cityRepository;
    private VisitRepository visitRepository;
    private VisitValidator visitValidator;

    @Autowired
    public CityService(final CityRepository cityRepository,
                       final VisitRepository visitRepository,
                       final VisitValidator visitValidator) {
        this.cityRepository = Objects.requireNonNull(cityRepository, "cityRepository must not be null");
        this.visitRepository = Objects.requireNonNull(visitRepository, "visitRepository must not be null");
        this.visitValidator = Objects.requireNonNull(visitValidator, "visitValidator must not be null");
    }

    public List<CityModel> getAllCities() {
        return cityRepository.findAll().stream()
                .map(CityMapper::toModel)
                .collect(Collectors.toList());
    }

    public List<CityModel> getCitiesVisitedByUser(final Long userId) {
        return visitRepository.findAllByUser_Id(userId).stream()
                .map(visit -> CityMapper.toModel(visit.getCity()))
                .collect(Collectors.toList());
    }

    public CityModel addCity(final CityModel cityModel) {
        final City city = CityMapper.toEntity(cityModel);
        return toModel(cityRepository.save(city));
    }

    public VisitModel markAsVisited(final Long userId, final Long cityId) {
        visitValidator.validateMarkAsVisited(userId, cityId);
        final Visit visitObjectToSave = Visit.builder()
                .city(City.builder().id(cityId).build())
                .user(User.builder().id(userId).build())
                .build();
        return VisitMapper.toModel(visitRepository.save(visitObjectToSave));
    }

    public void unmarkAsVisited(final Long userId, final Long cityId) {
        visitValidator.validateUnmarkAsVisited(userId, cityId);
        final Optional<Visit> visitObjectToDelete = visitRepository.findByUserIdAndCityId(userId, cityId);
        visitRepository.delete(visitObjectToDelete.get());
    }

    public List<CityModel> getTop10Cities() {
        return cityRepository.findTop10Cities().stream()
                .map(CityMapper::toModel)
                .collect(Collectors.toList());
    }
}
