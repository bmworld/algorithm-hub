package 프로그래머스.알고리즘고득점Kit.해시.베스트앨범

import util.validate

class Solution {

  val SEP = 100_000
  val MAX_SONG_CNT = 2
  fun solution(genres: Array<String>, plays: IntArray): IntArray {
    val playlist = HashMap<String, MutableList<Int>>()
    val timer = HashMap<String, Int>()

    var uniqueGenreCnt = 0
    for (i in 0 until genres.size) {
      val g = genres[i]
      val t = plays[i]

      var songs = playlist[g]
      val e = t * SEP + i
      if (songs == null) {
        playlist[g] = mutableListOf(e)
        uniqueGenreCnt++
      } else {
        playlist[g]!!.add(e)
      }

      timer[g] = (timer[g] ?: 0) + t
    }

    var albumSize = uniqueGenreCnt * MAX_SONG_CNT
    val tmp = IntArray(albumSize)

    var uniqueCnt = 0
    for (e in timer.toSortedMap(compareByDescending { it })) {

      var r1 = -1
      var r1Play = 0
      var r2 = -1
      var r2Play = 0

      for (e in playlist[e.key]!!) {
        val t = e / SEP
        val i = e % SEP
        if (t > r1Play) {
          r2 = r1
          r2Play = r1Play
          r1 = i
          r1Play = t
        } else if (t > r2Play) {
          r2 = i
          r2Play = t
        }
      }

      tmp[uniqueCnt++] = r1
      if (r2 >= 0) tmp[uniqueCnt++] = r2
    }

    val ans = IntArray(uniqueCnt)
    System.arraycopy(tmp, 0, ans, 0, uniqueCnt)
    return ans
  }
}

/**
 * ```
 * ME:
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("classic", "pop", "classic", "classic", "pop"),
    intArrayOf(500, 600, 150, 800, 2500)
  ), intArrayOf(4, 1, 3, 0))

}
