package com.cc.learn.asm;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.util.ASMifier;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Created by chenchang on 2018/11/2.
 */

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }

    public ClassPrinter(ClassVisitor classVisitor) {
        super(ASM4, classVisitor);
    }

    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
    }

    public void visitOuterClass(String owner, String name, String desc) {
        super.visitOuterClass(owner, name, desc);
    }

    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return super.visitAnnotation(desc, visible);
    }

    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
    }

    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return super.visitField(access, name, desc, signature, value);
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    public void visitEnd() {
        FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, "aaaa", "I", null, 3);
        if (fv != null) {
            fv.visitEnd();
        }
        super.visitEnd();
    }

    public static void main(String[] args) throws Exception {
        //ClassReader作为字节码生产者，ClassPrinter作为字节码消费者

        ClassWriter classWriter = new ClassWriter(0);
        ClassPrinter cp = new ClassPrinter(classWriter);
        ClassReader cr = new ClassReader("com.cc.bean.Student");
//        cr.accept(cp, 0);
//        File file = new File("d://Student.class");
//        if (file.exists()) {
//            file.delete();
//        }
//        FileOutputStream stream = new FileOutputStream(file);
//        stream.write(classWriter.toByteArray());

        TraceClassVisitor tv = new TraceClassVisitor(classWriter, new PrintWriter(System.out));
        tv.visit(V1_8, Opcodes.ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "org/by/Cwtest", null, Type.getDescriptor(Object.class), null);
        tv.visitField(ACC_PUBLIC+ACC_STATIC+ACC_FINAL,"LESS","I",
                null,-1).visitEnd();
        tv.visitField(ACC_PUBLIC+ACC_STATIC+ACC_FINAL,"EQUAL","I",
                null,0).visitEnd();
        tv.visitField(ACC_PUBLIC+ACC_STATIC+ACC_FINAL,"GRATER","I",
                null,1).visitEnd();
        tv.visitMethod(ACC_PUBLIC+ACC_ABSTRACT,"compareTo","(Ljava/lang/Object;)I",
                null,null).visitEnd();
        tv.visitEnd();

        ASMifier.main(new String[]{"com.cc.bean.Student"});
    }

}


