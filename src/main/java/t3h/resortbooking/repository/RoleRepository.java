package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.RoleEntity;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAllByName(String name);

    RoleEntity findFirstById(Long id);

    @Modifying
    void deleteRoleEntityById(Long id);
}
