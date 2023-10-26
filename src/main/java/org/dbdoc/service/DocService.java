package org.dbdoc.service;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import org.dbdoc.config.DocConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DocConfig docConfig;

    public void createDoc() {
        documentGeneration(dataSource,docConfig);
    }


    /**
     * 文档生成
     */
    private void documentGeneration(DataSource dataSource,DocConfig docConfig ) {
        //数据源
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(docConfig.getFileOutputDir())
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName(docConfig.getFileName()).build();

        // 忽略表
        List<String> ignoreTableName = docConfig.getIgnoreTableName();
        // 忽略表前缀
        List<String> ignorePrefix = docConfig.getIgnorePrefix();
        // 忽略表后缀
        List<String> ignoreSuffix = docConfig.getIgnoreSuffix();
        // 当存在指定表
        List<String> designatedTableName = docConfig.getDesignatedTableName();
        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(designatedTableName)
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<String>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<String>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version(docConfig.getVersion())
                //描述
                .description(docConfig.getDescription())
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig)
                .build();
        //执行生成
        new DocumentationExecute(config).execute();
    }
}
