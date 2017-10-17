package com.iydsj.sw.common.utils;


import java.nio.charset.Charset;
import java.util.Random;

public class BarCodeUtils {
    private static final int[][] phonePermIndexs = {
            {2, 5, 3, 0, 1, 4},
            {2, 5, 3, 1, 0, 4},
            {2, 5, 4, 0, 1, 3},
            {2, 5, 4, 1, 0, 3},
            {3, 0, 1, 2, 5, 4},
            {3, 0, 1, 4, 5, 2},
            {3, 0, 1, 5, 2, 4},
            {3, 0, 1, 5, 4, 2},
            {3, 0, 2, 1, 5, 4},
            {3, 0, 2, 5, 1, 4},
            {3, 0, 4, 1, 5, 2},
            {3, 0, 4, 5, 1, 2},
            {3, 0, 5, 1, 2, 4},
            {3, 0, 5, 1, 4, 2},
            {3, 0, 5, 2, 1, 4},
            {3, 0, 5, 4, 1, 2},
            {3, 1, 0, 2, 5, 4},
            {3, 1, 0, 4, 5, 2},
            {3, 1, 0, 5, 2, 4},
            {3, 1, 0, 5, 4, 2},
            {3, 1, 2, 0, 5, 4},
            {3, 1, 2, 5, 0, 4},
            {3, 1, 4, 0, 5, 2},
            {3, 1, 4, 5, 0, 2},
            {3, 1, 5, 0, 2, 4},
            {3, 1, 5, 0, 4, 2},
            {3, 1, 5, 2, 0, 4},
            {3, 1, 5, 4, 0, 2},
            {3, 2, 0, 1, 5, 4},
            {3, 2, 0, 5, 1, 4},
            {3, 2, 1, 0, 5, 4},
            {3, 2, 1, 5, 0, 4},
            {3, 2, 5, 0, 1, 4},
            {3, 2, 5, 1, 0, 4},
            {3, 4, 0, 1, 5, 2},
            {3, 4, 0, 5, 1, 2},
            {3, 4, 1, 0, 5, 2},
            {3, 4, 1, 5, 0, 2},
            {3, 4, 5, 0, 1, 2},
            {3, 4, 5, 1, 0, 2},
            {3, 5, 0, 1, 2, 4},
            {3, 5, 0, 1, 4, 2},
            {3, 5, 0, 2, 1, 4},
            {3, 5, 0, 4, 1, 2},
            {3, 5, 1, 0, 2, 4},
            {3, 5, 1, 0, 4, 2},
            {3, 5, 1, 2, 0, 4},
            {3, 5, 1, 4, 0, 2},
            {3, 5, 2, 0, 1, 4},
            {3, 5, 2, 1, 0, 4},
            {3, 5, 4, 0, 1, 2},
            {3, 5, 4, 1, 0, 2},
            {4, 0, 1, 2, 5, 3},
            {4, 0, 1, 3, 5, 2},
            {4, 0, 1, 5, 2, 3},
            {4, 0, 1, 5, 3, 2},
            {4, 0, 2, 1, 5, 3},
            {4, 0, 2, 5, 1, 3},
            {4, 0, 3, 1, 5, 2},
            {4, 0, 3, 5, 1, 2},
            {4, 0, 5, 1, 2, 3},
            {4, 0, 5, 1, 3, 2},
            {4, 0, 5, 2, 1, 3},
            {4, 0, 5, 3, 1, 2},
            {4, 1, 0, 2, 5, 3},
            {4, 1, 0, 3, 5, 2},
            {4, 1, 0, 5, 2, 3},
            {4, 1, 0, 5, 3, 2},
            {4, 1, 2, 0, 5, 3},
            {4, 1, 2, 5, 0, 3},
            {4, 1, 3, 0, 5, 2},
            {4, 1, 3, 5, 0, 2},
            {4, 1, 5, 0, 2, 3},
            {4, 1, 5, 0, 3, 2},
            {4, 1, 5, 2, 0, 3},
            {4, 1, 5, 3, 0, 2},
            {4, 2, 0, 1, 5, 3},
            {4, 2, 0, 5, 1, 3},
            {4, 2, 1, 0, 5, 3},
            {4, 2, 1, 5, 0, 3},
            {4, 2, 5, 0, 1, 3},
            {4, 2, 5, 1, 0, 3},
            {4, 3, 0, 1, 5, 2},
            {4, 3, 0, 5, 1, 2},
            {4, 3, 1, 0, 5, 2},
            {4, 3, 1, 5, 0, 2},
            {4, 3, 5, 0, 1, 2},
            {4, 3, 5, 1, 0, 2},
            {4, 5, 0, 1, 2, 3},
            {4, 5, 0, 1, 3, 2},
            {4, 5, 0, 2, 1, 3},
            {4, 5, 0, 3, 1, 2},
            {4, 5, 1, 0, 2, 3},
            {4, 5, 1, 0, 3, 2},
            {4, 5, 1, 2, 0, 3},
            {4, 5, 1, 3, 0, 2},
            {4, 5, 2, 0, 1, 3},
            {4, 5, 2, 1, 0, 3},
            {4, 5, 3, 0, 1, 2},
            {4, 5, 3, 1, 0, 2},
    };

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int MAX_PERMUTATION = 100;
    private static final int BAR_CODE_LENGTH = 18;
    private static final int PHONE_NUMBER_LENGTH = 11;


