package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.http._
import zio.{Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl() extends ApplicationService {
  override def getTest: ZIO[Any, Throwable, Response] = ZIO.attempt(Response.text("Hello World!"))
}

object ApplicationServiceImpl {
  val layer: ZLayer[Any, Nothing, ApplicationService] =
    ZLayer(ZIO.succeed(ApplicationServiceImpl()))

}
