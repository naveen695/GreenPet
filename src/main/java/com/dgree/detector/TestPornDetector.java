package com.dgree.detector;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kenny on 3/26/15.
 */
public class TestPornDetector {


    private static final SkinToneDetector SKIN_TONE_SPANNING_PORN_DETECTOR = new SkinToneSpanningDetector();

    @Test
    public void testSpanningSkinToneClassifier() throws IOException {
        SKIN_TONE_SPANNING_PORN_DETECTOR.setIsDelta(5);
        System.out.println("porn:");
        runTest("corpus/image/porn/sexy-teen-and-milf-fuck-a-big-black-cock2.jpg");
        runTest("corpus/image/safe/img-thing.jpg");
        runTest("corpus/image/porn/5.png");
        runTest("corpus/image/porn/JOy-Logo1.png");
        runTest("corpus/image/porn/02.jpg");
        runTest("corpus/image/porn/Sketch.png");
        runTest("corpus/image/porn/514987426.jpg");
        runTest("corpus/image/porn/i.jpg");

        runTest("corpus/image/porn/2.jpg");
        runTest("corpus/image/porn/3.jpg");
        runTest("corpus/image/porn/4.jpg");
        runTest("corpus/image/porn/5.jpg");
        runTest("corpus/image/porn/6.jpg");
        runTest("corpus/image/porn/7.jpg");
        runTest("corpus/image/porn/8.jpg");
            
        
    }
 /*
    @Test
    public void testAveragedSkinToneClassifier() throws IOException {
        SKIN_TONE_AVG_PORN_DETECTOR.setIsPornDelta(20);
        System.out.println("porn:");
 runTest2("corpus/image/porn/sexy-teen-and-milf-fuck-a-big-black-cock2.jpg");
        runTest2("corpus/image/safe/img-thing.jpg");
        runTest2("corpus/image/porn/5.png");
        runTest2("corpus/image/porn/JOy-Logo1.png");
        runTest2("corpus/image/porn/02.jpg");
        runTest2("corpus/image/porn/Sketch.png");
        runTest2("corpus/image/porn/514987426.jpg");
        runTest2("corpus/image/porn/i.jpg");

        runTest2("corpus/image/porn/2.jpg");
        runTest2("corpus/image/porn/3.jpg");
        runTest2("corpus/image/porn/4.jpg");
        runTest2("corpus/image/porn/5.jpg");
        runTest2("corpus/image/porn/6.jpg");
        runTest2("corpus/image/porn/7.jpg");
        runTest2("corpus/image/porn/8.jpg");
            }

*/    private void runTest(final String resource) throws IOException {
        System.out.println(SKIN_TONE_SPANNING_PORN_DETECTOR.isNotValid(getStream(resource)) + "\t" + resource);
    }


    private InputStream getStream(final String resource) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
    }
}