    public static EncodeResult encode(String phone) {
        Random rand = new Random(System.nanoTime());
        byte[] barCode = new byte[BAR_CODE_LENGTH];

        int perm = rand.nextInt(MAX_PERMUTATION);
        int barCodeIndex = 0;
        barCode[barCodeIndex] = (byte) (perm / 10 + 0x30);
        barCode[++barCodeIndex] = (byte) (perm % 10 + 0x30);

        int randomCode = 0;
        byte[] phoneNumbers = phone.getBytes(UTF8);
        int[] permIndexs = phonePermIndexs[perm];
        for (int i = 0; i < permIndexs.length; i++) {
            int index = permIndexs[i];
            if (index == 0) {
                barCode[++barCodeIndex] = phoneNumbers[0];
            } else {
                int j = (index - 1) * 2 + 1;
                barCode[++barCodeIndex] = phoneNumbers[j];
                barCode[++barCodeIndex] = phoneNumbers[j + 1];
            }
            if (i != permIndexs.length - 1) {
                int r = rand.nextInt(10);
                randomCode = randomCode * 10 + r;
                barCode[++barCodeIndex] = (byte) (r + 0x30);
            }
        }

        return new EncodeResult(new String(barCode, UTF8), randomCode);
    }

    public static DecodeResult decode(String barCode) {
        byte[] barCodeNumbers = barCode.getBytes(UTF8);
        int perm = (barCodeNumbers[0] - 0x30) * 10 + barCodeNumbers[1] - 0x30;
        int[] permIndexs = phonePermIndexs[perm];
        byte[] phoneNumber = new byte[PHONE_NUMBER_LENGTH];
        int barCodeIndex = 2;
        int randomCode = 0;
        for (int i = 0; i < permIndexs.length; i++) {
            int index = permIndexs[i];
            if (index == 0) {
                phoneNumber[0] = barCodeNumbers[barCodeIndex++];
            } else {
                int j = (index - 1) * 2 + 1;
                phoneNumber[j] = barCodeNumbers[barCodeIndex++];
                phoneNumber[j + 1] = barCodeNumbers[barCodeIndex++];
            }
            if (i != permIndexs.length - 1) {
                randomCode = randomCode * 10 + barCodeNumbers[barCodeIndex++] - 0x30;
            }
        }

        return new DecodeResult(new String(phoneNumber, UTF8), randomCode);
    }

    public static class EncodeResult {
        public String barCode;
        public int code;

        public EncodeResult(String barCode, int code) {
            this.barCode = barCode;
            this.code = code;
        }
    }

    public static class DecodeResult {
        public String phone;
        public int code;

        public DecodeResult(String phone, int code) {
            this.phone = phone;
            this.code = code;
        }
    }
}
