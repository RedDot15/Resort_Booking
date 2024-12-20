package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import t3h.resortbooking.entities.CountryEntity;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    List<CountryEntity> findAllByName(String name);

    CountryEntity findFirstById(Long id);

    @Modifying
    void deleteCountryEntityById(Long id);
}
