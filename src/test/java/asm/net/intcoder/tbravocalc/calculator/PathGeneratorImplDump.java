package asm.net.intcoder.tbravocalc.calculator;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PathGeneratorImplDump implements Opcodes {

    public static byte[] dump() throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V16, ACC_PUBLIC | ACC_SUPER, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", null, "java/lang/Object", new String[]{"net/intcoder/tbravocalc/calculator/PathGenerator"});

        classWriter.visitSource("PathGeneratorImpl.java", null);

        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "continueCalculate", "Z", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Lnet/intcoder/tbravocalc/calculator/PathHandler;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(14, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(12, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(15, label2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(16, label3);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_VARARGS, "start", "([D)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(19, label0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(ARRAYLENGTH);
            Label label2 = new Label();
            methodVisitor.visitJumpInsn(IF_ICMPGE, label2);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitInsn(DALOAD);
            methodVisitor.visitVarInsn(DSTORE, 3);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label3);
            methodVisitor.visitJumpInsn(GOTO, label2);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");

            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitIntInsn(NEWARRAY, T_DOUBLE);
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(DLOAD, 3);
            methodVisitor.visitInsn(DASTORE);

            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "handle", "([D)Z", true);
            Label label4 = new Label();
            methodVisitor.visitJumpInsn(IFNE, label4);
            methodVisitor.visitInsn(ICONST_1);
            Label label5 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label5);
            methodVisitor.visitLabel(label4);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitLabel(label5);
            methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
//START
            {
                methodVisitor.visitVarInsn(ILOAD, 2);
                methodVisitor.visitInsn(ICONST_1);
                methodVisitor.visitInsn(IADD);
                methodVisitor.visitVarInsn(ISTORE, 5);
                Label label6 = new Label();
                methodVisitor.visitLabel(label6);
                methodVisitor.visitVarInsn(ILOAD, 5);
                methodVisitor.visitVarInsn(ALOAD, 1);
                methodVisitor.visitInsn(ARRAYLENGTH);
                Label label7 = new Label();
                methodVisitor.visitJumpInsn(IF_ICMPGE, label7);
                methodVisitor.visitVarInsn(ALOAD, 1);
                methodVisitor.visitVarInsn(ILOAD, 5);
                methodVisitor.visitInsn(DALOAD);
                methodVisitor.visitVarInsn(DSTORE, 6);
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                Label label8 = new Label();
                methodVisitor.visitJumpInsn(IFNE, label8);
                methodVisitor.visitJumpInsn(GOTO, label7);
                methodVisitor.visitLabel(label8);
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitVarInsn(ALOAD, 0);
                methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");

                methodVisitor.visitInsn(ICONST_2);
                methodVisitor.visitIntInsn(NEWARRAY, T_DOUBLE);
                methodVisitor.visitInsn(DUP);
                methodVisitor.visitInsn(ICONST_0);
                methodVisitor.visitVarInsn(DLOAD, 3);
                methodVisitor.visitInsn(DASTORE);
                methodVisitor.visitInsn(DUP);
                methodVisitor.visitInsn(ICONST_1);
                methodVisitor.visitVarInsn(DLOAD, 6);
                methodVisitor.visitInsn(DASTORE);

                methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "handle", "([D)Z", true);
                Label label9 = new Label();
                methodVisitor.visitJumpInsn(IFNE, label9);
                methodVisitor.visitInsn(ICONST_1);
                Label label10 = new Label();
                methodVisitor.visitJumpInsn(GOTO, label10);
                methodVisitor.visitLabel(label9);
                methodVisitor.visitInsn(ICONST_0);
                methodVisitor.visitLabel(label10);
                methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                {
                    methodVisitor.visitVarInsn(ILOAD, 5);
                    methodVisitor.visitInsn(ICONST_1);
                    methodVisitor.visitInsn(IADD);
                    methodVisitor.visitVarInsn(ISTORE, 8);
                    Label label11 = new Label();
                    methodVisitor.visitLabel(label11);
                    methodVisitor.visitVarInsn(ILOAD, 8);
                    methodVisitor.visitVarInsn(ALOAD, 1);
                    methodVisitor.visitInsn(ARRAYLENGTH);
                    Label label12 = new Label();
                    methodVisitor.visitJumpInsn(IF_ICMPGE, label12);
                    methodVisitor.visitVarInsn(ALOAD, 1);
                    methodVisitor.visitVarInsn(ILOAD, 8);
                    methodVisitor.visitInsn(DALOAD);
                    methodVisitor.visitVarInsn(DSTORE, 9);
                    methodVisitor.visitVarInsn(ALOAD, 0);
                    methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                    Label label13 = new Label();
                    methodVisitor.visitJumpInsn(IFNE, label13);
                    methodVisitor.visitJumpInsn(GOTO, label12);
                    methodVisitor.visitLabel(label13);
                    methodVisitor.visitVarInsn(ALOAD, 0);
                    methodVisitor.visitVarInsn(ALOAD, 0);
                    methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");

                    methodVisitor.visitInsn(ICONST_3);
                    methodVisitor.visitIntInsn(NEWARRAY, T_DOUBLE);
                    methodVisitor.visitInsn(DUP);
                    methodVisitor.visitInsn(ICONST_0);
                    methodVisitor.visitVarInsn(DLOAD, 3);
                    methodVisitor.visitInsn(DASTORE);
                    methodVisitor.visitInsn(DUP);
                    methodVisitor.visitInsn(ICONST_1);
                    methodVisitor.visitVarInsn(DLOAD, 6);
                    methodVisitor.visitInsn(DASTORE);
                    methodVisitor.visitInsn(DUP);
                    methodVisitor.visitInsn(ICONST_2);
                    methodVisitor.visitVarInsn(DLOAD, 9);
                    methodVisitor.visitInsn(DASTORE);

                    methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "handle", "([D)Z", true);
                    Label label14 = new Label();
                    methodVisitor.visitJumpInsn(IFNE, label14);
                    methodVisitor.visitInsn(ICONST_1);
                    Label label15 = new Label();
                    methodVisitor.visitJumpInsn(GOTO, label15);
                    methodVisitor.visitLabel(label14);
                    methodVisitor.visitInsn(ICONST_0);
                    methodVisitor.visitLabel(label15);
                    methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                    {
                        methodVisitor.visitVarInsn(ILOAD, 8);
                        methodVisitor.visitInsn(ICONST_1);
                        methodVisitor.visitInsn(IADD);
                        methodVisitor.visitVarInsn(ISTORE, 11);
                        Label label16 = new Label();
                        methodVisitor.visitLabel(label16);
                        methodVisitor.visitVarInsn(ILOAD, 11);
                        methodVisitor.visitVarInsn(ALOAD, 1);
                        methodVisitor.visitInsn(ARRAYLENGTH);
                        Label label17 = new Label();
                        methodVisitor.visitJumpInsn(IF_ICMPGE, label17);
                        methodVisitor.visitVarInsn(ALOAD, 1);
                        methodVisitor.visitVarInsn(ILOAD, 11);
                        methodVisitor.visitInsn(DALOAD);
                        methodVisitor.visitVarInsn(DSTORE, 12);
                        methodVisitor.visitVarInsn(ALOAD, 0);
                        methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                        Label label18 = new Label();
                        methodVisitor.visitJumpInsn(IFNE, label18);
                        methodVisitor.visitJumpInsn(GOTO, label17);
                        methodVisitor.visitLabel(label18);
                        methodVisitor.visitVarInsn(ALOAD, 0);
                        methodVisitor.visitVarInsn(ALOAD, 0);
                        methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");

                        methodVisitor.visitInsn(ICONST_4);
                        methodVisitor.visitIntInsn(NEWARRAY, T_DOUBLE);
                        methodVisitor.visitInsn(DUP);
                        methodVisitor.visitInsn(ICONST_0);
                        methodVisitor.visitVarInsn(DLOAD, 3);
                        methodVisitor.visitInsn(DASTORE);
                        methodVisitor.visitInsn(DUP);
                        methodVisitor.visitInsn(ICONST_1);
                        methodVisitor.visitVarInsn(DLOAD, 6);
                        methodVisitor.visitInsn(DASTORE);
                        methodVisitor.visitInsn(DUP);
                        methodVisitor.visitInsn(ICONST_2);
                        methodVisitor.visitVarInsn(DLOAD, 9);
                        methodVisitor.visitInsn(DASTORE);
                        methodVisitor.visitInsn(DUP);
                        methodVisitor.visitInsn(ICONST_3);
                        methodVisitor.visitVarInsn(DLOAD, 12);
                        methodVisitor.visitInsn(DASTORE);

                        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "handle", "([D)Z", true);
                        Label label19 = new Label();
                        methodVisitor.visitJumpInsn(IFNE, label19);
                        methodVisitor.visitInsn(ICONST_1);
                        Label label20 = new Label();
                        methodVisitor.visitJumpInsn(GOTO, label20);
                        methodVisitor.visitLabel(label19);
                        methodVisitor.visitInsn(ICONST_0);
                        methodVisitor.visitLabel(label20);
                        methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
                        methodVisitor.visitIincInsn(11, 1);
                        methodVisitor.visitJumpInsn(GOTO, label16);
                        methodVisitor.visitLabel(label17);
                    }
                    methodVisitor.visitIincInsn(8, 1);
                    methodVisitor.visitJumpInsn(GOTO, label11);
                    methodVisitor.visitLabel(label12);
                }
                methodVisitor.visitIincInsn(5, 1);
                methodVisitor.visitJumpInsn(GOTO, label6);
                methodVisitor.visitLabel(label7);
            }
