package pageobjects;

import pagecomponents.CatalogMenu;
import pagecomponents.MainPageHeader;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    // Шапка на главной странице
    private static final MainPageHeader mainPageHeader = page(MainPageHeader.class);

    // Компонент "Меню каталога"
    private static final CatalogMenu catalogMenu = page(CatalogMenu.class);

    public static MainPageHeader getMainPageHeader() {
        return mainPageHeader;
    }

    public static CatalogMenu getCatalogMenu() {
        return catalogMenu;
    }
}