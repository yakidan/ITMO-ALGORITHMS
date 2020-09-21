import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task4 {
    FastScanner in;
    PrintWriter out;
    ArrayList<Pair> graph[];
    boolean used[];
    int n;
    long[] dist;
    ArrayList<Edge> edgelist;

    class Pair {
        int to;
        int weight;

        Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    class Edge {
        int from;
        int to;
        long weight;

        Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    void short_path(int s) {
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[s] = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < edgelist.size(); j++) {
                int from = edgelist.get(j).from;
                int to = edgelist.get(j).to;
                long weight = edgelist.get(j).weight;
                if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                    dist[to] = dist[from] + weight;
                }
            }
        }
        String[] ans = new String[n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < edgelist.size(); j++) {
                int from = edgelist.get(j).from;
                int to = edgelist.get(j).to;
               long weight = edgelist.get(j).weight;
                if (dist[from] != Long.MAX_VALUE && dist[from] + weight < dist[to]) {
                    dist[to] = (long)(-9*1e18);;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dist[i] <= -9*1e18) {
                ans[i] = "-";
            } else if (dist[i] == Long.MAX_VALUE) {
                ans[i] = "*";
            } else {
                ans[i] = String.valueOf(dist[i]);
            }
        }
       // ans[s] = "0";
        for (int i = 0; i < n; i++) {
            out.println(ans[i]);
        }

    }


    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        graph = new ArrayList[n];
        used = new boolean[n];
        dist = new long[n];
        edgelist = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int from = in.nextInt() - 1;
            int to = in.nextInt() - 1;
            long weight = in.nextLong();
            edgelist.add(new Edge(from, to, weight));
        }
        short_path(s - 1);
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
                new FastScanner(new FileInputStream("path.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("path.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();
    }
}