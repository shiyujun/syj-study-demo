package cn.shiyujun.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * d
 *
 * @author syj
 * CreateTime 2019/05/10
 * describe:
 */

public class GCStackParamTest {
    @Test
    public void heapMaxAndMin() {
        //参数：-Xmx200m -Xms50m
        System.out.println("堆最大空间：" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("空闲空间：" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("可用空间：" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
        //输出
        //堆最大空间：193M
        //空闲空间：43M
        //可用空间：48M
    }

    @Test
    public void testHeapMaxAndMin() {
        byte[] b = new byte[10 * 1024 * 1024];
        //参数：-Xmx200m -Xms50m
        System.out.println("堆最大空间：" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("空闲空间：" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("可用空间：" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
        //输出
        //堆最大空间：193M
        //空闲空间：36M
        //可用空间：48M
    }

    @Test
    public void testHeapMin() {
        byte[] b = new byte[100 * 1024 * 1024];
        //参数：-Xmx200m -Xms50m
        System.out.println("堆最大空间：" + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
        System.out.println("空闲空间：" + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "M");
        System.out.println("可用空间：" + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "M");
        //输出
        //堆最大空间：193M
        //空闲空间：47M
        //可用空间：148M
    }

    @Test
    public void heapNewRatio() {
        //参数：-Xmx20m -Xms20m -Xmn1m -XX:NewRatio=1 -XX:SurvivorRatio=2 -XX:+PrintGCDetails
        byte[] b = null;
        for (int i = 0; i < 10; i++)
            b = new byte[1 * 1024 * 1024];
        //输出

        //[GC (Allocation Failure) [DefNew: 512K->256K(768K), 0.0010095 secs] 512K->437K(20224K), 0.0010346 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 767K->138K(768K), 0.0028026 secs] 949K->559K(20224K), 0.0029066 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 650K->95K(768K), 0.0009031 secs] 1071K->651K(20224K), 0.0009274 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [GC (Allocation Failure) [DefNew: 602K->151K(768K), 0.0025152 secs] 1159K->707K(20224K), 0.0025515 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 663K->187K(768K), 0.0009912 secs] 1219K->811K(20224K), 0.0010081 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 699K->67K(768K), 0.0011263 secs] 1323K->867K(20224K), 0.0011534 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 579K->98K(768K), 0.0019352 secs] 1379K->898K(20224K), 0.0019764 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //GC (Allocation Failure) [DefNew: 610K->109K(768K), 0.0005148 secs] 1410K->908K(20224K), 0.0005434 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] [GC (Allocation Failure) [DefNew: 621K->107K(768K), 0.0018682 secs] 1420K->907K(20224K), 0.0018946 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] Heap
        //def new generation   total 768K, used 619K [0x04800000, 0x04900000, 0x04900000)
        //eden space 512K, 100% used [0x04800000, 0x04880000, 0x04880000)
        //from space 256K,  42% used [0x048c0000, 0x048daed0, 0x04900000)
        //to   space 256K,   0% used [0x04880000, 0x04880000, 0x048c0000)
        //tenured generation   total 19456K, used 11040K [0x04900000, 0x05c00000, 0x05c00000)
        //the space 19456K,  56% used [0x04900000, 0x053c8010, 0x053c8200, 0x05c00000)
        //Metaspace       used 774K, capacity 2608K, committed 2624K, reserved 4480K

    }

    @Test
    public void saveDumpFile() {
        //参数：-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\test.dump
        List list = new ArrayList();
        byte[] b = null;
        for (int i = 0; i < 100; i++) {
            b = new byte[1 * 1024 * 1024];
            list.add(b);
        }

    }

    @Test
    public void afterDumpDo() {
        //参数：-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError=/app/test.sh
        List list = new ArrayList();
        byte[] b = null;
        for (int i = 0; i < 100; i++) {
            b = new byte[1 * 1024 * 1024];
            list.add(b);
        }

    }

    @Test
    public void permSiz() {
        //参数(jdk7)：-XX:PermSize5m  -XX:MaxPermSize5m -XX:+PrintGCDetails
        //参数(jdk8)：-XX:MaxMetaspaceSize=5m  -XX:+PrintGCDetails

        List list = new ArrayList();
        while (true) {
            String s = "test";
            list.add(s.intern());
        }
        //输出
        //[GC (Allocation Failure) [DefNew: 4416K->512K(4928K), 0.0024584 secs] 4416K->1180K(15872K), 0.0024976 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GC (Allocation Failure) [DefNew: 4728K->0K(4928K), 0.0043926 secs] 5396K->2582K(15872K), 0.0044927 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
        //[GC (Allocation Failure) [DefNew: 2110K->0K(4992K), 0.0019415 secs] 4692K->4692K(15936K), 0.0019697 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        //[GC (Allocation Failure) [DefNew: 3165K->0K(4992K), 0.0019503 secs][Tenured: 9440K->5922K(10944K), 0.0106374 secs] 12605K->5922K(15936K), [Metaspace: 3139K->3139K(4480K)], 0.0126413 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(4992K), 0.0026139 secs][Tenured: 13044K->8296K(18068K), 0.0114199 secs] 13044K->8296K(23060K), [Metaspace: 3139K->3139K(4480K)], 0.0140955 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
        //[GC (Allocation Failure) [DefNew: 145K->0K(8192K), 0.0044885 secs][Tenured: 18979K->11857K(28752K), 0.0151308 secs] 19125K->11857K(36944K), [Metaspace: 3139K->3139K(4480K)], 0.0196866 secs] [Times: user=0.02 sys=0.00, real=0.02 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(12992K), 0.0061151 secs][Tenured: 27882K->16846K(28752K), 0.0224836 secs] 27882K->16846K(41744K), [Metaspace: 3139K->3139K(4480K)], 0.0286286 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(12992K), 0.0075516 secs][Tenured: 40882K->24858K(52792K), 0.0325786 secs] 40882K->24858K(65784K), [Metaspace: 3139K->3139K(4480K)], 0.0402459 secs] [Times: user=0.05 sys=0.00, real=0.04 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(23808K), 0.0121149 secs][Tenured: 60913K->36876K(88848K), 0.0432062 secs] 60913K->36876K(112656K), [Metaspace: 3139K->3139K(4480K)], 0.0554088 secs] [Times: user=0.06 sys=0.00, real=0.05 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(40064K), 0.0171657 secs][Tenured: 90959K->54904K(142932K), 0.0678199 secs] 90959K->54904K(182996K), [Metaspace: 3139K->3139K(4480K)], 0.0851337 secs] [Times: user=0.08 sys=0.00, real=0.08 secs]
        //[GC (Allocation Failure) [DefNew: 0K->0K(64384K), 0.0263310 secs][Tenured: 136028K->81945K(142932K), 0.0989528 secs] 136028K->81945K(207316K), [Metaspace: 3139K->3139K(4480K)], 0.1253180 secs] [Times: user=0.13 sys=0.00, real=0.13 secs]
        //[Full GC (Allocation Failure) [Tenured: 81945K->81901K(174784K), 0.0996936 secs] 81945K->81901K(239168K), [Metaspace: 3139K->3139K(4480K)], 0.0998724 secs] [Times: user=0.11 sys=0.00, real=0.10 secs]

    }

    private static int i = 0;

    private void recursion() {
        i++;
        recursion();
    }

    @Test
    public void xss() {
        //参数：-Xss200k
        try {
            recursion();
        } catch (Throwable e) {
            System.out.println("调用深度：" + i);
            e.printStackTrace();
        }
        //输出
        //调用深度：6852
        //调用深度：17209
    }


}
