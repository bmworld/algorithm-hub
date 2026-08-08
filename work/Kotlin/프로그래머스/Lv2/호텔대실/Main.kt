package 프로그래머스.Lv2.호텔대실

import util.validate

class Solution {
  private companion object {

    const val ZERO = 48
    const val SEP = 10_000
    const val CLEANING_TIME = 10
  }

  fun solution(books: Array<Array<String>>): Int {
    val N = books.size

    val rooms = mutableListOf<Int>()
    val revs = IntArray(N)
    for (i in 0 until N) {
      val rev = books[i]
      revs[i] = getTime(rev[1]) * SEP + getTime(rev[0])
    }

    qs(revs, 0, N - 1)


    l@ for (t in revs) {
      val end = t / SEP
      val stt = t % SEP

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
 * 테스트 1 〉	통과 (0.05ms, 60MB)
 * 테스트 2 〉	실패 (0.55ms, 61.3MB)
 * 테스트 3 〉	실패 (3.64ms, 61.8MB)
 * 테스트 4 〉	실패 (1.39ms, 61MB)
 * 테스트 5 〉	통과 (0.02ms, 60.5MB)
 * 테스트 6 〉	실패 (3.83ms, 60MB)
 * 테스트 7 〉	실패 (3.51ms, 59.6MB)
 * 테스트 8 〉	실패 (0.89ms, 60.3MB)
 * 테스트 9 〉	실패 (0.73ms, 60.8MB)
 * 테스트 10 〉	실패 (2.54ms, 59.2MB)
 * 테스트 11 〉	실패 (3.39ms, 61.5MB)
 * 테스트 12 〉	실패 (3.09ms, 61.9MB)
 * 테스트 13 〉	실패 (0.33ms, 60.7MB)
 * 테스트 14 〉	실패 (3.28ms, 61.4MB)
 * 테스트 15 〉	실패 (3.19ms, 60.9MB)
 * 테스트 16 〉	실패 (0.91ms, 59.5MB)
 * 테스트 17 〉	실패 (3.57ms, 62.3MB)
 * 테스트 18 〉	실패 (1.91ms, 61.5MB)
 * 테스트 19 〉	통과 (4.79ms, 61.6MB)
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
//
//  validate(
//    s.solution(arrayOf(
//      arrayOf("09:10", "10:10"),
//      arrayOf("10:20", "12:20"),
//    )), 1
//  )
//
//  validate(
//    s.solution(arrayOf(
//      arrayOf("10:20", "12:30"),
//      arrayOf("10:20", "12:30"),
//      arrayOf("10:20", "12:30"),
//    )), 3
//  )
}

//       println("[$stt ~ $end] [${rooms.size}]")
//        println("rooms[$j]= ${rooms[j]}")
