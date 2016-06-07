package me.nickpierson

import java.time._

class Torrent private[nickpierson] (val name: String, val size: String, val files: Int, val uploaded: LocalDate) {

}
