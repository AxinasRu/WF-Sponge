package wfplugin.wfplugin.storage;

import com.google.gson.Gson;
import wfplugin.wfplugin.WFPlugin;

import java.io.*;

public class ConfigManager {

    public static <T> T load(String path, Class<T> classOfT) throws IOException {
        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return gson.fromJson(br, classOfT);
        }
    }

    public static <T> void flush(String path, T src) throws IOException {
        Gson gson = new Gson();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            gson.toJson(src, bw);
        }
    }

    public static void flush(String path, String src) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append(src).flush();
        }
    }

    public static <T> T loadOrCreate(String path, T orNotExist) throws IOException {
        File parentFile = new File(path).getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.isDirectory())
                throw new IOException(parentFile + " must be directory");
            if (!parentFile.mkdirs())
                throw new IOException("Can't create " + parentFile);
        }
        try {
            return ConfigManager.load(path, (Class<T>) orNotExist.getClass());
        } catch (IOException e) {
            return orNotExist;
        } finally {
            flush(path, orNotExist);
        }
    }
}
