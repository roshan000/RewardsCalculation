package com.example.rewardsController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewardsDAO.RewardsRepository;
import com.example.rewardsDAO.RewardsRepositoryService;
import com.example.rewardsService.Rewards;

@RestController
@RequestMapping("/rewards/v1")
public class RewardController {

	@Autowired
	RewardsRepositoryService rewardsRepositoryService;

	@Autowired
	RewardsRepository rewardRepository;

	/**
	 * Get all Transaction list.
	 *
	 * @return the list
	 */
	@GetMapping(path="/transactions", produces = "application/json")
	public List<Rewards> getAllPrice() {
		return rewardsRepositoryService.findAll();
	}

	/**
	 * Get Total Rewards Of Last 3 Month.
	 *
	 * @return the list
	 */
	@GetMapping(path="/customer/totalrewards/{id}", produces = "application/json")
	public ResponseEntity<Integer> getTotalRewards(@PathVariable Iterable<Long> id) {
		int sum = rewardRepository.getTotalRewardOfCustomer(id); // It will return Last three Month Rewards
		System.out.println(sum);
		return new ResponseEntity<>(sum, HttpStatus.OK);
	}

	/**
	 * Get 3 month Reward Of Customer month wise.
	 *
	 * @return the list
	 */
	@GetMapping(path="/customer/rewards/monthwise/{id}", produces = "application/json")
	public ResponseEntity<List<Integer>> rewardsPerMonth(@PathVariable Iterable<Long> id) {
		List<Integer> last3MonthRewards = rewardRepository.rewardsMonthWise(id);
		return new ResponseEntity<>(last3MonthRewards, HttpStatus.OK);
	}
}
