package tennisClub;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


// Methods to solve this problem
// 1. using sweep line O(max(time))
// 2. by sorting both start & end seprately and traversing O(n logn)
// 3. using priority queue O(n logn)
// 4. using two for loops O(n*n)


public class TennisClub {

    // sweepline approach
    // TC: O(maxTime)
    // works when time range is small and discrete
    public int getMinimumTennisCourts1(List<int[]> intvl) {
        if (intvl.isEmpty()) return 0;

        // Find max end time to size our timeline
        int maxTime = 0;
        for (int[] i : intvl) {
            maxTime = Math.max(maxTime, i[1]);
        }

        int[] timeline = new int[maxTime + 2];
        for (int[] i : intvl) {
            timeline[i[0]] += 1;
            timeline[i[1]] -= 1;
        }

        int ongoing = 0, maxCourts = 0;
        for (int t = 0; t <= maxTime; ++t) {
            ongoing += timeline[t];
            maxCourts = Math.max(maxCourts, ongoing);
        }

        return maxCourts;
    }

    // sort start[] and end[] separately
    // TC: O(n log n)
    public int getMinimumTennisCourts2(List<int[]> intvl) {
        if (intvl.isEmpty()) return 0;

        int n = intvl.size();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; ++i) {
            start[i] = intvl.get(i)[0];
            end[i] = intvl.get(i)[1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int i = 0, j = 0, courts = 0, maxCourts = 0;
        while (i < n && j < n) {
            if (start[i] < end[j]) {
                courts++;
                i++;
            } else {
                courts--;
                j++;
            }
            maxCourts = Math.max(maxCourts, courts);
        }

        return maxCourts;
    }

    // using priority Queue
    // TC: O(n log n)
    public int getMinimumTennisCourts3(List<int[]> intvl) {
        if (intvl.isEmpty()) return 0;

        intvl.sort((a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;

        for (int i = 0; i < intvl.size(); ++i) {
            if (!pq.isEmpty() && pq.peek() <= intvl.get(i)[0]) pq.poll();
            pq.offer(intvl.get(i)[1]);

            ans = Math.max(ans, pq.size());
        }

        return ans;
    }


    // TC O(n * n)
    public int getMinimumTennisCourts4(List<int[]> intvl) {
        if (intvl.isEmpty()) return 0;

        intvl.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        List<Integer> days = new ArrayList<>();
        days.add(intvl.get(0)[1]);

        for (int i = 1; i < intvl.size(); ++i) {
            boolean added = false;
            for (int j = 0; j < days.size(); ++j) {
                if (days.get(j) <= intvl.get(i)[0]) {
                    days.set(j, intvl.get(i)[1]);
                    added = true;
                    break;
                }
            }

            if (!added) {
                days.add(intvl.get(i)[1]);
            }
        }

        return days.size();
    }
}
