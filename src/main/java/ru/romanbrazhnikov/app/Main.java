package ru.romanbrazhnikov.app;

import io.reactivex.Flowable;

public class Main {
    public static void main(String args[]){
        Flowable.just("Hello, RxJava!").subscribe(System.out::println);

        Hello h = new Hello();
        h.hello("Anna", "Bella", "Valentina");
        h.grokking();

        h.parse();

        h.parseMany();

        System.out.println("========= MAIN FINISHED =========");
    }
}
