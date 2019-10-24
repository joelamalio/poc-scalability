package br.com.joelamalio.scalabilityconsumer.resources.repositories

class MetricRepository {

    private val database = mutableMapOf<String, Int>()

    fun insert(key: String) {
        database[key] = if (database.containsKey(key)) database[key]!!.plus(1) else 1
    }

    fun listAll(): List<String> = database.map { (k, v) -> "$k : $v" }.sorted()

}