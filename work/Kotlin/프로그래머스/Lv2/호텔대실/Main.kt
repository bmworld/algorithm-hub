package 프로그래머스.Lv2.호텔대실

import util.validate
import java.util.*

class Solution {
  private companion object {

    const val EMPTY = -1
    const val ZERO = 48
    const val SEP = 10_000
    const val CLEANING_TIME = 10
  }

  fun solution(books: Array<Array<String>>): Int {
    val N = books.size

    val revs = IntArray(N)
    for (i in 0 until N) {
      val rev = books[i]
      revs[i] = getTime(rev[1]) * SEP + getTime(rev[0])
    }

    qs(revs, 0, N - 1)

    val rooms = PriorityQueue<Int>()
    for (t in revs) {
      val stt = t % SEP
      val end = t / SEP + CLEANING_TIME

      rooms.add(end + if (rooms.isNotEmpty() && stt >= rooms.peek()) rooms.poll() else 0)
    }

    return rooms.size
  }

  private fun getTime(hhMM: String): Int =
    (hhMM[0].code - ZERO) * 600 + (hhMM[1].code - ZERO) * 60 + (hhMM[3].code - ZERO) * 10 + (hhMM[4].code - ZERO)


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

    while (pos <= pr) {
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
 * 테스트 1 〉	실패 (0.26ms, 60.6MB)
 * 테스트 2 〉	실패 (0.51ms, 61.2MB)
 * 테스트 3 〉	실패 (1.48ms, 62.4MB)
 * 테스트 4 〉	실패 (1.29ms, 60.9MB)
 * 테스트 5 〉	통과 (0.22ms, 60.1MB)
 * 테스트 6 〉	실패 (1.72ms, 60MB)
 * 테스트 7 〉	실패 (1.84ms, 60.6MB)
 * 테스트 8 〉	실패 (1.20ms, 60.1MB)
 * 테스트 9 〉	실패 (0.83ms, 61.5MB)
 * 테스트 10 〉	실패 (1.37ms, 61.1MB)
 * 테스트 11 〉	실패 (1.85ms, 60.4MB)
 * 테스트 12 〉	실패 (1.37ms, 60.7MB)
 * 테스트 13 〉	실패 (0.77ms, 61.8MB)
 * 테스트 14 〉	실패 (1.81ms, 61.6MB)
 * 테스트 15 〉	실패 (1.59ms, 60.8MB)
 * 테스트 16 〉	실패 (0.84ms, 61.6MB)
 * 테스트 17 〉	실패 (1.80ms, 61.8MB)
 * 테스트 18 〉	실패 (1.15ms, 60.9MB)
 * 테스트 19 〉	통과 (1.05ms, 62.1MB)
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


  validate(
    s.solution(arrayOf(
      arrayOf("12:20", "12:30"),
      arrayOf("12:40", "12:50"),
      arrayOf("12:40", "13:00"),
      arrayOf("12:50", "13:00"),
    )), 3
  )


  validate(
    s.solution(arrayOf(
      arrayOf("00:00", "01:00"),
      arrayOf("00:50", "01:00"),
      arrayOf("00:30", "01:20"),
      arrayOf("01:00", "01:20"),
      arrayOf("01:10", "01:20"),
    )), 4
  )
}

//       println("[$stt ~ $end] [${rooms.size}]")
//        println("rooms[$j]= ${rooms[j]}")
