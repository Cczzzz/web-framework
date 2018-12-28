package com.cc.learn.asm;

import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.util.ASMifier;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * Created by chenchang on 2018/12/27.
 */
public class ASmifierDemo {
    public static void main(String[] args) throws Exception {

        ASMifier.main(new String[]{"com.cc.bean.AA"});


        ClassWriter cw = new ClassWriter(0);
        FieldVisitor fv;
        MethodVisitor mv;
        AnnotationVisitor av0;

        cw.visit(52, ACC_PUBLIC + ACC_SUPER, "com/cc/bean/AA", "Ljava/lang/Object;Lcom/cc/bean/Cope<Lcom/cc/bean/Student;Lcom/cc/bean/Sthdent2;>;", "java/lang/Object", new String[]{"com/cc/bean/Cope"});

        mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC, "ccc", "(Lcom/cc/bean/Student;Lcom/cc/bean/Sthdent2;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 2);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Student", "getName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Sthdent2", "setName", "(Ljava/lang/String;)V", false);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Student", "getAge", "()Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Sthdent2", "setAge", "(Ljava/lang/Integer;)V", false);
        mv.visitVarInsn(ALOAD, 2);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Student", "getClassName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/Sthdent2", "setClassName", "(Ljava/lang/String;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(2, 3);
        mv.visitEnd();

        mv = cw.visitMethod(ACC_PUBLIC + ACC_BRIDGE + ACC_SYNTHETIC, "ccc", "(Ljava/lang/Object;Ljava/lang/Object;)V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(ALOAD, 1);
        mv.visitTypeInsn(CHECKCAST, "com/cc/bean/Student");
        mv.visitVarInsn(ALOAD, 2);
        mv.visitTypeInsn(CHECKCAST, "com/cc/bean/Sthdent2");
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/cc/bean/AA", "ccc", "(Lcom/cc/bean/Student;Lcom/cc/bean/Sthdent2;)V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
        cw.visitEnd();

    }
}
