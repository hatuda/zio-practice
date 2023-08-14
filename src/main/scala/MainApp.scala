package jp.webcrew.hands.on.zio

import application.service.ApplicationService
import application.service.impl.ApplicationServiceImpl

import zio.*

import java.text.SimpleDateFormat
import java.util.Date

object MainApp extends ZIOAppDefault {
  def run: ZIO[Any, Throwable, Unit] = {
    for {
      date <- ZIO.attempt {
        import java.text.SimpleDateFormat
        // 任意の日付文字列
        val inpDateStr = "2023/07/aa 17:46:00"
        // 取り扱う日付の形にフォーマット設定
        val sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        // Date型に変換( DateFromatクラスのparse() )
        val date = sdformat.parse(inpDateStr)
        date
      }
      _ <- consoleOutput(date)
    } yield ()
  }.catchAllCause(cause => Console.printLine(cause))

  def consoleOutput(date: Date): ZIO[Any, Throwable, Unit] =
    for {
      _ <- Console.printLine(
        s"${new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date)} Hello, World!"
      )
    } yield ()
}
