package jp.webcrew.hands.on.zio.application.service
import zio.ZIO

trait ApplicationService {
  def consoleOutput(): ZIO[Any, Throwable, Unit]
}

object ApplicationService {
  def consoleOutput(): ZIO[ApplicationService, Throwable, Unit] =
    ZIO.serviceWithZIO[ApplicationService](_.consoleOutput())
}
