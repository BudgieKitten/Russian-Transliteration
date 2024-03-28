package com.example.project3;

import java.util.HashMap;

public class ConversionEngine {

    public static String ruToLatin(String rus, boolean checkName, boolean checkSilent) {
        HashMap<String, String> tableUpperChar = getRuUpperChar();
        HashMap<String, String> tableLowerChar = getRuLowerChar();

        if (checkName) {
            tableLowerChar.put("е", "e");
            tableLowerChar.put("ё", "ё");
            tableLowerChar.put("й", "i");
            tableLowerChar.put("ю", "iu");
            tableLowerChar.put("я", "ia");
        }

        if (!checkSilent) {
            tableLowerChar.put("ь", "");
            tableLowerChar.put("ъ", "");
            tableUpperChar.put("Ь", "");
            tableUpperChar.put("Ъ", "");
        }

        String latin = "";

        for (int i = 0; i < rus.length(); i++) {
            /*
            Checking if the character entered is Cyrillic or not
             */
            char currentChar = rus.charAt(i);

            if (tableLowerChar.containsKey(Character.toString(currentChar))) {
                // Do nothing b/c it's good
            } else if (tableUpperChar.containsKey(Character.toString(currentChar))) {
                // Do nothing b/c it's good
            } else {
                // Handle user's (bad) input
                latin += currentChar;
                continue;
            }

            if (Character.isUpperCase(currentChar)) {
                latin += tableUpperChar.get(Character.toString(currentChar));
            } else if (Character.isLowerCase(currentChar)) {
                latin += tableLowerChar.get(Character.toString(currentChar));
            }

        }

        return latin;
    }

