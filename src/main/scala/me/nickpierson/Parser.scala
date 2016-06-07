package me.nickpierson

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.{Document, Element}

private[nickpierson] object Parser {

  def parseTorrents(document: Document): List[Torrent] = {
    val torrents = document >> elementList("tr[id^=torrent_]")
    torrents.map(_.toTorrent)
  }

  implicit class ElementToTorrent(element: Element) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    def toTorrent: Torrent = {
      val dateText = element >> attr("title")("td:nth-child(4)")

      new Torrent(
        element >> text("a.cellMainLink"),
        element >> text("td:nth-child(2)"),
        (element >> text("td:nth-child(3)")).toInt,
        LocalDate.parse(dateText, formatter)
      )
    }
  }
}
