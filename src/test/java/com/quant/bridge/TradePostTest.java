package com.quant.bridge;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by yuer on 2016/11/29.
 */
public class TradePostTest {

    String url = "http://127.0.0.1:8080";
    String token = "hello";

    @Test
    public void testBuy() throws IOException {
        Response response = Request.Post(url + "/buy/" + token).bodyForm(
                Form.form()
                        .add("security", "000002")
                        .add("name", "万科A")
                        .add("price", "10.00")
                        .add("position", "10000.00")
                        .add("amount", "100")
                        .build(), Charset.forName("utf8")
        ).execute();
        String x = response.returnContent().asString();
        System.out.println(x);
    }

    @Test
    public void testSell() throws IOException {
        String response = Request.Post(url + "/sell/" + token).bodyForm(
                Form.form()
                        .add("security", "000002")
                        .add("name", "万科A")
                        .add("price", "10.00")
                        .add("position", "10000.00")
                        .add("amount", "100")
                        .build(), Charset.forName("utf8")
        ).execute().returnContent().asString();
        System.out.println(response);
    }
}
