package 프로그래머스.Lv0.할일목록

import util.validate

class Solution {

  fun solution(todo_list: Array<String>, finished: BooleanArray): Array<String> {
    val tmp: Array<String> = Array(todo_list.size) { "" }
    var len = 0
    for (i in todo_list.indices) if (!finished[i]) tmp[len++] = todo_list[i]
    return Array(len) { tmp[it] }
  }
}

/**
 * ```
 * [ME]
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "problemsolving", "practiceguitar", "swim", "studygraph"
  ), booleanArrayOf(true, false, true, false)),
    arrayOf("practiceguitar", "studygraph"))

}
