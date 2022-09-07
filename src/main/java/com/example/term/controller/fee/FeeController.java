package com.example.term.controller.fee;

import com.example.term.dto.fee.FeeDto;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.service.fee.FeeService;
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
@RequestMapping("/fee")
public class FeeController {

    @Resource
    private FeeService feeService;

    @JwtAuth
    @GetMapping("/all")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> getFeeList(HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", feeService.getFeeList(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));

        return FormattedResponse.success(data);
    }

    @JwtAuth
    @RequestMapping("/add")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> addFee(HttpServletRequest request,
                                       @Valid @RequestBody FeeDto feeDto) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", feeService.addFee(CommonUtil.objToInteger(request.getAttribute("userType")), feeDto));

        return FormattedResponse.success(data);
    }

    @PutMapping("/renew")
    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> updateFee(HttpServletRequest request,@Valid @RequestBody FeeDto feeDto) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", feeService.updateFee(CommonUtil.objToInteger(request.getAttribute("userType")),feeDto));

        return FormattedResponse.success(data);
    }


    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    @DeleteMapping("/delete")
    public FormattedResponse<?> delFee(HttpServletRequest request,
                                                @RequestParam(name = "id") Integer Id) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", feeService.deleteFee(CommonUtil.objToInteger(request.getAttribute("userType")),Id));

        return FormattedResponse.success(data);
    }
}
