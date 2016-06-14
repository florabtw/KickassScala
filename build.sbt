organization := "me.nickpierson"

name := "kickass-scala"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalactic"    %% "scalactic"                   % "2.2.6"
libraryDependencies += "org.scalatest"    %% "scalatest"                   % "2.2.6" % "test"
libraryDependencies += "org.scalamock"    %% "scalamock-scalatest-support" % "3.2.2" % "test"

libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "1.0.0"
libraryDependencies += "com.netaporter"   %% "scala-uri"     % "0.4.14"
