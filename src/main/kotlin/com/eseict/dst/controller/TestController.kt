package com.eseict.dst.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("test")
class TestController {

    @RequestMapping("")
    fun test():ResponseEntity<String> {

        return ResponseEntity.ok("GOOD")
    }

}