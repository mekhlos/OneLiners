import java.util.concurrent.TimeUnit

/**
 * Created by mik on 25/06/16.
 */
class GroovyOneLiners {

    public static void main(String[] args) {

        int n = 54321
        int b = 16
        int maxValue = 1234


        def (res, runningTime) = measureMillis(toAnyBase, [n, b])
        println(res)
        println("Took: ${runningTime} ms\n")


    }

    /* Converts a number n from base 10 to the base b with the given maximum number of digits (optional)
     * Only for 0 < b <= 10 */
    static def toBase = {
        int n, int b, digits = 20 ->
            (0..digits).collect { (int) (n / b**it) }.findAll { it > 0 }*.mod(b).reverse().inject('') { res, d -> res + d }
    }


    /* Converts a number n from base 10 to the base b with the given maximum number of digits (optional) b > 0*/
    static def toAnyBase = {
        int n, int b, digits = 20 ->
            (0..digits)
                    .collect { (int) (n / b**it) }
                    .findAll { it > 0 }*.mod(b)
                    .collect { it >= 10 ? (char) (it - 10 + ('A' as char)) : it }
                    .reverse().inject('') { res, d -> res + d }
    }

    static def primes = {
        int maxValue ->
            (2..Math.sqrt(maxValue)).inject(2..maxValue) { res, x -> res - (x * x..maxValue).step(x) }
    }

    /* Generates all the primes up to maxValue using the classic method of checking for each number whether
       it's only divisible by 1 and itself */
    static def primesClassic = {
        int maxValue ->
            (0..maxValue).findAll { int i -> (i == 2 || !(2..Math.sqrt(i)).any { i % it == 0 }) }
    }

    /* Measures the running time of closure(args) and returns the result of the closure and the running time */

    static def measureMillis(Closure closure, List args) {
        Long start = System.nanoTime()
        def res = closure(*args)
        Long end = System.nanoTime()
        def runningTime = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS)

        return [res, runningTime]

    }


}
