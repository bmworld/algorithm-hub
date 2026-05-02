package 프로그래머스.입문.중앙값구하기

class Solution {

  fun solution(arr: IntArray): Int {
    val len = arr.size
    val goal = len / 2

    repeat(goal + 1) {

      var pos = it
      var v1 = arr[pos]
      var min = v1
      for (j in pos + 1 until len) {
        val v2 = arr[j]
        if (v2 < min) {
          min = v2
          pos = j
        }
      }
      arr[it] = min
      arr[pos] = v1
    }
    return arr[goal]
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(9, -9, 0)) == 0)
  check(s.solution(intArrayOf(5, 3, 1, 8, 9)) == 5)
}

// AS IS
//테스트 1 〉	통과 (9.83ms, 66MB)
//테스트 2 〉	통과 (9.01ms, 65.6MB)
//테스트 3 〉	통과 (9.57ms, 65.7MB)
//테스트 4 〉	통과 (9.25ms, 65.9MB)
//테스트 5 〉	통과 (10.97ms, 66.1MB)
//테스트 6 〉	통과 (9.76ms, 66.6MB)
//테스트 7 〉	통과 (9.39ms, 66.3MB)
//테스트 8 〉	통과 (9.51ms, 67.8MB)
//테스트 9 〉	통과 (9.64ms, 66.4MB)

// TO BE
//테스트 1 〉	통과 (0.02ms, 63.3MB)
//테스트 2 〉	통과 (0.02ms, 61.3MB)
//테스트 3 〉	통과 (0.02ms, 61.4MB)
//테스트 4 〉	통과 (0.02ms, 61.3MB)
//테스트 5 〉	통과 (0.02ms, 64.5MB)
//테스트 6 〉	통과 (0.08ms, 62.6MB)
//테스트 7 〉	통과 (0.08ms, 62.3MB)
//테스트 8 〉	통과 (0.08ms, 62.9MB)
//테스트 9 〉	통과 (0.02ms, 63.2MB)
