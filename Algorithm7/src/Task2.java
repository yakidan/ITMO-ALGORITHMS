import java.awt.List;
import java.io.*;
import java.util.*;

public class Task2 {

    FastScanner in;
    PrintWriter out;
    public ArrayList<Integer> arr[];
    Top root = null;
    Top[] out_top;

    class Top {
        Top left;
        Top right;
        int count;
        Top root;

        public void setIndex_left(int index_left) {
            this.index_left = index_left;
        }

        public void setIndex_right(int index_right) {
            this.index_right = index_right;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        int index;
        int index_left = 0;
        int index_right = 0;

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

        public void setRoot(Top root) {
            this.root = root;
        }


        Top(int count, Top left, Top right, Top root, int index) {
            this.count = count;
            this.left = left;
            this.right = right;
            this.root = root;
            this.index = index;
            height = 1;
        }
    }

    public int heightOfBinaryTree(Top Top) {
        if (Top == null) {
            return 0;
        } else {
            return 1 +
                    Math.max(heightOfBinaryTree(Top.left),
                            heightOfBinaryTree(Top.right));
        }
    }

    int height(Top p) {
        int height_left = p.left != null ? p.left.height : 0;
        int height_right = p.right != null ? p.right.height : 0;
        return (height_right - height_left);
    }

    void rotate_small_left(Top a) {
        Top b = a.right;
        a.right = b.left;
        b.left = a;
            a.setHeight(height(a.right) - height(a.left));
            b.setHeight(height(b.right) - height(b.left));
    }

    void rotate_small_right(Top a) {
        Top b = a.left;
        a.left = b.right;
        b.right = a;
           a.setHeight(height(a.right) - height(a.left));
           b.setHeight(height(b.right) - height(b.left));
    }

    void rotate_left_big(Top p) {
        Top right = p.right.left;
        rotate_small_right(p.right);
        p.right = right;
        rotate_small_left(p);
    }
    void rotate_right_big(Top p) {
        Top left = p.left.right;
        rotate_small_left(p.left);
        p.left = left;
        rotate_small_right(p);
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
        out_top = new Top[n + 1];
        for (int i = 0; i < n; i++) {
            top[i] = new Top(0, null, null, null, i + 1);
        }
        for (int i = n - 1; i >= 0; i--) {
            if (left[i] == 0 && right[i] == 0) {
                top[i].setCount(count[i]);
            } else if (left[i] != 0 && right[i] == 0) {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);

                top[i].setHeight(top[i].left.height + 1);
            } else if (left[i] == 0 && right[i] != 0) {
                top[i].setCount(count[i]);
                top[i].setRight(top[right[i] - 1]);
                top[right[i] - 1].setRoot(top[i]);
                top[i].setHeight(top[i].right.height + 1);
            } else {
                top[i].setCount(count[i]);
                top[i].setLeft(top[left[i] - 1]);
                top[i].setRight(top[right[i] - 1]);
                top[left[i] - 1].setRoot(top[i]);
                top[right[i] - 1].setRoot(top[i]);
                top[i].setHeight(Math.max(top[i].right.height, top[i].left.height) + 1);
            }
            top[i].index_left = left[i];
            top[i].index_right = right[i];
        }

//        for (int i = n - 1; i >= 0; i--) {
//           change_height(top[i]);
//        }
//        for (int i = 0; i <n ; i++) {
//            out.println(top[i].height-1);
//        }
//        for (int i = 0; i < n; i++) {
//            if (top[i].left != null && top[i].right != null) {
//                out.println(top[i].right.height - top[i].left.height);
//            } else if (top[i].left == null && top[i].right != null) {
//                out.println(top[i].right.height);
//            } else if (top[i].left != null && top[i].right == null) {
//                out.println(-top[i].left.height);
//            } else {
//                out.println(0);
//            }
        //}
        root = top[0];
        Top cur = null;

        Top a = null;
        Top b = null;
        Top c = null;
        if (top[0] == null) {
            out.println(0);
            return;
        } else {
            a = top[0];
        }
        if (top[0].right == null) {
            out.println(n);
            for (int i = 0; i < n; i++) {
                out.println(top[i].count + " " + top[i].index_left + " " + top[i].index_right);
            }
            return;
        } else {
            b = a.right;
        }

        if (b.left != null) {
            c = b.left;
        }
        if ((height(a) == 2 && height(b) == 1) || (height(a) == 2 && height(b) == 0)) {
            root = b;
            rotate_small_left(a);
        } else if (c != null && ((height(a) == 2 && height(b) == -1) ||
                (height(a) == 2 && height(b) == -1) ||
                (height(a) == 2 && height(b) == -1))) {
            root = c;
            rotate_left_big(a);
        }
        out.println(n);
        width(root);

    }

    void width(Top top) {
        Queue<Top> queue = new LinkedList<>();
        queue.add(top);
        int i = 1;
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            cur.setIndex(i);
            i++;

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        queue.clear();
        queue.add(top);
        while (!queue.isEmpty()) {
            Top cur = queue.poll();
            output(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    void A(Top root, int i) {
        if (root.left != null) {
            root.left.setIndex(2 * i);
            out_top[root.left.index] = root.left;
            A(root.left, i + 1);
        }
        if (root.right != null) {
            root.right.setIndex(2 * i + 1);
            out_top[root.right.index] = root.right;
            A(root.right, 2 * i + 1);
        }
    }

    void output(Top root) {
        int index_left = 0;
        int index_right = 0;
        if (root.left != null && root.right != null) {
            index_left = root.left.index;
            index_right = root.right.index;
            out.println(root.count + " " + index_left + " " + index_right);
        } else if (root.left != null && root.right == null) {
            index_left = root.left.index;
            index_right = 0;
            out.println(root.count + " " + index_left + " " + index_right);
        } else if (root.left == null && root.right != null) {
            index_left = 0;
            index_right = root.right.index;
            out.println(root.count + " " + index_left + " " + index_right);

        } else {
            index_left = 0;
            index_right = 0;
            out.println(root.count + " " + index_left + " " + index_right);
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
                new FastScanner(new FileInputStream("rotation.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("rotation.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task2().run();
    }
}