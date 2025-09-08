
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val size = br.readLine().toInt()
  val st = StringTokenizer(br.readLine(), " ")
  val arr = IntArray(size) { st.nextToken().toInt() }

  println(solution(arr))
}

/**
 * @param arr 번호표 목록
 * @return 간식수령 가능여부 (Nice: 번호표와 stack 활용하여 순서대로 처리할 수 있을 경우)
 */
fun solution(arr: IntArray): String {

  val stack = Stack<Int>() // 임시 대기줄

  var ch = 1
  for (i in 0 until arr.size) {
    val n = arr[i]
    var isUsed = false
    if (n == ch) {
      ch++
      isUsed = true
    }
    // STEP 1. 임시대기줄 - 입장
    while (!stack.isEmpty()) {
      val peek = stack.peek()
      if (peek == ch) {
        stack.pop()
        ch++
      } else {
        break
      }
    }

    // STEP 2. 미입장 시, 임시 대기줄
    if (!isUsed) {
      stack.add(n)
    }
  }

  return if (stack.isEmpty()) "Nice" else "Sad"
}
