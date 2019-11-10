(ns genericgacha.stats-test
  (:require [clojure.test :refer :all]
            [genericgacha.stats :refer :all]
            [clojure.math.numeric-tower :as nt]))

(deftest binomial-coefficient-benchmark
  (testing "How fast is Pascal's Rule"
    (time (stable-binom-coefficient 20 10)) 0
    (time (stable-binom-coefficient 200 100)) 0
    (time (stable-binom-coefficient 2000 1000)) 0
    (time (stable-binom-coefficient 20000 10000)) 0))
    ; (time (stable-binom-coefficient 200000 100000)) 0)) takes ~10000 ms
    ; (time (stable-binom-coefficient 2000000 100000)) 0)) takes ~22000 ms
    ;

(deftest binomial-probability-test
  (testing "is binomial point probability calculated properly"
    (is (= (binomial-probability 1 1 1) 1))
    (is (= (binomial-probability 6 2 0.25) 0.296630859375))
    (is (= (+ (binomial-probability 6 1 0.25)
              (binomial-probability 6 0 0.25))
          0.533935546875))
    (is (= (binomial-probability 4 1 0.2) 0.40960000000000013))
    (is (= (+ (binomial-probability 5 4 0.6)
              (binomial-probability 5 5 0.6))
          0.33696))))
(deftest tail-probability-test
  (testing "is tail probability calculated properly"
    (is (= (upper-tail-probability 5 0.6 4) 0.33696))
    (is (= (lower-tail-probability 6 0.25 1) 0.533935546875))))
