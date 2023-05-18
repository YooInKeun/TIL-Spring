import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

fun main() {
    val apiKey = "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" // OpenAI API Key
    val prompt = "Hello, how are you?"

    val url = URL("https://api.openai.com/v1/chat/completions")
    val con = url.openConnection() as HttpURLConnection
    con.requestMethod = "POST"
    con.setRequestProperty("Content-Type", "application/json")
    con.setRequestProperty("Authorization", "Bearer $apiKey")

    // API 요청 바디 생성
    val requestBody = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [
                        {
                            "role": "user", 
                            "content": "$prompt"
                        }
                ],
                "temperature": 0.7
            }"""

    // API 요청 바디 전송
    con.doOutput = true
    con.outputStream.write(requestBody.toByteArray(StandardCharsets.UTF_8))

    // API 응답 수신
    val response = StringBuilder()
    con.inputStream.bufferedReader().useLines { lines ->
        lines.forEach {
            response.append(it)
        }
    }

    // API 응답 출력
    println(response.toString())
}
