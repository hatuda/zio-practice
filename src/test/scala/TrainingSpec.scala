package jp.webcrew.hands.on.zio

import application.service.ApplicationService
import application.service.impl.ApplicationServiceImpl
import zio.*
import zio.http.{Body, Client, Method, Request, URL}
import zio.test.*
import zio.test.Assertion.*

object TrainingSpec extends ZIOSpecDefault {
  def spec: Spec[Any, Throwable] = suite("HelloWorldSpec")(
    test("sayHello correctly displays output") {
      for {
        test <- ZIO.succeed("test")
        uri  <- ZIO.fromEither(URL.decode("http://localhost:8080/postTest"))
        req <- ZIO.attempt(
          Request
            .default(
              Method.POST,
              uri,
              Body.fromString(
                s"userName=$test"
              )
            )
        )
        res <- ApplicationService.postTest(req)
        bodyString <- res.body.asString
      } yield assertTrue(bodyString == s"あなたの名前は${test}です。")
    }.provide(ApplicationServiceImpl.layer, Client.default)
  )
}
