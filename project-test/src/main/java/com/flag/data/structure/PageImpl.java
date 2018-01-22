package com.flag.data.structure;

import java.util.Iterator;

/**
 * @author xuj
 * @since 2018-01-16-11:08
 */
public class PageImpl<T> implements Page<T> {

    public PageImpl(boolean buttom) {
        super();
    }

    @Override
    public void close() {

    }

    @Override
    public void add(T key) {

    }

    @Override
    public void add(Page<T> page) {

    }

    @Override
    public boolean isExternal() {
        return false;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }

    @Override
    public Page next(T key) {
        return null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public Page split() {
        return null;
    }

    @Override
    public Iterator<T> keys() {
        return null;
    }
}
