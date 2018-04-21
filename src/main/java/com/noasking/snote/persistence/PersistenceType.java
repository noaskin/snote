package com.noasking.snote.persistence;

/**
 * 持久化方式
 * 无论是GIT还是SVN都是依赖于本地化的，只是如果不是Local方式提供保存后提交，而Local保存和提交时一体的
 */
public enum PersistenceType {

    /**
     * GIT方式
     */
    GIT,
    /**
     * SVN方式
     */
    SVN,
    /**
     * 本地化方式
     */
    LOCAL;

}
