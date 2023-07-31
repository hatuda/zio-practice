package jp.webcrew.hands.on.zio

import zio._

object MainApp extends ZIOAppDefault {
  def run: ZIO[Any, Throwable, Unit] = Console.printLine("Hello, World!")
}
