package steps;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.TestParams.*;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseSteps {
    @Step("Open page {url}")
    public static void open(String url, Class pageObjectClass) {
        Selenide.open(url, pageObjectClass);
    }

    @Step("Check status code")
    public static void checkStatusCode(Response response, Integer expected) {
        Integer actual = response.then().extract().statusCode();
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    @Step("Insert value {value} into field {element}")
    public static void fillField(SelenideElement element, String value) {
        element.setValue(value);
    }

    @Step("Click on element {element}")
    public static void clickElement(SelenideElement element) {
        element.click();
    }

    @Step("Click on element {element}")
    public static void clickElementAtBeginning(SelenideElement element) {
        int xOffset = 1; // small offset from the top-left corner
        int yOffset = 1;
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].click();", element.toWebElement(), xOffset, yOffset);
    }

    @Step("Double click on element {element}")
    public static void doubleClickElement(SelenideElement element) {
        element.doubleClick();
    }

    @Step("Check value")
    public static void checkValue(Object actual, Object expected) {
        assertThat(actual).isEqualTo(expected);
    }

    @Step("Get value from DB")
    public static <T> T getValueFromDB(String query, String columnLabel, Class<T> typeOf) {
        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            Connection conn = DriverManager.getConnection(getFirebirdUrl(), getFirebirdUsername(), getFirebirdPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt1 = conn.createStatement();
                ResultSet res = stmt1.executeQuery(query);

                while (res.next()) {
                    return res.getObject(columnLabel, typeOf);
                }

                stmt1.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }

        return null;
    }

    public static String changeDateFormat(String date, String input, String output) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat(input);
        SimpleDateFormat outputFormat = new SimpleDateFormat(output);
        Date d = inputFormat.parse(date);
        return outputFormat.format(d);
    }

    @Step("Disable animations")
    public static void disableAnimation() {
        String disableAnimationsScript = "var style = document.createElement('style');" +
                "style.innerHTML = '* { animation-duration: 0s !important; transition-duration: 0s !important; }';" +
                "document.head.appendChild(style);";
        Selenide.executeJavaScript(disableAnimationsScript);
    }

    @Step("Convert Excel to string")
    public static String convertExcelToString(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        // Открываем файл
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            // Проходим по всем листам
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                content.append("Sheet: ").append(sheet.getSheetName()).append("\n");

                // Проходим по всем строкам и ячейкам
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        String cellValue = getCellValueAsString(cell);
                        content.append(cellValue).append("\t");
                    }
                    content.append("\n");
                }
            }
        }

        return content.toString().substring(0, content.length() - 2);
    }

    // Convert cell value to string
    private static String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "Unknown Cell Type";
        }
    }

    @Step("Download file")
    public static void downloadFile(SelenideElement downloadElement, String path, String fileName) throws IOException, InterruptedException {
        File downloadedFile;

        if ("a".equals(downloadElement.getTagName()) && downloadElement.getAttribute("href") != null) {
            // If it's a link, download the file
            downloadedFile = downloadElement.download();
        } else {
            // If it's a button or another element, initiate the download and wait for the file to appear
            downloadElement.click();
            Thread.sleep(15000); // Wait 15 seconds for the download to complete

            // Find the downloaded file
            downloadedFile = findDownloadedFile(path);
        }

        // Copy the file to the specified path
        Path destinationPath = Paths.get(path, fileName);
        Files.copy(downloadedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private static File findDownloadedFile(String path) throws IOException {
        Path downloadsPath = Paths.get(path).toAbsolutePath();

        try (Stream<Path> files = Files.walk(downloadsPath)) {
            // Find the latest downloaded file with the .xlsx extension
            Optional<Path> lastDownloadedFile = files
                    .filter(p -> p.toString().endsWith(".xlsx"))
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()));

            return lastDownloadedFile
                    .orElseThrow(() -> new IOException("File not found after download"))
                    .toFile();
        }
    }

    @Step("Execute DB query")
    public static void executeQuery(String query, String db) {
        try {
            Connection conn;
            if (db.equals("firebird")) {
                Class.forName("org.firebirdsql.jdbc.FBDriver");
                conn = DriverManager.getConnection(getFirebirdUrl(), getFirebirdUsername(), getFirebirdPassword());
            } else {
                throw new AssertionError("Unknown database");
            }

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt1 = conn.createStatement();
                stmt1.execute(query);

                stmt1.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }
    }
}