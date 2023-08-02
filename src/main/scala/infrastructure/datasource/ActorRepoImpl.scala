package jp.webcrew.hands.on.zio
package infrastructure.datasource

import domain.model.Actor
import domain.repository.ActorRepo
import infrastructure.config.MySQLContext

import io.getquill.*
import zio.*

import java.sql.SQLException

object ActorRepoImpl {
  val layer: ZLayer[MySQLContext, Nothing, ActorRepoImpl] =
    ZLayer.fromFunction(ActorRepoImpl.apply _)
}

final case class ActorRepoImpl(ctx: MySQLContext) extends ActorRepo {
  import ctx.q.*
  def getActor: ZIO[Any, SQLException, List[Actor]] =
    run(quote(query[Actor].sortBy(a => a.actorId)))
}
