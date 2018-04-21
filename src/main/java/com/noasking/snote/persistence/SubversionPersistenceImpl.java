package com.noasking.snote.persistence;

/**
 * SVN版本实现
 */
public class SubversionPersistenceImpl extends LocalPersistenceImpl implements RemotePersistencePathInterface {

    public SubversionPersistenceImpl(String localPath) {
        super(localPath);
    }

    @Override
    public boolean commit() {
        return false;
    }
}
