package 프로그래머스.Lv2._2023_KAKAO_BLIND_RECRUITMENT.이모티콘할인행사

import util.validate

class Solution {
  companion object {

    const val SUBSCRIBED = Int.MAX_VALUE
  }

  fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
    val N = users.size
    val M = emoticons.size
    qs(emoticons, 0, M - 1)

    var totalSubs = 0
    var totalSales = 0
    val payments = IntArray(N)
    val discounts = intArrayOf(10, 20, 30, 40)


    for (p in emoticons) {

      var bestD = 0
      var bestSubs = 0
      var bestSales = 0

      for (d in discounts) {

        var subs = 0
        var sales = 0
        val price = p * (100 - d) / 100

        for (i in users.indices) {
          val user = users[i]
          val ud = user[0]
          if (ud > d) continue
          val paid = payments[i]
          if (paid == SUBSCRIBED) continue
          if (paid + price >= user[1]) {
            subs++
            sales -= paid
          } else {
            sales += price
          }
        }

        if (subs > bestSubs) {
          bestD = d
          bestSubs = subs
          bestSales = sales
        } else if (subs == bestSubs && sales > bestSales) {
          bestD = d
          bestSales = sales
        }

        println(
          "[p=$p->$price]  d=$d vs $bestD, subs=$subs vs $bestSubs, sales=$sales vs $bestSales")
      }


      for (i in users.indices) {
        val user = users[i]
        val ud = user[0]
        if (ud > bestD) continue
        val paid = payments[i]
        if (paid == SUBSCRIBED) continue
        val price = p * (100 - bestD) / 100
        if (paid + price >= user[1]) {
          payments[i] = SUBSCRIBED
          totalSubs++
          totalSales -= paid
        } else {
          payments[i] += price
          totalSales += price
        }
      }

      println(
        "[p=$p] bestD=$bestD, bestSales=$bestSales, bestSubs=$bestSubs ---- $totalSubs, $totalSales")
      println("[p=$p] payments = ${payments.contentToString()}")


    }


    return intArrayOf(totalSubs, totalSales)
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
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()

//  validate(
//    s.solution(
//      arrayOf(
//        intArrayOf(40, 10000),
//        intArrayOf(24, 10000),
//      ),
//      intArrayOf(7000, 9000)
//    ),
//    intArrayOf(1, 5400)
//  )

  validate(
    s.solution(
      arrayOf(
        intArrayOf(40, 2900),
        intArrayOf(23, 10000),
        intArrayOf(11, 5200),
        intArrayOf(5, 5900),
        intArrayOf(40, 3100),
        intArrayOf(27, 9200),
        intArrayOf(32, 6900),
      ),
      intArrayOf(1300, 1500, 1600, 4900)
    ),
    intArrayOf(4, 13860)
  )
}
