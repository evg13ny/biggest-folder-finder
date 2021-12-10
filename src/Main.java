import java.io.File;
import java.text.DecimalFormat;
import java.util.concurrent.ForkJoinPool;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:\\Users\\evg_p\\Downloads";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(getHumanReadableSize(size));

//        System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                sum += getFolderSize(file);
            }
        }

        return sum;
    }

    public static String getHumanReadableSize(long size) {
        String[] units = new String[]{"B", "Kb", "Mb", "Gb", "Tb"};
        int unitIndex = (int) (Math.log10(size) / 3);
        double unitValue = 1 << (unitIndex * 10);

        return new DecimalFormat("#,##0.#").format(size / unitValue) + " " + units[unitIndex];
    }

    public static long getSizeFromHumanReadable(String size) {
        String numbers = size.replaceAll("[^0-9]", "");
        String letters = size.replaceAll("[^A-z]", "");

        return switch (letters) {
            case "K", "Kb" -> (long) (parseInt(numbers) * Math.pow(1024, 1));
            case "M", "Mb" -> (long) (parseInt(numbers) * Math.pow(1024, 2));
            case "T", "Tb" -> (long) (parseInt(numbers) * Math.pow(1024, 3));
            default -> (long) parseInt(numbers);
        };
    }
}
