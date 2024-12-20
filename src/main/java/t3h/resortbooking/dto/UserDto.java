package t3h.resortbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    @Email(message = "Không đúng định dạng email")
    private String email;
    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;
    private String avatarImg;
    private String phone;
    private Long roleId;
    private Long genderId;

    private String oldPassword;
    private String newPassword;
    private String rePassword;


    MultipartFile fileImage;
}
