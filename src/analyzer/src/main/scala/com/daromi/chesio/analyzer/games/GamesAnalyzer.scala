package com.daromi.chesio.analyzer.games

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, count}

object GamesAnalyzer {

  def topOpenings(df: DataFrame, topN: Int = 5): DataFrame = {
    require(!df.isEmpty, "dataframe is empty")

    df.groupBy(col("opening_name"))
      .agg(count("*").alias("games_played"))
      .orderBy(col("games_played").desc)
      .limit(topN)
  }
}
