package jp.webcrew.hands.on.zio

import zio._
import zio.http._

object MainApp extends ZIOAppDefault {

  val app: App[Any] =
    Http.collect[Request] {
      case Method.GET -> Root / "text" => Response.text("Hello World!")
    }

  override val run =
    Server.serve(app).provide(Server.default)
}