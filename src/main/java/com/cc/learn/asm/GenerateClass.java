package com.cc.learn.asm;


import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by chenchang on 2018/11/26.
 */
public class GenerateClass {

//    public void generateClass() {
//        //方法的栈长度和本地变量表长度用户自己计算
//        ClassWriter classWriter = new ClassWriter(0);
//        //Opcodes.V1_6指定类的版本
//        //Opcodes.ACC_PUBLIC表示这个类是public，
//        //“org/victorzhzh/core/classes/MyClass”类的全限定名称
//        //第一个null位置变量定义的是泛型签名，
//        //“java/lang/Object”这个类的父类
//        //第二个null位子的变量定义的是这个类实现的接口
//        classWriter.visit(Opcodes.V1_6, Opcodes.ACC_PUBLIC,
//                "org/victorzhzh/core/classes/MyClass", null,
//                "java/lang/Object", null);
//        ClassAdapter classAdapter = new MyClassAdapter(classWriter);
//        classAdapter.visitField(Opcodes.ACC_PRIVATE, "name", Type.getDescriptor(String.class), null, "aaaaaa");//定义name属性
//        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null).visitCode();//定义构造方法
//        String setMethodDesc = "(" + Type.getDescriptor(String.class) + ")V";
//        classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "setName", setMethodDesc, null, null).visitCode();//定义setName方法
//        // String getMethodDesc = "()" + Type.getDescriptor(String.class);
//        //   MethodVisitor methodVisitor = classAdapter.visitMethod(Opcodes.ACC_PUBLIC, "getName", getMethodDesc, null, null).visitCode();//定义getName方法
//
//        byte[] classFile = classWriter.toByteArray();//生成字节码
//
//        MyClassLoader classLoader = new MyClassLoader();//定义一个类加载器
//        Class clazz = classLoader.defineClassFromClassFile(
//                "org.victorzhzh.core.classes.MyClass", classFile);
//        try {//利用反射方式，访问getName
//            Object obj = clazz.newInstance();
//            Method method = clazz.getMethod("getName");
//            System.out.println(obj.toString());
//            System.out.println(method.invoke(obj, null));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    class MyClassLoader extends ClassLoader {
        public Class defineClassFromClassFile(String className, byte[] classFile)
                throws ClassFormatError {
            return defineClass(className, classFile, 0, classFile.length);
        }
    }

//    public static void main(String[] args) throws NoSuchMethodException {
//        System.out.println(Type.getDescriptor(GenerateClass.class));
//        System.out.println(Type.getDescriptor(String.class));
//        System.out.println(Type.getDescriptor(long.class));
//        System.out.println(Type.getInternalName(String.class));
//        System.out.println(Type.getInternalName(Integer.class));
//        System.out.println(Type.getInternalName(GenerateClass.class));
//        Method m = String.class.getMethod("substring", int.class);
//        System.out.println(Type.getMethodDescriptor(m));
//
//        System.out.println(Type.getConstructorDescriptor(String.class.getConstructor()));
//
//
//        GenerateClass generateClass = new GenerateClass();
//        generateClass.generateClass();
//    }

//    public class MyClassAdapter extends ClassAdapter {
//
//        public MyClassAdapter(ClassVisitor cv) {
//            super(cv);
//        }
//
//
//        @Override
//        public MethodVisitor visitMethod(int access, String name, String desc,
//                                         String signature, String[] exceptions) {
//            MethodVisitor methodVisitor = cv.visitMethod(access, name, desc,
//                    signature, exceptions);
//            if (name.equals("&lt;init&gt;")) {
//                return new InitMethodAdapter(methodVisitor);
//            } else if (name.equals("setName")) {
//                return new SetMethodAdapter(methodVisitor);
//            } else if (name.equals("getName")) {
//                return new GetMethodAdapter(methodVisitor);
//            } else {
//                return super.visitMethod(access, name, desc, signature, exceptions);
//            }
//        }
//
//        //这个类生成具体的构造方法字节码
//        class InitMethodAdapter extends MethodAdapter {
//            public InitMethodAdapter(MethodVisitor mv) {
//                super(mv);
//            }
//
//            @Override
//            public void visitCode() {
//                mv.visitVarInsn(Opcodes.ALOAD, 0);
//                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object",
//                        "&lt;init&gt;", "()V");//调用父类的构造方法
//                mv.visitVarInsn(Opcodes.ALOAD, 0);
//                mv.visitLdcInsn("Kuzury");//将常量池中的字符串常量加载刀栈顶
//                mv.visitFieldInsn(Opcodes.PUTFIELD,
//                        "org/victorzhzh/core/classes/MyClass", "name",
//                        Type.getDescriptor(String.class));//对name属性赋值
//                mv.visitInsn(Opcodes.RETURN);//设置返回值
//                mv.visitMaxs(2, 1);//设置方法的栈和本地变量表的大小
//            }
//        }
//
//        ;
//
//        //这个类生成具体的setName方法字节码
//        class SetMethodAdapter extends MethodAdapter {
//            public SetMethodAdapter(MethodVisitor mv) {
//                super(mv);
//            }
//
//            @Override
//            public void visitCode() {
//                mv.visitVarInsn(Opcodes.ALOAD, 0);
//                mv.visitVarInsn(Opcodes.ALOAD, 1);
//                mv.visitFieldInsn(Opcodes.PUTFIELD,
//                        "org/victorzhzh/core/classes/MyClass", "name",
//                        Type.getDescriptor(String.class));
//                mv.visitInsn(Opcodes.RETURN);
//                mv.visitMaxs(2, 2);
//            }
//
//        }
//
//
//        //这个类生成具体的getName方法字节
//        class GetMethodAdapter extends MethodAdapter {
//
//            public GetMethodAdapter(MethodVisitor mv) {
//                super(mv);
//            }
//
//            @Override
//            public void visitCode() {
//                mv.visitVarInsn(Opcodes.ALOAD, 0);
//                mv.visitFieldInsn(Opcodes.GETFIELD,
//                        "org/victorzhzh/core/classes/MyClass", "name",
//                        Type.getDescriptor(String.class));//获取name属性的值
//                mv.visitInsn(Opcodes.ARETURN);//返回一个引用，这里是String的引用即name
//                mv.visitMaxs(1, 1);
//            }
//        }
//    }

    public static void main(String args[]) throws Exception {


        ClassWriter classWriter = new ClassWriter(0);
        String className = "com/sunchao/asm/HelloWorld";
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC, className, null,
                "java/lang/Object", null);


        MethodVisitor initVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null);
        initVisitor.visitCode();
        initVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        initVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "V()");
        initVisitor.visitInsn(Opcodes.RETURN);
        initVisitor.visitMaxs(1, 1);
        initVisitor.visitEnd();

        MethodVisitor helloVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "sayHello", "()V;", null, null);
        helloVisitor.visitCode();
        helloVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",   "Ljava/io/PrintStream;");
        helloVisitor.visitLdcInsn("hello world!");
        helloVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V");
        helloVisitor.visitInsn(Opcodes.RETURN);
        helloVisitor.visitMaxs(1, 1);
        helloVisitor.visitEnd();

        classWriter.visitEnd();
        byte[] code = classWriter.toByteArray();
        File file = new File("D:\\HelloWorld.class");
        FileOutputStream output = new FileOutputStream(file);
        output.write(code);
        output.close();
    }

}
