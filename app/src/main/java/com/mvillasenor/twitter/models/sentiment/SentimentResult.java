package com.mvillasenor.twitter.models.sentiment;

/**
 * Created by MarthaKarina on 03/06/2016.
 */
public class SentimentResult {

    private String label;
    private Probability probability;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Probability getProbability() {
        return probability;
    }

    public void setProbability(Probability probability) {
        this.probability = probability;
    }

    public static class Probability {
        private String neg;
        private String neutral;
        private String pos;

        public String getNeg() {
            return neg;
        }

        public void setNeg(String neg) {
            this.neg = neg;
        }

        public String getNeutral() {
            return neutral;
        }

        public void setNeutral(String neutral) {
            this.neutral = neutral;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }
    }

}
