package t3h.resortbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import t3h.resortbooking.entities.ExtensionEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResortExtensionDto {
    private Long id;
    private Long resortId;
//    private Long extensionId;

    ExtensionEntity extensionEntity;
}
