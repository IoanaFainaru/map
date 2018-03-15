package fils.licenta.ioana.service

import fils.licenta.ioana.entity.City
import fils.licenta.ioana.entity.User
import fils.licenta.ioana.entity.Visit
import fils.licenta.ioana.model.CityModel
import fils.licenta.ioana.repository.CityRepository
import fils.licenta.ioana.repository.VisitRepository
import fils.licenta.ioana.validator.VisitValidator
import spock.lang.Specification

class CityServiceSpec extends Specification {
    def cityRepository = Mock(CityRepository)
    def visitRepository = Mock(VisitRepository)
    def visitValidator = Mock(VisitValidator)
    def cityService = new CityService(cityRepository, visitRepository, visitValidator)

    def 'getAllCities'() {
        given:
        def list = [new City()]

        when:
        def result = cityService.getAllCities()

        then:
        1 * cityRepository.findAll() >> list
        result == [new CityModel()]
    }

    def 'getCitiesVisitedByUser'() {
        given:
        def userId = 1L
        def list = [new Visit(1, new User(),  new City())]

        when:
        def result = cityService.getCitiesVisitedByUser(userId)

        then:
        1 * visitRepository.findAllByUser_Id(userId) >> list
        result == [new CityModel()]
    }

    def 'addCity'() {
        given:
        def cityModel = new CityModel()
        def city = new City()

        when:
        def result = cityService.addCity(cityModel)

        then:
        1 * cityRepository.save(city) >> city
        result == cityModel
    }

    def 'getTop10Cities'() {
        given:
        def list = [new City()]

        when:
        def result = cityService.getTop10Cities()

        then:
        1 * cityRepository.findTop10Cities() >> list
        result == [new CityModel()]
    }
}
