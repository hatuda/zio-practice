package jp.webcrew.hands.on.zio
package domain.repository

import domain.model.Actor
import zio._
import java.sql.SQLException

trait ActorRepo {
  def getActor: ZIO[Any, SQLException, List[Actor]]
}

object ActorRepo {
  def getActor: ZIO[ActorRepo, SQLException, List[Actor]] =
    ZIO.serviceWithZIO[ActorRepo](_.getActor)
}
