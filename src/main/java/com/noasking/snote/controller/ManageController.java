package com.noasking.snote.controller;

import com.noasking.snote.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController(value = "manage")
public class ManageController {

    @Autowired
    private DirectoryService directoryService;


    /**
     * 新增目录
     *
     * @return
     */
    @RequestMapping(value = "directory/add", method = RequestMethod.GET)
    public ResponseEntity<?> addDirectory(String parentPath, String newName) throws IOException {
        directoryService.addDirectory(parentPath, newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除目录
     *
     * @param fullPath
     * @return
     */
    @RequestMapping(value = "directory/delete", method = RequestMethod.GET)
    public ResponseEntity<?> deleteDirectory(String fullPath) throws IOException {
        directoryService.deleteSummary(fullPath);
        return null;
    }

    /**
     * 更新目录名称
     *
     * @param oldPath 旧的路径，从根开始，但是可以不是最后一级
     * @param newName 新名称
     * @return
     */
    @RequestMapping(value = "directory/update", method = RequestMethod.GET)
    public ResponseEntity<?> updateDirectory(String oldPath, String newName) {
        directoryService.updateDirectoryName(oldPath,newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * 保存内容
     *
     * @return
     */
    @RequestMapping(value = "note/save", method = RequestMethod.GET)
    public ResponseEntity<?> saveNote() {
        return null;
    }

    /**
     * 保存内容
     *
     * @return
     */
    @RequestMapping(value = "note/upload", method = RequestMethod.GET)
    public ResponseEntity<?> upload() {
        return null;
    }


}
