package com.noasking.snote.persistence.summary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 目录节点
 */
@Data
public class SummaryNode {

    /**
     * 节点名称
     */
    private String name;

    /**
     * 父节点
     */
    private SummaryNode parent;

    /**
     * 子节点，有序（LinkedList）
     */
    private List<SummaryNode> children;

}
