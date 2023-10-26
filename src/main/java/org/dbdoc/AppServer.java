package org.dbdoc;

import org.dbdoc.service.DocService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppServer {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AppServer.class, args);
        DocService docService = ctx.getBean(DocService.class);
        // 创建文档
        docService.createDoc();
    }


}
