package com.example.term.controller.repair;

import com.example.term.dto.repair.RepairDto;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.service.repair.RepairService;
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
@RequestMapping("/repair")
public class RepairController {

    @Resource
    private RepairService repairService;

    @JwtAuth
    @GetMapping("/all")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> getRepairList(HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", repairService.getRepairList(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));

        return FormattedResponse.success(data);
    }

    @RequestMapping("/add")
    @SneakyThrows
    @ResponseExceptionCatcher
    @JwtAuth
    public FormattedResponse<?> addRepair(HttpServletRequest request,
                                           @Valid @RequestBody RepairDto repairDto) {
        HashMap<String, Object> data = new HashMap<>(1);
        System.out.println(repairDto);
        data.put("data", repairService.addRepair(repairDto, CommonUtil.objToString(request.getAttribute("uuid")),
                CommonUtil.objToInteger(request.getAttribute("userType"))));

        return FormattedResponse.success(data);
    }

    @PutMapping("/renew")
    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> updateRepair(HttpServletRequest request,@Valid @RequestBody RepairDto repairDto) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", repairService.updateRepair(
                CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid")),
                repairDto));

        return FormattedResponse.success(data);
    }


    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    @DeleteMapping("/delete")
    public FormattedResponse<?> deleteRepair(HttpServletRequest request,
                                              @RequestParam(name = "id") Integer Id) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", repairService.deleteRepair(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid")),
                Id));

        return FormattedResponse.success(data);
    }
}
