package wfplugin.wfplugin.storage;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;

public class ConfigManager {

    public static <T> T load(String path, Type classOfT) throws IOException {
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
        if (!parentFile.isDirectory())
            throw new IOException(parentFile + " must be directory");
        if (!parentFile.exists())
            if (!parentFile.mkdirs())
                throw new IOException("Can't create " + parentFile);
        try {
            return ConfigManager.load(path, orNotExist.getClass());
        } catch (IOException e) {
            flush(path, orNotExist);
            return orNotExist;
        }
    }
}
