package jp.webcrew.hands.on.zio.presentation.controller

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.http._

object TestController {
  def apply(): Http[ApplicationService, Throwable, Request, Response] = Http.collectZIO[Request] {
    case Method.GET -> Root / "text"            => ApplicationService.getTest
    case req @ Method.POST -> Root / "postTest" => ApplicationService.postTest(req)
    case Method.GET -> Root / "setCookie"       => ApplicationService.setCookie()
    case req @ Method.GET -> Root / "getCookie" => ApplicationService.getCookie(req)
    case Method.GET -> Root / "getApi"          => ApplicationService.getApi
    case Method.GET -> Root / "postApi" / test       => ApplicationService.postApi(test)
  }
}
