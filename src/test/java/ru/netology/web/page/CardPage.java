package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CardPage {
    @FindBy(css = "[data-test-id=amount] input")
    private SelenideElement sumToSet;
    @FindBy(css = "[data-test-id=from] input")
    private SelenideElement cardToGet;
    @FindBy(css = "[data-test-id=action-transfer]")
    private SelenideElement sendButton;

    public DashboardPage moneySend(DataHelper.SendMoney info) {
        sumToSet.setValue(info.getSum());
        cardToGet.setValue(info.getFrom());
        sendButton.click();
        return page(DashboardPage.class);
    }

}
