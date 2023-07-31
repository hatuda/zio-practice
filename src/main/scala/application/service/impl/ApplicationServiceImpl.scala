package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(currentDate: Date) extends ApplicationService {
  override def consoleOutput(): ZIO[Any, Throwable, Unit] =
    for {
      _ <- ZIO
        .attempt {
          // 末尾最適化されていない再帰関数をエラーが発生するまで呼び出します。
          def recurForError(num: Int): Int = {
            if (recurForError(num * 500) % 2 == 0 || recurForError(num - 1) % 21 == 1) recurForError(num * 10000) else 500
          }

          recurForError(50000000)
        }
      _ <- Console.printLine(
        s"${new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate)} Hello, World!"
      )
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
