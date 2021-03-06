package com.practice.generator.helloworld;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class HelloWorldGenerator {
    public void generateJavaSource() {
        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld).build();

        try {
            javaFile.writeTo(Paths.get("./src"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HelloWorldGenerator helloWorldGenerator = new HelloWorldGenerator();
        helloWorldGenerator.generateJavaSource();
    }
}
