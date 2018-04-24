package com.noasking.snote.persistence;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 文件路径参数，对应配置文件开头：snote.path
 */
@Component
@Data
public class PathProperties {

    /**
     * 持久化方式 SVN、GIT、LOCAL（可选，默认为LOCAL）
     */
    @Value("${snote.path.type}")
    private String persistenceTypeValue;

    private PersistenceType persistenceType = PersistenceType.LOCAL;

    /**
     * 本地路径(可选，默认项目路径下面的res文件夹)
     */
    @Value("${snote.path.url}")
    private String url = "res";

    /**
     * 远程地址(persistenceType为Local时可选,否则必填)
     */
    @Value("${snote.path.remote.url}")
    private String remoteUrl;


    /**
     * 远程用户名(persistenceType为Local时可选,否则必填)
     */
    @Value("${snote.path.remote.username}")
    private String username;

    /**
     * 远程密码(persistenceType为Local时可选,否则必填)
     */
    @Value("${snote.path.remote.password}")
    private String password;

    @PostConstruct
    public void init() {
        if (!StringUtils.isEmpty(persistenceTypeValue)) {
            persistenceType = PersistenceType.valueOf(persistenceTypeValue.toUpperCase());
        }
    }

    public String appendPathHeader(String footer) {
        if (footer.startsWith(File.separator)) {
            return url + footer;
        } else {
            return url + File.separator + footer;
        }
    }

}
