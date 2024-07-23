package io.reflectoring.buckpal.application.port.out;

import io.reflectoring.buckpal.application.domain.model.Account;

/**
 * アカウント状態更新ポート
 */
public interface UpdateAccountStatePort {

	void updateActivities(Account account);

}
