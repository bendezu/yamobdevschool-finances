package com.bendezu.yandexfinances.data.network

import com.bendezu.yandexfinances.data.model.Currency
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val SERVER_URL = "https://free.currencyconverterapi.com/"
private const val METHOD = "api/v6/convert"
private const val QUERY = "?q="
private const val COMPACT = "compact=ultra"
private const val AMPERSAND = "&"

fun convert(from: Currency, to: Currency, callback: (String)->(Unit)) {
    launch {
        val value = convertSync(from.label, to.label)
        launch(UI) {
            callback(value)
        }
    }
}

fun convertSync(from: String, to: String): String {
    if (from == to) return "1"
    val stringUrl = SERVER_URL + METHOD + QUERY + from + "_" + to + AMPERSAND + COMPACT
    val url = URL(stringUrl)
    val connection = url.openConnection() as HttpURLConnection
    connection.connectTimeout = 5000
    connection.readTimeout = 5000
    var code = 0
    try {
        code = connection.responseCode
    } catch (e: Exception) {
        throw IOException("No internet connection")
    }
    when (code) {
        HttpURLConnection.HTTP_OK -> {
            val streamReader = InputStreamReader(connection.inputStream)
            val reader = BufferedReader(streamReader)
            val response = reader.readLine()
            val jsonObject = JSONObject(response)
            return jsonObject.getString("${from}_${to}")
            //return response.substring(response.indexOf(':') + 1, response.length - 1)
        }
        else -> {
            throw IOException("Error")
        }
    }
}