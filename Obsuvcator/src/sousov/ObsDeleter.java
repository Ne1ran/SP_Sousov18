package sousov;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ObsDeleter extends Thread{
    String filePath;
    String outputTempFile;
    String outputFinale;

    ObsDeleter(String filePath, String outputTempFile, String outputFinale) {
        this.filePath = filePath;
        this.outputTempFile = outputTempFile;
        this.outputFinale = outputFinale;
        this.start();
    }

    @Override
    public void run() {
        this.delComs(); // Метод удаления комментариев
        this.renamingVars(); // Метод переименования переменных
        this.delSpaces(); // Метод удаления пробелов
    }
    public void delComs(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.outputTempFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFinale))) {
                String line;
                boolean Check = true; // Проверка. Если найден многострочный комментарий, то будет пропуск считывания строк, если в них нет */
                String templine;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("*/")) { // Та самая проверка
                        Check = true;
                        templine = line.substring(line.indexOf("*/") + 2); //Обрез строки до того, что стоит после окончания комментария
                    } else if (!Check) {
                        continue;
                    } else if (line.contains("/*")) { // Начало многострочного комма
                        Check = false;
                        templine = line.substring(0, line.indexOf("/*")); // Обрез до начала комментария
                    } else if (line.contains("//")) { // Начало обычного комма
                        templine = line.substring(0, line.indexOf("//")); // Обрез до его начала
                    } else templine = line; // Если не было комментариев - переприсваивание строки для удобства
                    writer.write(templine.trim() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка writer");
            }
        } catch (Exception e) {
            System.out.println("Ошибка reader");
        }
    }
    public void renamingVars(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.outputFinale))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputTempFile))) {
                String line;
                String matches = ""; // Массивы для параллельной записи названий и сокращений переменных.
                String keys = "";
                while ((line = reader.readLine()) != null) {
                    String templine = line;
                    if (line.contains(" = ")) { // Поиск по = переменных
                        String[] tempArr = line.split(" "); // Массив из строки по условию
                        int index = 1;
                        for (int i = 0; i < tempArr.length; i++) { // Поиск индекса = в массива
                            if (tempArr[i].equals("=")) {
                                index = i;
                                break;
                            }
                        }
                        String var = tempArr[index - 1]; // Присваивание значения перед равно (названия)
                        String key = "";
                        if (!matches.contains(var) && var.charAt(0) != '(' && !var.contains("[") &&
                                !var.contains("this")) { // Исключения + проверки по условия
                            if (var.length() > 3) { // Не делать ключ, если название меньше 3 символов
                                matches += (var + " ");
                                key = var.charAt(0) + var.substring(var.length() - 1);
                                keys += (key + " ");
                            }
                        }
                        for (int i = 0; i < matches.split(" ").length; i++) { // Проход по значениям в массиве названий переменных
                            templine = templine.replaceAll(matches.split(" ")[i], keys.split(" ")[i]); // Изменения совпадений
                        }

                    } else if (line.contains(";") && !line.contains("import") && !line.contains("package")) {
                        String[] tempArr = line.split(" "); // Поиск по ; + условия исключений
                        String var = tempArr[tempArr.length - 1].substring(0, tempArr[tempArr.length - 1].length() - 1); // Вычисление имени переменной
                        String key = "";
                        if (!matches.contains(var) && var.charAt(0) != '(' && !var.contains("[") && !var.contains("this")
                                && !var.contains(")")) { // Ее добавление (как выше при = ) в массивы и т.д.
                            if (var.length() > 3) {
                                matches += (var + " ");
                                key = var.charAt(0) + var.substring(var.length() - 1);
                                keys += (key + " ");
                            }
                        }
                        for (int i = 0; i < matches.split(" ").length; i++) {
                            templine = templine.replaceAll(matches.split(" ")[i], keys.split(" ")[i]); // Также изменение линии
                        }
                    } else
                        for (int i = 0; i < matches.split(" ").length; i++) {
                            templine = templine.replaceAll(matches.split(" ")[i], keys.split(" ")[i]);
                        }
                    writer.write(templine + "\n");
                }

            } catch (Exception e) {
                System.out.println("Ошибка writer");
            }
        } catch (Exception e) {
            System.out.println("Ошибка reader");
        }
    }
    public void delSpaces(){
        try (BufferedReader reader = new BufferedReader(new FileReader(this.outputTempFile))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFinale))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String templine = line;
                    if (line.contains("{")) { // Условия для удаления лишних пробелов (много, реплейс возможных вариантов)
                        templine = templine.replaceAll(" \\{", "{");
                        templine = templine.replaceAll("\\{ ", "{");
                        templine = templine.replaceAll(" \\{ ", "{");
                    }
                    if (line.contains("(")) {
                        templine = templine.replaceAll(" \\(", "(");
                        templine = templine.replaceAll("\\( ", "(");
                        templine = templine.replaceAll(" \\( ", "(");
                    }
                    if (line.contains(",")) {
                        templine = templine.replaceAll(", ", ",");
                        templine = templine.replaceAll(" ,", ",");
                        templine = templine.replaceAll(",", ",");
                        templine = templine.replaceAll(" , ", ",");
                    }
                    if (line.contains("=")) {
                        templine = templine.replace(" = ", "=");
                        templine = templine.replace(" =", "=");
                        templine = templine.replace("= ", "=");
                    }
                    if (line.contains("+")) {
                        templine = templine.replace(" + ", "+");
                        templine = templine.replace("+ ", "+");
                        templine = templine.replace(" +", "+");
                    }
                    if (line.contains("}")) {
                        templine = templine.replaceAll(" }", "}");
                        templine = templine.replaceAll("} ", "}");
                        templine = templine.replaceAll(" } ", "}");
                    }
                    if (line.contains(")")) {
                        templine = templine.replaceAll(" \\)", ")");
                        templine = templine.replaceAll("\\) ", ")");
                        templine = templine.replaceAll(" \\) ", ")");
                    }
                    if (line.contains("@")){ //Для аннотаций в конце строки пробел, ибо они не отделяются ; или }
                        templine += " ";
                    }
                    writer.write(templine);
                }
            } catch (Exception e) {
                System.out.println("Ошибка writer");
            }
        } catch (Exception e) {
            System.out.println("Ошибка reader");
        }
    }
    }