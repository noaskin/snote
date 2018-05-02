package com.noasking.snote.service;

import ch.qos.logback.core.util.FileUtil;
import com.noasking.snote.config.SystemException;
import com.noasking.snote.persistence.PathProperties;
import com.noasking.snote.persistence.PersistenceFactory;
import com.noasking.snote.persistence.summary.SummaryNode;
import com.noasking.snote.utils.Const;
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
public class DirectoryService {

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

    /**
     * 新增目录
     * @param parentPath
     * @param newName
     * @throws IOException
     */
    public void addDirectory(String parentPath, String newName) throws IOException {
        File file = new File(properties.appendPathHeader(parentPath));
        if (file.exists()) {
            if (file.isDirectory()) {
                addDirectory(parentPath + File.separator + newName);
            } else {
                throw new SystemException(parentPath + "是文件而不是目录");
            }
        } else {
            throw new SystemException("目录不存在:" + parentPath);
        }
    }

    /**
     * @return
     */
    private boolean checkExists() {
        return false;
    }


    private List<SummaryNode> list(File file, SummaryNode parentSummaryNode) {
        List<SummaryNode> result = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + Const.DEFAULT_TEXT_NAME);
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
        List<File> result = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + Const.DEFAULT_TEXT_NAME);
                if (readmeFile.exists()) {
                    result.add(f);
                }
                list(f);
            }
        }
        return result;
    }

    /**
     * 删除指定目录
     *
     * @param path 目录名称
     * @return
     */
    public void deleteSummary(String path) throws IOException {
        FileUtils.deleteDirectory(new File(properties.appendPathHeader(path)));
    }

    /**
     * 新增指定目录(每次只能创建一级目录)
     *
     * @param path
     * @return
     */
    public boolean addDirectory(String path) throws IOException {
        File directory = new File(properties.appendPathHeader(path));
        // 创建目录
        FileUtils.forceMkdir(directory);
        // 新增README文件
        File file = new File(properties.appendPathHeader(path + File.separator + Const.DEFAULT_TEXT_NAME));
        return file.createNewFile();
    }

    /**
     * 修改目录名称
     *
     * @param path
     * @param newname
     * @return
     */
    public void updateDirectoryName(String path, String newname) {
        File directory = new File(properties.appendPathHeader(path));
        directory.renameTo(new File(directory.getPath().substring(0,
                directory.getPath().lastIndexOf(File.separator)) + File.separator + newname));
    }


}

