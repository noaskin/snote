package com.noasking.snote.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 文件持久化工厂
 */
@Component
public class PersistenceFactory {

    @Autowired
    private PathProperties pathProperties;

    private PersistencePathInterface persistencePathInterface;

    @PostConstruct
    private void init() {
        switch (pathProperties.getPersistenceType()) {
            case LOCAL:
                persistencePathInterface = new LocalPersistenceImpl(pathProperties.getUrl());
                break;
            case GIT:
                // TODO
                persistencePathInterface = new GitPersistenceImpl(pathProperties.getUrl());
                break;
            case SVN:
                // TODO
                persistencePathInterface = new SubversionPersistenceImpl(pathProperties.getUrl());
            default:
        }
    }

    public PersistencePathInterface getPersistencePathInterface() {
        return persistencePathInterface;
    }


}
