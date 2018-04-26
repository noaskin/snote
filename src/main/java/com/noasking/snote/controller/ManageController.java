package com.noasking.snote.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "manage")
public class ManageController {


    /**
     * 新增目录
     * @return
     */
    @RequestMapping(value = "directory/add",method = RequestMethod.GET)
    public ResponseEntity<?> addDirectory(){
        return null;
    }

    /**
     * 删除目录
     * @return
     */
    @RequestMapping(value = "directory/delete",method = RequestMethod.GET)
    public ResponseEntity<?> deleteDirectory(){
        return null;
    }

    /**
     * 更新目录名称
     * @return
     */
    @RequestMapping(value = "directory/update",method = RequestMethod.GET)
    public ResponseEntity<?> updateDirectory(){
        return null;
    }


    /**
     * 保存内容
     * @return
     */
    @RequestMapping(value = "note/save",method = RequestMethod.GET)
    public ResponseEntity<?> saveNote(){
        return null;
    }

    /**
     * 保存内容
     * @return
     */
    @RequestMapping(value = "note/save",method = RequestMethod.GET)
    public ResponseEntity<?> upload(){

    }












}
