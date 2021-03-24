package com.example.MyJupiter.serlvet;

import com.example.MyJupiter.external.TwitchClient;
import com.example.MyJupiter.external.TwitchException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
public class GameServlet extends HttpServlet {
    // do not support for post, we only get data from Twitch
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String name = jsonRequest.getString("name");
        String developer = jsonRequest.getString("developer");
        String releaseTime = jsonRequest.getString("release_time");
        String website = jsonRequest.getString("website");
        float price = jsonRequest.getFloat("price");
        // Print game information to IDE console
        System.out.println("Name is: " + name);
        System.out.println("Developer is: " + developer);
        System.out.println("Release time is: " + releaseTime);
        System.out.println("Website is: " + website);
        System.out.println("Price is: " + price);
        // Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        ObjectMapper mapper = new ObjectMapper();
//
//        GameV.Builder builder = new GameV.Builder();
//        builder.setName("World of Warcraft");
//        builder.setDeveloper("Blizzard Entertainment");
//        builder.setReleaseTime("Feb 11, 2005");
//        builder.setWebsite("https://www.worldofwarcraft.com");
//        builder.setPrice(49.99);
//
//        GameV game = builder.build();
//        response.getWriter().print(mapper.writeValueAsString(game));



        String gameName = request.getParameter("game_name");
        TwitchClient client = new TwitchClient();

        response.setContentType("application/json;charset=UTF-8");

        try {
            if (gameName != null) {
                // use response, since we get data from website
                response.getWriter().println(new ObjectMapper().writeValueAsString(client.searchGame(gameName)));
            } else {
                // if limit is 0, will convert to 20 as default limit
                response.getWriter().print(new ObjectMapper().writeValueAsString(client.topGames(0)));
            }
        } catch (TwitchException e) {
            throw new ServletException(e);
        }

        // when use request, when use response?
        // Talk Shows & Podcasts

        /**
         * 1. java lambda how to write
         * 2. response, request, when to use, how to use
         * (example code and recheck my questions)
         * 3. Talk Shows & Podcasts
         * http://localhost:8082/MyJupiter/game?game_name=Talk%20Shows%20&%20Podcasts
         * {"id":"417752","name":"Talk Shows & Podcasts","box_art_url":"https://static-cdn.jtvnw.net/ttv-boxart/Talk%20Shows%20&%20Podcasts-{width}x{height}.jpg"}
         * 4. check for company's infra, how they set it up
         * */
    }
}
