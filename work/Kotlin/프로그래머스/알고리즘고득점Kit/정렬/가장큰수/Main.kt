package 프로그래머스.알고리즘고득점Kit.정렬.가장큰수

import util.validate

class Solution {

  val ZERO = 48
  val DIGITS = 10
  val MAX = 1000
  val SEP = MAX * 10

  fun solution(nums: IntArray): String {
    val digits = Array(DIGITS) { mutableListOf<Int>() }
    var totalLen = 0
    for (n in nums) {
      var x = n
      var len = 0
      while (x >= 0) {
        totalLen++
        len++
        if (x < 10) {
          digits[x] += len * SEP + n
          break
        }
        x /= 10
      }
    }

    val ans = CharArray(totalLen)
    var ai = 0
    for (x in 9 downTo 1) {
      val arr = digits[x].toIntArray()
      val len = arr.size
      if (len == 0) continue

      qs(arr, 0, len - 1)

      for (i in 0 until len) {
        val e = arr[i]
        var x = e % SEP
        val len = e / SEP
        repeat(len) {
          val d = len - (it + 1)
          ans[ai + d] = (x % 10 + ZERO).toChar()
          x /= 10
        }

        ai += len
      }

    }

    if (ai == 0) {
      ai++
      ans[0] = ZERO.toChar()
    } else {
      val zeros = digits[0].toIntArray()
      val len = zeros.size
      repeat(len) {
        ans[ai++] = ZERO.toChar()
      }
    }

    return ans.concatToString(0, ai)
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun sorter(
    a: IntArray,
    l: Int,
    r: Int,
  ): Pair<Int, Int> {
    var pos = l
    var pl = l
    var pr = r
    val pe = a[r]
    val pLen = pe / SEP
    val piv = pe % SEP
    val pivPow10 = pow10(pLen)


    loop@ while (pos <= pr) {
      var x = piv
      var x10 = pivPow10

      val e = a[pos]
      val len = e / SEP
      var v = e % SEP
      var v10 = pow10(len)

      for (i in 0 until minOf(len, pLen)) {
        val xd = x / x10
        val vd = v / v10
        when {
          xd > vd -> {
            swap(a, pos, pr--)
            continue@loop
          }

          xd < vd -> {
            swap(a, pos++, pl++)
            continue@loop
          }

          x10 < 10 && v10 >= 10 -> {
            val nv = v % v10
            val nvd = nv / (v10 / 10)
            if (nvd > xd) swap(a, pos++, pl++)
            else swap(a, pos, pr--)
            continue@loop
          }
          v10 < 10 && x10 >= 10 -> {
            val nx = x % x10
            val nxd = nx / (x10 / 10)
            if (vd > nxd) swap(a, pos++, pl++)
            else swap(a, pos, pr--)
            continue@loop
          }
        }

        x %= x10
        x10 /= 10
        v %= v10
        v10 /= 10
      }

      pos++

    }
    return Pair(pl, pr)
  }

  fun pow10(digits: Int): Int {
    var r = 1
    repeat(digits - 1) { r *= 10 }
    return r
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    val (pl, pr) = sorter(a, l, r)
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	실패 (43.87ms, 72.8MB)
 * 테스트 2 〉	실패 (33.30ms, 72.8MB)
 * 테스트 3 〉	실패 (58.47ms, 77MB)
 * 테스트 4 〉	실패 (10.98ms, 65.8MB)
 * 테스트 5 〉	실패 (45.45ms, 73.8MB)
 * 테스트 6 〉	실패 (38.10ms, 70.3MB)
 * 테스트 7 〉	통과 (8.57ms, 64.2MB)
 * 테스트 8 〉	통과 (10.08ms, 64.2MB)
 * 테스트 9 〉	통과 (8.45ms, 64.5MB)
 * 테스트 10 〉	통과 (8.48ms, 64.4MB)
 * 테스트 11 〉	통과 (11.69ms, 64.2MB)
 * 테스트 12 〉	통과 (9.64ms, 65.2MB)
 * 테스트 13 〉	통과 (10.21ms, 64.4MB)
 * 테스트 14 〉	통과 (8.55ms, 64.2MB)
 * 테스트 15 〉	통과 (8.34ms, 65.4MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(0, 0, 0)
    ),
    "0"
  )

  validate(
    s.solution(
      intArrayOf(9, 99, 999, 90, 89, 98, 90)
    ),
    "99999998909089"
  )


  validate(
    s.solution(
      intArrayOf(3, 30, 34, 5, 9)
    ),
    "9534330"
  )

  validate(
    s.solution(
      intArrayOf(1, 0, 0)
    ),
    "100"
  )

  validate(
    s.solution(
      intArrayOf(24, 2, 21, 0, 0)
    ),
    "2422100"
  )


}

//println("[$i] tmp[$k] = ${tmp[k]}")
//    println("[IN] pos = ${pos}, pl=$pl, pr=$pr, piv=$piv, $pLen")
//println("[OUT] pos = ${pos}, pl=$pl, pr=$pr, piv=$piv")
//println("$v -> nvd = ${nvd} vs $x -> $xd")
//println("$x -> nxd = $nxd vs $v -> $vd")
