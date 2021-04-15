package net.intcoder.bc;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class BytecodeGenerator implements Opcodes {

    private ClassNode cn = new ClassNode(ASM9);

    protected void init() {
        generateBase();
        generateConstructor();
    }

    public ClassNode generate(int depth) {
        //
    }

    /**
     * .version 60 0
     * .class public super net/intcoder/tbravocalc/calculator/PathGeneratorImpl
     * .super java/lang/Object
     * .implements net/intcoder/tbravocalc/calculator/PathGenerator 
     */
    protected void generateBase() {
        // Class
        cn.visit(V16, ACC_PUBLIC, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl ",
                null, "java/lang/Object",
                new String[] { "net/intcoder/tbravocalc/calculator/PathGenerator" });

        // Fields
        cn.visitField(ACC_PRIVATE, "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;",
                null, null);
        cn.visitField(ACC_PRIVATE, "continueCalculate", "Z", null, null);
    }

    /**
     * L0:     aload_0
     * L1:     invokespecial Method java/lang/Object <init> ()V
     * L4:     aload_0
     * L5:     iconst_1
     * L6:     putfield Field net/intcoder/tbravocalc/calculator/PathGeneratorImpl continueCalculate Z
     * L9:     aload_0
     * L10:    aload_1
     * L11:    putfield Field net/intcoder/tbravocalc/calculator/PathGeneratorImpl pathHandler Lnet/intcoder/tbravocalc/calculator/PathHandler;
     * L14:    return
     */
    protected void generateConstructor() {
        var mn = cn.visitMethod(ACC_PUBLIC, "<init>", "(Lnet/intcoder/tbravocalc/calculator/PathHandler;)V",
                null, null);

        mn.visitVarInsn(ALOAD, 0);
        mn.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mn.visitVarInsn(ALOAD, 0);
        mn.visitInsn(ICONST_1);
        mn.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl",
                "continueCalculate", "Z");
        mn.visitVarInsn(ALOAD, 0);
        mn.visitVarInsn(ALOAD, 1);
        mn.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl",
                "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");
        mn.visitInsn(RETURN);
    }
}
