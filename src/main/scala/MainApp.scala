package jp.webcrew.hands.on.zio

import application.service.ApplicationService
import application.service.impl.ApplicationServiceImpl
import infrastructure.config.MySQLContext.mysqlLayer
import infrastructure.datasource.ActorRepoImpl

import zio.*

object MainApp extends ZIOAppDefault {
  def run: ZIO[Any, Throwable, Unit] = ApplicationService.consoleOutput().provide(
    ZLayer.fromZIO(ZIO.attempt {
      import java.text.SimpleDateFormat
      // 任意の日付文字列
      val inpDateStr = "2023/07/25 17:46:00"

      // 取り扱う日付の形にフォーマット設定
      val sdformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

      // Date型に変換( DateFromatクラスのparse() )
      sdformat.parse(inpDateStr)
    }),
    ApplicationServiceImpl.layer,
    ActorRepoImpl.layer,
    mysqlLayer
  )
}
