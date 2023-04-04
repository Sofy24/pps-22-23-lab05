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

  def loadReview(article: Int, scores: Map[Int, Question]): Unit

  def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit

  def orderedScores(article: Int, question: Question): List[Int]

  def averageFinalScore(article: Int): Double

  def acceptedArticles: Set[Int]

  def sortedAcceptedArticles: List[(Int, Double)]

  def averageWeightedFinalScoreMap: Map[Int, Double]

  object ConferenceReviewing:
    private class ConferenceReviewingImpl() extends ConferenceReviewing:
      private val reviews: List[Pair[Int, Map[Question, Int]]] =  List()

      override def loadReview(article: Int, scores: Map[Int, Question]): Unit =
          if scores.size < Question.values.length then throw new IllegalArgumentException()
          //reviews.::((article, EnumMap(scores)))
          //TODO Pair refactor


      override def loadReview(article: Int, relevance: Int, significance: Int, confidence: Int, fin: Int): Unit = ???
        //private val map: Map[Question, Int] = //new EnumMap<>(Question.class);
        /*map += Question.RELEVANCE -> relevance
        map += Question.SIGNIFICANCE -> significance
        map += Question.CONFIDENCE -> confidence
        map += Question.FINAL -> fin
        reviews.::(Pair(article, map))*/


      override def orderedScores(article: Int, question: Question): List[Int] = ???
        //reviews.filter(p -> p == Pair(article, _)).map(p -> p(_, question)).sorted.toList
        /*reviews.stream()
          .filter(p -> p.getX() == article)
          .map(p -> p.getY().get(question))
          .sorted()
          .collect(Collectors.toList())*/

      override def averageFinalScore(article: Int): Double = ???
        /*val filteredElements = reviews.filter(p -> p == Pair(article, _))
          filteredElements.map(p -> p(_, Question.FINAL)).sum / filteredElements.*/
        /*return reviews.stream()
          .filter(p -> p.getX() == article)
          .mapToInt(p -> p.getY().get(Question.FINAL))
          .average()
          .getAsDouble();*/

      override def acceptedArticles: Set[Int] = ???

      override def sortedAcceptedArticles: List[(Int, Double)] = ???

      override def averageWeightedFinalScoreMap: Map[Int, Double] = ???

    def apply(): ConferenceReviewing = ConferenceReviewingImpl()