package com.noasking.snote.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "note")
public class NoteController {

    /**
     * 展示目录树
     */
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResponseEntity<?> listSummary(){

        return null;
    }


}
