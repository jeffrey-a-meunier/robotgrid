package robotgrid.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * AKA Trie
 */
public class PrefixTree<T> {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Map<String, PrefixTree<T>> _nodes = new HashMap<>();
    protected T _value;

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public List<String> allKeys() {
        List<String> keys = new ArrayList<>();
        _allKeys("", _nodes, keys);
        return keys;
    }

    protected void _allKeys(final String prefix, final Map<String, PrefixTree<T>> nodes, final List<String> allKeys) {
        Set<String> keys = nodes.keySet();
        for (String key : keys) {
            PrefixTree<T> tree = nodes.get(key);
            if (tree._value == null) {
                String prefix1 = prefix;
                if (prefix1.length() > 0) {
                    prefix1 += ' ';
                }
                _allKeys(prefix1 + key, tree._nodes, allKeys);
            }
            else {
                allKeys.add(prefix + ' ' + key);
            }
        }
    }

    public void insert(T value, final String ... keys) {
        PrefixTree<T> root = this;
        for (String key : keys) {
            PrefixTree<T> trie = root._nodes.get(key);
            if (trie == null) {
                trie = new PrefixTree<T>();
                root._nodes.put(key, trie);
            }
            root = trie;
        }
        root._value = value;
    }

    /**
     * Finds the value associated with the exact sequence in the keys array.
     */
    public T lookup(final String ... keys) {
        PrefixTree<T> root = this;
        for (String key : keys) {
            PrefixTree<T> trie = root._nodes.get(key);
            if (trie == null) {
                return null;
            }
            root = trie;
        }
        return root._value;
    }

    /**
     * Finds the value associated with the longest subsequence of the keys array.
     */
    public T lookupLongest(final String ... keys) {
        PrefixTree<T> root = this;
        T saved = null;
        for (String key : keys) {
            PrefixTree<T> trie = root._nodes.get(key);
            if (trie == null) {
                return saved;
            }
            saved = trie._value;
            root = trie;
        }
        return root._value;
    }

}
