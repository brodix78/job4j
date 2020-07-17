package ru.job4j.exam;

import java.util.List;
import java.util.Map;

public class CameraFactory implements Factory<Camera>{

    @Override
    public List<String> links() {
        return List.of("sourceDataUrl", "tokenDataUrl");
    }

    @Override
    public Camera getInstance(Map<String, String> fields) {
        Camera camera = null;
        if(fields.keySet().containsAll(List.of("urlType", "videoUrl", "value", "ttl"))) {
            if (!fields.containsKey("id")) {
                fields.put("id", "N/A");
            }
            try {
               int ttl = Integer.parseInt(fields.get("ttl"));
                camera = new Camera(fields.get("id"), fields.get("urlType"), fields.get("videoUrl"),
                        fields.get("value"), ttl);
            } catch (NumberFormatException e) {
                camera =null;
            }
        }
        return camera;
    }

    @Override
    public Camera getCopy(Camera original) {
        return new Camera(original.getId(), original.getUrlType(), original.getVideoUrl(),
                original.getValue(), original.getTtl());
    }
}
