package jp.webcrew.hands.on.zio.application.service.impl

import jp.webcrew.hands.on.zio.application.service.ApplicationService
import zio.http.*
import zio.http.Header.Cookie.render
import zio.{Chunk, Console, ZIO, ZLayer}

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

case class ApplicationServiceImpl(client: Client) extends ApplicationService {
  override def getTest: ZIO[Any, Throwable, Response] = ZIO.attempt(Response.text("Hello World!"))
  override def postTest(request: Request): ZIO[Any, Throwable, Response] = for {
    form <- request.body.asURLEncodedForm
    response <- form.get("userName") match
      case Some(userName) =>
        for {
          un <- userName.asText
        } yield Response.text(s"あなたの名前は${un}です。")
      case _ => ZIO.attempt(Response.text("userNameを指定してください"))
  } yield response

  override def setCookie(): ZIO[Any, Throwable, Response] = for {
    responseCookie <- ZIO.attempt(Cookie.Response("key", "hello"))
  } yield Response.ok.addCookie(responseCookie)

  override def getCookie(request: Request): ZIO[Any, Throwable, Response] = for {
    cookie <- ZIO.attempt{request.header(Header.Cookie) match
      case Some(ck) => ck.value.toChunk.mkString("")
      case _ => "先にsetCookieをよびだしてください"}
    _ <- Console.printLine("cookie:"+cookie)
  } yield Response.text(cookie).setHeaders(Headers("Content-Type", "text/plain;charset=UTF-8")) // 文字化け対策をしています

  override def getApi: ZIO[Any, Throwable, Response] = for{
    uri <- ZIO.fromEither(URL.decode("http://localhost:8080/text"))
    res <- client.request(
      Request
        .default(
          Method.GET,
          uri,
          Body.empty
        )
    )
    data <- res.body.asString
  } yield Response.text(s"http://localhost:8080/text:$data")
}
object ApplicationServiceImpl {
  val layer: ZLayer[Client, Nothing, ApplicationService] =
    ZLayer{for {
      client <- ZIO.service[Client]
    } yield ApplicationServiceImpl(client)}

}
