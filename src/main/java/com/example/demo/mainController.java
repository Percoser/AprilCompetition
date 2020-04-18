package com.example.demo;

import com.example.demo.Models.Item;
import com.example.demo.Models.ItemRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Array;
import java.util.*;

@Controller
public class mainController {
    @Autowired
    ItemRepo itemRepo;

    @RequestMapping(value="/push")
    public ModelAndView push(@RequestParam("price") double price,
                             @RequestParam("timestamp") String timestamp){
        ModelAndView mv = new ModelAndView("redirect:/");
        Item itemToPush;

        itemToPush = new Item();
        itemToPush.setId(UUID.randomUUID().toString());
        itemToPush.setPrice(price);
        itemToPush.setTimestamp(timestamp);
        itemRepo.save(itemToPush);
        return mv;
    }

    @RequestMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView mv = new ModelAndView("redirect:/");
        itemRepo.deleteById(id);
        return mv;
    }

    @RequestMapping("/")
    public ModelAndView doHome() throws Exception {
        ModelAndView mv = new ModelAndView("index");

            String from = "USD";
            String to = "BTC";
            String key = "CZ2VUB5JBZCRVDCC";

            URL url = new URL("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="+to+"&to_currency="+from+"&apikey="+key);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                json.append(line);
            }
            JSONObject obj = new JSONObject(json.toString());
            JSONObject data = obj.getJSONObject(("Realtime Currency Exchange Rate"));
            String result = data.getString("9. Ask Price");
            String timestamp = data.getString("6. Last Refreshed");
            double price = Double.valueOf(result);

            mv.addObject("price", price);
            mv.addObject("timestamp", timestamp);


        mv.addObject("itemList", itemRepo.findAll());

        return mv;
    }

}
