package dev.quosty.mongo.adapter;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import dev.quosty.mongo.annotations.Exclude;

public class AnnotationExclusionStrategy implements ExclusionStrategy {

  @Override
  public boolean shouldSkipField(FieldAttributes f) {
    return f.getAnnotation(Exclude.class) != null;
  }

  @Override
  public boolean shouldSkipClass(Class<?> clazz) {
    return false;
  }
}