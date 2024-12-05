package com.examplekicklaandwebsite.KickLaand.util;

import java.util.Random;

public class OtpGenerator {

    public OtpGenerator() {
    }

    public static Integer generatorOtp() {
        Random random = new Random();
        return random.nextInt(100_000,999_999);
    }
}
