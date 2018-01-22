package com.flag.data.structure;

import java.util.Comparator;

/**
 * @author xuj
 * @since 2018-01-16-11:02
 */
public class BTreeSet<Key extends Comparator<Key>> {
    private Page<Key> root = new PageImpl<>(true);

    public BTreeSet(Key sentinel) {
        add(sentinel);
    }

    public boolean contains(Key key) {
        return contains(root, key);
    }

    private boolean contains(Page<Key> h, Key key) {
        if (h.isExternal()) {
            return h.contains(key);
        }
        return contains(h.next(key), key);
    }

    public void add(Key key) {
        add(root, key);
        if (root.isFull()) {
            Page<Key> left = root;
            Page<Key> right = root.split();
            root = new PageImpl<>(false);
            root.add(left);
            root.add(right);
        }
    }

    public void add(Page<Key> h, Key key) {
        if (h.isExternal()) {
            h.add(key);
            return;
        }

        Page<Key> next = h.next(key);
        add(next, key);
        if (next.isFull()) {
            h.add(next.split());
        }
        next.close();
    }
}
