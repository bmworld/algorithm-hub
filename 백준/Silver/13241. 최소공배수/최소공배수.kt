
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val st = StringTokenizer(br.readLine(), " ")

  println(lcm(st.nextToken().toLong(), st.nextToken().toLong()))
}

/** @return 최소공배수 */
fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

/** @return 최대 공약수 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
