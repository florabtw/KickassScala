package me.nickpierson

import java.time._

class Torrent private[nickpierson](val name: String,
                                   val size: String,
                                   val files: Int,
                                   val uploaded: LocalDate,
                                   val seeders: Int,
                                   val leechers: Int,
                                   val uploader: String,
                                   val numberOfComments: Int,
                                   val isVerified: Boolean,
                                   val magnetLink: String,
                                   val downloadLink: String
                                  )
