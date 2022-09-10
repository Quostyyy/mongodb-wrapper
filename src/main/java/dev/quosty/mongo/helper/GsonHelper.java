package dev.quosty.mongo.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.quosty.mongo.adapter.AnnotationExclusionStrategy;

public class GsonHelper {

  protected static final Gson gson = new GsonBuilder()
      .setExclusionStrategies(new AnnotationExclusionStrategy())
      .serializeNulls()
      .create();

  public static Gson getGson() {
    return gson;
  }
}
