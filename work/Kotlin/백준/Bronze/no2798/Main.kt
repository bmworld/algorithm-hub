package 백준.Bronze.no2798

import java.util.*

fun main() {

  val sc = Scanner(System.`in`)
  val count = sc.nextInt()
  val m = sc.nextInt()

  val arr = Array(count) {
    sc.nextInt()
  }
  println(solution(arr, m))

}

/**
 * @arr '카드이 적힌 수' 목록
 * @m 최대값
 * @return 최대값에 근접한 카드 3장의 합
 */
fun solution(
  arr: Array<Int>,
  max: Int
): Int {

  var answers = IntArray(1)

  val ch = BooleanArray(arr.size)

  dfs(0, ch, answers, arr, max, 0)


  return answers[0]

}


fun dfs(
  level: Int,
  ch: BooleanArray,
  answer: IntArray,
  arr: Array<Int>,
  max: Int,
  acc: Int
) {

  if(level > 3 || acc > max) return
  if (level == 3) {

    answer[0] = Math.max(answer[0], acc)

  } else {

    var sum = 0
    arr.forEachIndexed { idx, v->
      if (ch[idx]) {
        sum += v
      }
    }


    arr.forEachIndexed{ idx, v ->

      if (!ch[idx]) {
        ch[idx] = true
        // 여기서 sum


        dfs(level+1, ch, answer, arr, max, sum + v)
        ch[idx] = false
      }

    }

  }

}
