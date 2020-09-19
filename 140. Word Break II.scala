// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
// add spaces in s to construct a sentence where each word is a valid dictionary word. 
// Return all such possible sentences.

// Note:
// The same word in the dictionary may be reused multiple times in the segmentation.
// You may assume the dictionary does not contain duplicate words.

// Example 1:
// Input:
// s = "catsanddog"
// wordDict = ["cat", "cats", "and", "sand", "dog"]
// Output:
// [
//   "cats and dog",
//   "cat sand dog"
// ]

// Example 2:
// Input:
// s = "pineapplepenapple"
// wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
// Output:
// [
//   "pine apple pen apple",
//   "pineapple pen apple",
//   "pine applepen apple"
// ]
// Explanation: Note that you are allowed to reuse a dictionary word.

// Example 3:
// Input:
// s = "catsandog"
// wordDict = ["cats", "dog", "sand", "and", "cat"]
// Output:
// []
import scala.collection.mutable
// 题目有毒，测试用例 31 必然爆内存
object Solution {
  def wordBreak(s: String, wordDict: List[String]): List[String] = {
    val map0 = wordDict.zipWithIndex.toMap // String -> Index
    val map1 = map0 map { case (i, word) => word -> i } // Index -> String
    val n = s.length
    val dp = new Array[IndexedSeq[List[Int]]](n + 1)
    dp.indices foreach {
      dp(_) = IndexedSeq()
    }

    (1 to n) foreach { i =>
      dp(i) = (1 until i) filter { j => dp(j).nonEmpty && map0.contains(s.slice(j, i)) } flatMap { j =>
        dp(j) map { li => li :+ map0(s.slice(j, i)) }
      }

      if (map0.contains(s.slice(0, i)))
        dp(i) = dp(i) :+ List(map0(s.slice(0, i)))
    }

    (dp(n) map { li => (li map {
      map1(_)
    }).mkString(" ")
    }).toList
  }
}