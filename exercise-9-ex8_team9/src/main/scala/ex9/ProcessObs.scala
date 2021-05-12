package ex9

import java.io.File
import java.net.URL

import com.github.tototoshi.csv.CSVReader

object ProcessObs {
  def main(args: Array[String]): Unit = {
    val ave_weight = parse()
    assert( ave_weight == 39556.0 ) // Neil told me we should somehow show that it is working.
  }

  def parse(): Double = {
    // Get the .csv as a stream
    val file_url: URL = getClass.getResource("/observations.csv")
    val reader = CSVReader.open(new File(file_url.toURI())).toStreamWithHeaders
    // Calculate and return the average weight in pounds, so long as whale weighs between 0 and 50,000 kg
    reader.foldLeft((0, 1))((tup, x) => {
      if (x("est_size").toInt > 0 && x("est_size").toInt < 50000) {
        (tup._1 + (x("est_size").toInt - tup._1) / tup._2, tup._2 + 1)
      } else {
        (tup._1, tup._2)
      }
    })._1 * 2.2
  }
}