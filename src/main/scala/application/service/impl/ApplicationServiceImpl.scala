package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(currentDate: Date) extends ApplicationService {
  override def consoleOutput(): ZIO[Any, Throwable, Unit] = for {
    // 以下に関して警告が表示されるかもしれませんが、動作確認が主目的のため一旦無視します。
    _ <- ZIO.foreach(List.range(1, 100))(n => Console.printLine(n))
  } yield ()
}

object ApplicationServiceImpl {
  val layer: ZLayer[Date, Nothing, ApplicationService] =
    ZLayer {
      for {
        currentDate <- ZIO.service[Date]
      } yield ApplicationServiceImpl(currentDate)
    }
}
