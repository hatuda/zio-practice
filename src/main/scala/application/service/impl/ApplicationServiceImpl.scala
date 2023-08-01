package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import jp.webcrew.hands.on.zio.domain.repository.ActorRepo
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(currentDate: Date, actorRepo: ActorRepo) extends ApplicationService {
  override def consoleOutput(): ZIO[Any, Throwable, Unit] =
    for {
      _ <- Console.printLine(
        s"${new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate)} Hello, World!"
      )
      actors <- actorRepo.getActor
      _      <- Console.printLine("The head actor:" + actors.head.toString)
    } yield ()
}

object ApplicationServiceImpl {
  val layer: ZLayer[Date & ActorRepo, Nothing, ApplicationService] =
    ZLayer {
      for {
        currentDate <- ZIO.service[Date]
        actorRepo   <- ZIO.service[ActorRepo]
      } yield ApplicationServiceImpl(currentDate, actorRepo)
    }
}
