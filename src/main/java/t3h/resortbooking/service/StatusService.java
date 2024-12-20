package t3h.resortbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t3h.resortbooking.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public void delete(Long id) {
        statusRepository.deleteStatusEntityById(id);
    }
}
