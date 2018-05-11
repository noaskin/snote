package com.noasking.snote.service;

import com.noasking.snote.config.SystemException;
import com.noasking.snote.config.PathProperties;
import com.noasking.snote.persistence.directory.DirectoryNode;
import com.noasking.snote.utils.Const;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    private static final Logger LOG = LoggerFactory.getLogger(DirectoryService.class);

    @Autowired
    private PathProperties pathProperties;

    @Cacheable(value = Const.CacheName.DIRECTORY_TREE)
    public DirectoryNode getDirectoryNode() {
        System.out.println(File.separator + "------" + pathProperties.getUrl());
        String url = pathProperties.getUrl();
        DirectoryNode rootNode = new DirectoryNode();
        File file = new File(url);
        rootNode.setPath(file.getPath());
        rootNode.setName(file.getName());
        rootNode.setChildren(list(file, rootNode));
        return rootNode;
    }

    /**
     * 新增目录
     *
     * @param parentPath
     * @param newName
     * @throws IOException
     */
    @Cacheable(value = Const.CacheName.DIRECTORY_TREE, key = "directory")
    public void addDirectory(String parentPath, String newName) throws IOException {
        File file = new File(pathProperties.appendPathHeader(parentPath));
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


    private List<DirectoryNode> list(File file, DirectoryNode parentSummaryNode) {
        List<DirectoryNode> result = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                File readmeFile = new File(f.getPath() + File.separator + Const.DEFAULT_TEXT_NAME);
                if (readmeFile.exists()) {
                    DirectoryNode node = new DirectoryNode();
                    node.setName(f.getName());
//                    node.setParent(parentSummaryNode);
                    node.setPath(f.getPath());
                    node.setChildren(list(f, node));
                    result.add(node);
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
        FileUtils.deleteDirectory(new File(pathProperties.appendPathHeader(path)));
    }

    /**
     * 新增指定目录(每次只能创建一级目录)
     *
     * @param path
     * @return
     */
    public boolean addDirectory(String path) throws IOException {
        File directory = new File(pathProperties.appendPathHeader(path));
        // 创建目录
        FileUtils.forceMkdir(directory);
        // 新增README文件
        File file = new File(pathProperties.appendPathHeader(path + File.separator + Const.DEFAULT_TEXT_NAME));
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
        File directory = new File(pathProperties.appendPathHeader(path));
        directory.renameTo(new File(directory.getPath().substring(0,
                directory.getPath().lastIndexOf(File.separator)) + File.separator + newname));
    }


}

