package t3h.resortbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t3h.resortbooking.entities.VerificationTokenEntity;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity,Long> {
    public VerificationTokenEntity findVerificationTokenEntityByToken(String verificationToken);
}
