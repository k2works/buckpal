package io.reflectoring.buckpal.application.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.NonNull;

/**
 * 取引履歴
 * アカウントの取引履歴。
 */
public class ActivityWindow {
	/**
	 * このウィンドウ内のアカウント取引のリスト。
	 */
	private final List<Activity> activities;

	/**
	 * このウィンドウ内の最初の取引のタイムスタンプを取得します。
	 */
	public LocalDateTime getStartTimestamp() {
		return activities.stream()
				.min(Comparator.comparing(Activity::getTimestamp))
				.orElseThrow(IllegalStateException::new)
				.getTimestamp();
	}

	/**
	 * このウィンドウ内の最後の取引のタイムスタンプを取得します。
	 * @return タイムスタンプ
	 */
	public LocalDateTime getEndTimestamp() {
		return activities.stream()
				.max(Comparator.comparing(Activity::getTimestamp))
				.orElseThrow(IllegalStateException::new)
				.getTimestamp();
	}

	/**
	 * この取引履歴のすべての取引の値を合計して残高を計算します。
	 */
	public Money calculateBalance(AccountId accountId) {
		Money depositBalance = activities.stream()
				.filter(a -> a.getTargetAccountId().equals(accountId))
				.map(Activity::getMoney)
				.reduce(Money.ZERO, Money::add);
		Money withdrawalBalance = activities.stream()
				.filter(a -> a.getSourceAccountId().equals(accountId))
				.map(Activity::getMoney)
				.reduce(Money.ZERO, Money::add);
		return Money.add(depositBalance, withdrawalBalance.negate());
	}

	public ActivityWindow(@NonNull List<Activity> activities) {
		this.activities = activities;
	}

	public ActivityWindow(@NonNull Activity... activities) {
		this.activities = new ArrayList<>(Arrays.asList(activities));
	}

	/**
	 * この取引履歴の取引のリストを取得します。
	 * @return 取引のリスト
	 */
	public List<Activity> getActivities() {
		return Collections.unmodifiableList(this.activities);
	}

	/**
	 * 取引を取引履歴に追加します。
	 * @param activity 追加する取引
	 */
	public void addActivity(Activity activity) {
		this.activities.add(activity);
	}
}
