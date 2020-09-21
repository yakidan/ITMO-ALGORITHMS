import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task6 {
    FastScanner in;
    PrintWriter out;
    int n;
    ArrayList<Top> graph;

    String letter(Top from, Top to) {
        String ans = "";
        if (from.i - to.i == 0) {
            if (from.j - to.j > 0) {
                ans = "L";
            } else {
                ans = "R";
            }
        } else if (from.j - to.j == 0) {
            if (from.i - to.i > 0) {
                ans = "U";
            } else {
                ans = "D";
            }
        }
        return ans;
    }

       String bfs1(int s,int finish) {
        int[] que = new int[10001];

        int[] dist = new int[graph.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        int que_left = 0, que_right = 0;
        String[] way = new String[graph.size()];
        way[s] = "";
        que[que_right++] = s;

        Top cur;
        int u;
        while (que_left < que_right) {
            int v = que[que_left++];
            cur = graph.get(v);
            for (int i = 0; i < cur.to.size(); i++) {
                u = cur.to.get(i);
                if (dist[u] > dist[v] + 1) {
                    dist[u] = dist[v] + 1;
                    way[u] = way[v] + letter(cur, graph.get(u));
                    que[que_right++] = u;
                }
            }
        }

        return way[finish];
    }
    String bfs2(int s,int finish) {
        Queue<Integer> queue = new LinkedList<>();
        int inf = (int) 1e9;
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, inf);
        dist[s] = 0;

        String[] way = new String[graph.size()];
        way[s] = "";
        queue.offer(s);
        //que[que_right++] = s;
        Top cur;
        int u;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            cur = graph.get(v);
            for (int i = 0; i < cur.to.size(); i++) {
                u = cur.to.get(i);
                if (dist[u] > dist[v] + 1) {
                    dist[u] = dist[v] + 1;
                    way[u] = way[v] + letter(cur, graph.get(u));
                    queue.offer(u);
                }
            }
        }

        return way[finish];
    }

    public class Top {
        int i;
        int j;
        int index;
        ArrayList<Integer> to = new ArrayList<Integer>();

        Top(int i, int j, int index) {
            this.i = i;
            this.j = j;
            this.index = index;
        }
    }

    private void solve() throws IOException {
        n = in.nextInt();
        int m = in.nextInt();



        Top top =new Top(1,2,3);

        Top []tops =new Top[2];




        char table[][] = new char[n][m];
        int help_table[][] = new int[n][m];
        int index_start = 0;
        int index_finish = 0;
        graph = new ArrayList<>();
        int l = 0;
        String s1="";
        for (int i = 0; i < n; i++) {
             s1 = in.next();
            for (int j = 0; j < m; j++) {
                table[i][j] = s1.charAt(j);
                if (table[i][j] == '.') {
                    graph.add(new Top(i, j, l));
                    help_table[i][j] = l;
                    l++;
                } else if (table[i][j] == 'S') {
                    graph.add(new Top(i, j, l));
                    table[i][j] = '.';
                    help_table[i][j] = l;
                    index_start = l;
                    l++;
                } else if (table[i][j] == 'T') {
                    graph.add(new Top(i, j, l));
                    help_table[i][j] = l;
                    table[i][j] = '.';
                    index_finish = l;
                    l++;
                }
            }
        }
        l = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (table[i][j] == '.') {
                    if (i - 1 >= 0 && table[i - 1][j] == '.') {
                        graph.get(l).to.add(help_table[i-1][j]);
                    }
                    if (i + 1 < n && table[i + 1][j] == '.') {
                        graph.get(l).to.add(help_table[i+1][j]);
                    }
                    if (j + 1 < m && table[i][j + 1] == '.') {
                        graph.get(l).to.add(help_table[i][j+1]);
                    }
                    if (j - 1 >= 0 && table[i][j - 1] == '.') {
                        graph.get(l).to.add(help_table[i][j-1]);
                    }
                    l++;
                }
            }
        }

        String s = bfs2(index_start,index_finish);
        if(s!=null) {
            out.println(s.length());
            out.println(s);
        }else {
            out.println(-1);
        }
        //        for (int i = 0; i <s.length ; i++) {
//            out.println(s[i]);
//        }

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
                new FastScanner(new FileInputStream("input.txt"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("output.txt"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task6().run();
    }
}