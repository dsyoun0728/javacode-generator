package com.practice.generator.parameterized_class;

import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class ParameterizedClassGenerator {
    ClassName exampleA = ClassName.get("com.practice.generator.parameterized_class", "ExampleA");
    ClassName list = ClassName.get("java.util", "List");
    ClassName arrayList = ClassName.get("java.util", "ArrayList");
    TypeName listOfExampleA = ParameterizedTypeName.get(list, exampleA);

    MethodSpec methodSpec = MethodSpec.methodBuilder("listMaker")
            .returns(listOfExampleA)
            .addStatement("$T result = new $T<>()", listOfExampleA, arrayList)
            .addStatement("result.add(new $T())", exampleA)
            .addStatement("result.add(new $T())", exampleA)
            .addStatement("result.add(new $T())", exampleA)
            .addStatement("return result")
            .build();

    TypeSpec typeSpec = TypeSpec.classBuilder("ParameterizedClass")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(methodSpec)
            .build();

    JavaFile javaFile = JavaFile.builder("com.example.parameterized_class", typeSpec).build();

    public ParameterizedClassGenerator() {
        try {
            this.javaFile.writeTo(Paths.get("./src"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ParameterizedClassGenerator parameterizedClassGenerator = new ParameterizedClassGenerator();
    }
}
