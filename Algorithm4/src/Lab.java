import java.io.*;
import java.util.*;

public class Lab {

    FastScanner in;
    PrintWriter out;
    int[] arr;
    int t = -1;

    public void push(int cur) {
        arr[t] = cur;
        sift();
        //sift_up(t);
    }

    void heapify_down(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1; // левый = 2*i + 1
        int r = 2 * i + 2; // правый = 2*i + 2
        if (l < n && arr[l] < arr[largest])
            largest = l;
        if (r < n && arr[r] < arr[largest])
            largest = r;
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify_down(arr, n, largest);
        }
    }

    void sift_up(int i) {
        while (arr[i] < arr[(i - 1) / 2]) {
            int swap = arr[i];
            arr[i] = arr[(i - 1) / 2];
            arr[(i - 1) / 2] = swap;
            i = (i - 1) / 2;
        }
    }

    void sift() {
        for (int i = (t+1) / 2 - 1; i >= 0; i--)
            heapify_down(arr, t+1, i);
    }
    void extractMin() {
        arr[0] = arr[t];
        arr[t] = Integer.MAX_VALUE;
        t--;
        sift();
        // heapify_down(arr, t + 1, 0);
    }

    void dicrease(int cur, int reItem) {
        int pos = 0;
        for (int i = 0; i < t + 1; i++) {
            if (cur == arr[i]) {
                arr[i] = reItem;
                pos = i;
                break;
            }
        }
        for (int i = (t+1) / 2 - 1; i >= 0; i--)
            heapify_down(arr, t+1, i);
//        heapify_down(arr, t + 1, pos);
//        sift_up(pos);
//        heapify_down(arr, t + 1, 0);
    }

    public void solve() throws IOException {
        arr = new int[2000002];
        Arrays.fill(arr, Integer.MAX_VALUE);
        int c = 0;
        int br[] = new int[2000002];
        int h = 0;
        int j = 0;
        while (in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            if (s[0].equals("push")) {
                t++;
                c = Integer.parseInt(s[1]);
                push(c);
                br[j] = c;
                j++;
            } else if (s[0].equals("extract-min")) {
                if (t < 0) {
                    out.println("*");
                } else {
                    out.println(arr[0]);
                    extractMin();
                }
            } else {
                int q = br[Integer.parseInt(s[1]) - 1];
                dicrease(q, Integer.parseInt(s[2]));
            }

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
        new Lab().run();
    }
}