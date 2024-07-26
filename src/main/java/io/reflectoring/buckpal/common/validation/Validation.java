package io.reflectoring.buckpal.common.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public class Validation {
  // IDEがValidatorFactoryを閉じる必要があると警告する場合がありますが、
  // ここでそれを行うとValidatorFactory#closeの契約に違反することになります。
  private final static Validator validator =
          buildDefaultValidatorFactory().getValidator();

  /**
   * ビーンバリデーションの注釈をすべて評価します。
   */
  public static <T> void validate(T subject) {
    Set<ConstraintViolation<T>> violations = validator.validate(subject);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
