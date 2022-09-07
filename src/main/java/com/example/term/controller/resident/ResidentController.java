package com.example.term.controller.resident;


import com.example.term.dto.resident.ResidentDto;
import com.example.term.entity.resident.ResidentEntity;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.service.resident.ResidentService;
import com.example.term.util.common.CommonUtil;
import com.example.term.util.response.FormattedResponse;
import com.example.term.util.response.ResponseExceptionCatcher;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@ResponseExceptionCatcher
@RequestMapping("/resident")
public class ResidentController {


    @Resource
    private ResidentService residentService;


    @JwtAuth
    @GetMapping("/all")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> getResidentList(HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", residentService.getResidentList(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));

        return FormattedResponse.success(data);
    }


    @PostMapping("/add")
    @SneakyThrows
    @JwtAuth
    @ResponseExceptionCatcher
    public FormattedResponse<?> addResident(HttpServletRequest request,
            @Valid @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toResidentEntity();

        HashMap<String, Object> data = new HashMap<>(1);

        data.put("data", residentService.addResident(residentEntity,
                CommonUtil.objToString(request.getAttribute("uuid")
                ), CommonUtil.objToInteger(request.getAttribute("userType"))));

        return FormattedResponse.success(data);
    }


    @PutMapping("/renew")
    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> updateResident(HttpServletRequest request, @RequestBody ResidentDto residentDto) {
        ResidentEntity residentEntity = residentDto.toUpdateResidentEntity();

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", residentService.updateResident(residentEntity,
                CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));
        return FormattedResponse.success(data);
    }


    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    @DeleteMapping("/delete")
    public FormattedResponse<?> deleteResident(HttpServletRequest request,
                                               @RequestParam(name = "id") Integer Id) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", residentService.deleteResident(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid")),
                Id));

        return FormattedResponse.success(data);
    }
}
