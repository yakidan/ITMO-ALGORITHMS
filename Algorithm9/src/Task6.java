import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task6 {
    FastScanner in;
    PrintWriter out;
    boolean used[];
    int[] array_components;
    int color[];
    ArrayList<Integer> graph[];
    ArrayList<Integer> graphT[];
    ArrayList<ArrayList<Integer>> list_component;
    ArrayList<Integer> time;
    ArrayList<Integer> components;
    char[] ans;
    int[] dist;
    ArrayList<Integer> sort;
    boolean win_first = false;
    int k = 1;

    void dfs(int v) {
        int to;
        int num_color = 0;
        used[v] = true;
        for (int i = 0; i < graph[v].size(); i++) {
            to = graph[v].get(i);
            if (!used[to]) {
                dfs(to);
            }
            if (color[to] == 0) {
                num_color = 1;
            }
        }
        color[v] = num_color;
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        k = in.nextInt() - 1;
        used = new boolean[n];
        ans = new char[n];
        graph = new ArrayList[n];
        graphT = new ArrayList[n];
        color = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
        }
        dfs(k);
        if (color[k] == 1) {
            out.println("First player wins");
        } else {
            out.println("Second player wins");
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
                new FastScanner(new FileInputStream("game.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("game.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task6().run();
    }
}