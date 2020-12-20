package com.example.springbootexception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello() {
        throw new SampleException();
    }

    // 컨트롤러 안에서만 동작
    // - JSON형태로 error 반환 
    //   {"message":"msg","reason":"reason"}
    @ExceptionHandler(SampleException.class)
    public @ResponseBody SampleError sampleError(SampleException e) {
        SampleError error = new SampleError();
        error.setMessage("test msg");
        error.setReason("reason");
        return error;
    }
}
