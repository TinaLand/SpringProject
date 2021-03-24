package com.example.MyJupiter.external;

import com.example.MyJupiter.entity.Game;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;


public class TwitchClientMine {

    private static final String TOKEN = "Bearer qx0ya5ff79tanei68dzwkullu7pjnd";
    private static final String CLIENT_ID = "tvcdhn56i2fdowizniupl2g0n3zblb";
    private static final String TOP_GAME_URL = "https://api.twitch.tv/helix/games/top?first=%s";
    private static final String GAME_SEARCH_URL_TEMPLATE = "https://api.twitch.tv/helix/games?name=%s";
    private static final int DEFAULT_GAME_LIMIT = 20;

    private String buildGameURL(String url, String gameName, int limit) {
        if (gameName.equals("")) {
            return String.format(url, limit);
        } else {
            try {
                gameName = URLEncoder.encode(gameName, "UTF-8"); // " " ==> %20
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return String.format(url, gameName);
        }
    }

    /*
    * 1. 定义一个 response
    * 2. 定义如何解析 这个 response 的 handler
    * 3. httpClient.execute(): 来发送请求
    *
    * */
    private String searchTwitch(String url)  {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int responseCode = response.getStatusLine().getStatusCode();
                if (responseCode != 200) {
                    throw new TwitchException("Failed to get result Twitch");
                }
                HttpEntity entity = response.getEntity();
                if (entity == null) {
                    throw new TwitchException("Failed to get result Twitch");
                }
                JSONObject obj = new JSONObject(EntityUtils.toString(entity));
                return obj.getJSONArray("data").toString();
            }
        };

        try {
            HttpGet request = new HttpGet(url);
            request.setHeader("Authorization", TOKEN);
            request.setHeader("Client-Id", CLIENT_ID);
            return httpClient.execute(request, responseHandler);
        } catch (IOException e) {
            throw new TwitchException("Failed to get result Twitch");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<Game> getGameList(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(data, Game[].class));
        } catch (JsonProcessingException e) {
            //e.printStackTrace();
            throw new TwitchException("Failed to get result Twitch");
        }
    }

    public List<Game> topGames(int limit) {
        if (limit <= 0) {
            limit = DEFAULT_GAME_LIMIT;
        }

        String url = buildGameURL(TOP_GAME_URL, "", limit);
        String response = searchTwitch(url);
        List<Game> games = getGameList(response);
        return games;
    }


    // wrong, did not check for size of game
//    public Game searchGame(String gameName) {
//        if (gameName == null) {
//            throw new TwitchException("Failed to get result Twitch");
//        }
//
//        String gamebody = searchTwitch(GAME_SEARCH_URL_TEMPLATE + gameName);
//        List<Game> game = getGameList(gamebody);
//        return game.get(0);
//    }

    public Game seachGame(String gameName) throws TwitchException {
        List<Game> gameList = getGameList(searchTwitch(buildGameURL(GAME_SEARCH_URL_TEMPLATE, "", 0)));
        if (gameList.size() != 0) {
            return gameList.get(0);
        }
        return null;
    }



    // how to write java lambda expression

    // Send HTTP request to Twitch Backend based on the given URL, and returns the body of the HTTP response returned from Twitch backend.
    // update to lambda
//    private String searchTwitch(String url) throws TwitchException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//
//        // Define the response handler to parse and return HTTP response body returned from Twitch
//        ResponseHandler<String> responseHandler = response -> {
//            int responseCode = response.getStatusLine().getStatusCode();
//            if (responseCode != 200) {
//                System.out.println("Response status: " + response.getStatusLine().getReasonPhrase());
//                throw new TwitchException("Failed to get result from Twitch API");
//            }
//            HttpEntity entity = response.getEntity();
//            if (entity == null) {
//                throw new TwitchException("Failed to get result from Twitch API");
//            }
//            JSONObject obj = new JSONObject(EntityUtils.toString(entity));
//            return obj.getJSONArray("data").toString();
//        };
//
//    }


}
