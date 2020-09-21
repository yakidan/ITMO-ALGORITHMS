import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task2 {
    FastScanner in;
    PrintWriter out;
    int graph[][];
    boolean used[];
    int n;
    long[] dist;

    class Pair {
        int to;
        int weight;

        Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    void short_path(int start) {
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(used, false);
        dist[start] = 0;

        while (true) {
            int v = -1;
            for (int i = 0; i < n; i++)
                if (!used[i] && dist[i] < Long.MAX_VALUE && (v == -1 || dist[v] > dist[i]))
                    v = i;
            if (v == -1) break;
            used[v] = true;
            for (int to = 0; to < n; to++)
                if (!used[to] && graph[v][to] < Long.MAX_VALUE && graph[v][to] != -1)
                    dist[to] = min(dist[to], dist[v] + graph[v][to]);

        }
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        graph = new int[n][n];
        used = new boolean[n];
        dist = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            graph[in.nextInt() - 1][in.nextInt() - 1] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            short_path(i);
            for (int j = 0; j < n; j++) {
                out.print(dist[j] + " ");
            }
            out.println();
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
                new FastScanner(new FileInputStream("pathsg.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("pathsg.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}