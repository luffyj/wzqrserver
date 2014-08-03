/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.luffy.wzqr.wzqrserver.beans;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author luffy
 */
@Service
public class BeanHelper {
    
    
    private void _escapeToWord(StringBuilder sb, Element ele) {
        if(ele.hasText())
            sb.append(ele.ownText());
    }
    
    /**
     * 将富文本去格式化
     * @param input
     * @return null if input is null
     */
    public String escapeToWord(String input){
        if(input==null)
            return null;
        Document doc = org.jsoup.Jsoup.parse(input);
        StringBuilder sb = new StringBuilder();
        for(Element ele:doc.getAllElements()){
            _escapeToWord(sb,ele);
        }
        return sb.toString();
    }

    /**
     * 将obj中的各项属性 列印出来
     *
     * @param obj
     * @param names
     * @param spacer
     * @param liner
     * @return
     */
    public String toString(Object obj, String[] names, String spacer, String liner) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (true) {
            try {
                String first = this.toString(obj, names[0], index, "");
                if (first == null || first.length() == 0) {
                    return sb.toString();
                }

                for (int i = 0; i < names.length; i++) {
                    String name = names[i];
                    sb.append(this.toString(obj, name, index, ""));
                    if (i >= names.length - 1) {
                        sb.append(liner);
                    } else {
                        sb.append(spacer);
                    }
                }
                index++;
            } catch (IllegalStateException ex) {
                return sb.toString();
            }
        }
    }

    /**
     * 直接返回指定列和指定属性
     *
     * @param obj
     * @param name
     * @param index 从0开始 实际中应该+1
     * @param nullValue
     * @return
     */
    public String toString(Object obj, String name, int index, String nullValue) {
        name = name + (index + 1);
        try {
            Object value = BeanUtils.getPropertyDescriptor(obj.getClass(), name).getReadMethod().invoke(obj);
            if (value == null) {
                return nullValue;
            }
            return value.toString();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
            throw new java.lang.IllegalStateException("无法获取属性" + name, ex);
        }
    }

}
