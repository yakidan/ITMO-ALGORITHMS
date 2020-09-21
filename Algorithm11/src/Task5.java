import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task5 {
    FastScanner in;
    PrintWriter out;
    int graph[][];
    boolean used[];
    int n;
    int prev[];
    long[] dist;
    ArrayList<Edge> edgelist;


    class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    void cycle() {
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[0] = 0;
        int start = -1;
        for (int i = 0; i < n - 1; i++) {
            start = -1;
            for (int j = 0; j < edgelist.size(); j++) {
                int from = edgelist.get(j).from;
                int to = edgelist.get(j).to;
                int weight = edgelist.get(j).weight;
                if (dist[from] + weight < dist[to]) {
                    dist[to] = dist[from] + weight;
                    prev[to] = from;
                    start = to;
                }
            }
        }
        if (start == -1) {
            out.print("NO");
            return;
        } else {
            out.println("YES");
            ArrayList<Integer> path = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                start = prev[start];
            }
            int cur = start;
            while (true) {
                path.add(cur + 1);
                if (cur == start && path.size() != 1) {
                    break;
                }
                cur = prev[cur];
            }
            out.println(path.size());
            Collections.reverse(path);
            for (int j = 0; j < path.size(); j++) {
                out.print(path.get(j) + " ");
            }

        }

    }


    private void solve() throws IOException {
        n = in.nextInt();
        prev = new int[n];
        graph = new int[n][n];
        used = new boolean[n];
        dist = new long[n];
        edgelist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = in.nextInt();
                edgelist.add(new Edge(i, j, graph[i][j]));
                if (i == j && graph[i][j] < 0) {
                    out.println("YES");
                    out.println("2");
                    int k = i + 1;
                    out.print(k + " " + k);
                    return;
                }
            }
        }
        cycle();

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
                new FastScanner(new FileInputStream("negcycle.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("negcycle.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();
    }
}