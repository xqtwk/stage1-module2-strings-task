package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer st = new StringTokenizer(signatureString, " (),");
        String accessModifier = st.nextToken();
        String returnType = st.nextToken();
        String methodName = st.nextToken();

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String argument;

        while (st.hasMoreTokens()) {
            argument = st.nextToken();
            System.out.println(argument);
            String argumentType = argument;
            argument = st.nextToken();
            System.out.println(argument);
            String argumentName = argument;
            arguments.add(new MethodSignature.Argument(argumentType, argumentName));
        }
        MethodSignature methodSignature = new MethodSignature(methodName,arguments);

        switch (accessModifier) {
            case "public":
                methodSignature.setAccessModifier("public");
                break;
            case "private":
                methodSignature.setAccessModifier("private");
                break;
            case "protected":
                methodSignature.setAccessModifier("protected");
                break;
            default:
                methodSignature.setAccessModifier("default");
        }

        methodSignature.setReturnType(returnType);

        return methodSignature;
    }

}
