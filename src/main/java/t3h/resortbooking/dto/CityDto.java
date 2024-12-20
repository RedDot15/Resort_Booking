package t3h.resortbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import t3h.resortbooking.entities.ResortEntity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private Long id;
    private Long countryId;
    private String name;
    private String imageName;

    MultipartFile fileImage;

    List<ResortEntity> resortEntityList;
}


