package com.noasking.snote.persistence;

/**
 * /**
 * Git版本实现
 */
public class GitPersistenceImpl extends LocalPersistenceImpl implements RemotePersistencePathInterface {
    public GitPersistenceImpl(String localPath) {
        super(localPath);
    }

    @Override
    public boolean commit() {
        return false;
    }
}
