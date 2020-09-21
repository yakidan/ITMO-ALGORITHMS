import java.io.*;
import java.util.*;

public class Lab5 {

    FastScanner in;
    PrintWriter out;


    class Pair {
        int item;
        int count;

        Pair(int item, int count) {
            this.item = item;
            this.count = count;
        }
    }

    Pair[] arr;
    int t = -1;

    void sift_down(Pair arr[], int n, int i) {
        int smallest = i;
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2
        if (l < n && arr[l].count < arr[smallest].count)
            smallest = l;

        if (r < n && arr[r].count < arr[smallest].count)
            smallest = r;
        if (smallest != i) {
            Pair swap = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = swap;
            sift_down(arr, n, smallest);
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

    void extractMin() {
        arr[0] = new Pair(arr[t].item, arr[t].count);
        arr[t] = null;
        t--;
        if (t >= 0) {
            sift_down(arr, t + 1, 0);
        }
    }

    void dicrease(int cur, int reItem) {
        int pos = 0;
        for (int i = 0; i < t + 1; i++) {
            if (cur == arr[i].item) {
                arr[i] = new Pair(arr[i].item, reItem);
                pos = i;
                break;
            }
        }
        sift_up(pos);
    }

    public void solve() throws IOException {
        arr = new Pair[1000002];

        int c = 0;
        int i = 0;
        int h = 0;
        int j = 0;
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("push")) {
                t++;
                arr[t] = new Pair(i, Integer.parseInt(s[1]));
                sift_up(t);
                j++;
            } else if (s[0].equals("extract-min")) {
                if (t < 0) {
                    out.println("*");
                } else {
                    out.println(arr[0].count);
                    extractMin();
                }
            } else {
                dicrease(Integer.parseInt(s[1]) - 1, Integer.parseInt(s[2]));
            }
            i++;
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
                new FastScanner(new FileInputStream("priorityqueue.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("priorityqueue.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Lab5().run();
    }
}