import java.io.*;
import java.util.*;

public class Task2 {

    FastScanner in;
    PrintWriter out;
    static String[] country;
    static String[] name;

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
        String[] Temp1 = new String[n];
        String[] Temp2 = new String[n];
        int i = low, j = mid + 1;
        int k = 0;
        boolean b = false;
        while (i <= mid || j <= high) {
            if (i > mid) {
                Temp[k] = arMain[j];
                Temp1[k] = country[j];
                Temp2[k] = name[j];
                k++;
                j++;
            } else if (j > high) {
                Temp[k] = arMain[i];
                Temp1[k] = country[i];
                Temp2[k] = name[i];
                k++;
                i++;
            } else if
            (country[i].compareTo(country[j])<0) {
                Temp[k] = arMain[i];
                Temp1[k] = country[i];
                Temp2[k] = name[i];
                k++;
                i++;

            } else if ( country[i].compareTo(country[j])==0) {
                Temp[k] = arMain[i];
                Temp1[k] = country[i];
                Temp2[k] = name[i];
                k++;
                i++;
            } else {
                Temp[k] = arMain[j];
                Temp1[k] = country[j];
                Temp2[k] = name[j];
                k++;
                j++;
            }
        }
        String s1="fgd";
        String s2= "fsgd";
        for (j = 0; j < n; j++) {
            arMain[low + j] = Temp[j];
            country[low + j] = Temp1[j];
            name[low + j] = Temp2[j];
        }
    } // end merge

    private void solve() throws IOException {
        int n = in.nextInt();
        country = new String[n];
        name = new String[n];
        int number[] = new int[n];
        for (int i = 0; i < n; i++) {
            country[i] = in.next();
            name[i] = in.next();
            number[i] = (int) country[i].toCharArray()[0];
        }
        mergeSort(number, 0, number.length - 1);

        String str = "";
        for (int i = 0; i < n; i++) {
            if (!str.equals(country[i])) {
                str = country[i];
                out.println("=== " + str + " ===");
                out.print(name[i]);
            } else {
                out.print(name[i]);
            }
            if (i != n - 1) {
                out.println();
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
                new FastScanner(new FileInputStream("race.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("race.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}