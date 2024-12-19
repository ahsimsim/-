import java.net.URLEncoder;  // URL 인코딩을 위한 클래스
import java.nio.charset.StandardCharsets;  // UTF-8 등의 문자 인코딩 상수
import java.io.UnsupportedEncodingException;  // 인코딩 예외 처리


public class TranslateASCII {
    public static String sendtoPUUID(String str) throws UnsupportedEncodingException {
        //문자열 아스키 코드로 변환
        String summonerName = str;
        String encodedName = URLEncoder.encode(summonerName, StandardCharsets.UTF_8.toString());
        return encodedName;
    }
}
