package jp.webcrew.hands.on.zio

import io.netty.handler.codec.http.HttpHeaderNames
import application.service.impl.ApplicationServiceImpl
import presentation.controller.TestController
import zio.*
import zio.http.*

object MainApp extends ZIOAppDefault {

  override val run: ZIO[Any, Throwable, Nothing] =
    Server
      .serve((TestController() @@ HttpAppMiddleware.addHeader("Content-Type", "text/html;charset=UTF-8")).catchAllCauseZIO { _ =>
        // エラー処理
        ZIO.succeed(Response(status = Status.InternalServerError))
      })
      .provide(
        Server.default,
        ApplicationServiceImpl.layer,
        Client.default
      )
}
