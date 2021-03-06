package com.example.covid_tracing_app;

import android.os.AsyncTask;
import android.util.Log;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class AmqpTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "AmqpTask";

    String QUEUE_NAME = "user.position.queue";
    ConnectionFactory factory = new ConnectionFactory();
    JSONObject jsonObject;

    AmqpTask(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG,"Beacon AMQP TEST");
        factory.setHost("203.250.32.29");
        factory.setPort(54326);
        factory.setUsername("covid");
        factory.setPassword("covid");
        try {
            Connection connection;
            Channel channel;
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("user.position", "user.position.route", new AMQP.BasicProperties.Builder().contentType("application/json").build(), jsonObject.toString().getBytes());
            System.out.println(" [x] Sent '" + jsonObject.toString() + "'");
            Thread.sleep(10);
            //channel.close();
            //connection.close();

        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}
