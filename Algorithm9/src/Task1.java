import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task1 {
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer> graph[];
    int used[];
    ArrayList<Integer> sort;
    boolean cycle = false;

    void dfs(int i) {

        used[i] = 1;
        for (int j = 0; j < graph[i].size(); j++) {
            int to = graph[i].get(j);
            if (used[to] == 1) {
                cycle = true;
            }
            if (used[to] == 0) {
                dfs(to);
            }
        }
        used[i] = 2;
        sort.add(i);
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n + 1];
        used = new int[n + 1];
        sort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            used[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            graph[in.nextInt() - 1].add(in.nextInt() - 1);
        }

        for (int i = 0; i < n; i++) {
            if (used[i] == 0) {
                dfs(i);
            }
        }
        if (cycle) {
            out.println(-1);
        } else {
            for (int i = sort.size() - 1; i >= 0; i--) {
                out.print(sort.get(i) + 1 + " ");
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
                new FastScanner(new FileInputStream("topsort.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("topsort.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}