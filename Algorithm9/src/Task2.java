import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task2 {
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer> graph[];
    int used[];
    int[] path;

    int cycle_start = -1;
    int cycle_end = -1;

    void dfs(int i) {

        used[i] = 1;
        for (int j = 0; j < graph[i].size(); j++) {
            int to = graph[i].get(j);
            if (used[to] == 0) {
                path[to] = i;
                dfs(to);
            }
            if (used[to] == 1) {
                cycle_start = to;
                cycle_end = i;
            }
        }
        used[i] = 2;
    }

//    void dfsB(int cycle_start,int cycle_end) {
//        used[i] = 1;
//        for (int j = 0; j < graph[i].size(); j++) {
//            int to = graph[i].get(j);
//            if (used[to] == 1) {
//                cycle = to;
//            }
//            if (used[to] == 0) {
//                dfs(to);
//            }
//        }
//        used[i] = 2;
//    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n + 1];
        used = new int[n + 1];
        path = new int[n + 1];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            used[i] = 0;
        }
        for (int i = 0; i < m; i++) {
            graph[in.nextInt() - 1].add(in.nextInt() - 1);
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (used[i] == 0) {
                dfs(i);
                if (cycle_start != -1) {
                    out.println("YES");
                    for (int j = cycle_end; j != cycle_start; j = path[j]) {
                        ans.add(j + 1);
                        // out.print(j + 1 + " ");
                    }
                    ans.add(cycle_start + 1);
                    //  out.print(cycle_start + 1);
                    for (int j = ans.size()-1; j >=0 ; j--) {
                        out.print(ans.get(j)+" ");
                    }
                    return;
                }

            }
        }
        out.println("NO");


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
                new FastScanner(new FileInputStream("cycle.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("cycle.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}