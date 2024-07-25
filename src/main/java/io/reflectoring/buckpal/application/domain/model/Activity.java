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
    AccountId ownerAccountId;

	/**
	 * 出金元アカウント。
	 */
	@NonNull
    AccountId sourceAccountId;

	/**
	 * 入金先アカウント。
	 */
	@NonNull
    AccountId targetAccountId;

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
			@NonNull AccountId ownerAccountId,
			@NonNull AccountId sourceAccountId,
			@NonNull AccountId targetAccountId,
			@NonNull LocalDateTime timestamp,
			@NonNull Money money) {
		this.id = null;
		this.ownerAccountId = ownerAccountId;
		this.sourceAccountId = sourceAccountId;
		this.targetAccountId = targetAccountId;
		this.timestamp = timestamp;
		this.money = money;
	}
}
