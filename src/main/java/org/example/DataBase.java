package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DataBase {
    private static final String URL = "jdbc:sqlite:/home/frish24dell/Downloads/SnikeJava/gameData.db";

    // 1. Инициализация базы данных (создает таблицу рекордов high_scores, если её нет)
    public static void initBase() {
        String sql = "CREATE TABLE IF NOT EXISTS high_scores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "score INTEGER NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("SQL: База данных успешно инициализирована!");

        } catch (SQLException e) {
            System.out.println("Ошибка initBase (SQL): " + e.getMessage());
        }
    }

    // 2. Универсальный метод для создания ЛЮБОЙ новой таблицы (для твоих скинов, сохранений и т.д.)
    public static void createNewTable(String sqlQuery) {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlQuery);
            System.out.println("SQL: Таблица проверена/создана успешно!");

        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы (SQL): " + e.getMessage());
        }
    }

    // 3. Универсальный метод впихивания данных (для сохранения игры или добавления скинов)
    // Отправляет данные в таблицу game_saves, которую ты создаешь в методе saveGame()
    public static void insertProject(String saveName, int score) {
        String sql = "INSERT INTO game_saves (save_name, saved_score) VALUES (?, ?);";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, saveName); // Вместо первого ? ставит, например, "score"
            pstmt.setInt(2, score);       // Вместо второго ? ставит твои очки из физики

            pstmt.executeUpdate();
            System.out.println("SQL: Данные успешно сохранены в game_saves!");

        } catch (SQLException e) {
            System.out.println("Ошибка insertProject (SQL): " + e.getMessage());
        }
    }

    // 4. Тот самый УНИВЕРСАЛЬНЫЙ метод чтения из любой таблицы
    // tableName — откуда берем (например, "game_saves" или "high_scores")
    // columnName — имя колонки с числами (например, "saved_score" или "score")
    // keyName — текст для поиска (например, "score" или имя скина). Для таблицы рекордов можно слать пустую строку ""
    public static int getBaseUniversal(String tableName, String columnName, String keyName) {
        String sql;

        // Перестраиваем логику запроса в зависимости от того, ищем ли мы по тексту или просто берем число
        if (tableName.equalsIgnoreCase("game_saves")) {
            sql = "SELECT " + columnName + " FROM " + tableName + " WHERE save_name = ? LIMIT 1;";
        } else {
            sql = "SELECT " + columnName + " FROM " + tableName + " LIMIT 1;";
        }

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Если таблица требует текстовый ключ для поиска, подставляем его
            if (tableName.equalsIgnoreCase("game_saves")) {
                pstmt.setString(1, keyName);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(columnName); // Возвращаем найденное число
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка getBaseUniversal (SQL): " + e.getMessage());
        }
        return 0; // Если ничего не нашлось, возвращаем ноль
    }

    // 5. Старая логика сохранения максимального рекорда (если вдруг захочешь использовать старую таблицу high_scores)
    public static void setBase(int score) {
        int currentHighScore = getBaseUniversal("high_scores", "score", "");

        try (Connection conn = DriverManager.getConnection(URL)) {
            if (currentHighScore == 0) {
                String insertSql = "INSERT INTO high_scores (score) VALUES (?);";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
                    pstmt.setInt(1, score);
                    pstmt.executeUpdate();
                }
            } else if (score > currentHighScore) {
                String updateSql = "UPDATE high_scores SET score = ?;";
                try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
                    pstmt.setInt(1, score);
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка setBase (SQL): " + e.getMessage());
        }
    }

    // 6. Очистка всей базы данных (если нужно будет обнулить игру)
    public static void clearBase(String tableName) {
        String sql = "DELETE FROM " + tableName + ";";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("SQL: Таблица " + tableName + " полностью очищена!");

        } catch (SQLException e) {
            System.out.println("Ошибка clearBase (SQL): " + e.getMessage());
        }
    }
}
