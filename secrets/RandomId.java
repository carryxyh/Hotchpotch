package com.iydsj.sw.common.utils.caesar;

import java.util.Random;

/**
 * 帮助获得8位唯一的序列号
 */
public class RandomId {

    private Random random;

    private String table;

    private static RandomId randomId = new RandomId();

    private RandomId() {
        random = new Random();
        table = "9612350487";
    }

    public static RandomId getInstance() {
        return randomId;
    }

    /**
     * 编码
     *
     * @param id
     * @return
     */
    public String decode(int id) {
        String ret = null,
                num = String.format("%05d", id);
        int key = random.nextInt(10),
                seed = random.nextInt(100);
        Caesar caesar = new Caesar(table, seed);
        num = caesar.encode(key, num);
        ret = num
                + String.format("%01d", key)
                + String.format("%02d", seed);

        return ret;
    }

//    public static void test(String[] args) {
//        RandomId r = new RandomId();
//        List<String> codeList = new ArrayList<String>();
//        int j = 0;
//        System.out.println("begin");
//        String code = "";
//        for (int i = 0; i < 500000; i++) {
//        	code = r.decode(i);
//        	if (codeList.contains(code)) {
//				System.out.println(code);
//				j++;
//			}
//        	codeList.add(code);
//        }
//        System.out.println(j);
//        System.out.println("done");
//    }
}