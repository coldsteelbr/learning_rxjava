package ru.romanbrazhnikov.app;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.romanbrazhnikov.parser.ICommonParser;
import ru.romanbrazhnikov.parser.RegExParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hello {
    public void hello(String... names) {
        Observable.fromArray(names)
                .subscribe(s -> System.out.println("Hello, " + s));
    }

    public void grokking() {
        Observable<String> myObs = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello, World");
                e.onComplete();
            }
        });


        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

        myObs.subscribe(myObserver);

        ///
        ///         CONSUMERS
        ///


        myObs.subscribe(
                System.out::println,
                throwable -> System.out.println(throwable.getMessage()),
                () -> System.out.println("Job's done"));


    }


    public void parse() {
        String pattern = "<td[^>]*>\\s*(?<LEFT>.*?)\\s*</td>\\s*"
                + "<td[^>]*>\\s*(?<RIGHT>.*?)\\s*</td>\\s*";
        String source =
                "<table>" +
                        "<tr>" +
                        "<td>Top left</td>" +
                        "<td>Top right</td>" +
                        "</tr>" +
                        "<tr>" +
                        "<td>Bottom left</td>" +
                        "<td>Bottom right</td>" +
                        "</tr>" +
                        "</table>";
        List<String> names = new ArrayList<>();
        names.add("LEFT");
        names.add("RIGHT");
        //names.add("fefre");


        ICommonParser parser = new RegExParser();

        parser.setSource(source);
        parser.setPattern(pattern);
        parser.setMatchNames(names);

        parser.parse()
                //.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .timeout(5, TimeUnit.SECONDS)

                .subscribe(
                parseResult -> {
                    List<Map<String, String>> res = parseResult.getResult();
                    for (Map<String, String> curRow : res) {
                        for (Map.Entry entry : curRow.entrySet()) {
                            System.out.print(entry + " ");
                        }
                        System.out.println();
                    }
                },
                System.out::println);


        System.out.println("========= HELLO FINISHED =========");
    }
}
