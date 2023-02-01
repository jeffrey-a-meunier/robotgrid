import robotgrid.utils.PrefixTree;

public class Test_PrefixTree {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    protected static void testPrefixTree() {
        PrefixTree<Integer> trie = new PrefixTree<>();
        trie.insert(0, "x");
        trie.insert(1, "x", "y");
        trie.insert(2, "x", "y", "z");
        trie.insert(10, "y");
        trie.insert(11, "y", "z");
        trie.insert(20, "z");
        assert(0 == trie.lookup("x"));
        assert(1 == trie.lookup("x", "y"));
        assert(2 == trie.lookup("x", "y", "z"));
        assert(10 == trie.lookup("y"));
        assert(11 == trie.lookup("y", "z"));
        assert(20 == trie.lookup("z"));
        assert(null == trie.lookup("x", "y", "z", "a"));
        assert(null == trie.lookup("abc"));
        
        trie.insert(30, "a");
        trie.insert(31, "a", "b");
        trie.insert(32, "a", "b", "c");
        assert(30 == trie.lookupLongest("a"));
        assert(30 == trie.lookupLongest("a", "x"));
        assert(31 == trie.lookupLongest("a", "b"));
        assert(31 == trie.lookupLongest("a", "b", "x"));
        assert(32 == trie.lookupLongest("a", "b", "c"));
        assert(32 == trie.lookupLongest("a", "b", "c", "x"));
    }

}
