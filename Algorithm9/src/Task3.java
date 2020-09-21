import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task3 {
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer> graph[];
    boolean used[];
    int[] color;

    boolean ans = true;

    void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        used[s] = true;
        queue.offer(s);
        int to = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int i = 0; i < graph[v].size(); i++) {
                to = graph[v].get(i);
                if (!used[to]) {
                    queue.offer(to);
                    used[to] = true;
                }
                if (color[v] == color[to]) {
                    ans = false;
                    return;
                } else if (color[to] == -1) {
                    if (color[v] == 1) {
                        color[to] = 2;
                    } else {
                        color[to] = 1;
                    }

                }
            }

        }
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n + 1];
        used = new boolean[n + 1];
        color = new int[n + 1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            color[i] = -1;
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                color[i] = 1;
                bfs(i);
            }
        }


        if (ans) {
            out.println("YES");
        } else {
            out.println("NO");
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
                new FastScanner(new FileInputStream("bipartite.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("bipartite.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}