package popularContent;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


//Popular content
//Imagine you are given a stream of content ids along with an associated action to be performed on them
//Example of contents are video, pages, posts etc. There cam be two actions associated with a content id:
//• increasePopularity -> increases the popularity of the content by 1. The popularity increases when someone comments on the content or likes the comtent
//• decreasePopularity-> decreases the popularity of the content by 1. The popularity decreases when a spam bot’s/users comments are deleted from the content or its likes are removed from the content
//• content ids are positive integers
//Implement a class that can return the mostPopular content id at any time while consuming the stream of content ids and its associated action. If there are no contentIds with popularity greater than 0, return -1


// Brute Force
// keep track of key & frequency in hashmap and list
// Map<Integer, Integer> => map(id, ids_frequency)
// List<Set<Integer>> => index of list represents freq, and set of ids represents all the ids with that frequency

// increase() - O(n)
// decrease() - O(n)
// getMostPopular() - O(n)


// Optimised approach
// keep track of key & frequency in hashmap and treemap
// Map<Integer, Integer> => map(id, ids_frequency)
// TreeMap<Integer, Set<Integer>> => key of entry represents freq, and set of ids represents all the ids with that

// increase() - O(log n)
// decrease() - O(log n)
// getMostPopular() - O(log n)


// Most Optimised approach
// keep track of key & frequency in hashmap and doubly-linked-list
// Map<Integer, Integer> => map(id, ids_frequency)
// Node{int freq, Set<Integers>}

// increase() - O(1)
// decrease() - O(1)
// getMostPopular() - O(1)


public class PopularContent {

    Node head;
    Node tail;
    Map<Integer, Node> map = new HashMap<>();

    public PopularContent() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public void increase(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            int freq = node.freq;
            node.keys.remove(key);

            Node nextNode = node.next;
            if (nextNode == tail || nextNode.freq != freq + 1) { // node with increased freq is not present in out doubly-linked-list, so we will add it
                Node newNode = addNodeBetween(node, nextNode, freq + 1, key);
                map.put(key, newNode);
            } else {
                nextNode.keys.add(key);
                map.put(key, nextNode);
            }

            if (node.keys.isEmpty()) {
                removeNode(node);
            }
        } else {
            Node firstNode = head.next;
            if (firstNode == tail || firstNode.freq > 1) {
                Node newNode = addNodeBetween(head, firstNode, 1, key);
                map.put(key, newNode);
            } else {
                firstNode.keys.add(key);
                map.put(key, firstNode);
            }
        }
    }

    public void decrease(int key) {
        if (!map.containsKey(key)) {
            return;
        }

        Node node = map.get(key);
        node.keys.remove(key);
        int freq = node.freq;

        if (freq == 1) {
            map.remove(key);
        } else {
            Node prevNode = node.prev;
            if (prevNode == head || prevNode.freq != freq - 1) {
                Node newNode = addNodeBetween(prevNode, node, freq - 1, key);
                map.put(key, newNode);
            } else {
                prevNode.keys.add(key);
                map.put(key, prevNode);
            }
        }

        if (node.keys.isEmpty()) {
            removeNode(node);
        }
    }

    public Integer getMostPopular() {
        if (tail.prev == head) {
            return null;
        }
        Node node = tail.prev;
        return node.freq > 0 ? node.keys.iterator().next() : -1;
    }

    public Integer getLeastPopular() {
        if (head.next == tail) {
            return null;
        }

        Node node = head.next;
        return node.freq > 0 ? node.keys.iterator().next() : -1;
    }

    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private Node addNodeBetween(Node prev, Node next, int freq, int key) {
        Node newNode = new Node(freq);
        newNode.keys.add(key);
        newNode.prev = prev;
        newNode.next = next;
        prev.next = newNode;
        next.prev = newNode;
        return newNode;
    }

}

class Node {
    int freq;
    Node prev;
    Node next;
    Set<Integer> keys = new HashSet<>();

    Node(int freq) {
        this.freq = freq;
    }
}
