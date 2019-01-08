package com.huyan.disruptor.heigh.chain;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WaitStrategy;

public class Handler2 implements EventHandler<Trade> {

	public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
		System.err.println("Handler 2 : SET ID");
		event.setId(UUID.randomUUID().toString());
		Thread.sleep(2000);
	}

}
