package com.codeborne.selenide.ex;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.impl.Describe;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.ex.ErrorMessages.timeout;

public class ElementShouldNot extends AssertionError {
  public ElementShouldNot(String searchCriteria, String prefix, Condition expectedCondition, WebElement element, long timeoutMs, String screenshot) {
    super("Element should not " + prefix + expectedCondition + " {" + searchCriteria + '}' +
        "\nElement: '" + Describe.describe(element) + '\'' +
        "\nScreenshot: " + screenshot +
        timeout(timeoutMs));
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
