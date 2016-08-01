package com.bcai.web.vo;

import java.io.Serializable;

public class MarketInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public double newestPrice;
	
	public double latelyBuyPrice;
	
	public double latelySellPrice;
	
	public double todayHighPrice;
	
	public double todayBottomPrice;
	
	public double volume;
	
	public String timestep;
    
	
	public String getTimestep() {
		return timestep;
	}

	public void setTimestep(String timestep) {
		this.timestep = timestep;
	}

	public double getNewestPrice() {
		return newestPrice;
	}

	public void setNewestPrice(double newestPrice) {
		this.newestPrice = newestPrice;
	}

	public double getLatelyBuyPrice() {
		return latelyBuyPrice;
	}

	public void setLatelyBuyPrice(double latelyBuyPrice) {
		this.latelyBuyPrice = latelyBuyPrice;
	}

	public double getLatelySellPrice() {
		return latelySellPrice;
	}

	public void setLatelySellPrice(double latelySellPrice) {
		this.latelySellPrice = latelySellPrice;
	}

	public double getTodayHighPrice() {
		return todayHighPrice;
	}

	public void setTodayHighPrice(double todayHighPrice) {
		this.todayHighPrice = todayHighPrice;
	}

	public double getTodayBottomPrice() {
		return todayBottomPrice;
	}

	public void setTodayBottomPrice(double todayBottomPrice) {
		this.todayBottomPrice = todayBottomPrice;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return "MarketInfo [newestPrice=" + newestPrice + ", latelyBuyPrice="
				+ latelyBuyPrice + ", latelySellPrice=" + latelySellPrice
				+ ", todayHighPrice=" + todayHighPrice + ", todayBottomPrice="
				+ todayBottomPrice + ", volume=" + volume + ", timestep="
				+ timestep + "]";
	}
	
	
	
}
