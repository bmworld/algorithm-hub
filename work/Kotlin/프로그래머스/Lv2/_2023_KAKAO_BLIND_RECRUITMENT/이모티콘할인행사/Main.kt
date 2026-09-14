package 프로그래머스.Lv2._2023_KAKAO_BLIND_RECRUITMENT.이모티콘할인행사

import util.validate

class Solution {
  companion object {

    const val SUBSCRIBED = Int.MAX_VALUE
  }

  fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
    val N = users.size
    val M = emoticons.size

    var maxSubs = 0
    var maxAmnt = 0
    val sales = IntArray(M)

    fun dfs(checked: Int) {
      if (checked == M) {
        var subs = 0
        var amnt = 0
        val payments = IntArray(N)

        repeat(M) { i ->
          val p = emoticons[i]
          val d = sales[i] * 10
          val sp = p * (100 - d) / 100

          for (j in users.indices) {
            val user = users[j]
            if (user[0] > d) continue

            val paid = payments[j]
            if (paid == SUBSCRIBED) continue

            if (paid + sp >= user[1]) {
              payments[j] = SUBSCRIBED
              subs++
              amnt -= paid
            } else {
              payments[j] += sp
              amnt += sp
            }
          }
        }

        if (subs > maxSubs) {
          maxSubs = subs
          maxAmnt = amnt
        } else if (subs == maxSubs && amnt > maxAmnt) {
          maxAmnt = amnt
        }

        return
      }


      for (d in 1..4) {
        sales[checked] = d
        dfs(checked + 1)
      }
    }

    dfs(0)

