package com.example.rewardsDAO;

import java.util.List;

public interface RewardsRepository {

	int getTotalRewardOfCustomer(Iterable<Long> id);

	List<Integer> rewardsMonthWise(Iterable<Long> id);

}
