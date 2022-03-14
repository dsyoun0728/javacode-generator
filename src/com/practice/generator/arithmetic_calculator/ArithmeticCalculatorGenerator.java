package com.practice.generator.arithmetic_calculator;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Paths;

public class ArithmeticCalculatorGenerator {
    TypeSpec.Builder typeSpecBuilder;
    public ArithmeticCalculatorGenerator(String className) {
        this.typeSpecBuilder = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
    }

    public void generateJavaSourceFile(String packageName) {
        TypeSpec typeSpec = this.typeSpecBuilder.build();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();

        try {
            javaFile.writeTo(Paths.get("./src"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMethodToClass(String methodName, int defaultValue, int from, int to, String operation) {
        this.typeSpecBuilder.addMethod(this.generateMethod(methodName, defaultValue, from, to, operation));
    }

    public MethodSpec generateMethod(String methodName, int defaultValue, int from, int to, String operation) {
        return MethodSpec.methodBuilder(methodName)
                .returns(int.class)
                .addStatement("int result = " + defaultValue)
//                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
//                .addStatement("result = result " + operation + " i")
//                .endControlFlow()
                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
                .addStatement("result = result $L i", operation)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    public static void main(String[] args) {
        ArithmeticCalculatorGenerator arithmeticCalculatorGenerator = new ArithmeticCalculatorGenerator("ArithmeticCalculator");
        arithmeticCalculatorGenerator.addMethodToClass("add1to50", 0, 1, 50, "+");
        arithmeticCalculatorGenerator.addMethodToClass("multiply5to20", 1, 5, 20, "*");
        arithmeticCalculatorGenerator.generateJavaSourceFile("com.example.arithmetic_calculator");
    }
}
