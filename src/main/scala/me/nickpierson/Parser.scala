package me.nickpierson

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model.{Document, Element}

private object Selectors extends Enumeration {
  val NAME = Value("a.cellMainLink")
  val SIZE = Value("td:nth-child(2)")
  val FILES = Value("td:nth-child(3)")
  val UPLOADED = Value("td:nth-child(4)")
  val SEEDERS = Value("td:nth-child(5)")
  val LEECHERS = Value("td:nth-child(6)")
  val UPLOADER = Value("a[href*=user]")
}

private[nickpierson] object Parser {

  def parseTorrents(document: Document): List[Torrent] = {
    val torrents = document >> elementList("tr[id^=torrent_]")
    torrents.map(_.toTorrent)
  }

  implicit class ElementToTorrent(element: Element) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    def toTorrent: Torrent = {
      val dateText = element >> attr("title")(Selectors.UPLOADED.toString)

      new Torrent(
        element >> text(Selectors.NAME.toString),
        element >> text(Selectors.SIZE.toString),
        (element >> text(Selectors.FILES.toString)).toInt,
        LocalDate.parse(dateText, formatter),
        (element >> text(Selectors.SEEDERS.toString)).toInt,
        (element >> text(Selectors.LEECHERS.toString)).toInt,
        element >> text(Selectors.UPLOADER.toString)
      )
    }
  }
}
