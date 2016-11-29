package com.quant.bridge.controller;

import com.alibaba.fastjson.JSON;
import com.quant.bridge.Application;
import com.quant.bridge.domain.Action;
import com.quant.bridge.domain.Message;
import com.quant.bridge.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/")
public class TradeController {
    private static Logger logger = LoggerFactory.getLogger(TradeController.class);

    @RequestMapping(value = "/buy/{token}", method = RequestMethod.POST)
    public String buy(@PathVariable("token") String token,
                      @RequestParam("security") String security,
                      @RequestParam("name") String name,
                      @RequestParam("price") double price,
                      @RequestParam("position") double position,
                      @RequestParam("amount") int amount) {
        if (!Application.clientIsOnline(token)) return "CLIENT (" + token + ") NOT FOUND";

        Action action = new Action(position, security, name, amount, price);
        Message message = new Message(Constants.COMMAND_BUY, JSON.toJSONString(action));
        Application.Connections.get(token).writeAndFlush(message);
        logger.debug(JSON.toJSONString(message));
        return "SUCCESS";//"hello" + token;
    }

    @RequestMapping(value = "/sell/{token}", method = RequestMethod.POST)
    public String sell(@PathVariable("token") String token,
                       @RequestParam("security") String security,
                       @RequestParam("name") String name,
                       @RequestParam("price") double price,
                       @RequestParam("position") double position,
                       @RequestParam("amount") int amount) {
        if (!Application.clientIsOnline(token)) return "CLIENT (" + token + ") NOT FOUND";

        Action action = new Action(position, security, name, amount, price);
        Message message = new Message(Constants.COMMAND_SELL, JSON.toJSONString(action));
        Application.Connections.get(token).writeAndFlush(message);
        logger.debug(JSON.toJSONString(message));
        return "SUCCESS";//"hello" + token;
    }

} 