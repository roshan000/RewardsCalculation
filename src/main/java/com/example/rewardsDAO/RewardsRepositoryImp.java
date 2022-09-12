package com.example.rewardsDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.rewardsService.Rewards;

public class RewardsRepositoryImp implements RewardsRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	List<Integer> price = new ArrayList<Integer>();
	@SuppressWarnings("null")
	public List<Integer> calculateRewardPoints(List<Integer> price) {
		int reward = 0;
		List<Integer> list = null;
		Iterator<Integer> itr = price.iterator();
		while (itr.hasNext()) {
			reward = itr.next();
			if (reward >= 50 && reward < 100) {
				list.add(reward - 50);
				return list;
			} else if (reward > 100) {
				list.add(2 * (reward - 100) + 50);
				return list;
			}
			return list;
		}
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked", "null" })
	@Override
	public int getTotalRewardOfCustomer(Iterable<Long> id) {

		List<Integer> last3MonthRewardsInDesc = null;
		List<Integer> price = null;
		List<Rewards> transactionList;
		int totalReward = 0;
		LocalDate todayDate = LocalDate.now();
		LocalDate oldDate = null;
		for (int i = 0; i < 3; i++) {
			oldDate = todayDate.minusMonths(i);
			transactionList = jdbcTemplate.query(
					"select * from Customer where Cust_Id = ? and created_date >= ? and created_date <= ?",
					new Object[] { id, todayDate, oldDate }, (rs, rowNum) -> new Rewards(rs.getLong("transaction_id"),
							(List<Integer>) rs.getObject("price"), rs.getInt("rewards"))

			);
			for (int j = 0; j < transactionList.size(); j++) {
				price = ((Rewards) transactionList.iterator().next()).getPrice();
				last3MonthRewardsInDesc.addAll(calculateRewardPoints(price));
			}
			totalReward += last3MonthRewardsInDesc.stream().mapToInt(a -> a).sum();
		}
		return totalReward;
	}

	@SuppressWarnings({ "deprecation", "null", "unchecked" })
	@Override
	public List<Integer> rewardsMonthWise(Iterable<Long> id) {
		List<Integer> last3MonthRewardsInDesc = null;
		List<Integer> price = null;
		List<Rewards> transactionList;
		int totalReward = 0;
		List<Integer> monthwiseReward = null;
		LocalDate todayDate = LocalDate.now();
		LocalDate oldDate = null;
		for (int i = 0; i < 3; i++) {
			oldDate = todayDate.minusMonths(i);
			transactionList = jdbcTemplate.query(
					"select * from Customer where Cust_Id = ? and created_date >= ? and created_date <= ?",
					new Object[] { id, todayDate, oldDate }, (rs, rowNum) -> new Rewards(rs.getLong("transaction_id"),
							(List<Integer>) rs.getObject("price"), rs.getInt("rewards"))

			);
			for (int j = 0; j < transactionList.size(); j++) {
				price = ((Rewards) transactionList.iterator().next()).getPrice();
				last3MonthRewardsInDesc.addAll(calculateRewardPoints(price));
			}

			totalReward += last3MonthRewardsInDesc.stream().mapToInt(a -> a).sum();
			monthwiseReward.add(totalReward);
		}
		return monthwiseReward;

	}

}
