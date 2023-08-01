package jp.webcrew.hands.on.zio
package domain.model
import java.util.Date
final case class Actor (actorId: Int,
                        firstName: String,
                        lastName: String,
                        lastUpdate: Date)
