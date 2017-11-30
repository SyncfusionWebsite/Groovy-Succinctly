/*
 *    Copyright 2016 Duncan Dickinson
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Performs a query on an SQLite database using a query variable
 * and displays the result
 */

//Source file: SQLiteDatabaseQuery.groovy

@GrabConfig(systemClassLoader = true)
@Grab('org.xerial:sqlite-jdbc:3.8.11.2')

import groovy.sql.Sql

Sql.withInstance('jdbc:sqlite:../../data/weather_sqlite.db') { sql ->
    def extremeTemp = 38

    println "Extreme temperatures (over $extremeTemp degrees):"
    sql.eachRow("""
      SELECT strftime('%Y-%m-%d', recordingDate) as recordingDate,
             maxTemp
      FROM WeatherData
      WHERE maxTemp > $extremeTemp""") { row ->
        println "  - ${row.recordingDate}: $row.maxTemp"
    }
}
