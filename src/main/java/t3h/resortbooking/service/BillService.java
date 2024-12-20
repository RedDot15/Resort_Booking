package t3h.resortbooking.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t3h.resortbooking.dto.BillDto;
import t3h.resortbooking.entities.BillEntity;
import t3h.resortbooking.entities.RoomEntity;
import t3h.resortbooking.repository.BillRepository;
import t3h.resortbooking.repository.RoomRepository;
import t3h.resortbooking.repository.VerificationTokenRepository;

import java.util.List;

@Service
@Transactional
public class BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public List<BillEntity> getAll(){
        return billRepository.findAll();
    }

    public void updateBill(Long id){
        BillEntity billEntity = billRepository.getBillEntityById(id);
        RoomEntity roomEntity = roomRepository.findFirstById(billEntity.getRoomEntity().getId());
        roomEntity.setStatus("free");
        roomRepository.save(roomEntity);
        billEntity.setStatusId((long) 3);
        billRepository.save(billEntity);
    }

    public BillEntity add(BillDto billDto){
        BillEntity billEntity = new BillEntity();
        BeanUtils.copyProperties(billDto,billEntity);
        billRepository.save(billEntity);
        billDto.setId(billEntity.getId());
        return billEntity;
    }

}
