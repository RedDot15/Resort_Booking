package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.UserEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByEmail(String email);

    UserEntity findFirstByPhone(String phone);

    UserEntity findFirstByEmail(String email);

    @Modifying
    void deleteUserEntityById(Long id);
}
