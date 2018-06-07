package com.litavadaski.fleamarket.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Indent {
	//订单编号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	//卖家id
	@NonNull
	private int sid;
	//买家id
	@NonNull
	private int bid;
	//商品id
	@NonNull
	private int gid;
	//下单时间
	private Date indentTime;
	//成交时间
	private Date endTime;
	//订单状态
	@NonNull
	private String status;
	//订单信息
	private String info;
	//订单总金额
	@NonNull
	private int price;
	//商品数目
	@NonNull
	private int number;
	//发货地址
	@NonNull
	private String sLocation;
	//收货地址
	@NonNull
	private String rLocation;
	//收货人电话
	@NonNull
	private int rPhone;
	//发货人电话
	@NonNull
	private int sPhone;
	//订单位置
	private String position;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public Date getIndentTime() {
		return indentTime;
	}
	public void setIndentTime(Date indentTime) {
		this.indentTime = indentTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getsLocation() {
		return sLocation;
	}
	public void setsLocation(String sLocation) {
		this.sLocation = sLocation;
	}
	public String getrLocation() {
		return rLocation;
	}
	public void setrLocation(String rLocation) {
		this.rLocation = rLocation;
	}
	public int getrPhone() {
		return rPhone;
	}
	public void setrPhone(int rPhone) {
		this.rPhone = rPhone;
	}
	public int getsPhone() {
		return sPhone;
	}
	public void setsPhone(int sPhone) {
		this.sPhone = sPhone;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bid;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + gid;
		result = prime * result + id;
		result = prime * result + ((indentTime == null) ? 0 : indentTime.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + number;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + price;
		result = prime * result + ((rLocation == null) ? 0 : rLocation.hashCode());
		result = prime * result + rPhone;
		result = prime * result + ((sLocation == null) ? 0 : sLocation.hashCode());
		result = prime * result + sPhone;
		result = prime * result + sid;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Indent other = (Indent) obj;
		if (bid != other.bid)
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (gid != other.gid)
			return false;
		if (id != other.id)
			return false;
		if (indentTime == null) {
			if (other.indentTime != null)
				return false;
		} else if (!indentTime.equals(other.indentTime))
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (number != other.number)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (price != other.price)
			return false;
		if (rLocation == null) {
			if (other.rLocation != null)
				return false;
		} else if (!rLocation.equals(other.rLocation))
			return false;
		if (rPhone != other.rPhone)
			return false;
		if (sLocation == null) {
			if (other.sLocation != null)
				return false;
		} else if (!sLocation.equals(other.sLocation))
			return false;
		if (sPhone != other.sPhone)
			return false;
		if (sid != other.sid)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Indent [id=" + id + ", sid=" + sid + ", bid=" + bid + ", gid=" + gid + ", indentTime=" + indentTime
				+ ", endTime=" + endTime + ", status=" + status + ", info=" + info + ", price=" + price + ", number="
				+ number + ", sLocation=" + sLocation + ", rLocation=" + rLocation + ", rPhone=" + rPhone + ", sPhone="
				+ sPhone + ", position=" + position + "]";
	}
	
}
