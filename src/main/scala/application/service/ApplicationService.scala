package jp.webcrew.hands.on.zio.application.service
import zio.ZIO
import zio.http.{Request, Response}

import java.io.IOException

trait ApplicationService {
  def getTest: ZIO[Any, Throwable, Response]
  def postTest(request: Request): ZIO[Any, Throwable, Response]
}

object ApplicationService {
  def getTest: ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.getTest)
  }

  def postTest(request: Request): ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.postTest(request))
  }
}
