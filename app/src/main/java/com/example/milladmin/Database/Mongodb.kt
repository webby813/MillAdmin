package com.example.milladmin

import android.os.AsyncTask
import android.util.Log
import com.example.milladmin.Database.MillData
import com.example.milladmin.Database.UserData
import com.example.milladmin.Database.fetchedMillData
import com.example.milladmin.Database.newMillData
import com.example.milladmin.Database.newUserData

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class GetMills(private val listener: OnDataFetchedListener) :
    AsyncTask<Void, Void, List<MillData>>() {

    interface OnDataFetchedListener {
        fun onDataFetched(data: List<MillData>)
    }

    private val mongoDBApiUrl =
        "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-eisuw/endpoint/get_mill"

    override fun doInBackground(vararg params: Void?): List<MillData> {
        try {
            val url = URL(mongoDBApiUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val jsonArray = JSONArray(stringBuilder.toString())
            val dataList = ArrayList<MillData>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val millId = jsonObject.getString("PiID")
                val millName = jsonObject.getString("name")
                val millStatus = jsonObject.getString("status")
                val steamPress = jsonObject.getString("steamPress")
                val steamFlow = jsonObject.getString("SteamFlow")
                val wtrLevel = jsonObject.getString("WtrLevel")
                val pwrFrq = jsonObject.getString("PwrFrq")
                val mode = jsonObject.getString("mode")
                val proxyport = jsonObject.getString("proxyport")
                val tunnelport = jsonObject.getString("tunnelport")

                dataList.add(
                    MillData(
                        millId,
                        millName,
                        millStatus,
                        steamPress,
                        steamFlow,
                        wtrLevel,
                        pwrFrq,
                        mode,
                        proxyport,
                        tunnelport
                    )
                )
            }
            return dataList
        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return ArrayList()
    }

    override fun onPostExecute(result: List<MillData>) {
        super.onPostExecute(result)
        listener.onDataFetched(result)
//        print(result)
    }
}


class GetUsers(private val listener: OnDataFetchedListener) :
    AsyncTask<Void, Void, List<UserData>>() {

    interface OnDataFetchedListener {
        fun onDataFetched(data: List<UserData>)
    }

    private val mongoDBApiUrl =
        "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-eisuw/endpoint/get_users"

    override fun doInBackground(vararg params: Void?): List<UserData> {
        try {
            val url = URL(mongoDBApiUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            val jsonArray = JSONArray(stringBuilder.toString())
            val dataList = ArrayList<UserData>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val username = jsonObject.getString("name")
                val phoneNum = jsonObject.getString("phoneNum")

                dataList.add(UserData(username,phoneNum))
            }

            return dataList
        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return ArrayList()
    }

    override fun onPostExecute(result: List<UserData>) {
        super.onPostExecute(result)
        listener.onDataFetched(result)
//        print(result)
    }
}

class CreateUser(private val listener: OnUserCreatedListener) :
    AsyncTask<newUserData, Void, Boolean>() {

    interface OnUserCreatedListener {
        fun onUserCreated(success: Boolean)
    }

    private val realmFunctionUrl =
        "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-eisuw/endpoint/create_user"

    override fun doInBackground(vararg params: newUserData): Boolean {
        try {
            val url = URL(realmFunctionUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.doOutput = true

            val userData = params[0]
            val jsonRequestBody = JSONObject()
            jsonRequestBody.put("name", userData.name)
            jsonRequestBody.put("phoneNum", userData.phoneNum)
            jsonRequestBody.put("status", userData.status)
            jsonRequestBody.put("role", userData.role)
            jsonRequestBody.put("state", userData.state)

            val requestString = jsonRequestBody.toString()
            Log.d("CreateUser", "Request JSON: $requestString")

            val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
            outputStreamWriter.write(requestString)
            outputStreamWriter.close()

            val responseCode = urlConnection.responseCode
            return responseCode == HttpURLConnection.HTTP_OK

        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return false
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        listener.onUserCreated(result)
    }
}

class CreateMill(private val listener: OnMillCreatedListener) :
    AsyncTask<newMillData, Void, Boolean>() {

    interface OnMillCreatedListener {
        fun onMillCreated(success: Boolean)
    }

    private val realmFunctionUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-eisuw/endpoint/create_mill"

        override fun doInBackground(vararg params: newMillData): Boolean {
        try {
            val url = URL(realmFunctionUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "POST"
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.doOutput = true

            val millData = params[0]
            val jsonRequestBody = JSONObject()
            jsonRequestBody.put("PiID", millData.PiID)
            jsonRequestBody.put("name", millData.name)
            jsonRequestBody.put("status", millData.status)
            jsonRequestBody.put("steamPress", millData.steamPress)
            jsonRequestBody.put("SteamFlow", millData.SteamFlow)
            jsonRequestBody.put("WtrLevel", millData.WtrLevel)
            jsonRequestBody.put("PwrFrq", millData.PwrFrq)
            jsonRequestBody.put("mode", millData.mode)
            jsonRequestBody.put("proxyport", millData.proxyport)
            jsonRequestBody.put("tunnelport", millData.tunnelport)

            val requestString = jsonRequestBody.toString()
            Log.d("CreateMill", "Request JSON: $requestString")

            val outputStreamWriter = OutputStreamWriter(urlConnection.outputStream)
            outputStreamWriter.write(requestString)
            outputStreamWriter.close()

            val responseCode = urlConnection.responseCode
            return responseCode == HttpURLConnection.HTTP_OK

        } catch (e: Exception) {
//            e.printStackTrace()
        }
        return false
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)
        listener.onMillCreated(result)
    }
}

class FetchDataAsHint(private val listener: OnDataFetchedListener, private val piID: String) :
    AsyncTask<Void, Void, List<fetchedMillData>>() {

    interface OnDataFetchedListener {
        fun onDataFetched(data: List<fetchedMillData>)
    }

    // Modify your mongoDBApiUrl to include the PiID parameter
    private val mongoDBApiUrl = "https://asia-south1.gcp.data.mongodb-api.com/app/application-0-eisuw/endpoint/fetch_selected_mill?PiID=$piID"

    override fun doInBackground(vararg params: Void?): List<fetchedMillData> {
        try {
            val url = URL(mongoDBApiUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStreamReader = InputStreamReader(urlConnection.inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuilder()
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            if (stringBuilder.isNotEmpty()) {
                try {
                    val jsonArray = JSONArray(stringBuilder.toString())
                    val fetchedDataList = ArrayList<fetchedMillData>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val PiID = jsonObject.getString("PiID")
                        val name = jsonObject.getString("name")
                        val status = jsonObject.getString("status")
                        val steamPress = jsonObject.getString("steamPress")
                        val SteamFlow = jsonObject.getString("SteamFlow")
                        val WtrLevel = jsonObject.getString("WtrLevel")
                        val PwrFrq = jsonObject.getString("PwrFrq")
                        val mode = jsonObject.getString("mode")
                        val proxyport = jsonObject.getString("proxyport")
                        val tunnelport = jsonObject.getString("tunnelport")

//                        println("Adding data to list: $fetchedDataList")

                        fetchedDataList.add(
                            fetchedMillData(
                                PiID, name, status, steamPress, SteamFlow,
                                WtrLevel, PwrFrq, mode, proxyport, tunnelport
                            )
                        )
                    }

                    return fetchedDataList
                } catch (e: JSONException) {
                }
            } else {
                Log.e("TestRun", "Empty response from the server")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ArrayList()
    }

    override fun onPostExecute(result: List<fetchedMillData>) {
        super.onPostExecute(result)
        listener.onDataFetched(result)
    }
}
