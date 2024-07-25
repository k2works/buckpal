package io.reflectoring.buckpal.application.port.out;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.AccountId;

/**
 * 口座ロックポート
 */
public interface AccountLock {

	void lockAccount(AccountId accountId);

	void releaseAccount(AccountId accountId);

}
