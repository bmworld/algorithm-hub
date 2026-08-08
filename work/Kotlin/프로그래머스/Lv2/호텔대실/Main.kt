package 프로그래머스.Lv2.호텔대실

import util.validate

class Solution {
  private companion object {

    const val ZERO = 48
    const val SEP = 10_000
    const val CLEANING_TIME = 10
    const val MAX_TIME = 1440 + CLEANING_TIME
  }

  fun solution(books: Array<Array<String>>): Int {
    val N = books.size

    val bks = IntArray(N)
    for (i in 0 until N) {
      val rev = books[i]
      bks[i] = getTime(rev[1]) * SEP + getTime(rev[0])
    }

    qs(bks, 0, N - 1)

    val rooms = IntArray(MAX_TIME)
    var len = 0
    l@ for (e in bks) {
      val stt = e % SEP
      val end = e / SEP + CLEANING_TIME

      for (ready in stt downTo 0) {
        if (rooms[ready] > 0) {
          rooms[ready]--
          rooms[end]++
          continue@l
        }
      }

      rooms[end]++
      len++
    }


    return len
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
 * 테스트 1 〉	통과 (0.04ms, 59.6MB)
 * 테스트 2 〉	통과 (0.44ms, 61.1MB)
 * 테스트 3 〉	통과 (1.94ms, 61.5MB)
 * 테스트 4 〉	통과 (1.18ms, 60.7MB)
 * 테스트 5 〉	통과 (0.03ms, 61.3MB)
 * 테스트 6 〉	통과 (1.44ms, 61.9MB)
 * 테스트 7 〉	통과 (1.60ms, 61.7MB)
 * 테스트 8 〉	통과 (0.76ms, 60MB)
 * 테스트 9 〉	통과 (0.62ms, 60.7MB)
 * 테스트 10 〉	통과 (1.22ms, 62.4MB)
 * 테스트 11 〉	통과 (2.24ms, 60.4MB)
 * 테스트 12 〉	통과 (1.75ms, 62.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(book_time: Array<Array<String>>): Int {
 *         var arr = IntArray(1 + 24 * 60 + 10)
 *
 *         for(times in book_time){
 *             var startH = times[0].split(":")[0].toInt()
 *             var startM = times[0].split(":")[1].toInt()
 *             var EndH = times[1].split(":")[0].toInt()
 *             var EndM = times[1].split(":")[1].toInt()
 *
 *             for(i in startH*60 + startM..EndH*60 + EndM + 9){
 *                 arr[i] = arr[i]+1
 *             }
 *         }
 *         return arr.maxOf{it}
 *     }
 * }
 * 테스트 1 〉	통과 (13.32ms, 64MB)
 * 테스트 2 〉	통과 (16.81ms, 63.7MB)
 * 테스트 3 〉	통과 (16.95ms, 76.3MB)
 * 테스트 4 〉	통과 (19.30ms, 66.4MB)
 * 테스트 5 〉	통과 (12.94ms, 63.6MB)
 * 테스트 6 〉	통과 (19.80ms, 74.4MB)
 * 테스트 7 〉	통과 (16.93ms, 72.9MB)
 * 테스트 8 〉	통과 (15.41ms, 68.3MB)
 * 테스트 9 〉	통과 (16.21ms, 62.9MB)
 * 테스트 10 〉	통과 (21.48ms, 72.7MB)
 * 테스트 11 〉	통과 (19.12ms, 76.2MB)
 * 테스트 12 〉	통과 (17.55ms, 76.5MB)
 *
 * [RIVAL 2]
 * import java.util.PriorityQueue
 * class Solution {
 *     fun solution(book_time: Array<Array<String>>): Int {
 *         var answer: Int = 0
 *         val pq = PriorityQueue<Int>()
 *         book_time.sortWith {o1, o2 ->
 *             if (o1[0] == o2[0]) {
 *                 o1[1].compareTo(o2[1])
 *             } else {
 *                 o1[0].compareTo(o2[0])
 *             }
 *         }
 *         for (book in book_time) {
 *             val start = getTime(book[0])
 *             val end = getTime(book[1]) + 10
 *
 *             if (pq.isEmpty() || pq.peek() > start) {
 *                 pq.add(end)
 *             } else {
 *                 pq.poll()
 *                 pq.add(end)
 *             }
 *             answer = answer.coerceAtLeast(pq.size)
 *         }
 *
 *         return answer
 *     }
 *
 *     fun getTime(s: String): Int {
 *         val (h, m) = s.split(":").map { it.toInt() }
 *         return h*60 + m
 *     }
 * }
 * 테스트 1 〉	통과 (17.09ms, 65.6MB)
 * 테스트 2 〉	통과 (19.95ms, 65.8MB)
 * 테스트 3 〉	통과 (21.46ms, 67.9MB)
 * 테스트 4 〉	통과 (20.49ms, 66.3MB)
 * 테스트 5 〉	통과 (21.08ms, 64.8MB)
 * 테스트 6 〉	통과 (20.29ms, 67.6MB)
 * 테스트 7 〉	통과 (22.11ms, 67MB)
 * 테스트 8 〉	통과 (19.19ms, 67MB)
 * 테스트 9 〉	통과 (19.74ms, 65.4MB)
 * 테스트 10 〉	통과 (21.55ms, 67.9MB)
 * 테스트 11 〉	통과 (21.63ms, 67MB)
 * 테스트 12 〉	통과 (22.82ms, 67.4MB)
 *
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


  validate(
    s.solution(arrayOf(
      arrayOf("00:00", "01:00"),
      arrayOf("00:50", "03:00"),
      arrayOf("03:10", "04:00"),
      arrayOf("01:10", "05:00"),
    )), 2
  )
}

//       println("[$stt ~ $end] [${rooms.size}]")
//        println("rooms[$j]= ${rooms[j]}")
