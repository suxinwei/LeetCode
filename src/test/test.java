package test;

import java.io.FileNotFoundException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

import test.bean.PlayBackFileInfo;

class test {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static void main(String[] args) throws CertificateException, FileNotFoundException {
//        testThread();

//        testMergePlayList();

//        testListSort();


//        int[] arr = {1, 3};

//        byte[] bytes = new byte[]{2, 17};
//        System.out.println(bytes2IntByBigEnd(bytes));
//        System.out.println(bytesToInt(bytes));
//        System.out.println(bytesToHex(bytes));
        byte[] bytes = {58, 29, 14, 7, -36, -18, -9, -5, -84, 86, -85, -43, 127, -65, -33, 111};
        System.out.println(bytesToHex(bytes));
        String text = "02080312047274684c18012a103a1d0e07dceef7fbac56abd57fbfdf6f";
        String test = "02080312047274684c18012a10f0f87c3e0b05028189c462b1160b0502";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            result.append(c);
            if (i % 2 == 1 && i != text.length() - 1) {
                result.append(":");
            }
        }
        System.out.println(result);
    }

    public static String bytesToHex(byte[] bytes) {
        if (null == bytes) {
            return "";
        }
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * 转换4byte到int
     */
    public static int bytesToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static int bytes2IntByBigEnd(byte[] bytes) {
        int num = 0;
        int length = bytes.length;
        for (int i = 0; i < length; i++) {
            num |= (bytes[length - 1 - i] & 0xFF) << (8 * i);
        }
        return num;
    }

    private static void testThread() {
        TestRunnable testRunnable = new TestRunnable();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(testRunnable);
            thread.start();
            try {
//                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void testMergePlayList() {
        List<PlayBackFileInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PlayBackFileInfo playBackFileInfo = new PlayBackFileInfo();
            playBackFileInfo.recordStartTime = i * 1000;
            playBackFileInfo.recordElapsedTime = 1000;
            list.add(playBackFileInfo);
        }
        List<PlayBackFileInfo> mergeList = mergePlayBackFileEvent(list);
        Utils.printList(mergeList);
    }

    private static void testListSort() {
        Person person1 = new Person(1, "1");
        Person person11 = new Person(1, "11");
        Person person2 = new Person(2, "2");
        Person person3 = new Person(null, "3");
        Person person4 = new Person(null, "4");
        Person person5 = new Person(0, "0");
        List<Person> list = new ArrayList<>();
        list.add(person2);
        list.add(person3);
        list.add(person11);
        list.add(person1);
        list.add(person4);
        list.add(person5);
        list.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                if (person.age == null) {
                    return 1;
                }

                if (t1.age == null) {
                    return -1;
                }
                return Integer.compare(person.age, t1.age);
            }
        });
        Utils.printList(list);
        Object o = new Object();
        String s = o + "";
        System.out.println(s);
    }

    public static List<PlayBackFileInfo> mergePlayBackFileEvent(List<PlayBackFileInfo> playBackFileInfos) {
        List<PlayBackFileInfo> mergePlayBackFileInfoList = new ArrayList<>();
        PlayBackFileInfo lastPlayBackFileInfo = null;
        for (PlayBackFileInfo playBackFileInfo : playBackFileInfos) {
            playBackFileInfo.recordEndTime = playBackFileInfo.recordStartTime + playBackFileInfo.recordElapsedTime;
            if (lastPlayBackFileInfo == null) {
                lastPlayBackFileInfo = playBackFileInfo;
            } else {
                if (lastPlayBackFileInfo.recordEndTime == playBackFileInfo.recordStartTime) {
                    lastPlayBackFileInfo.recordEndTime = playBackFileInfo.recordEndTime;
                } else {
                    mergePlayBackFileInfoList.add(lastPlayBackFileInfo);
                    lastPlayBackFileInfo = playBackFileInfo;
                }
            }
        }
        if (lastPlayBackFileInfo != null) {
            mergePlayBackFileInfoList.add(lastPlayBackFileInfo);
        }
        return mergePlayBackFileInfoList;
    }

    static class Person {
        Integer age;
        String name;

        public Person(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class TestRunnable implements Runnable {
        final Object mObject = new Object();
        Semaphore mSemaphore = new Semaphore(1);

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "：acquire前");
            try {
                mSemaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "：acquire后");
                new Thread("release线程") {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "：release前");
                        mSemaphore.release();
                        System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "：release后");
                    }

                }.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
