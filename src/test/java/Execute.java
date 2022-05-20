import Pages.Links;
import Pages.Ozon;
import static com.codeborne.selenide.Selenide.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

public class Execute {

    Ozon ozon;
    Links links;

    @Before
    public void firstAction() {
        ozon = new Ozon();
        links = new Links();
        links.flag = false;
    }

    @Test
    public void test1() {
        System.out.println("Start test 1");
        String[] arr;
        ozon.openMainPage();
        ozon.catalog(links.categorySports, links.subcategoryBikes);
        ozon.brandNameSearch(links.fakeBrandName);
        if (links.flag == true) {
            Assert.assertFalse(links.flag);
        }
        arr = ozon.getHeadersOfResults();
        for (String str : arr) {
            if (true != str.toLowerCase().contains("merida")) {
                System.out.println("Наименование бренда не во всех результатах выдачи!");
                links.flag = true;
                break;
            }
        }
        Assert.assertFalse(links.flag);
        System.out.println("End test 1");
    }

    @Test
    public void test2() {
        System.out.println("Start test 2");
        String[] arr = new String[30];
        int[] arrNums = new int[30];
        ozon.openMainPage();
        ozon.catalog(links.categorySports, links.subcategoryBikes);
        ozon.brandNameSearch(links.brandName1);
        ozon.sortResults("PriceHighFirst");
        arr = ozon.getPrices();
        for (int i = 0; i < 30; i++) {
            String arrNumElement = "";
            for (int j = 0; j < arr[i].length(); j++) {
                if (arr[i].charAt(j) > 47 && arr[i].charAt(j) < 58) {
                    arrNumElement += arr[i].charAt(j);
                }
            }
            arrNums[i] = Integer.parseInt(arrNumElement);
            System.out.println("Arr int # " + i + " = " + arrNums[i]);
        }
        for (int i = 1; i < 30; i++) {
            if (arrNums[i] > arrNums[i - 1]) {
                System.out.println("Неверная сортировка!");
                System.out.println(arrNums[i - 1]);
                System.out.println(arrNums[i]);
                links.flag = true;
                break;
            }
        }
        Assert.assertFalse(links.flag);
    }

    @Test
    public void test3() {
        System.out.println("Start test 3");
        int num1 = 0;
        int num2 = 0;

        ozon.openMainPage();
        ozon.catalog(links.categoryBooks, links.subcategoryComputerBooks);
        ozon.simpleClick(links.javaCheckbox);
        String javaBookPrice1 = $(By.xpath(links.javaBookPrice)).getText();
        ozon.simpleClick(links.javaBookToCart);
        ozon.simpleClick(links.cart);
        ozon.simpleClick(links.closeCartAd);
        num1 = ozon.getNumberOfProducts();
        System.out.println("Число товаров в корзине: " + num1);
        back();
        String javaBookPrice2 = $(By.xpath(links.javaBookPrice2)).getText();
        ozon.simpleClick(links.javaBookToCart2);
        ozon.simpleClick(links.cart);
        num2 = ozon.getNumberOfProducts();
        System.out.println("Число товаров в корзине: " + num2);
        if (num1 == 1 && num2 == 2) {
            System.out.println("Товары успешно добавляются в корзину");
        } else {
            links.flag = true;
        }

        Assert.assertFalse(links.flag);
        Assert.assertFalse(ozon.cartPriceValidator(javaBookPrice1, javaBookPrice2, $(By.xpath(links.cartPrice)).getText()));
    }

    @Test
    public void test4() {
        System.out.println("Start test 4");
        int num1 = 0;
        int num2 = 0;

        ozon.openMainPage();
        ozon.catalog(links.categoryBooks, links.subcategoryComputerBooks);
        ozon.simpleClick(links.javaCheckbox);
        ozon.simpleClick(links.javaBookToCart);
        System.out.println("Book 1 OK");
        ozon.simpleClick(links.javaBookToCart2);
        System.out.println("Book 2 OK");
        ozon.simpleClick(links.cart);
        System.out.println("Go to cart OK");
        ozon.closeCartAd();
        System.out.println("Close AD OK");
        num1 = ozon.getNumberOfProducts();
        ozon.cartDelete();
        num2 = ozon.getNumberOfProducts();
        if (num1 == 2 && num2 == 1) {
            System.out.println("Товары успешно удаляются из корзины");
        } else {
            links.flag = true;
        }

        Assert.assertFalse(links.flag);
    }

    @Test
    public void test5() {
        System.out.println("Start test 5");
        ozon.openMainPage();
        ozon.catalog(links.categoryBooks, links.subcategoryComputerBooks);
        ozon.fakePriceVals(String.valueOf(1), String.valueOf(99999));
        ozon.fakePriceVals(String.valueOf(99999), String.valueOf(1));
    }

    @Test
    public void test6() {
        System.out.println("Start test 6");
        ozon.openMainPage();
        ozon.searchByName("iphone 13", "jlsdjngfkldjngjksfd");
        Assert.assertFalse(links.flag);
    }
}
