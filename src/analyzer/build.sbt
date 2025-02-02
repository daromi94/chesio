ThisBuild / scalaVersion := "2.13.16"
ThisBuild / organization := "com.daromi.chesio"
ThisBuild / version      := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).settings(name := "analyzer")

libraryDependencies := Dependencies.all
