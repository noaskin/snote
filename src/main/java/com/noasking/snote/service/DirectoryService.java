package com.noasking.snote.service;

import com.noasking.snote.config.SystemException;
import com.noasking.snote.config.PathProperties;
import com.noasking.snote.persistence.directory.DirectoryNode;
import com.noasking.snote.utils.Const;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
        LOG.info("重新加载目录树缓存！");
        String url = pathProperties.getUrl();
        DirectoryNode rootNode = new DirectoryNode();
        File file = new File(url);
        rootNode.setPath(File.separator);
        rootNode.setName("");
        rootNode.setChildren(list(file, rootNode));
        return rootNode;
    }

    @CacheEvict(value = Const.CacheName.DIRECTORY_TREE)
    public void clear() {
        LOG.info("清空目录树缓存！");
        return;
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
                    node.setPath(parentSummaryNode.getPath() + File.separator + f.getName());
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

}

