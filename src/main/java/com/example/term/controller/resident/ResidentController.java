package com.example.term.controller.resident;


import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.service.resident.ResidentService;
import com.example.term.util.response.FormattedResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class ResidentController {


    @Resource
    private ResidentService residentService;

    @GetMapping("/resident/all")
    public FormattedResponse<?> getResidentList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", residentService.getResidentList());

        return FormattedResponse.success(data);
    }


    @PostMapping("/resident/add")
    public FormattedResponse<?> addResident(HttpServletRequest request,
                                            @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toResidentEntity();

        residentService.addResident(residentEntity);

        HashMap<String, Object> data = new HashMap<>();

        data.put("data", null);

        return FormattedResponse.success();
    }


    @PutMapping("/resident/renew")
    public FormattedResponse<?> updateResident(HttpServletRequest request, @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toUpdateResidentEntity();

        residentService.updateResident(residentEntity);
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", null);

        return FormattedResponse.success();
    }


    @DeleteMapping("/resident/delete")
    public FormattedResponse<?> deleteResident(HttpServletRequest request, @RequestParam(name = "id") Integer Id) {
        residentService.deleteResident(Id);

        HashMap<String, Object> data = new HashMap<>();
        data.put("data", null);

        return FormattedResponse.success();
    }
}
