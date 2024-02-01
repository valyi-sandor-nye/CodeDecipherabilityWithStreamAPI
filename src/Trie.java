import java.util.HashMap;
/* source from
 * https://connect2grp.medium.com/understanding-trie-with-java-an-under-utilized-data-structure-b9a9cc50b9df
 */
public class Trie {
    private class TrieNode {
        // A map of child nodes indexed by the next character in the key
        private HashMap<Character, TrieNode> children;
        // A flag to indicate that this node represents the end of a key
        private boolean endOfKey;

        public TrieNode() {
            children = new HashMap<>();
            endOfKey = false;
        }
    }

    // The root node of the trie
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a key into the trie
    public void insert(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node;
        }
        current.endOfKey = true;
    }

    // Returns true if the trie contains the given key, false otherwise
    public boolean contains(String key) {
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfKey;
    }

    // Returns true if the trie contains a key that starts with the given prefix, false otherwise
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }

    public static void main(String[] args) {


        Trie trie = new Trie();
        trie.insert("ca\12t");
        trie.insert("ca\\r");
        trie.insert("ca\"rt");

        System.out.println(trie.contains("ca\12t")); // prints "true"
        System.out.println(trie.contains("dog")); // prints "false"
        System.out.println(trie.startsWith("ca")); // prints "true"
    }
}