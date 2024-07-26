package io.reflectoring.buckpal.application.port.in;

import io.reflectoring.buckpal.application.domain.model.AccountId;
import io.reflectoring.buckpal.application.domain.model.Money;

/**
 * 口座残高取得ユースケース
 */
public interface GetAccountBalanceUseCase {

	Money getAccountBalance(GetAccountBalanceQuery query);

	/**
	 * 口座残高取得クエリ
	 */
	record GetAccountBalanceQuery(AccountId accountId) {
	}
}
