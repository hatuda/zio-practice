package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(currentDate: Date) extends ApplicationService {
  override def consoleOutput(): ZIO[Any, Throwable, Unit] =
    Console.printLine(
      s"${new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate)} Hello, World!"
    )
}

object ApplicationServiceImpl {
  val layer: ZLayer[Date, Nothing, ApplicationService] =
    ZLayer {
      for {
        currentDate <- ZIO.service[Date]
      } yield ApplicationServiceImpl(currentDate)
    }
}

