package jp.webcrew.hands.on.zio

import jp.webcrew.hands.on.zio.application.service.impl.ApplicationServiceImpl
import jp.webcrew.hands.on.zio.presentation.controller.TestController
import zio.*
import zio.http._

object MainApp extends ZIOAppDefault {


  override val run: ZIO[Any, Throwable, Nothing] =
    Server
      .serve(TestController().mapError { _ =>
        // エラー処理
        Response(status = Status.InternalServerError)
      })
      .provide(
        Server.default,
        ApplicationServiceImpl.layer
      )
}
