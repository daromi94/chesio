package com.daromi.chesio.analyzer

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types._

object Main extends App {
  val spark = SparkSession
    .builder()
    .appName("analyzer")
    .master("local[8]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("WARN")

  val DatasetPath = "datasets/games.csv"

  // Define schema
  val schema = StructType(
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

  // Read dataset
  val games =
    spark.read
      .option("header", "true")
      .schema(schema)
      .csv(DatasetPath)
      .na
      .drop()

  games.printSchema()
  games.show()

  // Query
  games
    .groupBy(col("opening_name"))
    .count()
    .orderBy(col("count").desc)
    .limit(5)
    .show()
}
