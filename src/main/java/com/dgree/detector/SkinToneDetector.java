package com.dgree.detector;

import java.io.IOException;
import java.io.InputStream;
public abstract class SkinToneDetector {

    protected static final int[][] SKINTONES = new int[][] {
            {255,223,196},
            {255,220,178},
            {253,228,200},
            {240,213,190},
            {240,200,201},
            {238,206,179},
            {234,189,157},
            {231,158,109},
            {229,194,152},
            {229,184,143},
            {229,160,115},
            {227,194,124},
            {225,184,153},
            {224,177,132},
            {223,166,117},
            {222,171,127},
            {221,168,160},
            {219,144,101},
            {208,146,110},
            {206,150,124},
            {203,132,66},
            {198,120,86},
            {189,144,60},
            {189,151,120},
            {186,108,73},
            {185,124,109},
            {173,100,82},
            {168,117,108},
            {165,114,87},
            {165,57,0},
            {163,134,106}
    };

    protected int skinToneDelta = 50;

    protected int isDelta = 5;

    public abstract boolean isNotValid(final InputStream inputStream) throws IOException;

	public int getIsDelta() {
		return isDelta;
	}

	public void setIsDelta(int isDelta) {
		this.isDelta = isDelta;
	}

    
}
