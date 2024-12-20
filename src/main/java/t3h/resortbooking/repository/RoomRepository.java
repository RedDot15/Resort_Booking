package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.RoomEntity;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    List<RoomEntity> findAllByName(String name);

    List<RoomEntity> findAllByResortId(Long id);

    RoomEntity findFirstById(Long id);

    @Modifying
    void deleteRoomEntityById(Long id);
}
