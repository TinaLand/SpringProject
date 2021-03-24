package com.example.MyJupiter.external;

//public class TwitchException {
//    public TwitchException(String errorMessage) {
//        super(errorMessage);
//    }
//}


public class TwitchException extends RuntimeException {
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }
}
