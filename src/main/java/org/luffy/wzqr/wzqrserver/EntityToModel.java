/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import javassist.Modifier;
import javax.persistence.Entity;
import org.eclipse.persistence.jaxb.xmlmodel.XmlRootElement;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

/**
 * 可以将Entity转换成Model 对于关系字段 将仅仅作为一个输出 所有整数都为int 带小数的都为float boolean date
 *
 * inbasepackage 输入java包 outpath 输入为 baseClass 默认为Ext.data.Model basePackage
 * 默认为空 附加工艺 fomatter 将直接附加在定义中
 *
 * 失败改成类吧
 *
 * @author luffy
 */
public class EntityToModel {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        new EntityToModel(
                "org.luffy.wzqr.wzqrserver.entity.Application",
                "target",
                "wzqr.spring.data.Model",
                "wzqr.model",
                "\tresourceURI: Utils.toApi('api/%s'),\n"
        ).
                work();
    }
    private StringBuilder buffer;
    private final String baseClass;
    private final String basePackage;
    private final String formatter;

    private void work() {

        this.buffer = new StringBuilder();
        buffer.append("Ext.define('")
                .append(basePackage)
                .append(".")
                .append(clazz.getSimpleName())
                .append("', {\n");
        buffer.append("\textend: '")
                .append(baseClass)
                .append("',\n");
        if (formatter != null) {
            new Formatter(buffer, Locale.US).format(formatter, clazz.getSimpleName().toLowerCase(Locale.US));
        }

        buffer.append("\tfields: [\n");

        //fields!!!
        process(clazz);

        buffer.append("\t]\n").append("});\n");
        System.out.println(buffer);

    }
    private final File outPath;
    private final Class<?> clazz;

    private EntityToModel(String className, String outpath,
            String baseClass, String basePackage,
            String fomatter) throws ClassNotFoundException, IOException {
        this.clazz = Class.forName(className);
        this.outPath = new File(outpath);

        this.baseClass = baseClass;
        this.basePackage = basePackage;
        this.formatter = fomatter;

    }

    private List<Class> findMyTypes(String basePackage) throws IOException, ClassNotFoundException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        List<Class> candidates = new ArrayList<Class>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                + resolveBasePackage(basePackage) + "/" + "**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if (isCandidate(metadataReader)) {
                    candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                }
            }
        }
        return candidates;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    private boolean isCandidate(MetadataReader metadataReader) throws ClassNotFoundException {
        try {
            Class c = Class.forName(metadataReader.getClassMetadata().getClassName());
            if (c.getAnnotation(XmlRootElement.class) != null) {
                return true;
            }
        } catch (Throwable e) {
        }
        return false;
    }

    private void process(Class<?> clz) {
        if (clz == Object.class) {
            return;
        }
        for (Field f : clz.getDeclaredFields()) {
            process(f);
        }
        process(clz.getSuperclass());
    }

    private void process(Field f) {
        if (Modifier.isNative(f.getModifiers())
                || Modifier.isStatic(f.getModifiers())
                || Modifier.isTransient(f.getModifiers())) {
            return;
        }

        JsonIgnore ignore = f.getAnnotation(JsonIgnore.class);
        if (ignore != null) {
            return;
        }
        if (f.getType().getAnnotation(Entity.class) != null) {
            submitFieldAsType(f, null);
        } else if (f.getType() == String.class) {
            submitFieldAsType(f, "string");
        } else if (f.getType().isPrimitive()) {
            if (f.getType() == Boolean.TYPE) {
                submitFieldAsType(f, "boolean");
            } else if (f.getType() == Float.TYPE || f.getType() == Double.TYPE) {
                submitFieldAsType(f, "float");
            } else {
                submitFieldAsType(f, "int");
            }
        } else if (Number.class.isAssignableFrom(f.getType())) {
            if (f.getType() == Float.class || f.getType() == Double.class) {
                submitFieldAsType(f, "float");
            } else {
                submitFieldAsType(f, "int");
            }
        } else if (f.getType() == Boolean.class) {
            submitFieldAsType(f, "boolean");
        } else if (Date.class.isAssignableFrom(f.getType())) {
            submitFieldAsType(f, "date");
        } else {
            System.out.println("unknow field" + f);
            submitFieldAsType(f, null);
        }
    }

    private void submitFieldAsType(Field f, String type) {
        buffer.append("\t\t{name: '")
                .append(f.getName())
                .append("'");
        if (type == null) {
            buffer.append("},\n");
        } else {
            //type: 'date',dateFormat:'time'},
            //type: 'date'},
            buffer.append(", type: '")
                    .append(type);
            if ("date".equals(type)){
                buffer.append("',dateFormat:'time'},\n");
            }else
                buffer.append("'},\n");
        }
    }

}
