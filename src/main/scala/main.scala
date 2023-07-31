package jp.webcrew.hands.on.zio

import zio._

object MainApp extends ZIOAppDefault {
  def run = Console.printLine("Hello, World!")
}