import sbt.*

object Dependencies {
  private val SparkVersion: String = "3.5.4"

  val prod: Seq[ModuleID] = Seq(
    "org.apache.spark" %% "spark-core"      % SparkVersion,
    "org.apache.spark" %% "spark-sql"       % SparkVersion,
    "org.apache.spark" %% "spark-streaming" % SparkVersion
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.2.19",
    "org.scalamock" %% "scalamock" % "6.1.1"
  ).map { _ % Test }

  val all: Seq[ModuleID] = prod ++ test
}
