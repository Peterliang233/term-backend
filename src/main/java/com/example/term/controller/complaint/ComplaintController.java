package com.example.term.controller.complaint;


import com.example.term.dto.complaint.ComplaintDto;
import com.example.term.interceptor.jwt.JwtAuth;
import com.example.term.service.complaint.ComplaintService;
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
@RequestMapping("/complaint")
public class ComplaintController {

    @Resource
    private ComplaintService complaintService;



    @JwtAuth
    @GetMapping("/all")
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> getComplaintList(HttpServletRequest request) {
        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", complaintService.getComplaintList(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid"))));

        return FormattedResponse.success(data);
    }


    @PostMapping("/add")
    @SneakyThrows
    @JwtAuth
    @ResponseExceptionCatcher
    public FormattedResponse<?> addComplaint(HttpServletRequest request,
            @Valid @RequestBody ComplaintDto complaintDto) {

        HashMap<String, Object> data = new HashMap<>(1);

        data.put("data", complaintService.addComplaint(complaintDto,
                CommonUtil.objToString(request.getAttribute("uuid")
                ), CommonUtil.objToInteger(request.getAttribute("userType"))));

        return FormattedResponse.success(data);
    }


    @PutMapping("/renew")
    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    public FormattedResponse<?> updateComplaint(HttpServletRequest request,@Valid @RequestBody ComplaintDto complaintDto) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", complaintService.updateComplaint(complaintDto,CommonUtil.objToString(request.getAttribute("uuid")),
                CommonUtil.objToInteger(request.getAttribute("userType"))));

        return FormattedResponse.success(data);
    }


    @JwtAuth
    @SneakyThrows
    @ResponseExceptionCatcher
    @DeleteMapping("/delete")
    public FormattedResponse<?> deleteComplaint(HttpServletRequest request,
                                               @RequestParam(name = "id") Integer Id) {

        HashMap<String, Object> data = new HashMap<>(1);
        data.put("data", complaintService.deleteComplaint(CommonUtil.objToInteger(request.getAttribute("userType")),
                CommonUtil.objToString(request.getAttribute("uuid")),
                Id));

        return FormattedResponse.success(data);
    }
}
