import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) {

        // API requests 요청 변수
        String key = "RGAPI-e94d56fb-4c10-4f88-bd55-25f9100d32bc";
        String baseURL = "https://americas.api.riotgames.com";
        String inputGameName = "Hide on bush";
        String encodedGameName = "";
        String inputTagLine = "KR1";


        // 플레이어 서버 정보
        String gameName = "";
        String tagLine = "";
        String puuid = "";
        String id = "";
        String accountId = "";

        //플레이어 프로필 정보
        int revisionDate = 0;
        String summonerLevel = "";

        String userInfoJSON = "";
        try {
            encodedGameName = TranslateASCII.sendtoPUUID(inputGameName);
            URL url = new URL(baseURL + "/riot/account/v1/accounts/by-riot-id/" + encodedGameName + "/" + inputTagLine + "?api_key=" + key);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            userInfoJSON = br.readLine();
            System.out.println("JSON 내용: " + userInfoJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            JSONObject playerInfo = (JSONObject) new JSONParser().parse(userInfoJSON);
                puuid = (String) playerInfo.get("puuid");
                gameName = (String) playerInfo.get("gameName");
                tagLine = (String) playerInfo.get("tagLine");
        }catch (Exception e){
            System.out.println("(Error!) playerInfo를 변수에 저장하지 못함");
        }

        String matchJSON = "";
        try{
            int count = 10;
            int queue = 420;
            URL url = new URL(baseURL + "/lol/match/v5/matches/by-puuid/" + puuid + "/ids?queue="+ queue +"&start=0&count="+ count +"&api_key=" + key);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            matchJSON = br.readLine();
        }catch (Exception e){
            System.out.println("(Error!) puuid를 통해서 매치정보를 얻지 못함");
        }
    }


}
