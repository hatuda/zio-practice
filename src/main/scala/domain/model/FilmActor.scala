package jp.webcrew.hands.on.zio
package domain.model

import java.util.Date

final case class FilmActor(actorId: Int,
                           filmId: Int,
                           lastUpdate: Date)
