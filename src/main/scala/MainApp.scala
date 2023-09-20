package jp.webcrew.hands.on.zio

import io.netty.handler.codec.http.HttpHeaderNames
import jp.webcrew.hands.on.zio.application.service.impl.ApplicationServiceImpl
import jp.webcrew.hands.on.zio.presentation.controller.TestController
import zio.*
import zio.http.*

object MainApp extends ZIOAppDefault {

  override val run: ZIO[Any, Throwable, Nothing] =
    Server
      .serve(TestController().catchAllCauseZIO { _ =>
        // エラー処理
        ZIO.succeed(Response(status = Status.InternalServerError))
      })
      .provide(
        Server.default,
        ApplicationServiceImpl.layer
      )
}
