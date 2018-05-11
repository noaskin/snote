package com.noasking.snote.persistence.directory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 目录节点
 */
@Data
public class DirectoryNode {

    /**
     * 节点名称
     */
    private String name;

    /**
     * 全路径名称
     */
    private String path;

//    /**
//     * 父节点
//     */
//    @JsonIgnore
//    private DirectoryNode parent;

    /**
     * 子节点，有序（LinkedList）
     */
    private List<DirectoryNode> children;

}
