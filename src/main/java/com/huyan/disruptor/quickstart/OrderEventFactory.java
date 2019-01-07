package com.huyan.disruptor.quickstart;

import com.lmax.disruptor.EventFactory;

public class OrderEventFactory implements EventFactory<OrderEvent>{

	public OrderEvent newInstance() {
		return new OrderEvent();		//杩欎釜鏂规硶灏辨槸涓轰簡杩斿洖绌虹殑鏁版嵁瀵硅薄锛圗vent锛�
	}

}
