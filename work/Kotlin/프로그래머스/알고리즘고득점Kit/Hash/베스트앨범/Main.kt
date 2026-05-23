package 프로그래머스.알고리즘고득점Kit.Hash.베스트앨범

import util.validate

class Solution {

  val SEP = 100_000L
  val MAX_SONG_CNT = 2
  fun solution(genres: Array<String>, plays: IntArray): IntArray {
    val playlist = HashMap<String, MutableList<Long>>()
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
    for (g in timer.toList().sortedByDescending { (_, value) -> value }.map { it.first }) {

      var r1 = -1
      var r1Play = 0L
      var r2 = -1
      var r2Play = 0L

      for (e in playlist[g]!!) {
        val t = e / SEP
        val i = (e % SEP).toInt()
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
 * 테스트 1 〉	통과 (12.97ms, 67MB)
 * 테스트 2 〉	통과 (12.81ms, 68MB)
 * 테스트 3 〉	통과 (5.97ms, 63.9MB)
 * 테스트 4 〉	통과 (6.23ms, 63.9MB)
 * 테스트 5 〉	통과 (14.63ms, 67.7MB)
 * 테스트 6 〉	통과 (12.95ms, 67.4MB)
 * 테스트 7 〉	통과 (14.62ms, 67.3MB)
 * 테스트 8 〉	통과 (13.06ms, 67.8MB)
 * 테스트 9 〉	통과 (13.29ms, 67.4MB)
 * 테스트 10 〉	통과 (15.28ms, 67.3MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(genres: Array<String>, plays: IntArray): IntArray {
 *         return genres.indices.groupBy { genres[it] }
 *             .toList()
 *             .sortedByDescending { it.second.sumBy { plays[it] } }
 *             .map { it.second.sortedByDescending { plays[it] }.take(2) }
 *             .flatten()
 *             .toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (16.99ms, 67.4MB)
 * 테스트 2 〉	통과 (18.10ms, 66.8MB)
 * 테스트 3 〉	통과 (17.07ms, 67.2MB)
 * 테스트 4 〉	통과 (16.47ms, 67.2MB)
 * 테스트 5 〉	통과 (17.75ms, 67.6MB)
 * 테스트 6 〉	통과 (17.83ms, 66.9MB)
 * 테스트 7 〉	통과 (17.93ms, 67.1MB)
 * 테스트 8 〉	통과 (20.93ms, 66.9MB)
 * 테스트 9 〉	통과 (20.87ms, 66.5MB)
 * 테스트 10 〉	통과 (19.05ms, 67.5MB)
 * 테스트 11 〉	통과 (19.12ms, 67.2MB)
 * 테스트 12 〉	통과 (20.60ms, 67.4MB)
 * ```
 */
fun main() {
  val s = Solution()
//  validate(s.solution(
//    arrayOf("classic", "pop", "classic", "classic", "pop"),
//    intArrayOf(500, 600, 150, 800, 2500)
//  ), intArrayOf(4, 1, 3, 0))
//
//  validate(s.solution(
//    arrayOf("classic"),
//    intArrayOf(500)
//  ), intArrayOf(0))
//
//  validate(s.solution(
//    arrayOf("classic", "classic"),
//    intArrayOf(100, 500)
//  ), intArrayOf(1, 0))

  validate(s.solution(
    arrayOf("jazz", "pop", "classic", "classic", "classic"),
    intArrayOf(900, 10000, 100, 500, 700)
  ), intArrayOf(1, 4, 3, 0))

  validate(s.solution(
    arrayOf("jazz", "pop", "jazz", "classic", "classic", "classic"),
    intArrayOf(900, 10000, 11000, 100, 500, 700)
  ), intArrayOf(2, 0, 1, 5, 4))

}
