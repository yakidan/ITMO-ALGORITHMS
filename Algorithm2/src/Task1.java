import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Collections.*;

public class Task1 {
    FastScanner in;
    PrintWriter out;

    public static void quickSort(int[] ar, int l, int r) {
        if (ar.length == 0)
            return;

        if (l >= r)
            return;

        int q = (int) ((Math.random() * ((r - l) + 1)) + l);
        int selected = ar[q];

        int i = l, j = r;
        while (i <= j) {
            while (ar[i] < selected) {
                i++;
            }
            while (ar[j] > selected) {
                j--;
            }
            if (i <= j) {//меняем местами
                int t = ar[i];
                ar[i] = ar[j];
                ar[j] = t;
                i++;
                j--;
            }
        }
        if (l < j)
            quickSort(ar, l, j);//для левой сортировки

        if (i < r)
            quickSort(ar, i, r);//для правой сортировки
    }

    void qs(int[] ar, int l, int r) {
        if (l>=r){
            return;
        }
        int q = (r - l) / 2;
        int per = ar[q];
        int i = l;
        int j = r;
        while (i <= j) {
            while (ar[i] < per) {
                i++;
            }
            while (ar[j] > per) {
                j--;
            }
            if(i<=j){
                int k=ar[j];
                ar[j]=ar[i];
                ar[i]=ar[j];
                i++;
                j--;
            }
        }
        if(l<j){
            qs(ar,l,j);
        }
        if(i<r){
            qs(ar,i,r);
        }
    }

    private void solve() throws IOException {
        int n = in.nextInt();
        int ar[] = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = in.nextInt();
        }
        quickSort(ar, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(ar[i] + " ");
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
                new FastScanner(new FileInputStream("sort.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("sort.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}