//END
            methodVisitor.visitIincInsn(2, 1);
            methodVisitor.visitJumpInsn(GOTO, label1);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(20, label2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "finish", "()V", true);
            Label label21 = new Label();
            methodVisitor.visitLabel(label21);
            methodVisitor.visitLineNumber(21, label21);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(7, 14);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }


    public static void generateEmbeddedFor(MethodVisitor methodVisitor, int length) {
        methodVisitor.visitVarInsn(ILOAD, 8);
        methodVisitor.visitInsn(ICONST_1);
        methodVisitor.visitInsn(IADD);
        methodVisitor.visitVarInsn(ISTORE, 11);
        Label label16 = new Label();
        methodVisitor.visitLabel(label16);
        methodVisitor.visitVarInsn(ILOAD, 11);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitInsn(ARRAYLENGTH);
        Label label17 = new Label();
        methodVisitor.visitJumpInsn(IF_ICMPGE, label17);
        methodVisitor.visitVarInsn(ALOAD, 1);
        methodVisitor.visitVarInsn(ILOAD, 11);
        methodVisitor.visitInsn(DALOAD);
        methodVisitor.visitVarInsn(DSTORE, 12);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
        Label label18 = new Label();
        methodVisitor.visitJumpInsn(IFNE, label18);
        methodVisitor.visitJumpInsn(GOTO, label17);
        methodVisitor.visitLabel(label18);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(GETFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "pathHandler", "Lnet/intcoder/tbravocalc/calculator/PathHandler;");
        generateArray(methodVisitor, length); // Arr
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "net/intcoder/tbravocalc/calculator/PathHandler", "handle", "([D)Z", true);
        Label label19 = new Label();
        methodVisitor.visitJumpInsn(IFNE, label19);
        methodVisitor.visitInsn(ICONST_1);
        Label label20 = new Label();
        methodVisitor.visitJumpInsn(GOTO, label20);
        methodVisitor.visitLabel(label19);
        methodVisitor.visitInsn(ICONST_0);
        methodVisitor.visitLabel(label20);
        methodVisitor.visitFieldInsn(PUTFIELD, "net/intcoder/tbravocalc/calculator/PathGeneratorImpl", "continueCalculate", "Z");
        methodVisitor.visitIincInsn(11, 1);
        methodVisitor.visitJumpInsn(GOTO, label16);
        methodVisitor.visitLabel(label17);
    }

    public static void generateArray(MethodVisitor methodVisitor, int length) {
        methodVisitor.visitLdcInsn(length);
        methodVisitor.visitIntInsn(NEWARRAY, T_DOUBLE);

        int h = 0;
        for (int i = 0; i < length; i++) {
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitLdcInsn(length);
            methodVisitor.visitVarInsn(DLOAD, h+=3);
            methodVisitor.visitInsn(DASTORE);
        }
    }

    @Test
    void name() {
        var list = new LinkedList<Integer>();

        int length = 4;
        int h = 0;
        for (int i = 0; i < length; i++) {
            list.add(h+=3);
        }

        assertEquals(List.of(3,6,9,12), list);
    }
}

