package edu.kis.vh.nursery;

import edu.kis.vh.nursery.factory.DefaultRhymersFactory;
import edu.kis.vh.nursery.factory.RhymersFactory;


//Projekt jest poprawny


/**
 * Klasa demonstracyjna prezentująca działanie różnych implementacji
 *  rymowanek (Rhymers).
 *
 *  Tworzy obiekty za pomocą fabryki {@link RhymersFactory}, a następnie
 *  testuje ich zachowanie poprzez dodawanie i usuwanie elementów.
 *
 *  Program pokazuje różnice w działaniu różnych strategii:
 *  standardowej, FIFO, fałszywej oraz Hanoi.
 *
 */

class RhymersDemo {

    /**
     * Punkt wejścia programu.
     *
     * Tworzy fabrykę rymowanek i uruchamia test działania różnych implementacji.
     *
     * @param args argumenty linii poleceń (nieużywane)
     */

    public static void main(String[] args) {
        RhymersFactory factory = new DefaultRhymersFactory();

        testRhymers(factory);

    }

    /**
     *  Metoda testująca różne implementacje rymowanek.
     *
     *  Tworzy tablicę obiektów typu {@code DefaultCountingOutRhymer}
     *  przy użyciu fabryki, a następnie:
     *      dodaje elementy do struktur danych,
     *      losowo zasila jeden z obiektów,
     *      opróżnia struktury i wypisuje wyniki,
     *      raportuje liczbę odrzuconych elementów dla HanoiRhymer.
     *
     *  @param factory fabryka dostarczająca różne implementacje rymowanek
     */

    private static void testRhymers(RhymersFactory factory) {
        DefaultCountingOutRhymer[] rhymers = { factory.GetStandardRhymer(), factory.GetFalseRhymer(),
                factory.GetFIFORhymer(), factory.GetHanoiRhymer()};

        for (int i = 1; i < 15; i++)
            for (int j = 0; j < 3; j++)
                rhymers[j].countIn(i);

        java.util.Random rn = new java.util.Random();
        for (int i = 1; i < 15; i++)
            rhymers[3].countIn(rn.nextInt(20));

        for (int i = 0; i < rhymers.length; i++) {
            while (!rhymers[i].callCheck())
                System.out.print(rhymers[i].countOut() + "  ");
            System.out.println();
        }

        System.out.println("total rejected is "
                + ((HanoiRhymer) rhymers[3]).reportRejected());
    }

}