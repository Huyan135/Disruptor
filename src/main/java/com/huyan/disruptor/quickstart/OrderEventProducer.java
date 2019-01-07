package com.huyan.disruptor.quickstart;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class OrderEventProducer {

	private RingBuffer<OrderEvent> ringBuffer;
	
	public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void sendData(ByteBuffer data) {
		//1 鍦ㄧ敓浜ц�呭彂閫佹秷鎭殑鏃跺��, 棣栧厛 闇�瑕佷粠鎴戜滑鐨剅ingBuffer閲岄潰 鑾峰彇涓�涓彲鐢ㄧ殑搴忓彿
		long sequence = ringBuffer.next();	//0	
		try {
			//2 鏍规嵁杩欎釜搴忓彿, 鎵惧埌鍏蜂綋鐨� "OrderEvent" 鍏冪礌 娉ㄦ剰:姝ゆ椂鑾峰彇鐨凮rderEvent瀵硅薄鏄竴涓病鏈夎璧嬪�肩殑"绌哄璞�"
			OrderEvent event = ringBuffer.get(sequence);
			//3 杩涜瀹為檯鐨勮祴鍊煎鐞�
			event.setValue(data.getLong(0));			
		} finally {
			//4 鎻愪氦鍙戝竷鎿嶄綔
			ringBuffer.publish(sequence);			
		}
	}

	
	
	
	
	
	
	
}
