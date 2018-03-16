package fils.licenta.ioana.controller;

import fils.licenta.ioana.model.CityModel;
import fils.licenta.ioana.model.VisitModel;
import fils.licenta.ioana.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(final CityService cityService) {
        this.cityService = Objects.requireNonNull(cityService, "cityService must not be null");
    }

    @GetMapping("city/all")
    public ResponseEntity<List<CityModel>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("city/user/{id}")
    public ResponseEntity<List<CityModel>> getCitiesVisitedByUser(@PathVariable(name = "id") final Long userId) {
        return ResponseEntity.ok(cityService.getCitiesVisitedByUser(userId));
    }

    @PostMapping("city")
    public ResponseEntity<CityModel> addNewCity(@RequestBody final CityModel cityModel) {
        //Cine adauga orase?
        return ResponseEntity.ok(cityService.addCity(cityModel));
    }

    @PostMapping("/user/{userId}/city/{cityId}")
    public ResponseEntity<VisitModel> userVisitsCity(@PathVariable(name = "userId") final Long userId,
                                                     @PathVariable(name = "cityId") final Long cityId) {
        return ResponseEntity.ok(cityService.markAsVisited(userId, cityId));
    }

    @DeleteMapping("/user/{userId}/city/{cityId}")
    public ResponseEntity<String> userDeletesVisit(@PathVariable(name = "userId") final Long userId,
                                                   @PathVariable(name = "cityId") final Long cityId) {
        cityService.unmarkAsVisited(userId, cityId);
        return ResponseEntity.ok("Visit deleted");
    }

    @GetMapping("city/top10")
    public ResponseEntity<List<CityModel>> getTop10Cities() {
        return ResponseEntity.ok(cityService.getTop10Cities());
    }

}
