package jp.webcrew.hands.on.zio
package domain.repository

import domain.model.{Actor, FilmActor}

import zio.*

import java.sql.SQLException

trait ActorRepo {
  def getFilmActor: ZIO[Any, SQLException, List[(Actor,FilmActor)]]
}

object ActorRepo {
  def getFilmActor: ZIO[ActorRepo, SQLException, List[(Actor,FilmActor)]] =
    ZIO.serviceWithZIO[ActorRepo](_.getFilmActor)
}
