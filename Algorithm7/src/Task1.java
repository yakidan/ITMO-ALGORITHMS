import java.awt.List;
import java.io.*;
import java.util.*;

public class Task1 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Integer> arr[];

    class Top {
        Top left;
        Top right;
        int count;

        public void setHeight(int height) {
            this.height = height;
        }
        int height;
        public void setLeft(Top left) {
            this.left = left;
        }
        public void setRight(Top right) {
            this.right = right;
        }
        public void setCount(int count) {
            this.count = count;
        }
        Top(int count, Top left, Top right) {
            this.count = count;
            this.left = left;
            this.right = right;
            height = 1;
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

    int height(Top p) {
        int height_left = p.left != null ? p.left.height : 0;
        int height_right = p.right != null ? p.right.height : 0;
        return height_right - height_left;
    }

    void change_height(Top p) {
        int height_left =p.left != null ? p.left.height : 0;
        int height_right =  p.right != null ? p.right.height : 0;
        p.height = (height_left > height_right ? height_left : height_right) + 1;
    }

    public void solve() throws IOException {
        int n = in.nextInt();
        int[] count = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            count[i] = in.nextInt();
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }
        Top top[] = new Top[n];
        for (int i = 0; i < n; i++) {
            top[i] = new Top(0, null, null );
        }
        for (int i = n - 1; i >= 0; i--) {
            if (left[i] == 0 && right[i] == 0) {
                top[i] = new Top(count[i], null, null);
            } else if (left[i] != 0 && right[i] == 0) {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] -1]);
                top[i].setHeight(top[i].left.height+1);
            } else if (left[i] == 0 && right[i] != 0) {
                top[i].setCount(count[i]);
                top[i].setRight(top[right[i] - 1]);
                top[i].setHeight(top[i].right.height+1);
            } else {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[i].setRight(top[right[i] - 1]);
                top[i].setHeight(Math.max(top[i].right.height,top[i].left.height)+1);
            }
        }
        Top root = top[0];


        for (int i = 0; i < n; i++) {
            if (top[i].left != null && top[i].right != null) {
                out.println(top[i].right.height - top[i].left.height);
            } else if (top[i].left == null && top[i].right != null) {
                out.println(top[i].right.height );
            } else if (top[i].left != null && top[i].right == null) {
                out.println(- top[i].left.height);
            } else {
                out.println(0);
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
                new FastScanner(new FileInputStream("balance.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("balance.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task1().run();
    }
}