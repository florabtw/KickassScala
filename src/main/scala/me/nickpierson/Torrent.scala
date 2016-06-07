package me.nickpierson

import java.time._

class Torrent private[nickpierson] (val name: String, val size: String, val numberOfFiles: Int, val uploaded: LocalDate) {

}
