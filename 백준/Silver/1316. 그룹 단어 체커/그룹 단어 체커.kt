
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

  val br = BufferedReader(InputStreamReader(System.`in`))
  val size = br.readLine().toInt()
  val arr = Array(size) { br.readLine() }

  println(solution(arr))
}

fun solution(arr: Array<String>): Int {

  var answer = 0

  for (string in arr) {

    // A -> B -> A 그룹단어 아님
    // A -> A -> B 그룹단어

    val chars = string.toCharArray()
    var lt = 0

    var valid = true
    while (valid && lt < chars.size - 1) {

      val cur = chars[lt]

      var hasChanged = false
      for (i in lt + 1 until chars.size) {
        val isSame = cur == chars[i]
        if (hasChanged && isSame) {
          valid = false
          break
        } else if (!isSame) hasChanged = true
      }

      lt++
    }

    if (valid) {
      answer++
    }
  }

  return answer
}
