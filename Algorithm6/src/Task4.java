import java.io.*;
import java.util.*;

public class Task4 {

    FastScanner in;
    PrintWriter out;

    class Quack {
        private ArrayDeque<Integer> que;
        private HashMap<Character, Integer> registers;
        private HashMap<String, Integer> labels;
        private ArrayList<String> commands;

        public Quack(ArrayList<String> commands) {

            que = new ArrayDeque<>();
            registers = new HashMap<>();
            labels = new HashMap<>();

            this.commands = commands;
            for (int i = 0; i < 26; ++i) {
                registers.put((char) ('a' + i), 0);
            }

            for (int i = 0; i < commands.size(); ++i) {
                String command = commands.get(i);
                if (command.charAt(0) == ':')
                    labels.put(command.substring(1), i + 1);
            }


        }

        public void start() {
            int i = 0;
            while (i < commands.size()) {
                String command = commands.get(i);
                int a;
                int b;
                // command = command.trim();
                int mod=65536;
                if (command.isEmpty())
                    continue;
                switch (command.charAt(0)) {
                    case '+':
                        a = que.poll();
                        b = que.poll();
                        que.add(((a+b)& 0xffff));
                        i++;
                        break;
                    case '-':
                        a = que.poll();
                        b = que.poll();
                        que.add((a-b)& 0xffff);

                        i++;
                        break;
                    case '*':
                        a = que.poll();
                        b = que.poll();
                        que.add((a*b)& 0xffff);

                        i++;
                        break;
                    case '%':
                        a = que.poll();
                        b = que.poll();
                        if(b==0){
                            que.add(0);
                        }else{
                            que.add((a%b)& 0xffff);
                        }
                        i++;
                        break;
                    case '/':
                        a = que.poll();
                        b = que.poll();
                        if(b==0){
                            que.add(0);
                        }else{
                            que.add((a/b)& 0xffff);
                        }

                        i++;
                        break;
                    case '<':
                        que.add(registers.get(command.charAt(1)));

                        i++;
                        break;
                    case '>':
                        registers.put(command.charAt(1), que.poll());

                        i++;
                        break;

                    case 'P':
                        if (command.length() == 1) {
                            out.println(que.poll().toString());
                        } else {
                            out.println(registers.get(command.charAt(1)));
                        }

                        i++;
                        break;
                    case 'C':
                        if (command.length() == 1) {
                            out.print((char) (que.poll() % 256));
                        } else {
                            out.print((char) (registers.get(command.charAt(1)) % 256));
                        }

                        i++;
                        break;
                    case 'Z':
                        if (registers.get(command.charAt(1)) == 0)
                            i = labels.get(command.substring(2));
                        else
                            i++;
                        break;
                    case 'J':
                        i = labels.get(command.substring(1));
                        break;

                    case 'E':
                        if (registers.get(command.charAt(1)).equals(registers.get(command.charAt(2))))
                            i = labels.get(command.substring(3));
                        else
                            i++;
                        break;
                    case 'G':
                        if (registers.get(command.charAt(1)) > registers.get(command.charAt(2)))
                            i = labels.get(command.substring(3));
                        else
                            i++;
                        break;
                    case 'Q':
                        return;
                    case ':':
                        i++;
                        break;
                    default:
                        a =Integer.parseInt(command)& 0xffff ;
                        que.add(a);
                        i++;
                        break;

                }
            }
        }
    }


    public void solve() throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();
        while (in.hasNextLine()) {
            arrayList.add(in.next());
        }
        Quack quack = new Quack(arrayList);
        quack.start();
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
                new FastScanner(new FileInputStream("quack.in"));
        out = //new PrintWriter(System.out);
                new PrintWriter(new FileOutputStream("quack.out"));
        solve();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Task4().run();
    }
}