package org.dbdoc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "doc")
public class DocConfig {

    private String fileOutputDir;
    private String fileName;
    private String version;
    private String description;
    /**
     * 忽略表
     */
    private List<String> ignoreTableName;
    /**
     * 忽略表前缀
     */
    private List<String> ignorePrefix;
    /**
     * 忽略表后缀
     */
    private List<String> ignoreSuffix;
    /**
     * 根据名称指定表生成
     */
    private List<String> designatedTableName;



}
