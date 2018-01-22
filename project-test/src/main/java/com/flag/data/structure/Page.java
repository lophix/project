package com.flag.data.structure;

import java.util.Iterator;

/**
 * @author xuj
 * @since 2018-01-16-11:02
 */
public interface Page<T> {
    /**
     * 关闭页
     */
    void close();

    /**
     * 将键插入页中
     * @param key 健
     */
    void add(T key);

    void add(Page<T> page);

    boolean isExternal();

    boolean contains(T key);

    Page<T> next(T key);

    boolean isFull();

    Page<T> split();

    Iterator<T> keys();
}
