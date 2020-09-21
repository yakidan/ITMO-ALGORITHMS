import java.io.*;
import java.util.*;

public class Task3 {
    FastScanner in;
    PrintWriter out;
    int n;
    int m;
    int path[];
    int match[];
    boolean visited[];
    boolean used[];
    private int[] level;

    static final long INF = Long.MAX_VALUE / 2;

    int s, t;

    protected boolean solved;

    protected long maxFlow;

    protected List<Edge>[] graph;

    private static class Edge {
        public int from, to;
        public Edge residual;
        public long flow;
        public final long capacity;

        public Edge(int from, int to, long capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }

        public boolean isResidual() {
            return capacity == 0;
        }

        public void augment(long bottleNeck) {
            flow += bottleNeck;
            residual.flow -= bottleNeck;
        }

        public long remainingCapacity() {
            return capacity - flow;
        }
    }

    boolean bfs() {
        Arrays.fill(level, -1);
        Deque<Integer> q = new ArrayDeque<>(n);
        q.offer(s);
        level[s] = 0;
        while (!q.isEmpty()) {
            int top = q.poll();
            for (Edge edge : graph[top]) {
                long cap = edge.remainingCapacity();
                if (cap > 0 && level[edge.to] == -1) {
                    level[edge.to] = level[top] + 1;
                    q.offer(edge.to);
                }
            }
        }
        return level[t] != -1;
    }

     long dfs(int to, int[] next, long flow) {
        if (to == t) return flow;

        for (; next[to] < graph[to].size(); next[to]++) {
            Edge edge = graph[to].get(next[to]);
            long cap = edge.remainingCapacity();
            if (cap > 0 && level[edge.to] == level[to] + 1) {

                long bottleNeck = dfs(edge.to, next, Math.min(flow, cap));
                if (bottleNeck > 0) {
                    edge.augment(bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return 0;
    }


    private void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        graph = new ArrayList[n];
        visited = new boolean[n];
        used = new boolean[n];
        match = new int[m];
        Arrays.fill(match, -1);
        for (int i = 0; i < m; i++) {
            
        }

        int[] next = new int[n];

        while (bfs()) {
            Arrays.fill(next, 0);

            for (long f = dfs(s, next, INF); f != 0; f = dfs(s, next, INF)) {
                maxFlow += f;
            }
        }
    }

class FastScanner {
    StringTokenizer st;
    BufferedReader br;

    FastScanner(InputStream s) {
        br = new BufferedReader(new InputStreamReader(s));
    }

    String next() throws IOException {
        while (st == null || !st.hasMoreTokens())
            st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }

    boolean hasNext() throws IOException {
        return br.ready() || (st != null && st.hasMoreTokens());
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    String nextLine() throws IOException {
        return br.readLine();
    }

    boolean hasNextLine() throws IOException {
        return br.ready();
    }

}

    private void run() throws IOException {
        in = //new FastScanner(System.in);
                new FastScanner(new FileInputStream("matching.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("matching.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}