package cn.shiyujun.jvm;

import org.junit.Test;



/**
 * d
 * @author syj
 * CreateTime 2019/05/10
 * describe:
 */
public class GCBaseParamTest {
    @Test
    public void printGC() {
        //参数：-XX:+PrintGC
        new Object();
        new Object();
        new Object();
        System.gc();
        new Object();
        new Object();
        //输出
        //[Full GC (System.gc())  4410K->1168K(15872K), 0.0062216 secs]
    }

    @Test
    public void printGCDetails() {
        //参数：-XX:+PrintGCDetails
        new Object();
        new Object();
        new Object();
        System.gc();
        new Object();
        new Object();
        //输出
        //
        // Heap
        //def new generation   total 4992K, used 86K [0x05400000, 0x05960000, 0x0a950000)
        //eden space 4480K,   1% used [0x05400000, 0x05415b78, 0x05860000)
        //from space 512K,   0% used [0x05860000, 0x05860000, 0x058e0000)
        //to   space 512K,   0% used [0x058e0000, 0x058e0000, 0x05960000)
        //tenured generation   total 10944K, used 1169K [0x0a950000, 0x0b400000, 0x15400000)
        //the space 10944K,  10% used [0x0a950000, 0x0aa74648, 0x0aa74800, 0x0b400000)
        //Metaspace       used 3143K, capacity 3192K, committed 3264K, reserved 4480K
    }

    @Test
    public void saveGCLog() {
        //参数：-Xloggc:D:\gc.log
        new Object();
        new Object();
        new Object();
        System.gc();
        new Object();
        new Object();

    }

    @Test
    public void printGCHeapAtGC() {
        //参数：-XX:+PrintHeapAtGC
        new Object();
        new Object();
        new Object();
        System.gc();
        new Object();
        new Object();
        //输出
        ///
        //{Heap before GC invocations=0 (full 0):
        // def new generation   total 4928K, used 4416K [0x04a00000, 0x04f50000, 0x09f50000)
        // eden space 4416K, 100% used [0x04a00000, 0x04e50000, 0x04e50000)
        //  from space 512K,   0% used [0x04e50000, 0x04e50000, 0x04ed0000)
        //  to   space 512K,   0% used [0x04ed0000, 0x04ed0000, 0x04f50000)
        // tenured generation   total 10944K, used 0K [0x09f50000, 0x0aa00000, 0x14a00000)
        //   the space 10944K,   0% used [0x09f50000, 0x09f50000, 0x09f50200, 0x0aa00000)
        // Metaspace       used 686K, capacity 2542K, committed 2624K, reserved 4480K
        //Heap after GC invocations=1 (full 0):
        // def new generation   total 4928K, used 512K [0x04a00000, 0x04f50000, 0x09f50000)
        //  eden space 4416K,   0% used [0x04a00000, 0x04a00000, 0x04e50000)
        //  from space 512K, 100% used [0x04ed0000, 0x04f50000, 0x04f50000)
        //  to   space 512K,   0% used [0x04e50000, 0x04e50000, 0x04ed0000)
        // tenured generation   total 10944K, used 606K [0x09f50000, 0x0aa00000, 0x14a00000)
        //   the space 10944K,   5% used [0x09f50000, 0x09fe79f0, 0x09fe7a00, 0x0aa00000)
        // Metaspace       used 686K, capacity 2542K, committed 2624K, reserved 4480K
        //}{Heap before GC invocations=1 (full 0):
        // def new generation   total 4928K, used 1301K [0x04a00000, 0x04f50000, 0x09f50000)
        //  eden space 4416K,  17% used [0x04a00000, 0x04ac56f0, 0x04e50000)
        //  from space 512K, 100% used [0x04ed0000, 0x04f50000, 0x04f50000)
        //  to   space 512K,   0% used [0x04e50000, 0x04e50000, 0x04ed0000)
        // tenured generation   total 10944K, used 606K [0x09f50000, 0x0aa00000, 0x14a00000)
        //   the space 10944K,   5% used [0x09f50000, 0x09fe79f0, 0x09fe7a00, 0x0aa00000)
        // Metaspace       used 771K, capacity 2608K, committed 2624K, reserved 4480K
        //Heap after GC invocations=2 (full 1):
        // def new generation   total 4992K, used 0K [0x04a00000, 0x04f60000, 0x09f50000)
        //  eden space 4480K,   0% used [0x04a00000, 0x04a00000, 0x04e60000)
        //  from space 512K,   0% used [0x04e60000, 0x04e60000, 0x04ee0000)
        //  to   space 512K,   0% used [0x04ee0000, 0x04ee0000, 0x04f60000)
        // tenured generation   total 10944K, used 1219K [0x09f50000, 0x0aa00000, 0x14a00000)
        //   the space 10944K,  11% used [0x09f50000, 0x0a080d58, 0x0a080e00, 0x0aa00000)
        // Metaspace       used 771K, capacity 2608K, committed 2624K, reserved 4480K
        //}

    }

    @Test
    public void traceClassLoading() {
        //参数：-XX:+TraceClassLoading
        new Object();
        new Object();
        new Object();
        System.gc();
        new Object();
        new Object();
        //输出
        //[Opened C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Object from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.io.Serializable from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Comparable from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.CharSequence from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.String from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.reflect.AnnotatedElement from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.reflect.GenericDeclaration from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.reflect.Type from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Class from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Cloneable from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.ClassLoader from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.System from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Throwable from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Error from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.ThreadDeath from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.Exception from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //[Loaded java.lang.RuntimeException from C:\Program Files (x86)\Java\jdk1.8.0_181\jre\lib\rt.jar]
        //。。。。。。。

    }


}
