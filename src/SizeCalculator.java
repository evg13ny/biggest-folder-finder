import java.text.DecimalFormat;

import static java.lang.Integer.parseInt;

public class SizeCalculator {

    public static String getHumanReadableSize(long size) {
        String[] units = new String[]{"B", "Kb", "Mb", "Gb", "Tb"};
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);

        return new DecimalFormat("#,##0.#").format(size / unitValue) + " " + units[unitIndex];
    }

    public static long getSizeFromHumanReadable(String size) {
        int numbers = parseInt(size.replaceAll("[^0-9]", ""));
        String letters = size.replaceAll("[^A-z]", "");

        return (long) switch (letters) {
            case "K", "Kb" -> numbers * 1024;
            case "M", "Mb" -> numbers * Math.pow(1024, 2);
            case "G", "Gb" -> numbers * Math.pow(1024, 3);
            case "T", "Tb" -> numbers * Math.pow(1024, 4);
            default -> numbers;
        };
    }
}