    public static String latinToRu(String latin, boolean checkName, boolean checkSilent) {
        HashMap<String, String> tableUpperChar = getLatinUpperChar();
        HashMap<String, String> tableLowerChar = getLatinLowerChar();

        if (checkName) {
            tableLowerChar.put("e", "е");
            tableLowerChar.put("ё", "ё");
            tableLowerChar.put("i", "й");
            tableLowerChar.put("iu", "ю");
            tableLowerChar.put("ia", "я");
        }

        if (!checkSilent) {
            tableLowerChar.put("\'", "");
            tableLowerChar.put("\"", "");
            tableUpperChar.put("\'", "");
            tableUpperChar.put("\"", "");
        }

        String rus = "";

        for (int i = 0; i < latin.length(); i++) {
            /*
            Checking if the character entered is Cyrillic or not
             */
            char currentChar = latin.charAt(i);

            if (tableLowerChar.containsKey(Character.toString(currentChar))) {
                // Do nothing b/c it's good
            } else if (tableUpperChar.containsKey(Character.toString(currentChar))) {
                // Do nothing b/c it's good
            } else {
                // Handle user's (bad) input
                rus += currentChar;
                continue;
            }

            if (Character.isUpperCase(latin.charAt(i))) {
                String exception = "";

                if (currentChar == 'Y' ||
                        currentChar == 'Z' ||
                        currentChar == 'P' ||
                        currentChar == 'K' ||
                        currentChar == 'T' ||
                        currentChar == 'C' ||
                        currentChar == 'S') {

                    try {
                        exception = currentChar + String.valueOf(latin.charAt(i+1));
                        if (tableUpperChar.containsKey(exception)) {
                            rus += tableUpperChar.get(exception);
                            i++;
                        } else if (latin.substring(i, i+4).equals("Shch")) {
                            rus += tableUpperChar.get(latin.substring(i, i+4));
                            i += 4;
                        } else {
                            rus += tableUpperChar.get(Character.toString(currentChar));
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        rus += tableUpperChar.get(Character.toString(currentChar));
                    }

                } else {
                    rus += tableUpperChar.get(Character.toString(currentChar));
                }

            } else if (Character.isLowerCase(latin.charAt(i))) {
                String exception = "";

                if (currentChar == 'y' ||
                        currentChar == 'z' ||
                        currentChar == 'p' ||
                        currentChar == 'k' ||
                        currentChar == 't' ||
                        currentChar == 'c' ||
                        currentChar == 's') {

                    try {
                        exception = currentChar + String.valueOf(latin.charAt(i+1));
                        if (tableLowerChar.containsKey(exception)) {
                            rus += tableLowerChar.get(exception);
                            i++;
                        } else if (latin.substring(i, i+4).equals("shch")) {
                            rus += tableLowerChar.get(latin.substring(i, i+4));
                            i += 4;
                        } else {
                            rus += tableLowerChar.get(Character.toString(currentChar));
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        rus += tableLowerChar.get(Character.toString(currentChar));
                    }

                } else {
                    rus += tableLowerChar.get(Character.toString(currentChar));
                }

            }

        }

        return rus;
    }

    /*
    Russian to English
     */
    private static HashMap<String, String> getRuUpperChar() {
        HashMap<String, String> transliterationTable = new HashMap<>();
        transliterationTable.put("А", "A");
        transliterationTable.put("Б", "B");
        transliterationTable.put("В", "V");
        transliterationTable.put("Г", "G");
        transliterationTable.put("Д", "D");
        transliterationTable.put("Е", "Ye");
        transliterationTable.put("Ё", "Yo");
        transliterationTable.put("Ж", "Zh");
        transliterationTable.put("З", "Z");
        transliterationTable.put("И", "I");
        transliterationTable.put("Й", "J");
        transliterationTable.put("К", "K");
        transliterationTable.put("Л", "L");
        transliterationTable.put("М", "M");
        transliterationTable.put("Н", "N");
        transliterationTable.put("О", "O");
        transliterationTable.put("П", "P");
        transliterationTable.put("Р", "R");
        transliterationTable.put("С", "S");
        transliterationTable.put("Т", "T");
        transliterationTable.put("У", "U");
        transliterationTable.put("Ф", "Ph");
        transliterationTable.put("Х", "Kh");
        transliterationTable.put("Ц", "Ts");
        transliterationTable.put("Ч", "Ch");
        transliterationTable.put("Ш", "Sh");
        transliterationTable.put("Щ", "Shch");
        transliterationTable.put("Ъ", "\"");
        transliterationTable.put("Ы", "Y");
        transliterationTable.put("Ь", "\'");
        transliterationTable.put("Э", "E");
        transliterationTable.put("Ю", "Yu");
        transliterationTable.put("Я", "Ya");

        return transliterationTable;

    }

    private static HashMap<String, String> getRuLowerChar() {
        HashMap<String, String> transliterationTable = new HashMap<>();
        transliterationTable.put("а", "a");
        transliterationTable.put("б", "b");
        transliterationTable.put("в", "v");
        transliterationTable.put("г", "g");
        transliterationTable.put("д", "d");
        transliterationTable.put("е", "ye");
        transliterationTable.put("ё", "yo");
        transliterationTable.put("ж", "zh");
        transliterationTable.put("з", "z");
        transliterationTable.put("и", "i");
        transliterationTable.put("й", "j");
        transliterationTable.put("к", "k");
        transliterationTable.put("л", "l");
        transliterationTable.put("м", "m");
        transliterationTable.put("н", "n");
        transliterationTable.put("о", "o");
        transliterationTable.put("п", "p");
        transliterationTable.put("р", "r");
        transliterationTable.put("с", "s");
        transliterationTable.put("т", "t");
        transliterationTable.put("у", "u");
        transliterationTable.put("ф", "ph");
        transliterationTable.put("х", "kh");
        transliterationTable.put("ц", "ts");
        transliterationTable.put("ч", "ch");
        transliterationTable.put("ш", "sh");
        transliterationTable.put("щ", "shch");
        transliterationTable.put("ъ", "\"");
        transliterationTable.put("ы", "y");
        transliterationTable.put("ь", "\'");
        transliterationTable.put("э", "e");
        transliterationTable.put("ю", "yu");
        transliterationTable.put("я", "ya");

        return transliterationTable;

    }

    /*
    English to Russian
     */

    private static HashMap<String, String> getLatinUpperChar() {
        HashMap<String, String> transliterationTable = new HashMap<>();
        transliterationTable.put("A", "А");
        transliterationTable.put("B", "Б");
        transliterationTable.put("V", "В");
        transliterationTable.put("G", "Г");
        transliterationTable.put("D", "Д");
        transliterationTable.put("Ye", "Е");
        transliterationTable.put("Yo", "Ё");
        transliterationTable.put("Zh", "Ж");
        transliterationTable.put("Z", "З");
        transliterationTable.put("I", "И");
        transliterationTable.put("J", "Й");
        transliterationTable.put("K", "К");
        transliterationTable.put("L", "Л");
        transliterationTable.put("M", "М");
        transliterationTable.put("N", "Н");
        transliterationTable.put("O", "О");
        transliterationTable.put("P", "П");
        transliterationTable.put("R", "Р");
        transliterationTable.put("S", "С");
        transliterationTable.put("T", "Т");
        transliterationTable.put("U", "У");
        transliterationTable.put("Ph", "Ф");
        transliterationTable.put("Kh", "Х");
        transliterationTable.put("Ts", "Ц");
        transliterationTable.put("Ch", "Ч");
        transliterationTable.put("Sh", "Ш");
        transliterationTable.put("Shch", "Щ");
        transliterationTable.put("\"", "Ъ");
        transliterationTable.put("Y", "Ы");
        transliterationTable.put("\'", "Ь");
        transliterationTable.put("E", "Э");
        transliterationTable.put("Yu", "Ю");
        transliterationTable.put("Ya", "Я");

        return transliterationTable;

    }

    private static HashMap<String, String> getLatinLowerChar() {
        HashMap<String, String> transliterationTable = new HashMap<>();
        transliterationTable.put("a", "а");
        transliterationTable.put("b", "б");
        transliterationTable.put("v", "в");
        transliterationTable.put("g", "г");
        transliterationTable.put("d", "д");
        transliterationTable.put("ye", "е");
        transliterationTable.put("yo", "ё");
        transliterationTable.put("zh", "ж");
        transliterationTable.put("z", "з");
        transliterationTable.put("i", "и");
        transliterationTable.put("j", "й");
        transliterationTable.put("k", "к");
        transliterationTable.put("l", "л");
        transliterationTable.put("m", "м");
        transliterationTable.put("n", "н");
        transliterationTable.put("o", "о");
        transliterationTable.put("p", "п");
        transliterationTable.put("r", "р");
        transliterationTable.put("s", "с");
        transliterationTable.put("t", "т");
        transliterationTable.put("u", "у");
        transliterationTable.put("ph", "ф");
        transliterationTable.put("kh", "х");
        transliterationTable.put("ts", "ц");
        transliterationTable.put("ch", "ч");
        transliterationTable.put("sh", "ш");
        transliterationTable.put("shch", "щ");
        transliterationTable.put("\"", "ъ");
        transliterationTable.put("y", "ы");
        transliterationTable.put("\'", "ь");
        transliterationTable.put("e", "э");
        transliterationTable.put("yu", "ю");
        transliterationTable.put("ya", "я");

        return transliterationTable;

    }

}
