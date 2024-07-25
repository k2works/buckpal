package io.reflectoring.buckpal.application.port.out;

import java.time.LocalDateTime;

import io.reflectoring.buckpal.application.domain.model.Account;
import io.reflectoring.buckpal.application.domain.model.AccountId;

/**
 * 口座ロードポート
 */
public interface LoadAccountPort {

	Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
