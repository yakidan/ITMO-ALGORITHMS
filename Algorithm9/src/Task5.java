import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task5 {
    FastScanner in;
    PrintWriter out;
    int used[];
    ArrayList<Integer> graph[];
    ArrayList<Integer> sort;

    void dfs(int i) {
        used[i] = 1;
        for (int j = 0; j < graph[i].size(); j++) {
            int to = graph[i].get(j);
            if (used[to] ==0) {
                dfs(to);
            }
        }
        used[i] = 2;
        sort.add(i);
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        used = new int[n];
        graph = new ArrayList[n];
        sort = new ArrayList();

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            used[i]=0;
        }
        int a, b;
        for (int i = 0; i < m; i++) {
            a = in.nextInt() - 1;
            b = in.nextInt() - 1;
            graph[a].add(b);
        }

        for (int i = 0; i <n ; i++) {
            if(used[i] !=1 &&used[i]!=2){
                dfs(i);
            }
        }
        Collections.reverse(sort);

//        for (int i = 0; i <sort.size() ; i++) {
//            out.println(sort.get(i));
//        }
        boolean ans;
        int k;
        for (int i = 0; i < sort.size() - 1; i++) {
            ans = false;
            k = sort.get(i);
            for (int j:graph[k]) {
                if(j==sort.get(i+1)){
                    ans=true;
                }
            }
//            for (int j = 0; j < graph[k].size(); j++) {
//                if (graph[k].get(j) == sort.get(i + 1)) {
//                    ans = true;
//                }
//            }
            if (!ans) {
                out.println("NO");
                return;
            }
        }
        out.println("YES");

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
                new FastScanner(new FileInputStream("hamiltonian.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("hamiltonian.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task5().run();
    }
}