package fils.licenta.ioana.mapper;

import fils.licenta.ioana.entity.City;
import fils.licenta.ioana.model.CityModel;

public final class CityMapper {

    private CityMapper() {

    }

    public static City toEntity(final CityModel cityModel) {
        return City.builder()
                .id(cityModel.getId())
                .name(cityModel.getName())
                .description(cityModel.getDescription())
                .build();
    }

    public static CityModel toModel(final City city) {
        return CityModel.builder()
                .id(city.getId())
                .name(city.getName())
                .description(city.getDescription())
                .build();
    }
}
