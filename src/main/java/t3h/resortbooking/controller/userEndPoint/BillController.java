package t3h.resortbooking.controller.userEndPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import t3h.resortbooking.dto.BillDto;
import t3h.resortbooking.dto.ResortDto;
import t3h.resortbooking.dto.ResortImageDto;
import t3h.resortbooking.dto.RoomDto;
import t3h.resortbooking.entities.BillEntity;
import t3h.resortbooking.entities.RoomEntity;
import t3h.resortbooking.repository.BillRepository;
import t3h.resortbooking.repository.RoomRepository;
import t3h.resortbooking.service.BillService;
import t3h.resortbooking.service.ResortImageService;
import t3h.resortbooking.service.ResortService;
import t3h.resortbooking.service.RoomService;

import javax.validation.Valid;

@Controller
public class BillController {

    @Autowired
    RoomService roomService;

    @Autowired
    ResortService resortService;

    @Autowired
    ResortImageService resortImageService;

    @Autowired
    BillService billService;

    @Autowired
    BillRepository billRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @RequestMapping(method = RequestMethod.GET, value = "/backend/bill")
    String list(Model model) {
        Object danhsach = billService.getAll();
        model.addAttribute("list", danhsach);
        return "/backend/bill/listBill.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/backend/updateBill/{id}")
    String updateBill(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        billService.updateBill(id);
        return "redirect:/backend/bill";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/booking/{roomid}")
    public String showFormBooking(@PathVariable Long roomid, Model model) {
        BillDto billDto = new BillDto();
        model.addAttribute("roomid", roomid);
        model.addAttribute("billDto", billDto);
        RoomDto roomDto = roomService.getDetailById(roomid);
        model.addAttribute("roomDto", roomDto);
        ResortDto resortDto = resortService.getDetailById(roomDto.getResortId());
        model.addAttribute("resortDto", resortDto);
        ResortImageDto resortImageDto = resortImageService.getFirstByResortId(roomDto.getResortId());
        model.addAttribute("resortImageDto", resortImageDto);
        return "frontend/booking.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/booking/{roomid}")
    public String booking(@Valid @ModelAttribute("billDto") BillDto billDto, BindingResult result,
            WebRequest request, Model model, @PathVariable Long roomid, RedirectAttributes redirectAttributes) {
        // RoomDto roomDto = roomService.getDetailById(id);
        // model.addAttribute("roomDto",roomDto);
        // ResortDto resortDto = resortService.getDetailById(roomDto.getResortId());
        // model.addAttribute("resortDto",resortDto);
        // ResortImageDto resortImageDto =
        // resortImageService.getFirstByResortId(roomDto.getResortId());
        // model.addAttribute("resortImageDto",resortImageDto);
        if (result.hasErrors()) {
            return "frontend/booking.html";
        }
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(roomid);
        billDto.setRoomEntity(roomEntity);
        billService.add(billDto);
        BillEntity billEntity = billRepository.getBillEntityById(billDto.getId());

        RoomDto roomDto = roomService.getDetailById(roomid);
        return "redirect:/api/v1/pay" + "?price=" + roomDto.getPrice() + "&billId=" + billEntity.getId();
    }


}
