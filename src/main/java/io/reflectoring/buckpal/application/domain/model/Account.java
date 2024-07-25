package io.reflectoring.buckpal.application.domain.model;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 口座
 * 一定の金額を保持する口座。{@link Account} オブジェクトは最新の口座活動のウィンドウのみを含みます。
 * 口座の総残高は、取引履歴の最初の取引が発生する前に有効だった基準残高と
 * 活動値の合計の合算です。
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

	/**
	 * 口座の一意のID。
	 */
	private final AccountId id;

	/**
	 * 口座の基準残高。これは 取引履歴 内の最初の活動前の口座の残高を示します。
	 */
	@Getter private final Money baselineBalance;

	/**
	 * この口座の最新の取引履歴。
	 */
	@Getter private final ActivityWindow activityWindow;

	/**
	 * IDなしの {@link Account} エンティティを作成します。まだ永続化されていない新しいエンティティを作成するために使用します。
	 */
	public static Account withoutId(
			Money baselineBalance,
			ActivityWindow activityWindow) {
		return new Account(null, baselineBalance, activityWindow);
	}

	/**
	 * ID付きの {@link Account} エンティティを作成します。永続化されたエンティティを再構成するときに使用します。
	 */
	public static Account withId(
			AccountId accountId,
			Money baselineBalance,
			ActivityWindow activityWindow) {
		return new Account(accountId, baselineBalance, activityWindow);
	}

	public Optional<AccountId> getId(){
		return Optional.ofNullable(this.id);
	}

	/**
	 * 基準残高に取引履歴を追加して口座の総残高を計算します。
	 */
	public Money calculateBalance() {
		return Money.add(
				this.baselineBalance,
				this.activityWindow.calculateBalance(this.id));
	}

	/**
	 * この口座から一定の金額を引き出そうとします。
	 * 成功した場合、負の値を持つ新しい取引を作成します。
	 *
	 * @return 引き出しが成功した場合は true、そうでない場合は false。
	 */
	public boolean withdraw(Money money, AccountId targetAccountId) {

		if (!mayWithdraw(money)) {
			return false;
		}

		Activity withdrawal = new Activity(
				this.id,
				this.id,
				targetAccountId,
				LocalDateTime.now(),
				money);
		this.activityWindow.addActivity(withdrawal);
		return true;
	}

	private boolean mayWithdraw(Money money) {
		return Money.add(
						this.calculateBalance(),
						money.negate())
				.isPositiveOrZero();
	}

	/**
	 * この口座に一定の金額を預けようとします。
	 * 成功した場合、正の値を持つ新しい取引を作成します。
	 *
	 * @return 預け入れが成功した場合は true、そうでない場合は false。
	 */
	public boolean deposit(Money money, AccountId sourceAccountId) {
		Activity deposit = new Activity(
				this.id,
				sourceAccountId,
				this.id,
				LocalDateTime.now(),
				money);
		this.activityWindow.addActivity(deposit);
		return true;
	}
}
