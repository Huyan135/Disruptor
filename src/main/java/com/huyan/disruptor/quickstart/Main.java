package com.huyan.disruptor.quickstart;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Main {

	
	
	public static void main(String[] args) {
		
		
		// 鍙傛暟鍑嗗宸ヤ綔
		OrderEventFactory orderEventFactory = new OrderEventFactory();
		int ringBufferSize = 4;
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		/**
		 * 1 eventFactory: 娑堟伅(event)宸ュ巶瀵硅薄
		 * 2 ringBufferSize: 瀹瑰櫒鐨勯暱搴�
		 * 3 executor: 绾跨▼姹�(寤鸿浣跨敤鑷畾涔夌嚎绋嬫睜) RejectedExecutionHandler
		 * 4 ProducerType: 鍗曠敓浜ц�� 杩樻槸 澶氱敓浜ц��
		 * 5 waitStrategy: 绛夊緟绛栫暐
		 */
		//1. 瀹炰緥鍖杁isruptor瀵硅薄
		Disruptor<OrderEvent> disruptor = new Disruptor<OrderEvent>(orderEventFactory,
				ringBufferSize,
				executor,
				ProducerType.SINGLE,
				new BlockingWaitStrategy());
		
		//2. 娣诲姞娑堣垂鑰呯殑鐩戝惉 (鏋勫缓disruptor 涓� 娑堣垂鑰呯殑涓�涓叧鑱斿叧绯�)
		disruptor.handleEventsWith(new OrderEventHandler());
		
		//3. 鍚姩disruptor
		disruptor.start();
		
		//4. 鑾峰彇瀹為檯瀛樺偍鏁版嵁鐨勫鍣�: RingBuffer
		RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
		
		OrderEventProducer producer = new OrderEventProducer(ringBuffer);
		
		ByteBuffer bb = ByteBuffer.allocate(8);
		
		for(long i = 0 ; i < 5; i ++){
			bb.putLong(0, i);
			producer.sendData(bb);
		}
		
		disruptor.shutdown();
		executor.shutdown();
		
	}
}
