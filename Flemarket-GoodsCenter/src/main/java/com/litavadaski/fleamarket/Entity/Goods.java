package com.litavadaski.fleamarket.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.BSON;
import org.springframework.lang.NonNull;

@Entity
public class Goods {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NonNull
	private int sellerId;
	private String name;
	private String info;
	@NonNull
	private int price;
	private String Tag;
//	BSON image;
	private int cCount;
	public Goods() {
		this.cCount=0;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getType() {
		return Tag;
	}
	public void setType(String type) {
		this.Tag = type;
	}
	public int getcCount() {
		return cCount;
	}
	public void setcCount(int cCount) {
		this.cCount = cCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cCount;
		result = prime * result + id;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		result = prime * result + sellerId;
		result = prime * result + ((Tag == null) ? 0 : Tag.hashCode());
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
		Goods other = (Goods) obj;
		if (cCount != other.cCount)
			return false;
		if (id != other.id)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		if (sellerId != other.sellerId)
			return false;
		if (Tag == null) {
			if (other.Tag != null)
				return false;
		} else if (!Tag.equals(other.Tag))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", sellerId=" + sellerId + ", name=" + name + ", info=" + info + ", price=" + price
				+ ", type=" + Tag + "]";
	}
	
}
