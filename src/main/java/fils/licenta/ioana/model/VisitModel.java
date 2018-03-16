package fils.licenta.ioana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitModel {
    private Long id;
    private Long userId;
    private Long cityId;
}
