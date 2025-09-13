
import java.util.*

fun main() {

  val sc = Scanner(System.`in`)

  val m = sc.nextInt()
  println(solution(m))
}

fun solution(v: Int): Long {

  val cache = LongArray(21) { 0 }

  fun factorial(n: Int): Long {
    return if (cache[n] != 0L) cache[n] else if (n <= 1) 1 else n * factorial(n - 1)
  }

  for (i in 0..20) {
    cache[i] = factorial(i)
  }

  return cache[v]
}
