package doubledown.newsservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import doubledown.newsservice.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class NewsApiCollector {

    private final String CLIENT_ID = "9qjT11RuqqEjC8jw8FPQ"; //애플리케이션 클라이언트 아이디값"
    private final String CLIENT_SECRET = "brEWFQRMdf"; //애플리케이션 클라이언트 시크릿값"

    private final String SITE_NAVER = "NAVER";

    /**
     * @param <<code>keyword</code>에 관련된 뉴스 정보 얻어오는 메서드입니다.
     * @return <code>List<NewsDTO></code>뉴스 정보를 list로 가져옵니다.
     * */
    public List<NewsDTO> requestNews(String keyword) {
        String responseBody = "";
        NewsDTO newsResult = null;
        List<NewsDTO> list = null;
        try {
            String encodeKeyword = URLEncoder.encode(keyword, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/news?query=" + encodeKeyword;    // json 결과

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("X-Naver-Client-Id", CLIENT_ID);
            requestHeaders.put("X-Naver-Client-Secret", CLIENT_SECRET);
            responseBody = get(apiURL,requestHeaders);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(responseBody);
            JSONArray infoArr = (JSONArray) jsonObject.get("items");

            list = new ArrayList<>();

            for(int i=0;i<infoArr.size();i++){
                JSONObject tmp = (JSONObject)infoArr.get(i);
                NewsDTO infoObj = new NewsDTO((String)tmp.get("title"), (String)tmp.get("originallink"), (String)tmp.get("link")
                , (String)tmp.get("description"), (String)tmp.get("pubDate"), SITE_NAVER, LocalDateTime.now(), keyword);
                list.add(infoObj);
            }

            if(list == null) {
                throw new NullPointerException("해당 키워드의 기사가 없습니다.");
            }

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
