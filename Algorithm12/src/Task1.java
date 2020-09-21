import java.io.*;
import java.util.*;

public class Task1 {
    FastScanner in;
    PrintWriter out;
    int n;
    int path[];

    long graph[][];
    boolean visited[];
    Queue<Integer> queue;


    public boolean bfs(int s, int t) {
        boolean pathFound = false;
        int cur;
        for (int i = 0; i < n; i++) {
            path[i] = -1;
            visited[i] = false;
        }

        queue.add(s);
        path[s] = -1;
        visited[s] = true;

        while (!queue.isEmpty()) {
            cur = queue.remove();

            for (int i = 0; i < n; i++) {
                if (graph[cur][i] != 0 && !visited[i]) {
                    path[i] = cur;
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        if (visited[t]) {
            pathFound = true;
        }
        return pathFound;
    }

    long max_trafic(int s, int t) {
        long sum = 0;
        int from, to;

        long pathFlow;

        while (bfs(s, t)) {
            pathFlow = Integer.MAX_VALUE;
            for (to = t; to != s; to = path[to]) {
                from = path[to];
                pathFlow = Math.min(pathFlow, graph[from][to]);
            }
            for (to = t; to != s; to = path[to]) {
                from = path[to];
                graph[from][to] -= pathFlow;
                graph[to][from] += pathFlow;
            }
            sum += pathFlow;
        }

        return sum;
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        graph = new long[n][n];
        visited = new boolean[n];
        path = new int[1000000];

        queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long trafic = in.nextLong();
            graph[from][to] = trafic;
        }
        out.print(max_trafic(0, n - 1));

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
                new FastScanner(new FileInputStream("maxflow.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("maxflow.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}