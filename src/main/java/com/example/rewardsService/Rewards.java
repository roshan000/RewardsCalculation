package com.example.rewardsService;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Rewards {

	private long transaction_Id;
	private List<Integer> price;
	private int reward;

	public Rewards(long transaction_Id, List<Integer> price, int reward) {
		this.transaction_Id = transaction_Id;
		this.price = price;
		this.reward = reward;
	}

	public Rewards() {
		
	}

	public long getTransaction_Id() {
		return transaction_Id;
	}

	public void setTransaction_Id(long transaction_Id) {
		this.transaction_Id = transaction_Id;
	}

	public List<Integer> getPrice() {
		return price;
	}

	public void setPrice(List<Integer> price) {
		this.price = price;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}
}
