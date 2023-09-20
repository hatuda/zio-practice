package jp.webcrew.hands.on.zio.presentation.controller

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.http._

object TestController {
  def apply(): Http[ApplicationService, Throwable, Request, Response] = Http.collectZIO[Request] {
    case Method.GET -> Root / "text" => ApplicationService.getTest
  }
}
