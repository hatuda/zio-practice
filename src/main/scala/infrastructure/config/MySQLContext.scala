package jp.webcrew.hands.on.zio
package infrastructure.config
import io.getquill.SnakeCase
import io.getquill.jdbczio.Quill
import zio.{ZEnvironment, ZIO, ZLayer}

import java.sql.{Connection, DriverManager, SQLException}
import javax.sql.DataSource
case class MySQLContext(q: Quill.Mysql[SnakeCase.type])
object MySQLContext {

  val dsLayer: ZLayer[Any, Throwable, DataSource] =
    Quill.DataSource.fromPrefix("mysql")

  val quillLayer: ZLayer[DataSource, Nothing, Quill.Mysql[SnakeCase.type]] =
    Quill.Mysql.fromNamingStrategy(SnakeCase)

  val mysqlLayer: ZLayer[Any, Throwable, MySQLContext] =
    (this.dsLayer >>> this.quillLayer)
      .map(zEnv => ZEnvironment(MySQLContext(zEnv.get)))

}
