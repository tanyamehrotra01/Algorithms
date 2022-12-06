class Pair {
    int node;
    int weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
class Solution {
    int MAX = Integer.MAX_VALUE;
    public int networkDelayTime(int[][] times, int n, int k) {
        if(k > n) return -1;

        int distance[] = new int[n+1];
        for(int i = 0; i <= n; i++) {
            distance[i] = MAX;
        }
        distance[k] = 0;

        List<Pair>[] graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] time: times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new Pair(v, w));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        pq.offer(new Pair(k, 0));

        while(!pq.isEmpty()) {
            Pair currNode = pq.poll();
            int u = currNode.node;

            if(distance[u] < currNode.weight) {
                continue;
            }

            List<Pair> connections = graph[u];
            for(Pair pair: connections) {
                int v = pair.node;
                int w = pair.weight;
                if(distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    pq.offer(new Pair(v, w));
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if(distance[i] == MAX)
                return -1;
            ans = Math.max(ans, distance[i]);
        }
        return ans;
    }
}
