package com.noasking.snote.controller;

import com.noasking.snote.persistence.directory.DirectoryNode;
import com.noasking.snote.service.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "note")
public class NoteController {

    @Autowired
    private DirectoryService directoryService;

    /**
     * 展示目录树
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<?> listSummary() {
        DirectoryNode directoryNode = directoryService.getDirectoryNode();
        return ResponseEntity.ok(directoryNode);
    }


}
