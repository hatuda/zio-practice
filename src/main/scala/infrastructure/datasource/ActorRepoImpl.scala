package jp.webcrew.hands.on.zio
package infrastructure.datasource

import domain.model.{Actor, FilmActor}
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
  def getFilmActor: ZIO[Any, SQLException, List[(Actor,FilmActor)]] =
    // テーブル結合例
    run(quote {
      for {
        actor     <- query[Actor]
        filmActor <- query[FilmActor].join(fa => fa.actorId == actor.actorId)
      } yield (actor,filmActor)
    })
}
