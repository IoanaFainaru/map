package fils.licenta.ioana.controller

import fils.licenta.ioana.model.CityModel
import fils.licenta.ioana.model.VisitModel
import fils.licenta.ioana.service.CityService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CityControllerSpec extends Specification {
    def cityService = Mock(CityService)
    def cityController = new CityController(cityService)

    def 'getAllCities'() {
        given:
        def list = [new CityModel()]

        when:
        def result = cityController.getAllCities()

        then:
        1 * cityService.getAllCities() >> list
        result == ResponseEntity.ok(list)
    }

    def 'getCitiesVisitedByUser'() {
        given:
        def list = [new CityModel()]
        def userId = 1L

        when:
        def result = cityController.getCitiesVisitedByUser(userId)

        then:
        1 * cityService.getCitiesVisitedByUser(userId) >> list
        result == ResponseEntity.ok(list)
    }

    def 'addNewCity'() {
        given:
        def cityModel = new CityModel()


        when:
        def result = cityController.addNewCity(cityModel)

        then:
        1 * cityService.addCity(cityModel) >> cityModel
        result == ResponseEntity.ok(cityModel)
    }

    def 'userVisitsCity'() {
        given:
        def userId = 1L
        def cityId = 1L
        def visitModel = new VisitModel()

        when:
        def result = cityController.userVisitsCity(userId, cityId)

        then:
        1 * cityService.markAsVisited(userId, cityId) >> visitModel
        result == ResponseEntity.ok(visitModel)
    }

    def 'userDeletesVisit'() {
        given:
        def userId = 1L
        def cityId = 1L

        when:
        def result = cityController.userDeletesVisit(userId, cityId)

        then:
        1 * cityService.unmarkAsVisited(userId, cityId)
        result == ResponseEntity.ok("Visit deleted")
    }

    def 'getTop10Cities'() {
        given:
        def list = [new CityModel()]

        when:
        def result = cityController.getTop10Cities()

        then:
        1 * cityService.getTop10Cities() >> list
        result == ResponseEntity.ok(list)
    }
}
