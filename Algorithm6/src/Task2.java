import java.io.*;
import java.util.*;

public class Task2 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Integer> arr[];

    class Top {
        Top left;

        public void setLeft(Top left) {
            this.left = left;
        }

        public void setRight(Top right) {
            this.right = right;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setRoot(Top root) {
            this.root = root;
        }

        Top root;
        Top right;
        int count;

        Top(int count, Top left, Top right, Top root) {
            this.count = count;
            this.left = left;
            this.right = right;
            this.root = root;
        }
    }

    public int heightOfBinaryTree(Top node) {
        if (node == null) {
            return 0;
        } else {
            return 1 +
                    Math.max(heightOfBinaryTree(node.left),
                            heightOfBinaryTree(node.right));
        }
    }

    boolean isTree(Top root) {
        return check(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean check(Top v, int min, int max) {
        if (v == null) {
            return true;
        }
        if (v.count <= min || v.count >= max) {
            return false;
        }
        return  (check(v.left,min,v.count) && check(v.right,v.count,max));
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int[] count = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        if (n == 0) {
            out.print("YES");
            return;
        }
        if (n == 1) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            out.print("YES");
            return;
        }
        for (int i = 0; i < n; i++) {
            count[i] = in.nextInt();
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }
        Top top[] = new Top[n];

        for (int i = 0; i < n; i++) {
            top[i] = new Top(0, null, null, null);
        }

        for (int i = 0; i < n; i++) {
            if (left[i] == 0 && right[i] == 0) {
                top[i].setCount(count[i]);
            } else if (left[i] != 0 && right[i] == 0) {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);
            } else if (left[i] == 0 && right[i] != 0) {
                top[i].setCount(count[i]);
                top[i].setRight(top[right[i] - 1]);
                top[right[i] - 1].setRoot(top[i]);
            } else {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[i].setRight(top[right[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);
                top[right[i] - 1].setRoot(top[i]);
            }
        }
        Top root = top[0];
        boolean bol = true;
        bol=isTree(root);
        if (bol) {
            out.print("YES");
        } else {
            out.print("NO");
        }

    }

 void solveA(){

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
                new FastScanner(new FileInputStream("check.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("check.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}