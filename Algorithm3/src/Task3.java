import java.io.*;
import java.util.*;

public class Task3 {

    FastScanner in;
    PrintWriter out;
    static String[] country;
    static String[] name;
    static long count = 0;

    public void sort(int arr[]) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
        heapify(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }

    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        boolean b = true;
        for (int i = 0; i <= n; i++) {
            if (2 * i + 1 < n) {
                if (arr[i] > arr[2 * i + 1]) {
                    b = false;
                    break;
                }
            }
            if (2 * i + 2 < n) {
                if (arr[i] > arr[2 * i + 2]) {
                    b = false;
                    break;
                }
            }
        }
        if (b) {
            out.print("YES");
        } else {
            out.print("NO");
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
                new FastScanner(new FileInputStream("isheap.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("isheap.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}