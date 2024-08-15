package com.baijiu.Baijiu_Back.common;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGeneration {

    public static void main(String[] args) {
        //连接板数据库
        String url = "jdbc:mysql://localhost:3306/baijiu?serverTimezone=UTC";
        String username = "root";
        String password = "025626Ltt@";

        //需要在哪里生成代码的路径
        String module = ""; //  表示项目的模块名称
        String outPath = System.getProperty("user.dir") + "/" + module + "/src/main/java"; // 文件输出路径
        String parent = "com.baijiu.Baijiu_Back"; // 父包的名称
        String moduleName = ""; // 模块名称

        //需要生成哪些包
        String entity = "entity";
        String mapper = "mapper";
        String service = "service";
        String serviceImpl = "service.Impl";
        String controller = "controller";
        String mapperXml = "mapper.xml";

        //需要生成代码的数据库名
        List<String> tables = new ArrayList<>();
        tables.add("users");//在这修改表名
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("ltt") // 设置作者
                           .enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .outputDir(outPath) // 指定输出目录
                            .disableOpenDir(); // 生成后不打开目录
                })

                // 包配置
                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity(entity)
                            .mapper(mapper)
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller)
                            .xml(mapperXml);
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("t_") // 设置过滤表前缀
//                            .addTableSuffix("")//设置过滤表的后缀
                            .entityBuilder() // 开启生成实体类
                            .enableLombok() // 开启lombok模型
                            .mapperBuilder() // 开启生成mapper
                            .superClass(BaseMapper.class)
//                            .enableMapperAnnotation() // 开启mapper注解
                            .formatMapperFileName("%sMapper") // 格式化mapper名称
                            .formatXmlFileName("%sMapper") // 格式化xml的名称
                            .serviceBuilder() // 开启生成service
                            .formatServiceFileName("%sService") //格式化service接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder() //开启controller生成
                            .formatFileName("%sController")
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
        System.out.println("代码生成成功！");
    }
}
