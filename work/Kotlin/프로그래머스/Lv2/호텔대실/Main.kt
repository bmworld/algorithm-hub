package 프로그래머스.Lv2.호텔대실

import util.validate

class Solution {
  private companion object {

    const val ZERO = 48
    const val SEP = 10_000
    const val CLEANING_TIME = 10
  }

  fun solution(book_time: Array<Array<String>>): Int {
    val N = book_time.size

    val rooms = mutableListOf<Int>()
    val revs = IntArray(N)
    for (i in 0 until N) {
      val times = book_time[i]
      revs[i] = getTime(times[0]) * SEP + getTime(times[1])
    }

    qs(revs, 0, N - 1)


    l@ for (t in revs) {
      val stt = t / SEP
      val end = t % SEP

      for (j in 0 until rooms.size) {
        if (stt >= rooms[j]) {
          rooms[j] = end + CLEANING_TIME
          continue@l
        }
      }

      rooms.add(end + CLEANING_TIME)
    }

    return rooms.size
  }

  private fun getTime(hhMM: String): Int =
    (hhMM[0].code - ZERO) * 600 + (hhMM[1].code - ZERO) * 60 + (hhMM[3].code - ZERO) * 10 + (hhMM[4].code - ZERO) * 10


  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    loop@ while (pos <= pr) {
      val x = a[pos]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }

    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 60.3MB)
 * 테스트 2 〉	실패 (0.45ms, 60.9MB)
 * 테스트 3 〉	실패 (3.21ms, 62.1MB)
 * 테스트 4 〉	실패 (1.49ms, 61MB)
 * 테스트 5 〉	통과 (0.03ms, 60MB)
 * 테스트 6 〉	실패 (3.19ms, 60.8MB)
 * 테스트 7 〉	통과 (3.01ms, 61.2MB)
 * 테스트 8 〉	실패 (1.11ms, 60.2MB)
 * 테스트 9 〉	실패 (0.54ms, 59.6MB)
 * 테스트 10 〉	실패 (2.17ms, 61.6MB)
 * 테스트 11 〉	실패 (3.41ms, 61.2MB)
 * 테스트 12 〉	실패 (4.09ms, 60.9MB)
 * 테스트 13 〉	실패 (0.48ms, 59.4MB)
 * 테스트 14 〉	실패 (3.03ms, 62.3MB)
 * 테스트 15 〉	실패 (3.11ms, 61.3MB)
 * 테스트 16 〉	실패 (1.15ms, 59.8MB)
 * 테스트 17 〉	실패 (4.99ms, 60.6MB)
 * 테스트 18 〉	실패 (1.99ms, 60.5MB)
 * 테스트 19 〉	통과 (6.18ms, 60.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(arrayOf(
      arrayOf("15:00", "17:00"),
      arrayOf("16:40", "18:20"),
      arrayOf("14:20", "15:20"),
      arrayOf("14:10", "19:20"),
      arrayOf("18:20", "21:20"),
    )), 3
  )

  validate(
    s.solution(arrayOf(
      arrayOf("09:10", "10:10"),
      arrayOf("10:20", "12:20"),
    )), 1
  )

  validate(
    s.solution(arrayOf(
      arrayOf("10:20", "12:30"),
      arrayOf("10:20", "12:30"),
      arrayOf("10:20", "12:30"),
    )), 3
  )
}

//       println("[$stt ~ $end] [${rooms.size}]")
//        println("rooms[$j]= ${rooms[j]}")
