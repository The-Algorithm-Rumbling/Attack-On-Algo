import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, d;
    static List<List<int []>> list = new ArrayList<>();
    static int [] dist;
    static PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> {return Integer.compare(a[1], b[1]);});
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= d; i++) {
            list.add(new ArrayList<>()); // 노드의 개수만큼 생성
        }
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (e > d || dist >= e - s) continue;
            
            list.get(s).add(new int[] {e, dist});
        }

        dist = new int [d+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        pq.add(new int [] {0, 0});

        while (!pq.isEmpty()) {
            int [] q = pq.poll();
            int tmp = q[0];
            int tmpCost = q[1];

            if (tmpCost > dist[tmp]) continue;

            if (tmp + 1 <= d && tmpCost+1 < dist[tmp+1]) {
                dist[tmp+1] = tmpCost + 1;
                pq.add(new int [] {tmp+1, tmpCost+1});
            }

            for (int [] edge : list.get(q[0])) {
                int next = edge[0];
                int nextCost = tmpCost + edge[1];

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;
                    pq.add(new int [] {next, nextCost});
                }
            }
        }
        
        System.out.println(dist[d]);
    }
}