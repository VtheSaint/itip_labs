import java.io.*;
public class FileManager {

    public static void main(String[] args) {
        String sourceFileName = "a.txt"; // Имя исходного файла
        String targetFileName = "b.txt"; // Имя файла, в который будет скопировано содержимое

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFileName);
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("Файл успешно скопирован.");
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден.");
        } catch (IOException e) {
            System.err.println("Ошибка: Произошла ошибка ввода/вывода.");
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }


}
