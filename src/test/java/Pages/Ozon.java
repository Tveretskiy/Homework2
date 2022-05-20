package Pages;

import Pages.Links;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Ozon {

    Links links = new Links();
    public void openMainPage() {
        open("https://www.ozon.ru/");
    }

    public void catalog(String left, String right) {
        $(By.xpath("//*[@id=\"stickyHeader\"]/div[2]/div/div[1]/button/span")).click();

        $(By.xpath(left)).hover();
        $(By.xpath(right)).click();
    }

    public void simpleClick(String link) {
        $(By.xpath(link)).click();
    }

    public void brandNameSearch(String brandName) {
        $(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[1]/aside/div[4]/div[2]/span/span")).click();
        $(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[1]/aside/div[4]/div[2]/div[1]/div[1]/div/input")).setValue(brandName);
        if ($(By.xpath("//*[.='Ничего не нашлось']")).exists()) {
            System.out.println("Такого бренда нет в ассортименте");
            return;
        }
        $(By.xpath("//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[1]/aside/div[4]/div[2]/div[2]/div/a/label/div[1]")).click();
    }

    public String[] getHeadersOfResults() {
        String[] arr = new String[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = $(By.xpath("//div[@data-widget = 'megaPaginator']/div[1]/div[1]/div[1]/div[" + (i + 1) + "]/div[2]/div[1]/a[1]/span/span")).getText();
        }
        return arr;
    }

    public String[] getHeadersOfResults2() {
        String[] arr = new String[30];
        for (int i = 0; i < 30; i++) {
            arr[i] = $(By.xpath("//div[@data-widget = 'megaPaginator']/div[1]/div[1]/div[1]/div[" + (i + 1) + "]/div[2]/div[1]/a[1]/span/span")).getText();
        }
        return arr;
    }

    public String[] getPrices() {
        String[] arr = new String[30];
        String arrElement;
        for (int i = 0; i < 30; i++) {
            arrElement = $(By.xpath("//div[@data-widget = 'megaPaginator']/div[1]/div[1]/div[1]/div[" + (i + 1) + "]/div[3]/div[1]/span")).getText();
            if (arrElement.contains("%")) {
                arr[i] = $(By.xpath("//div[@data-widget = 'megaPaginator']/div[1]/div[1]/div[1]/div[" + (i + 1) + "]/div[3]/div[2]/span")).getText();
            } else {
                arr[i] = $(By.xpath("//div[@data-widget = 'megaPaginator']/div[1]/div[1]/div[1]/div[" + (i + 1) + "]/div[3]/div[1]/span")).getText();
            }
        }
        return arr;
    }

    public void sortResults(String sortBy) {
        $(By.xpath("//input[@role = \"combobox\"]")).click();
        switch (sortBy) {
            case "New":
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ENTER);
                break;
            case "PriceLowFirst":
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ENTER);
                break;
            case "PriceHighFirst":
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ARROW_DOWN);
                $(By.xpath("//input[@role = \"combobox\"]")).sendKeys(Keys.ENTER);
                break;
        }
    }

    public int getNumberOfProducts() {
        int number;
        String firstSym = "";
        String cartInfo = $(By.xpath("//span[contains(text(), \"Ваша корзина\")]/following-sibling::span")).getText();
        System.out.println(cartInfo);
        for (int i = 0; i < 3; i++) {
            if (cartInfo.charAt(i) > 47 && cartInfo.charAt(i) < 58) {
                firstSym += cartInfo.charAt(i);
            }
        }
        number = Integer.parseInt(firstSym);
        return number;
    }

    public boolean cartPriceValidator(String price1, String price2, String cartPrice) {
        String p1s = "";
        String p2s = "";
        String cps = "";

        for (int i = 0; i < price1.length(); i++) {
            if (price1.charAt(i) > 47 && price1.charAt(i) < 58) {
                p1s += price1.charAt(i);
            }
        }
        int intPrice1 = Integer.parseInt(p1s);

        for (int i = 0; i < price2.length(); i++) {
            if (price2.charAt(i) > 47 && price2.charAt(i) < 58) {
                p2s += price2.charAt(i);
            }
        }
        int intPrice2 = Integer.parseInt(p2s);

        for (int i = 0; i < cartPrice.length(); i++) {
            if (cartPrice.charAt(i) > 47 && cartPrice.charAt(i) < 58) {
                cps += cartPrice.charAt(i);
            }
        }
        int intCartPrice = Integer.parseInt(cps);

        if (intPrice1 + intPrice2 != intCartPrice) {
            return true;
        } else {
            return false;
        }
    }

    public void closeCartAd() {
        $(By.xpath(links.closeCartAd)).shouldBe(Condition.visible).click();
    }

    public void cartDelete() {
        $(By.xpath(links.cartDelete)).click();
        $(By.xpath(links.cartDeleteConfirm)).shouldBe(Condition.visible).click();
    }

    public void fakePriceVals(String valMin, String valMax) {
        $(By.xpath(links.priceMin)).setValue(valMin).sendKeys(Keys.ENTER);
        $(By.xpath(links.priceMax)).setValue(valMax).sendKeys(Keys.ENTER);
        System.out.println($(By.xpath(links.priceOnPage)).getText());

        $(By.xpath(links.priceMin)).clear();
        $(By.xpath(links.priceMax)).clear();

        $(By.xpath(links.priceMin)).setValue("valMin").sendKeys(Keys.ENTER);
        $(By.xpath(links.priceMax)).setValue("valMax").sendKeys(Keys.ENTER);
        System.out.println($(By.xpath(links.priceOnPage)).getText());
    }

    public void searchByName(String realName, String fakeName) {
        $(By.xpath(links.mainSearchInput)).setValue(realName).sendKeys(Keys.ENTER);
        String[] results = getHeadersOfResults();
        System.out.println(results[1]);
        if (results[1].toLowerCase().contains(realName)) {
            System.out.println("Первый пункт выдачи содержит необходимое слово");
        } else {
            links.flag = true;
        }

        $(By.xpath(links.mainSearchInput)).clear();

        $(By.xpath(links.mainSearchInput)).setValue(fakeName).sendKeys(Keys.ENTER);
        if ($(By.xpath(links.noProduct)).isDisplayed()) {
            System.out.println("Вывелась заглушка об отсутствии товара");
        } else {
            links.flag = true;
        }
    }
}
