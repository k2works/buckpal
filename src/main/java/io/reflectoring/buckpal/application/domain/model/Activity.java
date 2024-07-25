package io.reflectoring.buckpal.application.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

/**
 * 取引
 * {@link Account} 間のお金の取引。
 */
@Getter
@Value
@RequiredArgsConstructor
public class Activity {

	ActivityId id;

	/**
	 * この活動を所有するアカウント。
	 */
	@NonNull
    Account.AccountId ownerAccountId;

	/**
	 * 出金元アカウント。
	 */
	@NonNull
    Account.AccountId sourceAccountId;

	/**
	 * 入金先アカウント。
	 */
	@NonNull
    Account.AccountId targetAccountId;

	/**
	 * 取引のタイムスタンプ。
	 */
	@NonNull
    LocalDateTime timestamp;

	/**
	 * アカウント間で移動したお金。
	 */
	@NonNull
    Money money;

	public Activity(
			@NonNull Account.AccountId ownerAccountId,
			@NonNull Account.AccountId sourceAccountId,
			@NonNull Account.AccountId targetAccountId,
			@NonNull LocalDateTime timestamp,
			@NonNull Money money) {
		this.id = null;
		this.ownerAccountId = ownerAccountId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.timestamp = timestamp;
		this.money = money;
	}

	@Value
	public static class ActivityId {
		Long value;
	}

}
