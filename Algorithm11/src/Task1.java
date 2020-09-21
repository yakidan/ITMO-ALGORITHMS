import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task1 {
    FastScanner in;
    PrintWriter out;
    int graph[][];
    boolean used[];
    int n;

    class Pair {
        int to;
        int weight;

        Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    long short_path(int start, int finish) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
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
        return dist[finish];
    }

    private void solve() throws IOException {
        n = in.nextInt();
        graph = new int[n][n];
        used = new boolean[n];
        int start = in.nextInt();
        int finish = in.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
            }
        }
        long ans = short_path(start - 1, finish - 1);
        if (ans != Long.MAX_VALUE)
            out.println(ans);
        else
            out.println(-1);

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
                new FastScanner(new FileInputStream("pathmgep.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("pathmgep.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}