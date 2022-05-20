package Pages;

public class Links {
    public static boolean flag;

    //search
    public final String mainSearchInput = "//input[@placeholder = 'Искать на Ozon']";
    public final String noProduct = "//div[@data-widget = \"searchResultsError\"]";

    public final String catalog = "//*[@id=\"stickyHeader\"]/div[2]/div/div[1]/button/span";
    public final String categorySports = "//*[@id=\"stickyHeader\"]/div[2]/div/div[2]/div/div[1]/div/a[8]/span";
    public final String categoryBooks = "//a[contains(@href, \"category/knigi\")]";
    public final String subcategoryBikes = "//*[@id=\"stickyHeader\"]/div[2]/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]/div/a[1]/span";
    public final String subcategoryComputerBooks = "//a[contains(@href, \"kompyuternye-tehnologii\")]";

    //Velo brand
    public final String seeAllBrands = "//*[@id=\"layoutPage\"]/div[1]/div[3]/div[2]/div[1]/aside/div[4]/div[2]/span/span";
    public final String brandName1 = "merida";
    public final String fakeBrandName = "Otter and sons bike company";

    //java checkbox
    public final String javaCheckbox = "//span[contains(@href, \"genre=111111\")]/label/div";
    public final String javaBookToCart = "//*[text() = \"Java. Руководство для начинающих. Современные методы создания, компиляции и выполнения программ на Java | Шилдт Герберт\"]/../../../div[3]/div/div/div";
    public final String javaBookToCart2 = "//*[text() = \"Java. Полное руководство | Шилдт Герберт\"]/../../../div[3]/div/div/div";
    public final String javaBookPrice = "//*[text() = \"Java. Руководство для начинающих. Современные методы создания, компиляции и выполнения программ на Java | Шилдт Герберт\"]/../../../div/span";
    public final String javaBookPrice2 = "//*[text() = \"Java. Полное руководство | Шилдт Герберт\"]/../../../div/span";
    public final String cart = "//a[@href = \"/cart\"]";
    public final String cartPrice = "//*[contains(text(), \"Общая стоимость\")]/../span[2]";
    public final String cartDelete = "//*[text() = \"Java. Полное руководство | Шилдт Герберт\"]/../../../div[1]/div[2]/div[1]/div[2]";
    public final String closeCartAd = "//*[@id=\"layoutPage\"]/div[1]/div/div/div[3]/div/div/div/div[2]/div/div/div/div/div[3]/div";
    public final String cartDeleteConfirm = "//*[contains(text(), \"Удаление товаров\")]/../div[3]/div/button";

    //price limits

    public final String priceMin = "//div[contains(text(), 'Цена')]/../div[2]/div[2]/div[1]/div[1]/input";
    public final String priceMax = "//div[contains(text(), 'Цена')]/../div[2]/div[2]/div[2]/div[1]/input";
    public final String priceOnPage = "//*[contains(text(), 'Цена: от')]";

}
