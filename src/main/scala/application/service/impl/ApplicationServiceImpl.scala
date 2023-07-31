package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(currentDate: Date) extends ApplicationService {
  override def consoleOutput(): ZIO[Any, IOException, Unit] =
    for {
      // ZIO型を返す関数へ引数を渡す段階で例外が投げられるとdefectエラーになります
      _ <- Console.printLine(
        s"${List.empty.head.toString} ${new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentDate)} Hello, World!"
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
