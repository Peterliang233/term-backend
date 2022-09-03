package com.example.term.controller.resident;


import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.service.resident.ResidentService;
import com.example.term.util.response.Response;
import com.example.term.util.result.ResultResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class ResidentController {


    @Resource
    private ResidentService residentService;

    @GetMapping("/resident/all")
    public Response getResidentList() {
        List<ResidentEntity> list = residentService.getResidentList();

        HashMap<String, Object> data = new HashMap<>();
        data.put("data", list);

        return ResultResponse.getSuccessResult(data);
    }


    @PostMapping("/resident/add")
    public Response addResident(HttpServletRequest request,
                                @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toResidentEntity();

        residentService.addResident(residentEntity);

        HashMap<String, Object> data = new HashMap<>();

        data.put("data", null);

        return ResultResponse.getSuccessResult(data);
    }


    @PutMapping("/resident/renew")
    public Response updateResident(HttpServletRequest request, @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toUpdateResidentEntity();

        residentService.updateResident(residentEntity);
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", null);

        return ResultResponse.getSuccessResult(data);
    }


    @DeleteMapping("/resident/delete")
    public Response deleteResident(HttpServletRequest request, @RequestParam(name = "id") Integer Id) {
        residentService.deleteResident(Id);

        HashMap<String, Object> data = new HashMap<>();
        data.put("data", null);

        return ResultResponse.getSuccessResult(data);
    }
}
