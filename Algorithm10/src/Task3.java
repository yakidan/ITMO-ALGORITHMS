import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task3 {
    FastScanner in;
    PrintWriter out;

    ArrayList<Top> graph[];
    boolean used[];
    long count[];
    PriorityQue que = new PriorityQue(1000000);

    class Top {
        int to;
        int weight;

        Top(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    class Pair {
        int from;
        int to;
        int count;

        Pair(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }
    }

    long prim(int v) {
        used[v] = true;
        HashSet<String> hashSet = new HashSet();
        for (int i = 0; i < graph[v].size(); i++) {
            que.push(v, graph[v].get(i).to, graph[v].get(i).weight);
          //  hashSet.add(String.valueOf(v) + String.valueOf(graph[v].get(i).to));
        }
        Pair pair;
        Top top;
        String s1, s2;
        long ans = 0;
        while (!que.isEmpty()) {
            pair = que.extractMin();
            if (!used[pair.to]) {
                v = pair.to;
                used[v] = true;
                ans += pair.count;
                for (int i = 0; i < graph[v].size(); i++) {
                    top = graph[v].get(i);
                    s1 = v + String.valueOf(top.to);
                    s2 = top.to + String.valueOf(v);

                        que.push(v, top.to, top.weight);
                     //   hashSet.add(String.valueOf(v) + String.valueOf(graph[v].get(i).to));

                }
            }
        }
        return ans;
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int m = in.nextInt();
        graph = new ArrayList[n];
        used = new boolean[n];
        count = new long[n];
        int from, to, weight;
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            from = in.nextInt() - 1;
            to = in.nextInt() - 1;
            weight = in.nextInt();
            graph[from].add(new Top(to, weight));
            graph[to].add(new Top(from, weight));
        }
        Arrays.fill(count, Long.MAX_VALUE);
        out.println(prim(0));
    }

    class PriorityQue {
        int i = 0;
        Pair[] arr;
        int t = -1;

        PriorityQue(int size) {
            this.arr = new Pair[size];
        }

        void sift_down(Pair arr[], int n, int i) {
            int largest = i;
            int l = 2 * i + 1; // левый = 2*i + 1
            int r = 2 * i + 2; // правый = 2*i + 2
            if (l < n && arr[l].count < arr[largest].count)
                largest = l;

            if (r < n && arr[r].count < arr[largest].count)
                largest = r;
            if (largest != i) {
                Pair swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;
                sift_down(arr, n, largest);
            }
        }

        void sift_up(int i) {
            while (i > 0 && arr[i].count < arr[(i - 1) / 2].count) {
                Pair swap = arr[i];
                arr[i] = arr[(i - 1) / 2];
                arr[(i - 1) / 2] = swap;
                i = (i - 1) / 2;
            }
        }

        Pair extractMin() {
            Pair out = arr[0];
            arr[0] = new Pair(arr[t].from, arr[t].to, arr[t].count);
            arr[t] = null;
            t--;
            if (t >= 0) {
                sift_down(arr, t + 1, 0);
            }
            return out;
        }

        void push(int from, int to, int weight) {
            t++;
            arr[t] = new Pair(from, to, weight);
            sift_up(t);
        }

        boolean isEmpty() {
            return t < 0 ? true : false;
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
                new FastScanner(new FileInputStream("spantree3.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("spantree3.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}