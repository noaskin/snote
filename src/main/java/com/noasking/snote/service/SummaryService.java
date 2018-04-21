package com.noasking.snote.service;

import com.noasking.snote.persistence.PathProperties;
import com.noasking.snote.persistence.PersistenceFactory;
import com.noasking.snote.persistence.summary.SummaryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Summary目录处理服务
 */
@Service
public class SummaryService {

    @Autowired
    private PersistenceFactory persistenceFactory;

    /**
     * 目录记录（缓存）
     */
    private List<SummaryNode> summaryNode;

    @Autowired
    private PathProperties properties;

    public List<SummaryNode> getSummaryNode() {
        return summaryNode;
    }

    /**
     * 加载Summary
     */
    private synchronized void reloadSummary() {

    }

    /**
     * 删除指定目录
     *
     * @param path 目录名称
     * @return
     */
    private boolean deleteSummary(String path) {

    }

    /**
     * 新增指定目录
     *
     * @param path
     * @return
     */
    private boolean addSummary(String path) {

    }

    /**
     * 修改目录名称
     *
     * @param path
     * @param newname
     * @return
     */
    private boolean updateSummaryName(String path, String newname) {

    }


}

