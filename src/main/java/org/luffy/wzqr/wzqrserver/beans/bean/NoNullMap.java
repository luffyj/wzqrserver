/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.luffy.wzqr.wzqrserver.beans.bean;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author luffy
 */
public class NoNullMap implements Map{
    
    private final Map realMap;

    public NoNullMap(Map realMap) {
        this.realMap = realMap;
    }
    
    /**
     * 强制不可以为null
     */
    @Override
    public Object put(Object key, Object value) {
        if(value==null)
            value = "";
        return realMap.put(key, value);
    }

    @Override
    public int size() {
        return realMap.size();
    }

    @Override
    public boolean isEmpty() {
        return realMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return realMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return realMap.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return realMap.get(key);
    }

    @Override
    public Object remove(Object key) {
        return realMap.remove(key);
    }

    @Override
    public void putAll(Map m) {
        realMap.putAll(m);
    }

    @Override
    public void clear() {
        realMap.clear();
    }

    @Override
    public Set keySet() {
        return realMap.keySet();
    }

    @Override
    public Collection values() {
        return realMap.values();
    }

    @Override
    public Set entrySet() {
        return realMap.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return realMap.equals(o);
    }

    @Override
    public int hashCode() {
        return realMap.hashCode();
    }

    @Override
    public Object getOrDefault(Object key, Object defaultValue) {
        return realMap.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer action) {
        realMap.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction function) {
        realMap.replaceAll(function);
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return realMap.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return realMap.remove(key, value);
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        return realMap.replace(key, oldValue, newValue);
    }

    @Override
    public Object replace(Object key, Object value) {
        return realMap.replace(key, value);
    }

    @Override
    public Object computeIfAbsent(Object key, Function mappingFunction) {
        return realMap.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        return realMap.computeIfPresent(key, remappingFunction);
    }

    @Override
    public Object compute(Object key, BiFunction remappingFunction) {
        return realMap.compute(key, remappingFunction);
    }

    @Override
    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        return realMap.merge(key, value, remappingFunction);
    }
    
    
    
}
