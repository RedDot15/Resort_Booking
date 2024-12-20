package t3h.resortbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import t3h.resortbooking.entities.ResortimageEntity;
import t3h.resortbooking.entities.RoomEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResortDto {
    private Long id;
    private Long cityId;
    private String name;
    private String infoHTML;
    private String infoMarkDown;
    private Integer point;
    private Float lat;
    private Float lng;


    private ArrayList<Long> extensionListId;

    private List<MultipartFile> multipartFileList;

    List<ResortimageEntity> resortimageEntityList;

    List<RoomEntity> roomEntityList;
}
