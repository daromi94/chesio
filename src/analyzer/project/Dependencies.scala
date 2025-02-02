import sbt.*

object Dependencies {
  val prod: Seq[ModuleID] = Seq(
    "org.apache.spark" %% "spark-core"      % "3.5.4" % Provided,
    "org.apache.spark" %% "spark-sql"       % "3.5.4" % Provided,
    "org.apache.spark" %% "spark-streaming" % "3.5.4" % Provided
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.2.19",
    "org.scalamock" %% "scalamock" % "6.1.1"
  ).map { _ % Test }

  val all: Seq[ModuleID] = prod ++ test
}
