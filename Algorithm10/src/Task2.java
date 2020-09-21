import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task2 {
    FastScanner in;
    PrintWriter out;

    Top graph[];
    boolean used[];
    int dist[];
    int table_dist[][];
    int n;

    class Top {
        int x;
        int y;

        Top(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void findMST() {

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0] = 0;
        for (int i = 0; i < n; ++i) {
            int v = -1;
            for (int j = 0; j < n; ++j)
                if (!used[j] && (v == -1 || dist[j] < dist[v]))
                    v = j;
            used[v] = true;

            for (int to = 0; to < n; ++to)
                if (!used[to]&& table_dist[v][to] < dist[to]) {
                    dist[to] = table_dist[v][to];
                }
        }
    }

    int calc_distance(Top a, Top b) {
        return (a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y);
    }

    private void solve() throws IOException {
        n = in.nextInt();
        graph = new Top[n ];
        used = new boolean[n ];
        dist = new int[n];
        int a, b;
        for (int i = 0; i < n; i++) {
            a = in.nextInt();
            b = in.nextInt();
            graph[i] = new Top(a, b);
        }
        table_dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j =0; j < n; j++) {
                table_dist[i][j] = calc_distance(graph[i], graph[j]);
                table_dist[j][i] = table_dist[i][j];
            }
        }

        findMST();
        double ans=0;
        for (int i = 0; i < n; i++) {
            ans+=Math.sqrt(dist[i]);
        }
        out.println(ans);

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
                new FastScanner(new FileInputStream("spantree.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("spantree.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}