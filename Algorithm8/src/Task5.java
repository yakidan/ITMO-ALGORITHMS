import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task5 {
    FastScanner in;
    PrintWriter out;
    int n;
    ArrayList[] graph;
    int short_distance[];
    boolean use[];


    int[] bfs(int s) {
        int inf = (int) 1e9;
        int[] dist = new int[n];
        Arrays.fill(dist, inf);
        int[] q = new int[30000];
        int ql = 0, qr = 0;
        dist[s] = 0;
        q[qr++] = s;
        while (ql < qr) {
            int v = q[ql++];
            for (Object u : graph[v]) {
                if (dist[(int) u] > dist[v] + 1) {
                    dist[(int) u] = dist[v] + 1;
                    q[qr++] = (int) u;
                }
            }
        }
        return dist;
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m=in.nextInt();
        graph = new ArrayList[n];
        short_distance = new int[n];
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
        //short_distance[0]=0;
      short_distance=bfs(0);

        for (int i = 0; i <n ; i++) {
            out.print(short_distance[i]+" ");
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
                new FastScanner(new FileInputStream("pathbge1.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("pathbge1.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();
    }
}