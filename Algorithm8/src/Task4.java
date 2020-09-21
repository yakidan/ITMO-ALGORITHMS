import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task4 {
    FastScanner in;
    PrintWriter out;
    int n;
    ArrayList[] graph;
    int number_of_component[];
    boolean use[];


    private void dfs(int vertex, int index) {

        use[vertex] = true;
        number_of_component[vertex] = index;
        for (int i = 0; i < graph[vertex].size(); i++) {
            if (!use[(int) graph[vertex].get(i)]) // если i-ая вершина не пройдена и есть путь vertex -> i
            {
                dfs((int) graph[vertex].get(i), index);
            }
        }
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m=in.nextInt();
        graph = new ArrayList[n];
        number_of_component = new int[n];
        use = new boolean[n];
        for (int i = 0; i <n ; i++) {
            graph[i]=new ArrayList();
        }
        int from;
        int to;
        for (int i = 0; i < m; i++) {
            from=in.nextInt()-1;
            to=in.nextInt()-1;
            graph[from].add(to);
            graph[to].add(from);

        }
        int j = 1;
        for (int i = 0; i < n; i++) {
            if (!use[i]) {
                dfs(i, j);
                j++;
            }
        }

        out.println(j-1);
        for (int i = 0; i <n ; i++) {
            out.print(number_of_component[i]+" ");
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
                new FastScanner(new FileInputStream("components.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("components.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();
    }
}