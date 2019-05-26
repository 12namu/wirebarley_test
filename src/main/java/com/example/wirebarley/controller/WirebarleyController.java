package com.example.wirebarley.controller;

import com.example.wirebarley.dto.ApiResult;
import com.example.wirebarley.dto.WireInfoDto;
import com.example.wirebarley.service.WirebarleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WirebarleyController {

    @Autowired
    private WirebarleyService service;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/index");
        WireInfoDto wireInfo = WirebarleyService.getWireInfoToApi();
        if (wireInfo != null) {
            mav.addObject("data", wireInfo);
        }
        return mav;
    }


    @PostMapping("getWireInfo.do")
    public @ResponseBody
    ApiResult getWireInfo() {
        ApiResult<WireInfoDto> result;
        try {
            result = new ApiResult<>(ApiResult.RESULT_CODE_SUCC);
            WireInfoDto wireInfo = WirebarleyService.getWireInfoToApi();
            if (wireInfo != null) {
                result.setData(wireInfo);
            }
        } catch (RuntimeException runtimeException) {
            result = new ApiResult<>(ApiResult.RESULT_CODE_ERROR);
        }

        return result;
    }


}
