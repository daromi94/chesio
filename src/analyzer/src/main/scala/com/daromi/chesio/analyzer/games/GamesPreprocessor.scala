package com.daromi.chesio.analyzer.games

import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.nio.file.{Files, Paths}

private object SourceSchema {
  val schema: StructType = StructType(
    Seq(
      StructField("id", StringType, nullable = false),
      StructField("rated", StringType, nullable = false),
      StructField("created_at", DoubleType, nullable = false),
      StructField("last_move_at", DoubleType, nullable = false),
      StructField("turns", IntegerType, nullable = false),
      StructField("victory_status", StringType, nullable = false),
      StructField("winner", StringType, nullable = false),
      StructField("increment_code", StringType, nullable = false),
      StructField("white_id", StringType, nullable = false),
      StructField("white_rating", IntegerType, nullable = false),
      StructField("black_id", StringType, nullable = false),
      StructField("black_rating", IntegerType, nullable = false),
      StructField("moves", StringType, nullable = false),
      StructField("opening_eco", StringType, nullable = false),
      StructField("opening_name", StringType, nullable = false),
      StructField("opening_ply", IntegerType, nullable = false)
    )
  )
}

final class GamesPreprocessor(private val spark: SparkSession) {

  def readCsv(path: String): DataFrame = {
    require(Files.exists(Paths.get(path)), s"$path: file not found")

    spark.read
      .option("header", "true")
      .option("inferSchema", "false")
      .schema(SourceSchema.schema)
      .csv(path)
      .na
      .drop()
  }
}
