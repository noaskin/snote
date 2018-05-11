package com.noasking.snote.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * 文件路径参数，对应配置文件开头：snote.path
 */
@Component
@Data
public class PathProperties {

    /**
     * 本地路径(可选，默认项目路径下面的res文件夹)
     */
    @Value("${snote.path.url}")
    private String url;

    private int urlLength;

    @PostConstruct
    public void init() throws Exception {
        File file = new File(url);
        if (file.isDirectory()) {
            url = file.getPath();
            urlLength = url.length();
        } else {
            throw new Exception("url is not directory!");
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
