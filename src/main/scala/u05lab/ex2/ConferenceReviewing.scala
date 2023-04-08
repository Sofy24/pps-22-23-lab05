package u05lab.ex2



import u05lab.ex2.Pair.Pair

import java.util.EnumMap
import scala.collection.immutable.*

trait ConferenceReviewing:
  enum Question:
    case RELEVANCE
    case SIGNIFICANCE
    case CONFIDENCE
    case FINAL

  def loadReview(article: Int, scores: Map[Question, Int]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def accepted(article: Int): Boolean

  def acceptedArticles: Set[Int]

  def sortedAcceptedArticles: List[Pair[Int, Double]]

  def averageWeightedFinalScore(article: Int): Double

  def averageWeightedFinalScoreMap(article: Int): Map[Int, Double]

object ConferenceReviewing:
  private class ConferenceReviewingImpl() extends ConferenceReviewing:
    private var reviews: List[Pair[Int, Map[Question, Int]]] =  List()

    override def loadReview(article: Int, scores: Map[Question, Int]): Unit =
      if scores.size < Question.values.length then throw new IllegalArgumentException()
      reviews.appended(Pair(article, scores))

    override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit =
      val map = Map(Question.RELEVANCE -> relevance,
        Question.SIGNIFICANCE -> significance,
        Question.CONFIDENCE -> confidence,
        Question.FINAL -> fin)
      reviews.appended(Pair(article, map))

    override def orderedScores(article: Int, question: Question): List[Int] =
      reviews.filter(p => p.getX == article)
        .map(p => p.getY(question))
        .sorted



    override def averageFinalScore(article: Int): Double =
      val finalScores = reviews
        .filter(p => p.getX == article)
        .map(p => p.getY(Question.FINAL))

      finalScores.sum.toDouble / finalScores.size

    override def accepted(article: Int): Boolean =
      averageFinalScore(article) > 5.0 && !reviews.filter(p => p.getX == article).map(p => p.getY).filter(p => p(Question.RELEVANCE) > 8).map(p => p.keySet).contains(Question.RELEVANCE)

    override def acceptedArticles: Set[Int] =
      reviews.map(p => p.getX).distinct.filter(this.accepted).toSet

    override def sortedAcceptedArticles: List[Pair[Int, Double]] =
      this.acceptedArticles.map(e => Pair(e, this.averageFinalScore(e))).toList.sorted((e1,e2)=>e1.getY.compareTo(e2.getY))

    override def averageWeightedFinalScore(article: Int): Double =
      val filteredElements = reviews.filter(p => p.getX == article).map(p => p.getY(Question.FINAL) * p.getY(Question.CONFIDENCE) / 10.0)
      filteredElements.sum / filteredElements.length

    override def averageWeightedFinalScoreMap(article: Int): Map[Int, Double] =
      reviews.map(p => p.getX).distinct.map(i => i -> averageWeightedFinalScore(i)).toMap

  def apply(): ConferenceReviewing = ConferenceReviewingImpl()