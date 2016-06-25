import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by mik on 25/06/16.
 */
public class JavaOneLiners {


    private static List<Integer> sieve(int max) {
        return IntStream.rangeClosed(2, (int) Math.sqrt(max)).boxed().reduce(
                IntStream.rangeClosed(2, max).boxed().collect(Collectors.toList()),
                (res, x) -> {res.removeAll(IntStream.rangeClosed(x * x, max).filter(n -> n % x == 0).boxed()
                        .collect(Collectors.toList())); return res;},
                (a, b) -> {a.addAll(b); return a;});
    }


    private static String toBase(int m, int b) {
        List<Integer> a = Stream.iterate(m, n -> n / b).limit(100).filter(n -> n > 0).map(n -> n % b).collect(Collectors.toList());
        String res = Stream.iterate(m, n -> n / b).limit(100).filter(n -> n > 0).map(n -> n % b).map(n -> n > 9 ? ("" + (char) ('A' + n - 10)) : n.toString()).reduce("", String::concat);
        Collections.reverse(a);
        return new StringBuilder(res).reverse().toString();
    }
}
