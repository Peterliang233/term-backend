package com.example.term.controller.parking;


import com.example.term.dto.fee.FeeDto;
import com.example.term.dto.parking.ParkingDto;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.service.fee.FeeService;
import com.example.term.service.parking.ParkingService;
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
@RequestMapping("/parking")
public class ParkingController {

    @Resource
    private ParkingService parkingService;

    @JwtAuth
    @GetMapping("/all")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> getParkingList(HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", parkingService.getParkingList(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));

        return FormattedResponse.success(data);
    }

    @JwtAuth
    @RequestMapping("/add")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> addParking(HttpServletRequest request,
                                       @Valid @RequestBody ParkingDto parkingDto) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", parkingService.addParking(CommonUtil.objToInteger(request.getAttribute("userType")), parkingDto));

        return FormattedResponse.success(data);
    }

    @PutMapping("/renew")
    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> updateParking(HttpServletRequest request,@Valid @RequestBody ParkingDto parkingDto) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", parkingService.updateParking(CommonUtil.objToInteger(request.getAttribute("userType")),parkingDto));

        return FormattedResponse.success(data);
    }


    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    @DeleteMapping("/delete")
    public FormattedResponse<?> deleteParking(HttpServletRequest request,
                                       @RequestParam(name = "id") Integer Id) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", parkingService.deleteParking(CommonUtil.objToInteger(request.getAttribute("userType")),Id));

        return FormattedResponse.success(data);
    }
}
