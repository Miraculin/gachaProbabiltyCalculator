(ns genericgacha.stats-test
  (:require [clojure.test :refer :all]
            [genericgacha.stats :refer :all]
            [clojure.math.numeric-tower :as nt]))

(deftest binomial-coefficient-benchmark
  (testing "Is pascal's rule faster than factorial"
    (is (> (time (stable-binom-coefficient 20 10)) 0))))


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
