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
  val NUMBER_OF_COMMENTS = Value("a.icommentjs")
  val IS_VERIFIED = Value("a[title=Verified Torrent]")
  val MAGNET_LINK = Value("a[title=Torrent magnet link]")
}

private[nickpierson] object Parser {

  def parseTorrents(document: Document): List[Torrent] = {
    val torrents = document >> elementList("tr[id^=torrent_]")
    torrents.map(_.toTorrent)
  }

  implicit class ElementToTorrent(el: Element) {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")

    def toTorrent: Torrent = {
      val dateText = el >> attr("title")(Selectors.UPLOADED.toString)

      new Torrent(
         el >>  text(Selectors.NAME.toString),
         el >>  text(Selectors.SIZE.toString),
        (el >>  text(Selectors.FILES.toString)).toInt,
        LocalDate.parse(dateText, formatter),
        (el >>  text(Selectors.SEEDERS.toString)).toInt,
        (el >>  text(Selectors.LEECHERS.toString)).toInt,
         el >>  text(Selectors.UPLOADER.toString),
        (el >?> text(Selectors.NUMBER_OF_COMMENTS.toString)).map(_.toInt).getOrElse(0),
        (el >?> element(Selectors.IS_VERIFIED.toString)).isDefined,
         el >>  attr("href")(Selectors.MAGNET_LINK.toString)
      )
    }
  }
}
