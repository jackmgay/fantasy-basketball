import org.scalatest.FunSuite

import TestData._
import FantasyBasketball._
import Experiment._
/**
  * Created by benjaminsmith on 10/29/17.
  */
class DraftSpec extends FunSuite{
  test("draft should end with each agent having a single player"){
   val startingEnv = Environment(TestData.allPlayers)

   val startingAgents = TestData.allAgents

   val (_, newAgents) = draft(startingEnv, startingAgents, Nil, remainingRounds = 1)

   assert(newAgents.forall(_.players.length == 1) && newAgents.nonEmpty)
  }
  test("should draft two players per agent"){
    val startingEnv = Environment(List(Player(2), Player(4), Player(10), Player(5)))

    val startingAgents = List(RandomAgent(),RandomAgent())

    val (_, newAgents) = draft(startingEnv, startingAgents, Nil, remainingRounds = 2)

    assert(newAgents.forall(_.players.length == 2)  && newAgents.nonEmpty)
  }
  test("should throw exception if more agents and rounds exist than players"){
    val startingEnv = Environment(List(Player(2)))

    val startingAgents = List(RandomAgent(),RandomAgent())

    val r = intercept[Throwable](draft(startingEnv, startingAgents, Nil, remainingRounds = 2))

    assert(r.isInstanceOf[Throwable])
  }

  test("should get the same number out of the draft that you put in"){
    val startingEnv = Environment(List(Player(2), Player(4), Player(10), Player(5)))

    val startingAgents = List(RandomAgent(),RandomAgent())

    val (_, newAgents) = draft(startingEnv, startingAgents, Nil, remainingRounds = 2)

    assert(newAgents.length == startingAgents.length)
  }
}
