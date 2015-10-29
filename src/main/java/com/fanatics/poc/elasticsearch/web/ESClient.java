package com.fanatics.poc.elasticsearch.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESClient {

	private static final Logger logger = LoggerFactory.getLogger(ESClient.class);
	private static TransportClient client;
	
	public static void startClient() {
		logger.debug("Entering ESClient.startClient()");
		Settings settings = ImmutableSettings.settingsBuilder()
				//Settings.settingsBuilder()
		        .put("cluster.name", "elasticsearch_deepak_dev").build();
		//client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		//client = TransportClient.builder().build()
		  //      .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		InetSocketTransportAddress address = new InetSocketTransportAddress(
		        "localhost", 9300);
		client = new TransportClient(settings);
		client.addTransportAddress(address);
		logger.debug("Exiting ESClient.startClient()");

	}
	
	public static void stopClient(){
		logger.debug("Entering ESClient.stop()");
		client.close();
		logger.debug("Exiting ESClient.stop()");
	}

	public static Client getClient(){
		logger.debug("Entering ESClient.getClient()");
		if(client == null)
			try {
				startClient();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Could not connecto to node", e);
			}
		
		logger.debug("Exiting ESClient.getClient()");
		return client;
	}
}
