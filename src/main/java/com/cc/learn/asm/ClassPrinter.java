package com.cc.learn.asm;

import jdk.internal.org.objectweb.asm.*;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

/**
 * Created by chenchang on 2018/11/2.
 */

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    public void visitSource(String source, String debug) {
    }

    public void visitOuterClass(String owner, String name, String desc) {
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    public void visitAttribute(Attribute attr) {
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("    " + desc + " " + name);
        return null;
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println(" " + name + desc);
        return null;
    }

    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws IOException {
        //ClassReader作为字节码生产者，ClassPrinter作为字节码消费者
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("com.cc.bean.Student");
        cr.accept(cp, 0);

    }

}


