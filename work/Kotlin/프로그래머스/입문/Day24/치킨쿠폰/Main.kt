package 프로그래머스.입문.Day24.치킨쿠폰

import util.validate

class Solution {

  val COND = 10

  fun solution(chicken: Int): Int {
    var ans = 0

    var coupon = chicken
    while (coupon >= COND) {
      val srvc = coupon / COND
      ans += srvc
      coupon = srvc + coupon % COND
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(100), 11)
  validate(s.solution(1081), 120)
}
