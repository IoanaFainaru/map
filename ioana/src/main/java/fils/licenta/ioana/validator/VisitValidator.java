package fils.licenta.ioana.validator;

import fils.licenta.ioana.entity.City;
import fils.licenta.ioana.entity.User;
import fils.licenta.ioana.entity.Visit;
import fils.licenta.ioana.exception.EntityAlreadyExistsException;
import fils.licenta.ioana.exception.EntityNotFoundException;
import fils.licenta.ioana.repository.CityRepository;
import fils.licenta.ioana.repository.UserRepository;
import fils.licenta.ioana.repository.VisitRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class VisitValidator {

    public static final String CITY_DOES_NOT_EXIST_ERROR_MESSAGE = "City does not exist";
    public static final String USER_DOES_NOT_EXIST_ERROR_MESSAGE = "User does not exist";
    public static final String VISIT_ALREADY_FOUND_ERROR_MESSAGE = "User already visited ths city";
    public static final String VISIT_NOT_FOUND_ERROR_MESSAGE = "User cannot delete this visit because it did not visit it first";


    private CityRepository cityRepository;
    private UserRepository userRepository;
    private VisitRepository visitRepository;

    public VisitValidator(final CityRepository cityRepository,
                          final UserRepository userRepository,
                          final VisitRepository visitRepository) {
        this.cityRepository = Objects.requireNonNull(cityRepository, "cityRepository must not be null");
        this.userRepository = Objects.requireNonNull(userRepository, "userRepository must not be null");
        this.visitRepository = Objects.requireNonNull(visitRepository, "visitRepository must not be null");
    }

    public void validateMarkAsVisited(final Long userId, final Long cityId) {
        checkIfUserExists(userId);
        checkIfCityExists(cityId);
        checkIfUserAlreadyVotedThisCity(userId, cityId);
    }

    public void validateUnmarkAsVisited(final Long userId, final Long cityId) {
        checkIfUserExists(userId);
        checkIfCityExists(cityId);
        checkIfUserDidNotVotedThisCity(userId, cityId);
    }

    private void checkIfCityExists(final Long cityId) {
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (!cityOptional.isPresent()) {
            throw new EntityNotFoundException(CITY_DOES_NOT_EXIST_ERROR_MESSAGE);
        }
    }

    private void checkIfUserExists(final Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException(USER_DOES_NOT_EXIST_ERROR_MESSAGE);
        }
    }

    private void checkIfUserAlreadyVotedThisCity(final Long userId, final Long cityId) {
        Optional<Visit> visitOptional = visitRepository.findByUserIdAndCityId(userId, cityId);
        if (visitOptional.isPresent()) {
            throw new EntityAlreadyExistsException(VISIT_ALREADY_FOUND_ERROR_MESSAGE);
        }
    }

    private void checkIfUserDidNotVotedThisCity(final Long userId, final Long cityId) {
        Optional<Visit> visitOptional = visitRepository.findByUserIdAndCityId(userId, cityId);
        if (!visitOptional.isPresent()) {
            throw new EntityNotFoundException(VISIT_NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
