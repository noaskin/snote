package com.noasking.snote.persistence;

import java.util.List;

/**
 * 本地化实现
 */
public class LocalPersistenceImpl implements PersistencePathInterface {

    private String localPath;

    public LocalPersistenceImpl(String localPath) {
        this.localPath = localPath;
    }
    
}
