package fils.licenta.ioana.mapper;

import fils.licenta.ioana.entity.Visit;
import fils.licenta.ioana.model.VisitModel;

public final class VisitMapper {

    private VisitMapper() {

    }

    public static VisitModel toModel(final Visit entity) {
        return VisitModel.builder()
                .id(entity.getId())
                .cityId(entity.getCity().getId())
                .userId(entity.getUser().getId())
                .build();
    }
}
