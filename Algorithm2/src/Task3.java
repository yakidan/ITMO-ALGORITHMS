import java.io.*;
import java.util.*;

public class Task3 {

    FastScanner in;
    PrintWriter out;
    static String[] country;
    static String[] name;
    static long count = 0;

    public static void mergeSort(int[] elements, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(elements, low, mid);
            mergeSort(elements, mid + 1, high);
            merge(elements, low, mid, high);
        }
    }


    private static void merge(int[] arMain, int low, int mid, int high) {

        int n = high - low + 1;
        int[] Temp = new int[n];
        int i = low, j = mid + 1;
        int k = 0;

        while (i <= mid || j <= high) {
            if (i > mid) {
                Temp[k] = arMain[j];
                k++;
                j++;
            } else if (j > high) {
                Temp[k] = arMain[i];
                k++;
                i++;
            } else if
            (arMain[i] <= arMain[j]) {
                Temp[k] = arMain[i];
                k++;
                i++;

            }  else {
                Temp[k] = arMain[j];
                count+=((mid-i+1));
                k++;
                j++;
            }
        }
        for (j = 0; j < n; j++) {
            arMain[low + j] = Temp[j];

        }
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int number[] = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = in.nextInt();
        }
        int k=0;

        mergeSort(number, 0, n - 1);
        out.print(count);
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
                new FastScanner(new FileInputStream("inversions.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("inversions.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task3().run();
    }
}