package u05lab.ex2

import scala.collection.immutable.Set
import scala.collection.immutable.Map

trait ConferenceReviewing:
  enum Question:
    case RELEVANCE
    case SIGNIFICANCE
    case CONFIDENCE
    case FINAL

  def loadReview(article: Int, scores: Map[Int, Question]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def acceptedArticles: Set[Int]

  def sortedAcceptedArticles: List[(Int, Double)]

  def averageWeightedFinalScoreMap: Map[Int, Double]