import java.io.File;

public class ParametersBag {
    private long sizeLimit = 0;
    private String path = "";

    public ParametersBag(String[] args) {
        setSizeLimit(args);
        setPath(args);
        File file = new File(path);

        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("Указанного файла или папки не существует");
        } else if (getPath().equals("")) {
            throw new IllegalArgumentException("Указан неверный путь. Используйте -d");
        } else if (getSizeLimit() <= 0) {
            throw new IllegalArgumentException("Указан неверный лимит. Используйте -l");
        }
    }

    private void setSizeLimit(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-l")) {
                sizeLimit = SizeCalculator.getSizeFromHumanReadable(args[i + 1]);
            }
        }
    }

    public long getSizeLimit() {
        return sizeLimit;
    }

    private void setPath(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                path = args[i + 1];
            }
        }
    }

    public String getPath() {
        return path;
    }
}
