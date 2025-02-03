package com.daromi.chesio.analyzer.spark

import org.apache.spark.sql.SparkSession

object SparkUtils {

  /*
   * TODO:
   *  - Prevent multiple sessions
   *  - Ensure proper resource cleanup
   *  - Verify thread safety in distributed execution
   */
  def init(appName: String, master: String = "local[*]"): SparkSession = {
    val spark = SparkSession
      .builder()
      .appName(appName)
      .master(master)
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    spark
  }
}
