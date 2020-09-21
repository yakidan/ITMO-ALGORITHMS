import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task4 {
    FastScanner in;
    PrintWriter out;
    boolean used[];
    int[] array_components;
    int used_color[];
    ArrayList<Integer> graph[];
    ArrayList<Integer> graphT[];
    ArrayList<ArrayList<Integer>> list_component;
    ArrayList<Integer> time;
    ArrayList<Integer> components;
    ArrayList<Integer> sort;
    boolean ans = true;
    boolean cycle = false;
    int k = 1;

    void dfs(int v) {
        used[v] = true;
        int to;
        for (int i = 0; i < graph[v].size(); i++) {
            to = graph[v].get(i);
            if (!used[to]) {
                dfs(to);
            }
        }
        time.add(v);

    }

    void dfs2(int v, int num_component) {
        used[v] = true;
        int to;
        array_components[v] = num_component;
        components.add(v);
        for (int i = 0; i < graphT[v].size(); i++) {
            to = graphT[v].get(i);
            if (!used[to]) {
                dfs2(to, num_component);
            }
        }
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new boolean[n];
        array_components = new int[n];
        graph = new ArrayList[n];
        graphT = new ArrayList[n];
        components = new ArrayList<>();
        time = new ArrayList<>();
        list_component = new ArrayList<>();
        used_color = new int[n + 1];
        sort = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            graphT[i] = new ArrayList<>();
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
            graphT[b].add(a);
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                dfs(i);
            }
        }
        Arrays.fill(used, false);
        int num_component = 1;
        for (int i = 0; i < n; i++) {
            int v = time.get(n - 1 - i);
            if (!used[v]) {
                dfs2(v, num_component);
                list_component.add(components);
                components.clear();
                num_component++;
            }
        }

        out.println(--num_component);
        for (int i = 0; i < n; i++) {
            out.print(array_components[i] + " ");
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
                new FastScanner(new FileInputStream("cond.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("cond.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();
    }
}