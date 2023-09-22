package jp.webcrew.hands.on.zio.application.service
import zio.ZIO
import zio.http.{Request, Response}

import java.io.IOException

trait ApplicationService {
  def getTest: ZIO[Any, Throwable, Response]
  def postTest(request: Request): ZIO[Any, Throwable, Response]
  def setCookie(): ZIO[Any, Throwable, Response]
  def getCookie(request: Request): ZIO[Any, Throwable, Response]
  def getApi: ZIO[Any, Throwable, Response]
  def postApi(param:String): ZIO[Any, Throwable, Response]
}

object ApplicationService {
  def getTest: ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.getTest)
  }

  def postTest(request: Request): ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.postTest(request))
  }

  def setCookie(): ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.setCookie())
  }
  def getCookie(request: Request): ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.getCookie(request))
  }

  def getApi: ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.getApi)
  }

  def postApi(param:String): ZIO[ApplicationService, Throwable, Response] = {
    ZIO.serviceWithZIO[ApplicationService](_.postApi(param))
  }
}
