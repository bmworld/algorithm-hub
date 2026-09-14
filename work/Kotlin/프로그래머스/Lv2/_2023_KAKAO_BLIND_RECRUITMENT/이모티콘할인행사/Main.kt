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

    var maxSubs = 0
    var maxSales = 0

    val discounts = intArrayOf(0, 10, 20, 30, 40)

    fun dfs(checked: Int, accSubs: Int, accSales: Int, userPayments: IntArray) {
      if (checked == M) {
        if (accSubs > maxSubs) {
          maxSubs = accSubs
          maxSales = accSales
        } else if (accSubs == maxSubs && accSales > maxSales) {
          maxSales = accSales
        }
        return
      }

      val p = emoticons[checked]

      for (d in discounts) {
        var subs = 0
        var sales = 0
        val tmpPayments = userPayments.copyOf()

        val sp = p * (100 - d) / 100
        for (ui in users.indices) {
          val user = users[ui]

          val ud = user[0]
          if (ud > d) continue

          val paid = tmpPayments[ui]
          if (paid == SUBSCRIBED) continue

          val up = user[1]
          if (paid + sp >= up) {
            tmpPayments[ui] = SUBSCRIBED
            subs++
            sales -= paid
          } else {
            tmpPayments[ui] += sp
            sales += sp
          }
        }

        println(
          "emoticons[$checked] sp= $sp, discount[$d], subs=$subs, sales=$sales, userPayments = ${tmpPayments.contentToString()}")
        dfs(checked + 1, accSubs + subs, accSales + sales, tmpPayments)
      }
    }


    dfs(0, 0, 0, IntArray(N))


    return intArrayOf(maxSubs, maxSales)
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
        x > piv -> swap(a, pos++, pl++)
        x < piv -> swap(a, pos, pr--)
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
  validate(
    s.solution(
      arrayOf(
        intArrayOf(40, 10000),
        intArrayOf(24, 10000),
      ),
      intArrayOf(7000, 9000)
    ),
    intArrayOf(1, 5400)
  )

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
