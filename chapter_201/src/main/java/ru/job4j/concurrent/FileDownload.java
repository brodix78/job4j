package ru.job4j.concurrent;

import java.io.*;
import java.net.URL;

public class FileDownload {

    URL url;
    int speed;

    public void download() {
        download(url, speed);
    }

    public void download(URL url, int speed) {
        try (BufferedInputStream in = new BufferedInputStream(url.openStream());
             FileOutputStream out = new FileOutputStream(url.toString().substring(
                     url.toString().lastIndexOf('/') + 1))){
            byte[] buffer = new byte[1024 * speed];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, bytesRead);
                long pause = 1000 - (System.currentTimeMillis() - start);
                if (pause > 0) {
                    Thread.sleep(pause);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean setUrl(String file) {
        try {
            url = new URL(file);
        } catch (Exception e) {
            System.out.println("Bad URL");
            url = null;
        }
        return url != null;
    }

    private boolean setSpeed(String text) {
        try {
            speed = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            speed = -1;
            System.out.println("Wrong speed format");
        }
        return speed > 0;
    }

    public static void main(String[] args) {
        FileDownload fd = new FileDownload();
        if (args.length == 2 && fd.setUrl(args[0]) && fd.setSpeed(args[1])) {
            fd.download();
        } else {
            System.out.println("Usage is: fget.jar file_URL MAX_download_Speed");
        }
    }
}