package com.noasking.snote.service;

import ch.qos.logback.core.util.FileUtil;
import com.noasking.snote.persistence.PathProperties;
import com.noasking.snote.persistence.PersistenceFactory;
import com.noasking.snote.persistence.summary.SummaryNode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private void reloadSummary() {

    }

    private List<SummaryNode> list(File file, SummaryNode parentSummaryNode) {
        List<SummaryNode> result = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + "README.md");
                if (readmeFile.exists()) {
                    SummaryNode node = new SummaryNode();
                    node.setName(f.getName());
                    node.setParent(parentSummaryNode);
                    node.setChildren(list(f, node));
                }
            }
        }
        return result;
    }

    private List<File> list(File file) {
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + "README.md");
                if (readmeFile.exists()) {
                    System.out.println(f.getPath());
                }
                list(f);
            }
        }
        return null;
    }

    /**
     * 删除指定目录
     *
     * @param path 目录名称
     * @return
     */
    private void deleteSummary(String path) throws IOException {
        FileUtils.deleteDirectory(new File(properties.appendPathHeader(path)));
    }

    /**
     * 新增指定目录(每次只能创建一级目录)
     *
     * @param path
     * @return
     */
    private boolean addSummary(String path) throws IOException {
        File directory = new File(properties.appendPathHeader(path));
        // 创建目录
        FileUtils.forceMkdir(directory);
        // 新增README文件
        File file = new File(properties.appendPathHeader(path + File.separator + "README.md"));
        return file.createNewFile();
    }

    /**
     * 修改目录名称
     *
     * @param path
     * @param newname
     * @return
     */
    private void updateSummaryName(String path, String newname) {
        File directory = new File(properties.appendPathHeader(path));
        directory.renameTo(new File(directory.getPath().substring(0,
                directory.getPath().lastIndexOf(File.separator)) + File.separator + newname));
    }

    private void


}