    return intArrayOf(maxSubs, maxAmnt)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.16ms, 58.1MB)
 * 테스트 2 〉	통과 (0.17ms, 59.5MB)
 * 테스트 3 〉	통과 (0.24ms, 61.1MB)
 * 테스트 4 〉	통과 (0.42ms, 59.6MB)
 * 테스트 5 〉	통과 (0.57ms, 60.2MB)
 * 테스트 6 〉	통과 (0.43ms, 58.1MB)
 * 테스트 7 〉	통과 (1.45ms, 61.1MB)
 * 테스트 8 〉	통과 (0.88ms, 60.8MB)
 * 테스트 9 〉	통과 (2.16ms, 62.6MB)
 * 테스트 10 〉	통과 (1.19ms, 62.4MB)
 * 테스트 11 〉	통과 (5.25ms, 63.6MB)
 * 테스트 12 〉	통과 (2.48ms, 63.5MB)
 * 테스트 13 〉	통과 (16.99ms, 70.1MB)
 * 테스트 14 〉	통과 (14.64ms, 71.4MB)
 * [ME v2]
 * 테스트 1 〉	통과 (0.16ms, 60.1MB)
 * 테스트 2 〉	통과 (0.15ms, 60.7MB)
 * 테스트 3 〉	통과 (0.25ms, 59.8MB)
 * 테스트 4 〉	통과 (0.60ms, 60MB)
 * 테스트 5 〉	통과 (0.76ms, 60.9MB)
 * 테스트 6 〉	통과 (0.61ms, 59.6MB)
 * 테스트 7 〉	통과 (1.76ms, 60.3MB)
 * 테스트 8 〉	통과 (0.94ms, 59.8MB)
 * 테스트 9 〉	통과 (4.71ms, 59.3MB)
 * 테스트 10 〉	통과 (2.03ms, 60.3MB)
 * 테스트 11 〉	통과 (11.56ms, 61.2MB)
 * 테스트 12 〉	통과 (4.72ms, 61.3MB)
 * 테스트 13 〉	통과 (31.37ms, 66.9MB)
 * 테스트 14 〉	통과 (24.51ms, 66.2MB)
 *
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     var answer = IntArray(2)
 *     fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
 *         val sales = intArrayOf(40, 30, 20, 10)
 *         permutation(users, emoticons, sales, emoticons.size, "")
 *         return answer
 *     }
 *
 *     fun afterSale(sale: Int, price: Int): Int {
 *         val convertSale = sale * 0.01
 *         val salesPrice = price * convertSale
 *         return (price - salesPrice).toInt()
 *     }
 *
 *     fun permutation(
 *         users: Array<IntArray>,
 *         emoticons: IntArray,
 *         arr: IntArray,
 *         num: Int,
 *         result: String
 *     ) {
 *         if (num == 0) {
 *             val temp = IntArray(2)
 *
 *             val prices = result.trim()
 *                 .split(" ")
 *                 .mapIndexed { index, sale ->
 *                     Pair(sale.toInt(), emoticons[index])
 *                 }
 *             users.forEach { (wSale, wPrice) ->
 *                 var total = 0
 *                 prices.forEach { (sale, price) ->
 *                     total += if(wSale <= sale) afterSale(sale, price) else 0
 *                 }
 *                 if(total >= wPrice) temp[0] += 1
 *                 else temp[1] += total
 *             }
 *             if(temp[0] > answer[0]) answer = temp
 *             else if(temp[0] == answer[0] && temp[1] > answer[1]) answer = temp
 *             return
 *         }
 *         for (i in arr.indices) {
 *             permutation(
 *                 users,
 *                 emoticons,
 *                 arr,
 *                 num - 1,
 *                 result + " " + arr[i]
 *             )
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (11.01ms, 62.5MB)
 * 테스트 2 〉	통과 (11.33ms, 62.3MB)
 * 테스트 3 〉	통과 (12.39ms, 61MB)
 * 테스트 4 〉	통과 (13.56ms, 62.1MB)
 * 테스트 5 〉	통과 (13.49ms, 61.7MB)
 * 테스트 6 〉	통과 (17.98ms, 61.9MB)
 * 테스트 7 〉	통과 (23.02ms, 63MB)
 * 테스트 8 〉	통과 (18.44ms, 68MB)
 * 테스트 9 〉	통과 (31.97ms, 71.6MB)
 * 테스트 10 〉	통과 (31.14ms, 76.5MB)
 * 테스트 11 〉	통과 (83.76ms, 77.1MB)
 * 테스트 12 〉	통과 (59.11ms, 90.1MB)
 * 테스트 13 〉	통과 (127.73ms, 87.5MB)
 * 테스트 14 〉	통과 (118.86ms, 92.5MB)
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     var answer: IntArray = intArrayOf(0,0)
 *     fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
 *         val new = IntArray(emoticons.size) {0}
 *         dfs(0, emoticons.size, users, emoticons, new)
 *
 *         return answer
 *     }
 *
 *     fun dfs(start: Int, target: Int,users: Array<IntArray>, emoticons: IntArray, sale: IntArray) {
 *         if (start == target) {
 *             val new = buy(users, emoticons, sale)
 *             if (new[0] > answer[0] || (new[0] == answer[0] && new[1] > answer[1])) {
 *                 answer = new
 *             }
 *             return
 *         }
 *         for (i in 1..4) {
 *             sale[start] = i * 10
 *             dfs(start + 1, target, users, emoticons, sale)
 *         }
 *     }
 *
 *     fun buy(users: Array<IntArray>, emoticons: IntArray, sale: IntArray): IntArray {
 *         val result = intArrayOf(0,0)
 *         for (u in users) {
 *             var buy = 0
 *             for (i in emoticons.indices) {
 *                 if (u[0] <= sale[i]) {
 *                     buy += emoticons[i] * (100 - sale[i]) / 100
 *                     if (buy >= u[1]) {
 *                         break
 *                     }
 *                 }
 *             }
 *             if (buy >= u[1]) {
 *                 result[0]++
 *             } else {
 *                 result[1] += buy
 *             }
 *         }
 *         return result
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.03ms, 60.6MB)
 * 테스트 3 〉	통과 (0.11ms, 60.6MB)
 * 테스트 4 〉	통과 (0.37ms, 60.1MB)
 * 테스트 5 〉	통과 (0.86ms, 60.1MB)
 * 테스트 6 〉	통과 (0.40ms, 60.4MB)
 * 테스트 7 〉	통과 (1.98ms, 60.1MB)
 * 테스트 8 〉	통과 (0.95ms, 59.2MB)
 * 테스트 9 〉	통과 (3.62ms, 60.8MB)
 * 테스트 10 〉	통과 (2.97ms, 60.1MB)
 * 테스트 11 〉	통과 (13.62ms, 60.4MB)
 * 테스트 12 〉	통과 (4.38ms, 61.8MB)
 * 테스트 13 〉	통과 (27.02ms, 61.1MB)
 * 테스트 14 〉	통과 (24.16ms, 61MB)
 *
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

//          println("[$p->$sp] payments = ${payments.contentToString()}")
