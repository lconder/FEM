package com.lconder.covid.models.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroLamp {

    @SerializedName("success")
    private boolean success;

    public RetroLamp(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class BodyRequest {
        @SerializedName("id")
        @Expose
        private transient int id;
        @SerializedName("r")
        @Expose
        int r;
        @SerializedName("g")
        @Expose
        int g;
        @SerializedName("b")
        @Expose
        int b;

        public BodyRequest() { }

        public BodyRequest(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public void setR(int r) {
            this.r = r;
        }

        public void setG(int g) {
            this.g = g;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}


