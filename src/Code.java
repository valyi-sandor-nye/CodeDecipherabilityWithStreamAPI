
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Code {

    private ArrayList<String> c;

    public Code(ArrayList<String> c) {
        super();
        this.c = c;
    }

    public boolean ud_by_equal_code_word_lengths() {
        for (String x : this.c) {
            if (x.length() != c.get(0).length()) {
                return false;
            }
        }
        return true;
    }

    public boolean decipherability_2() {
        for (String x : this.c) {
            for (String y : this.c) {
                if (x.indexOf(y) == 0 && !x.equals(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean decipherability_3() {
        for (String x : this.c) {
            for (String y : this.c) {
                if (x.lastIndexOf(y) == x.length() - y.length() && !x.equals(y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean decipherability_4() {
        ArrayList<Character> tc = new ArrayList<>();
        ArrayList<Integer> ti = new ArrayList<>();
        for (String x : this.c) {
            for (int i = 0; i < x.length(); i++) {
                if (!tc.contains(x.charAt(i))) {
                    tc.add(x.charAt(i));
                }
            }
            ti.add(x.length());
        }
        int k = tc.size();
        double sum = 0;
        for (int a : ti) {
            sum += Math.pow(k, -a);
        }
        return sum <= 1;
    }

    public boolean ud_by_PSA() {
        ArrayList<String> u_i = new ArrayList<>();
        ArrayList<String> u_i_1 = new ArrayList<>();
        ArrayList<ArrayList<String>> u = new ArrayList<ArrayList<String>>();
        String s1;
        for (String x : this.c) {
            for (String y : this.c) {
                if (!x.equals(y) && x.indexOf(y) == 0) {
                    s1 = x.substring(y.length(), x.length());
                    if (!u_i.contains(s1)) {
                        u_i.add(s1);
                    }
                }
            }
        }
        Collections.sort(u_i);
        u.add(u_i);
        int index = 0;
        boolean b = true;
        while (b) {
            u_i = new ArrayList<>();
            u_i_1 = u.get(index);
            index++;
            String y;
            for (String x : c) {
                for (int i = 0; i < u_i_1.size(); i++) {
                    y = u_i_1.get(i);
                    if (x.indexOf(y) == 0) {
                        s1 = x.substring(y.length(), x.length());
                        if (!u_i.contains(s1)) {
                            u_i.add(s1);
                        }
                    }
                }
            }
            for (int i = 0; i < u_i_1.size(); i++) {
                for (String x : c) {
                    y = u_i_1.get(i);
                    if (y.indexOf(x) == 0) {
                        s1 = y.substring(x.length(), y.length());
                        if (!u_i.contains(s1)) {
                            u_i.add(s1);
                        }
                    }
                }
            }
            Collections.sort(u_i);
        }
        if (u_i.contains("")) {
            return false;
        }
        if (u_i.size() == 0) {
            return true;
        }
        for (ArrayList<String> aa : u) {
            if (u_i.equals(aa)) {
                return true;
            }
        }
        u.add(u_i);
        return true;
    }

    public static void main(String[] args) {
        String[] codeWordArray = new String[]
                {"abc", "ab", "bc"};
        ArrayList<String> codeWordsList = new ArrayList<>();
        codeWordsList.addAll(Arrays.asList(codeWordArray));
        Code code = new Code(codeWordsList);
        System.out.println(code.ud_by_equal_code_word_lengths());
        System.out.println(code.decipherability_2());
        System.out.println(code.decipherability_3());
        System.out.println(code.decipherability_4());
        System.out.println(code.ud_by_equal_code_word_lengths_with_streams());
       // System.out.println(code.ud_by_PSA());
        System.out.println(code.decipherability_2_with_streams());

    }

    private boolean ud_by_equal_code_word_lengths_with_streams() {
        Stream<String> streamCode =  c.stream();
        return
                streamCode
                        .map(s->s.length())
                        .distinct()
                        .count() <= 1;
    }




    private boolean decipherability_2_with_streams() {
        Stream<String> codeWordStream = c.stream();
        codeWordStream.findFirst();
        return false; // XXX
    }

}
