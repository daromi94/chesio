package com.daromi.chesio.analyzer

import com.daromi.chesio.analyzer.games.{GamesAnalyzer, GamesPreprocessor}
import com.daromi.chesio.analyzer.spark.SparkUtils

private object Config {
  val SparkAppName: String = "games"
  val SparkMaster: String  = "local[8]"
  val DatasetPath: String  = "datasets/games.csv"
}

object Main {

  def main(args: Array[String]): Unit = {
    val spark = SparkUtils.init(Config.SparkAppName, Config.SparkMaster)

    val preprocessor = new GamesPreprocessor(spark)

    val df = preprocessor.readCsv(Config.DatasetPath)

    val topOpenings = GamesAnalyzer.topOpenings(df)

    topOpenings.show()

    // TODO: Ensure proper Spark session cleanup
  }
}
