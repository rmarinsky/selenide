package com.codeborne.selenide.collections;

import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SizeGreaterThanOrEqualTest {
  @Test
  public void testApplyWithEmptyList() {
    assertFalse(new SizeGreaterThanOrEqual(10).apply(emptyList()));
  }

  @Test
  public void testApplyWithWrongSizeList() {
    assertFalse(new SizeGreaterThanOrEqual(10).apply(singletonList(mock(WebElement.class))));
  }

  @Test
  public void testApplyWithSameSize() {
    assertTrue(new SizeGreaterThanOrEqual(1).apply(singletonList(mock(WebElement.class))));
  }

  @Test
  public void testApplyWithGreaterSize() {
    assertTrue(new SizeGreaterThanOrEqual(1).apply(asList(mock(WebElement.class), mock(WebElement.class))));
  }

  @Test
  public void testFailMethod() {
    WebElementsCollection mockedWebElementCollection = mock(WebElementsCollection.class);
    when(mockedWebElementCollection.description()).thenReturn("Collection description");

    try {
      new SizeGreaterThanOrEqual(10).fail(mockedWebElementCollection,
          emptyList(),
          new Exception("Exception message"),
          10000);
    } catch (ListSizeMismatch ex) {
      assertEquals(": expected: >= 10, actual: 0, collection: Collection description\n" +
          "Elements: []", ex.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals("size >= 10", new SizeGreaterThanOrEqual(10).toString());
  }
}
