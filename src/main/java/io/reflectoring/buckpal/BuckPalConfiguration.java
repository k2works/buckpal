package io.reflectoring.buckpal;

import io.reflectoring.buckpal.application.domain.service.MoneyTransferProperties;
import io.reflectoring.buckpal.application.domain.model.Money;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BuckPalConfigurationProperties.class)
public class BuckPalConfiguration {

  /**
   * ユースケース固有の {@link MoneyTransferProperties} オブジェクトをアプリケーションコンテキストに追加します。
   * プロパティはSpring-Boot固有の {@link BuckPalConfigurationProperties} オブジェクトから読み取られます。
   */
  @Bean
  public MoneyTransferProperties moneyTransferProperties(BuckPalConfigurationProperties buckPalConfigurationProperties){
    return new MoneyTransferProperties(Money.of(buckPalConfigurationProperties.getTransferThreshold()));
  }

}
