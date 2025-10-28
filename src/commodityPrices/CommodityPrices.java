package commodityPrices;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// problem statement
//Commodity Prices
//Imagine you are given a stream of data points consisting of <timestamp, commodityPrice> you are supposed to return the maxCommodityPrice at any point in time.
//The timestamps in the stream can be out of order, or there can be duplicate timestamps, we need to update the commodityPrice at that particular timestamp if an entry for the timestamp already exists
//Create an in-memory solution tailored to prioritize frequent reads and writes for the given problem statement

// ask clarifying questions about frequency of method calls..

// brute force (good solution if max/min method calls are not frequent)
// keep a map of timestamp and prices
// iterate map everytime you want to find max/min price
// update() - O(1)
// current() - O(1)
// maximum() & minimum() - O(n)


// optimised
// keep a map of timestamp and prices
// keep a treemap of prices as key and set of timestamps as value TreeMap<INtegetr, Set<Integer>>
// find max/min using treemap
// update() - O(log n)
// current() - O(1)
// maximum() & minimum() - O(log n)


// optimised
// keep a map of timestamp and prices
// keep a treemap of prices as key and set of timestamps as value TreeMap<INtegetr, Set<Integer>>
// find max/min using treemap
// update() - O(log n)
// current() - O(1)
// maximum() & minimum() - O(log n)


// optimised
// keep a map of timestamp and prices
// keep track of max and minimum prices using two heaps (max heap and min heap)
// update() - O(log n)
// current() - O(1)
// maximum() & minimum() - O(log n)


public class CommodityPrices {
    PriorityQueue<int[]> mx;
    PriorityQueue<int[]> mn;
    Map<Integer, Integer> map;
    int currPrice = 0;
    int currTime = 0;

    public CommodityPrices() {
        mx = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        mn = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        map = new HashMap<>();
    }

    public void update(int timestamp, int price) {
        mx.offer(new int[]{timestamp, price});
        mn.offer(new int[]{timestamp, price});
        map.put(timestamp, price);

        if (timestamp >= currTime) {
            currTime = timestamp;
            currPrice = price;
        }
    }

    public int current() {
        return currPrice;
    }

    public int maximum() {
        if (mx.isEmpty()) return 0;

        int[] max = mx.peek();
        if (map.getOrDefault(max[0], -1) == max[1]) return max[1];

        mx.poll();
        return maximum();
    }

    public int minimum() {
        if (mn.isEmpty()) return 0;

        int[] min = mn.peek();
        if (map.getOrDefault(min[0], -1) == min[1]) return min[1];

        mn.poll();
        return minimum();
    }
}