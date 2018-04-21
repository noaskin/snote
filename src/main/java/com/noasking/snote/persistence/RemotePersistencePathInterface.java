package com.noasking.snote.persistence;

/**
 * 远程持久化路径接口,在本地的基础上提供的提交选项
 */
public interface RemotePersistencePathInterface {

    boolean commit();

}
