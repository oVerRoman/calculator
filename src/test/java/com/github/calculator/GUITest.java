package com.github.calculator;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GUITest {

    private FrameFixture window;

    @BeforeAll
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        GUI frame = GuiActionRunner.execute(() -> new GUI());
        window = new FrameFixture(frame);
        window.show(frame.getSize());
    }

    @Test
    void gui_showNumberInTextFieldWhenClickNumberButton() {
        window.button("9").click();
        window.button("8").click();
        window.button("7").click();
        window.button("6").click();
        window.button("5").click();
        window.button("4").click();
        window.button("3").click();
        window.button("2").click();
        window.button("1").click();
        window.button("0").click();
        window.textBox().requireText("9876543210");
    }

    @Test
    void gui_showOnlyOnePointWhenClickDecimalButtonSeveralTimes() {
        window.button("5").click();
        window.button("decimal").click();
        window.button("decimal").click();
        window.button("3").click();
        window.button("5").click();
        window.button("decimal").click();
        window.textBox().requireText("5.35");
    }

    @Test
    void gui_showEmptyTextFieldWhenClickClearButton() {
        window.button("3").click();
        window.button("decimal").click();
        window.button("4").click();
        window.button("5").click();
        window.button("clear").click();
        window.textBox().requireText("");
    }

    @Test
    void gui_showNumberWithoutLastSignWhenClickDeleteButton() {
        window.button("3").click();
        window.button("decimal").click();
        window.button("7").click();
        window.button("5").click();
        window.button("delete").click();
        window.textBox().requireText("3.7");
    }

    @Test
    void gui_showSumOfTwoNumbersWhenOperatorIsPlus() {
        window.button("5").click();
        window.button("decimal").click();
        window.button("3").click();
        window.button("plus").click();
        window.button("decimal").click();
        window.button("6").click();
        window.button("equal").click();
        window.textBox().requireText("5.9");
    }

    @Test
    void gui_showDifferenceOfTwoNumbersWhenOperatorIsMinus() {
        window.button("5").click();
        window.button("decimal").click();
        window.button("3").click();
        window.button("minus").click();
        window.button("decimal").click();
        window.button("2").click();
        window.button("1").click();
        window.button("equal").click();
        window.textBox().requireText("5.09");
    }

    @Test
    void gui_showProductOfTwoNumbersWhenOperatorIsTimes() {
        window.button("7").click();
        window.button("times").click();
        window.button("2").click();
        window.button("decimal").click();
        window.button("4").click();
        window.button("equal").click();
        window.textBox().requireText("16.8");
    }

    @Test
    void gui_showQuotientOfTwoNumbersWhenOperatorIsObelus() {
        window.button("9").click();
        window.button("6").click();
        window.button("obelus").click();
        window.button("5").click();
        window.button("equal").click();
        window.textBox().requireText("19.2");
    }

    @Test
    void gui_showNegativeNumberWhenClickNegativeButton() {
        window.button("8").click();
        window.button("negative").click();
        window.textBox().requireText("-8");
        window.button("negative").click();
        window.textBox().requireText("8");
    }

    @Test
    void gui_showInfinityWhenDivizorIsZero() {
        window.button("1").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.textBox().requireText("Infinity");
        window.button("2").click();
        window.button("negative").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.textBox().requireText("-Infinity");
        window.button("negative").click();
        window.textBox().requireText("Infinity");
    }

    @Test
    void gui_showNumberThatReplacedInfinityWhenClickNumberButton() {
        window.button("1").click();
        window.button("negative").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.button("8").click();
        window.textBox().requireText("8");
    }

    @Test
    void gui_showNumberThatReplacedZeroWhenClickNumberButton() {
        window.button("0").click();
        window.button("7").click();
        window.textBox().requireText("7");
    }

    @Test
    void gui_showZeroWithPointThatReplacedInfinityWhenClickDecimalButton() {
        window.button("1").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.button("decimal").click();
        window.textBox().requireText("0.");
    }

    @Test
    void gui_showZeroWithPointWhenClickDecimalButton() {
        window.button("decimal").click();
        window.textBox().requireText("0.");
    }

    @Test
    void gui_showEmptyTextFieldThatReplacedMinusWithNumberWhenClickDeleteButton() {
        window.button("2").click();
        window.button("negative").click();
        window.button("delete").click();
        window.textBox().requireText("");
    }

    @Test
    void gui_showInfinityIfOneOfNumbersIsInfinityWhenClickEqualButton() {
        window.button("1").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.button("times").click();
        window.button("3").click();
        window.button("equal").click();
        window.textBox().requireText("Infinity");
    }

    @Test
    void gui_showEmptyTextFieldIfTextFieldWasInfinityWhenClickDeleteButton() {
        window.button("1").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.button("delete").click();
        window.textBox().requireText("");
    }

    @Test
    void gui_showMinusInfinityIfTextFieldWasInfinityWhenClickNegativeButton() {
        window.button("1").click();
        window.button("obelus").click();
        window.button("0").click();
        window.button("equal").click();
        window.button("negative").click();
        window.textBox().requireText("-Infinity");
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }
}