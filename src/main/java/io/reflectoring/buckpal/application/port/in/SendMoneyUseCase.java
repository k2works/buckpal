package io.reflectoring.buckpal.application.port.in;

/**
 * 送金ユースケース
 */
public interface SendMoneyUseCase {

	boolean sendMoney(SendMoneyCommand command);

}
