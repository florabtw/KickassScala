package me.nickpierson

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.{Document, Element}

private[nickpierson] object Parser {

  def parseTorrents(document: Document): List[Torrent] = {
    val torrents = document >> elementList("tr[id^=torrent_]")
    torrents.map(_.toTorrent)
  }

  implicit class ElementToTorrent(element: Element) {
    def toTorrent: Torrent = {
      new Torrent(
        element >> text("a.cellMainLink"),
        element >> text("td:nth-child(2)"),
        (element >> text("td:nth-child(3)")).toInt
      )
    }
  }
}
