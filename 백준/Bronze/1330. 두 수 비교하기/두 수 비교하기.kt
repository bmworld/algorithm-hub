
import java.util.*

fun main() {

  val sc = Scanner(System.`in`)

  val a = sc.nextInt()
  val b = sc.nextInt()
  println(solution(a, b))
}

fun solution(
  a: Int,
  b: Int
): String {

  return if(a == b) "==" else if (a > b) ">" else "<"
}
