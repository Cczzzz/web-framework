package javac;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

//@AutoService(Processor.class)
@SupportedAnnotationTypes({"test.JavacA", "com.cc.learn.javac.JavacA"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class JavacTest extends AbstractProcessor {
    private Types mTypeUtils;
    private Elements mElementUtils;
    private Filer mFiler;
    private Messager mMessager;
    private static Trees trees;
    private static TreeMaker make;
    private static Context context;
    private static Name.Table names;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("我被执行了");
        for (TypeElement te : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
                if (element.getKind() == ElementKind.CLASS) {
                    JCTree tree = (JCTree) trees.getTree(element);
                    MyTreeTranslator myTreeTranslator = new MyTreeTranslator();
                    tree.accept(myTreeTranslator);
                }
            }
        }
        return true;
    }


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        //初始化我们需要的基础工具
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        trees = Trees.instance(processingEnv);
        context = ((JavacProcessingEnvironment)
                processingEnv).getContext();
        make = TreeMaker.instance(context);
        names = Names.instance(context).table;

    }

    public static class MyTreeTranslator extends TreeTranslator {



        @Override
        public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
            super.visitMethodDef(jcMethodDecl);

//            System.out.println("--------------------------");
//            System.out.println("修饰符：" + jcMethodDecl.getModifiers());
//            System.out.println("名称：" + jcMethodDecl.getName());
//            System.out.println("返回值：" + jcMethodDecl.getReturnType());
//            System.out.println("泛型参数：" + jcMethodDecl.getTypeParameters());
//            System.out.println("参数：" + jcMethodDecl.getParameters());
//            System.out.println("异常：" + jcMethodDecl.getThrows());
//            System.out.println("方法体" + jcMethodDecl.getBody());
//            System.out.println("默认值" + jcMethodDecl.getDefaultValue());
        }

        @Override
        public void visitUnary(JCTree.JCUnary jcUnary) {
            super.visitUnary(jcUnary);
            System.out.println(jcUnary);
            System.out.println(jcUnary.getOperator());
        }

        @Override
        public void visitBinary(JCTree.JCBinary jcBinary) {
            super.visitBinary(jcBinary);
            System.out.println(jcBinary);
            System.out.println(jcBinary.getOperator());
        }
    }


}
>>>>>>> b8ba36e81f0283b8ce7acb681c8ee4d2dfb9c73a
