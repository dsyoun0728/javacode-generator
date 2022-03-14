package com.example.parameterized_class;

import com.practice.generator.parameterized_class.ExampleA;
import java.util.ArrayList;
import java.util.List;

public final class ParameterizedClass {
  List<ExampleA> listMaker() {
    List<ExampleA> result = new ArrayList<>();
    result.add(new ExampleA());
    result.add(new ExampleA());
    result.add(new ExampleA());
    return result;
  }
}
